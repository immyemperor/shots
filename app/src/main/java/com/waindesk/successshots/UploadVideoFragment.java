package com.waindesk.successshots;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.VideoView;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UploadVideoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UploadVideoFragment extends Fragment {

    private VideoView videoView;
    private ImageView imageView;
    private Button uploadButton;
    private Button editButton;
    private Button imageSelectImage;
    private EditText titleEditText;
    int SELECT_PICTURE = 200;
    private boolean isImage = true;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_upload_video, container, false);

        videoView = view.findViewById(R.id.video_view);
        uploadButton = view.findViewById(R.id.upload_button);
        editButton = view.findViewById(R.id.edit_button);
        titleEditText = view.findViewById(R.id.title_edit_text);
        imageSelectImage = view.findViewById(R.id.imageSelectImage);
        imageView = view.findViewById(R.id.image_view);

        imageSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageChooser();
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

        return view;
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

            if (requestCode == SELECT_PICTURE && isImage) {
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
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Set the video to play.
//        Uri videoUri = Uri.parse("android.resource://com.example.myapplication/raw/my_video");
//        videoView.setVideoURI(videoUri);
//        videoView.start();
    }
}
