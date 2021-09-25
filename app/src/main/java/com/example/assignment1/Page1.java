package com.example.assignment1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.assignment1.R;

import java.io.IOException;

public class Page1 extends AppCompatActivity  {

    private final int REQUEST_VIDEO_CAPTURE = 1;
    private CameraManager cameraManager;
    private String getCameraID;
    Uri videoUri=null;


    private Camera mCamera;
    private TextureView mTextureView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page1);


        // Pressing symptoms -> next page
        // Symptoms button
        Button b1 = (Button) findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int1 = new Intent(getApplicationContext(), Page2.class);
                startActivity(int1);
            }
        });

        // Readying the camera
        cameraManager=(CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            getCameraID = cameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
        // Heart-rate button
        Button b3 = (Button) findViewById(R.id.button3);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ViewView Code
                toggleTorchLight(true);
                Toast.makeText(view.getContext(), "Heart rate measure, put finger on camera with Flash on",Toast.LENGTH_LONG).show();
                Intent takeFingerPrintVideo = new Intent("android.media.action.VIDEO_CAPTURE");
                takeFingerPrintVideo.putExtra(MediaStore.EXTRA_DURATION_LIMIT,15);
                startActivityForResult(takeFingerPrintVideo,REQUEST_VIDEO_CAPTURE);
                Log.i("VIDEO_CAPTURE","Done with video capture code");
                toggleTorchLight(false);
                //Video view code end

            }
        });

    }


    //  To switch on/off torchlight
    protected void toggleTorchLight(boolean y){
        try {
            // true sets the torch in ON mode
            cameraManager.setTorchMode(getCameraID, y);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    // For storing the video
    @Override
    protected void onActivityResult( int requestCode, int resultCode, Intent intent){
        VideoView vV=(VideoView) findViewById(R.id.videoView);
        super.onActivityResult( requestCode,  resultCode,  intent);
        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
            videoUri = intent.getData();
            vV.setVideoURI(videoUri);
            vV.start();
            Log.i("VIDEO_CAPTURE","Data at URI ready for viewing");
        }
    }

}
