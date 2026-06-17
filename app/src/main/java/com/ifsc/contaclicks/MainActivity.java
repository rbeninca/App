package com.ifsc.contaclicks;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.view.PreviewView;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class MainActivity extends AppCompatActivity {
    private PreviewView previewView;
    private ImageView imageThumbnail;
    private ExecutorService cameraExecutor;
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
    }
    private void takePhoto() {
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
