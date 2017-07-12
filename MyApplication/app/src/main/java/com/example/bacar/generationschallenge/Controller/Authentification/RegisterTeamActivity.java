package com.example.bacar.generationschallenge.Controller.Authentification;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bacar.generationschallenge.R;
import com.example.bacar.generationschallenge.Test.NavMainActivity;
import com.squareup.picasso.Picasso;

/**
 * Created by Bacar on 06/07/2017.
 */

public class RegisterTeamActivity extends RegisterActivity {

    private final int es_photo = 2;

    private EditText registerTeamName;
    private EditText registerTeamColor;
    private EditText registerTeamNumber;
    private ImageView registerTeamPhoto;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_team);

        init();
        setObs();
        action();

        registerTeamPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _launchPackerIntent();
            }
        });


    }

    @Override
    public void init() {
        super.init();
        registerTeamName = (EditText) findViewById(R.id.registerTeamName);
        registerTeamColor = (EditText) findViewById(R.id.registerTeamColor);
        registerTeamNumber = (EditText) findViewById(R.id.registerTeamNumber);
        registerTeamPhoto = (ImageView) findViewById(R.id.registerTeamTakePhoto);
    }


    @Override
    public void action() {
        super.action();
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (register_firstname.getText().toString().equalsIgnoreCase("a")) {
                    Toast.makeText(getApplicationContext(), "C'est bon deja", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void _launchPackerIntent() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_GET_CONTENT);
        photoPickerIntent.setType("image/*");
        startActivityForResult(Intent.createChooser(photoPickerIntent, "Select a photo"), es_photo);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch (requestCode) {
            case select_photo:
                if (resultCode == RESULT_OK) {
                    profileUri = imageReturnedIntent.getData();

                    Picasso.with(RegisterTeamActivity.this)
                            .load(profileUri)
                            .into(registerPhoto);
                }
            case es_photo:
                if (resultCode == RESULT_OK) {
                    profileUri = imageReturnedIntent.getData();

                    Picasso.with(RegisterTeamActivity.this)
                            .load(profileUri)
                            .into(registerTeamPhoto);
                }
        }
    }

}
