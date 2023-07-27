package com.example.task1_flashlightapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageButton toggleButton;

    boolean hascameraflash = false;
    boolean flashon = false;
    private BroadcastReceiver screenoffreceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toggleButton = findViewById(R.id.toggleButton);

        hascameraflash = getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);

        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (hascameraflash) {
                    if (flashon) {
                        flashon = false;
                        toggleButton.setImageResource(R.drawable.power_off);
                        flashLightOff();


                    } else {
                        flashon = true;
                        toggleButton.setImageResource(R.drawable.power_on);
                        flashLightOn();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "NO Flash available on your device", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void flashLightOff() {
        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        assert cameraManager != null;
        String cameraId;
        try {
            cameraId = cameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            throw new RuntimeException(e);
        }
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                cameraManager.setTorchMode(cameraId, false);
            }
        } catch (CameraAccessException e) {
            throw new RuntimeException(e);
        }
        Toast.makeText(MainActivity.this, "Flashlight is off", Toast.LENGTH_SHORT).show();
    }

    private void flashLightOn() {
        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        assert cameraManager != null;
        String cameraId;
        try {
            cameraId = cameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            throw new RuntimeException(e);
        }
        try {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                cameraManager.setTorchMode(cameraId, true);
            }

        } catch (CameraAccessException e) {
            throw new RuntimeException(e);
        }
        Toast.makeText(MainActivity.this, "Flashlight is on", Toast.LENGTH_SHORT).show();
    }

}