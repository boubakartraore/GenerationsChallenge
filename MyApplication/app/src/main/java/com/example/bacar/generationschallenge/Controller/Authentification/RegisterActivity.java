package com.example.bacar.generationschallenge.Controller.Authentification;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bacar.generationschallenge.Controller.MainActivity;
import com.example.bacar.generationschallenge.Model.Equipe;
import com.example.bacar.generationschallenge.R;
import com.example.bacar.generationschallenge.Test.NavMainActivity;
import com.mvc.imagepicker.ImagePicker;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import android.net.Uri;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bacar on 30/05/2017.
 */

public class RegisterActivity extends AppCompatActivity {

    /***/
    private final int select_photo = 1;
    private Uri profileUri = null;
    /***/

    private SharedPreferences sharedPreferences;

    private TextView register_title;
    private TextView register_selectPhotoText;
    private ImageView registerPhoto;
    private EditText register_firstname;
    private EditText register_lastname;
    private EditText register_mail;
    private EditText register_password;
    private EditText register_confirmedpassword;
    private Spinner register_poste;
    private Spinner register_teamid;
    private EditText register_telephone;
    private TextView register_team;
    private Button register_button;


    private String [] enregistrement;
    private List<String> lesPostes = new ArrayList<String>();
    private List<String> spin = new ArrayList<String>();

    private List<Equipe> maListe = new ArrayList<Equipe>();
    private Equipe equipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        register_firstname = (EditText) findViewById(R.id.register_firstname);
        register_lastname = (EditText) findViewById(R.id.register_lastname);
        register_mail = (EditText) findViewById(R.id.register_mail);
        register_password = (EditText) findViewById(R.id.register_password);
        register_confirmedpassword = (EditText) findViewById(R.id.register_confirmedpassword);
        register_poste = (Spinner) findViewById(R.id.register_poste);
        register_teamid = (Spinner) findViewById(R.id.register_teamid);
        register_telephone = (EditText) findViewById(R.id.register_telephone);
        register_title = (TextView) findViewById(R.id.register_title);
        register_team = (TextView) findViewById(R.id.register_team);
        register_button = (Button) findViewById(R.id.register_button);
        register_selectPhotoText = (TextView) findViewById(R.id.register_selectPhotoText);
        registerPhoto = (ImageView) findViewById(R.id.register_photo);

        register_title.setText(R.string.registerTitle);
        register_team.setText(R.string.registerTeamText);

        R_poste();
        TeamMatch();

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, lesPostes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter adapter1 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spin);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        register_poste.setAdapter(adapter);
        register_teamid.setAdapter(adapter1);

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(register_password.getText().toString().equals(register_confirmedpassword.getText().toString()))){
                    Toast.makeText(getApplicationContext(), "Les mots de passes sont différents", Toast.LENGTH_LONG).show();
                } else {
                    sharedPreferences = getSharedPreferences("MyData",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("Logged", 1);
                    editor.commit();
                    Toast.makeText(getApplicationContext(), "Inscrit", Toast.LENGTH_LONG).show();
                    //register(register_firstname.getText().toString(), register_lastname.getText().toString(), register_mail.getText().toString(), register_password.getText().toString(), register_poste.toString(), register_teamid.toString(), register_telephone.toString());
                    Intent intent = new Intent(RegisterActivity.this, NavMainActivity.class);
                    //intent.putExtra("enregistrement", enregistrement);
                    startActivity(intent);
                }

            }
        });

        registerPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _launchPickerIntent();
            }
        });
    }


    private void register(String firstname, String lastname, String mail, String password, String poste, String team_id, String telephone) {
        enregistrement[0] = firstname;
        enregistrement[1] = lastname;
        enregistrement[2] = mail;
        enregistrement[3] = password;
        enregistrement[4] = poste;
        enregistrement[5] = team_id;
        enregistrement[6] = telephone;
    }

    private void R_poste () {
        String poste;
        poste = "Attaquant";
        lesPostes.add(poste);
        poste = "Milieu";
        lesPostes.add(poste);
        poste = "Defenseur";
        lesPostes.add(poste);
        poste = "Gardien de but";
        lesPostes.add(poste);
    }

    public void TeamMatch() {
        equipe = new Equipe("Equipe 1", "Test", "Vert", 2,0,1,0,0);
        maListe.add(equipe);
        equipe = new Equipe("Equipe 2", "Test", "Vert", 4,0,4,0,0);
        maListe.add(equipe);
        equipe = new Equipe("Equipe 3", "Test", "Vert", 2,0,3,0,0);
        maListe.add(equipe);
        equipe = new Equipe("Equipe 4", "Test", "Vert", 2,3,11,0,0);
        maListe.add(equipe);

        for (int i = 0 ; i < maListe.size() ; i++) {
            spin.add(maListe.get(i).getName());
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void _launchPickerIntent() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_GET_CONTENT);
        photoPickerIntent.setType("image/*");
        startActivityForResult(Intent.createChooser(photoPickerIntent, "Select a photo"), select_photo);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch (requestCode) {
            case select_photo:
                if (resultCode == RESULT_OK) {
                    profileUri = imageReturnedIntent.getData();

                    Picasso.with(RegisterActivity.this)
                            .load(profileUri)
                            .into(registerPhoto);
                }
        }
    }

}
