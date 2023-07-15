package com.waindesk.successshots;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.VideoView;

import com.waindesk.successshots.databinding.ActivityUploadViewBinding;

public class UploadViewActivity extends AppCompatActivity {
    private ActivityUploadViewBinding binding;
    private VideoView videoView;
    private ImageView imageView;
    private Button uploadButton;
    private Button editButton;
    private Button imageSelectImage;
    private Button selectVideo;
    private EditText titleEditText;
    int SELECT_PICTURE = 200;
    private boolean isImage = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUploadViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        videoView = binding.videoView;
        uploadButton = binding.uploadButton;
        editButton = binding.editButton;
        titleEditText = binding.titleEditText;
        imageSelectImage = binding.imageSelectImage;
        selectVideo = binding.selectVideo;
        imageView = binding.imageView;

        imageSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageChooser();
            }
        });
        selectVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoChooser();
            }
        });
        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Upload the video to the server.
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Edit the video.
            }
        });


    }

    void imageChooser() {

        // create an instance of the
        // intent of the type image
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        isImage = true;

        // pass the constant to compare it
        // with the returned requestCode
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }
    void videoChooser() {

        // create an instance of the
        // intent of the type image
        Intent i = new Intent();
        i.setType("video/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        isImage = false;
        // pass the constant to compare it
        // with the returned requestCode
        startActivityForResult(Intent.createChooser(i, "Select Video"), SELECT_PICTURE);
    }

    // this function is triggered when user
    // selects the image from the imageChooser
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE && isImage) {
                // Get the url of the image from data
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    imageView.setImageURI(selectedImageUri);
                }
            }

            if (requestCode == SELECT_PICTURE && !isImage) {
                // Get the url of the image from data
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    videoView.setVideoURI(selectedImageUri);
                    videoView.start();
                }
            }
        }
    }
}