package com.ifsc.contaclicks;
import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class MainActivity extends AppCompatActivity {
    private PreviewView previewView;
    private ImageView imageThumbnail;
    private ImageCapture imageCapture;
    private CameraSelector cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA;
    private ListenableFuture<ProcessCameraProvider> cameraProviderFuture;
    private ExecutorService cameraExecutor;
    private File lastPhotoFile;
    private final ActivityResultLauncher<String> permissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    startCamera();
                } else {
                    Toast.makeText(this, R.string.camera_permission_needed, Toast.LENGTH_LONG).show();
                }
            });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        previewView = findViewById(R.id.previewView);
        imageThumbnail = findViewById(R.id.imageThumbnail);
        Button buttonCapture = findViewById(R.id.buttonCapture);
        Button buttonSwitchCamera = findViewById(R.id.buttonSwitchCamera);
        cameraExecutor = Executors.newSingleThreadExecutor();
        buttonCapture.setOnClickListener(v -> takePhoto());
        buttonSwitchCamera.setOnClickListener(v -> switchCamera());
        imageThumbnail.setOnClickListener(v -> openLastPhoto());
        checkCameraPermissionAndStart();
    }
    private void checkCameraPermissionAndStart() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {
            startCamera();
        } else {
            permissionLauncher.launch(Manifest.permission.CAMERA);
        }
    }
    private void startCamera() {
        cameraProviderFuture = ProcessCameraProvider.getInstance(this);
        cameraProviderFuture.addListener(() -> {
            try {
                ProcessCameraProvider cameraProvider = cameraProviderFuture.get();
                bindUseCases(cameraProvider);
            } catch (Exception e) {
                Toast.makeText(this, R.string.photo_error, Toast.LENGTH_SHORT).show();
            }
        }, ContextCompat.getMainExecutor(this));
    }
    private void bindUseCases(ProcessCameraProvider cameraProvider) {
        Preview preview = new Preview.Builder().build();
        preview.setSurfaceProvider(previewView.getSurfaceProvider());
        imageCapture = new ImageCapture.Builder().build();
        cameraProvider.unbindAll();
        cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageCapture);
    }
    private void takePhoto() {
        if (imageCapture == null) {
            return;
        }
        File outputDir = new File(getFilesDir(), "photos");
        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }
        String fileName = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US)
                .format(System.currentTimeMillis()) + ".jpg";
        File photoFile = new File(outputDir, fileName);
        ImageCapture.OutputFileOptions outputOptions =
                new ImageCapture.OutputFileOptions.Builder(photoFile).build();
        imageCapture.takePicture(outputOptions, cameraExecutor, new ImageCapture.OnImageSavedCallback() {
            @Override
            public void onImageSaved(ImageCapture.OutputFileResults outputFileResults) {
                runOnUiThread(() -> {
                    lastPhotoFile = photoFile;
                    updateThumbnail(photoFile);
                    Toast.makeText(MainActivity.this, R.string.photo_saved, Toast.LENGTH_SHORT).show();
                });
            }
            @Override
            public void onError(ImageCaptureException exception) {
                runOnUiThread(() -> Toast.makeText(MainActivity.this, R.string.photo_error, Toast.LENGTH_SHORT).show());
            }
        });
    }
    private void updateThumbnail(File photoFile) {
        Bitmap bitmap = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
        imageThumbnail.setImageBitmap(bitmap);
    }
    private void switchCamera() {
    }
    private void openLastPhoto() {
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        cameraExecutor.shutdown();
    }
}
