package com.ifsc.contaclick;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.video.FileOutputOptions;
import androidx.camera.video.Quality;
import androidx.camera.video.QualitySelector;
import androidx.camera.video.Recorder;
import androidx.camera.video.Recording;
import androidx.camera.video.VideoCapture;
import androidx.camera.video.VideoRecordEvent;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.common.util.concurrent.ListenableFuture;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "CameraActivity";
    private static final int REQUEST_CODE_PERMISSIONS = 10;
    // Permissões requisitadas — construídas dinamicamente porque WRITE_EXTERNAL_STORAGE
    // não é necessária em Android Q+ (usas MediaStore scoped storage)
    // Método getRequiredPermissions() abaixo retorna a lista correta.

    private PreviewView viewFinder;
    private ImageCapture imageCapture;
    private VideoCapture<Recorder> videoCapture;
    private Recording currentRecording;
    private ExecutorService cameraExecutor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        viewFinder = findViewById(R.id.viewFinder);
        Button btnTakePhoto = findViewById(R.id.btnTakePhoto);
        Button btnStartStopVideo = findViewById(R.id.btnStartStopVideo);

        cameraExecutor = Executors.newSingleThreadExecutor();

        if (allPermissionsGranted()) {
            startCamera();
        } else {
            ActivityCompat.requestPermissions(
                    this,
                    getRequiredPermissions(),
                    REQUEST_CODE_PERMISSIONS
            );
        }

        btnTakePhoto.setOnClickListener(v -> takePhoto());

        btnStartStopVideo.setOnClickListener(v -> {
            if (currentRecording == null) {
                startVideoRecording();
                btnStartStopVideo.setText("Parar vídeo");
            } else {
                stopVideoRecording();
                btnStartStopVideo.setText("Vídeo");
            }
        });
    }

    private String[] getRequiredPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            return new String[]{
                    Manifest.permission.CAMERA,
                    Manifest.permission.RECORD_AUDIO
            };
        } else {
            return new String[]{
                    Manifest.permission.CAMERA,
                    Manifest.permission.RECORD_AUDIO,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            };
        }
    }

    private boolean allPermissionsGranted() {
        for (String permission : getRequiredPermissions()) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    // Callback de permissões
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {
                startCamera();
                return;
            }

            // Se aqui, alguma permissão foi negada. Mostrar orientação ao usuário.
            boolean shouldShowRationale = false;
            for (String perm : getRequiredPermissions()) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, perm)) {
                    shouldShowRationale = true;
                    break;
                }
            }

            // Mostrar diálogo com opções: Tentar novamente / Abrir configurações
            showPermissionsDeniedDialog(shouldShowRationale);
        }
    }

    private void showPermissionsDeniedDialog(boolean canRequestAgain) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("Permissões necessárias")
                .setMessage("O aplicativo precisa de permissão para usar a câmera e o áudio.\n\nConceder agora?")
                .setCancelable(false)
                .setNegativeButton("Abrir configurações", (dialog, which) -> openAppSettings());

        if (canRequestAgain) {
            builder.setPositiveButton("Tentar novamente", (dialog, which) ->
                    ActivityCompat.requestPermissions(MainActivity.this, getRequiredPermissions(), REQUEST_CODE_PERMISSIONS));
        } else {
            // Se não podemos solicitar novamente (usuário marcou 'não perguntar'), oferecemos apenas abrir configurações
            builder.setPositiveButton("Sair", (dialog, which) -> finish());
        }

        builder.show();
    }

    private void openAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.fromParts("package", getPackageName(), null));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void startCamera() {
        ListenableFuture<ProcessCameraProvider> cameraProviderFuture =
                ProcessCameraProvider.getInstance(this);

        cameraProviderFuture.addListener(() -> {
            try {
                ProcessCameraProvider cameraProvider = cameraProviderFuture.get();

                // Seletor de câmera (traseira)
                CameraSelector cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA;

                // Caso de uso: PREVIEW
                Preview preview = new Preview.Builder().build();
                preview.setSurfaceProvider(viewFinder.getSurfaceProvider());

                // Caso de uso: IMAGE CAPTURE (foto)
                imageCapture = new ImageCapture.Builder()
                        .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
                        .build();

                // Caso de uso: VIDEO CAPTURE
                QualitySelector qualitySelector = QualitySelector.from(
                        Quality.HD); // pode usar UHD, FHD, etc.

                Recorder recorder = new Recorder.Builder()
                        .setQualitySelector(qualitySelector)
                        .build();

                videoCapture = VideoCapture.withOutput(recorder);

                // Re-bind para novo ciclo
                cameraProvider.unbindAll();
                cameraProvider.bindToLifecycle(
                        this,
                        cameraSelector,
                        preview,
                        imageCapture,
                        videoCapture
                );

            } catch (ExecutionException | InterruptedException e) {
                Log.e(TAG, "Erro ao iniciar câmera", e);
            }
        }, ContextCompat.getMainExecutor(this));
    }
    // ---------- FOTO ----------
    private void takePhoto() {
        if (imageCapture == null) {
            Toast.makeText(this, "ImageCapture não inicializado", Toast.LENGTH_SHORT).show();
            return;
        }

        File outputDir = getOutputDirectory();
        String fileName = "IMG_" + System.currentTimeMillis() + ".jpg";
        File photoFile = new File(outputDir, fileName);

        ImageCapture.OutputFileOptions outputOptions =
                new ImageCapture.OutputFileOptions.Builder(photoFile).build();

        imageCapture.takePicture(
                outputOptions,
                cameraExecutor,
                new ImageCapture.OnImageSavedCallback() {
                    @Override
                    public void onImageSaved(
                            @NonNull ImageCapture.OutputFileResults outputFileResults) {
                        runOnUiThread(() ->
                                Toast.makeText(MainActivity.this,
                                        "Foto salva: " + photoFile.getAbsolutePath(),
                                        Toast.LENGTH_SHORT).show());

                        // Indexa a foto imediatamente no MediaStore / Galeria
                        saveFileToGallery(photoFile, true);
                    }

                    @Override
                    public void onError(
                            @NonNull ImageCaptureException exception) {
                        Log.e(TAG, "Erro ao salvar foto: ", exception);
                    }
                }
        );
    }

    // ---------- VÍDEO ----------
    private void startVideoRecording() {
        if (videoCapture == null) {
            Toast.makeText(this, "VideoCapture não inicializado", Toast.LENGTH_SHORT).show();
            return;
        }

        File outputDir = getOutputDirectory();
        String fileName = "VID_" + System.currentTimeMillis() + ".mp4";
        File videoFile = new File(outputDir, fileName);

        FileOutputOptions fileOutputOptions =
                new FileOutputOptions.Builder(videoFile).build();

        // Preparar a gravação
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        currentRecording = videoCapture.getOutput()
                .prepareRecording(this, fileOutputOptions)
                .withAudioEnabled() // se quiser gravar áudio
                .start(
                        ContextCompat.getMainExecutor(this),
                        videoRecordEvent -> {
                            if (videoRecordEvent instanceof VideoRecordEvent.Start) {
                                Log.d(TAG, "Gravação iniciada");
                            } else if (videoRecordEvent instanceof VideoRecordEvent.Finalize) {
                                VideoRecordEvent.Finalize finalizeEvent =
                                        (VideoRecordEvent.Finalize) videoRecordEvent;
                                if (!finalizeEvent.hasError()) {
                                    Toast.makeText(
                                            this,
                                            "Vídeo salvo: " + videoFile.getAbsolutePath(),
                                            Toast.LENGTH_SHORT
                                    ).show();

                                    // Indexa o vídeo imediatamente no MediaStore / Galeria
                                    saveFileToGallery(videoFile, false);

                                } else {
                                    Log.e(TAG, "Erro ao gravar vídeo"
                                    );
                                }
                                currentRecording = null;
                            }
                        }
                );
    }

    private void stopVideoRecording() {
        if (currentRecording != null) {
            currentRecording.stop();
            // O callback VideoRecordEvent.Finalize será chamado
        }
    }

    private File getOutputDirectory() {
        File mediaDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        if (mediaDir != null && mediaDir.exists()) {
            return mediaDir;
        } else {
            return getFilesDir();
        }
    }

    // Novo: salva/registro o arquivo no MediaStore ou copia para pasta pública e dispara scan
    private void saveFileToGallery(File srcFile, boolean isImage) {
        if (srcFile == null || !srcFile.exists()) {
            Log.w(TAG, "Arquivo inválido para salvar na galeria");
            return;
        }

        String fileName = srcFile.getName();
        String mimeType = isImage ? "image/jpeg" : "video/mp4";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            // Insere no MediaStore e copia os bytes
            ContentValues values = new ContentValues();
            values.put(MediaStore.MediaColumns.DISPLAY_NAME, fileName);
            values.put(MediaStore.MediaColumns.MIME_TYPE, mimeType);
            values.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + "/Contaclick");

            Uri collection = isImage ? MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
                    : MediaStore.Video.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY);
            try {
                Uri uri = getContentResolver().insert(collection, values);
                if (uri == null) {
                    Log.e(TAG, "Falha ao inserir no MediaStore");
                    // fallback: scan original
                    MediaScannerConnection.scanFile(this, new String[]{srcFile.getAbsolutePath()}, null,
                            (path, uri2) -> Log.d(TAG, "Scanned file: " + path + " -> uri=" + uri2));
                    return;
                }

                try (InputStream in = new FileInputStream(srcFile);
                     OutputStream out = getContentResolver().openOutputStream(uri)) {
                    if (out == null) throw new IOException("Unable to open output stream for uri");
                    copyStream(in, out);
                    Log.d(TAG, "Arquivo copiado para MediaStore: " + uri);
                }

                // opcional: deletar arquivo temporário se for do diretório do app
                boolean deleted = srcFile.delete();
                Log.d(TAG, "Arquivo temporário deletado: " + deleted);

            } catch (Exception e) {
                Log.e(TAG, "Erro ao salvar arquivo no MediaStore", e);
                // Garantir que o arquivo seja indexado mesmo em erro
                MediaScannerConnection.scanFile(this, new String[]{srcFile.getAbsolutePath()}, null,
                        (path, uri2) -> Log.d(TAG, "Scanned file (fallback): " + path + " -> uri=" + uri2));
            }

        } else {
            // API < Q: copia para pasta pública e solicita scan
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                // não temos permissão - apenas tenta scan (pode ou não aparecer em algumas galerias)
                MediaScannerConnection.scanFile(this, new String[]{srcFile.getAbsolutePath()}, null,
                        (path, uri) -> Log.d(TAG, "Scanned file (no write perm): " + path + " -> uri=" + uri));
                Toast.makeText(this, "Conceda permissão de armazenamento para salvar na pasta pública", Toast.LENGTH_LONG).show();
                return;
            }

            File pictures = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            File appDir = new File(pictures, "Contaclick");
            if (!appDir.exists()) appDir.mkdirs();
            File dest = new File(appDir, fileName);
            try {
                copyFile(srcFile, dest);
                MediaScannerConnection.scanFile(this, new String[]{dest.getAbsolutePath()}, null,
                        (path, uri) -> Log.d(TAG, "Scanned file: " + path + " -> uri=" + uri));
            } catch (IOException e) {
                Log.e(TAG, "Erro ao copiar arquivo para pasta pública", e);
                MediaScannerConnection.scanFile(this, new String[]{srcFile.getAbsolutePath()}, null,
                        (path, uri) -> Log.d(TAG, "Scanned fallback: " + path + " -> uri=" + uri));
            }
        }
    }

    private void copyStream(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[8192];
        int len;
        while ((len = in.read(buffer)) > 0) {
            out.write(buffer, 0, len);
        }
        out.flush();
    }

    private void copyFile(File src, File dst) throws IOException {
        try (InputStream in = new FileInputStream(src);
             OutputStream out = new FileOutputStream(dst)) {
            copyStream(in, out);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (cameraExecutor != null) {
            cameraExecutor.shutdown();
        }
    }

}