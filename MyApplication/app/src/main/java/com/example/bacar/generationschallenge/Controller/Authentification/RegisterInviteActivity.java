package com.example.bacar.generationschallenge.Controller.Authentification;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bacar.generationschallenge.Model.Equipe;
import com.example.bacar.generationschallenge.R;
import com.example.bacar.generationschallenge.Test.NavMainActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bacar on 04/07/2017.
 */

public class RegisterInviteActivity extends AppCompatActivity {

    /***/
    private final int select_photo = 1;
    private Uri profileUri = null;
    /***/

    private SharedPreferences sharedPreferences;

    private TextView register_title;
    private EditText register_firstname;
    private EditText register_lastname;
    private EditText register_mail;
    private EditText register_password;
    private EditText register_confirmedpassword;
    private Button register_button;


    private String [] enregistrement;
    private List<String> lesPostes = new ArrayList<String>();
    private List<String> spin = new ArrayList<String>();

    private List<Equipe> maListe = new ArrayList<Equipe>();
    private Equipe equipe;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_invite);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        register_firstname = (EditText) findViewById(R.id.register_firstname);
        register_lastname = (EditText) findViewById(R.id.register_lastname);
        register_mail = (EditText) findViewById(R.id.register_mail);
        register_password = (EditText) findViewById(R.id.register_password);
        register_confirmedpassword = (EditText) findViewById(R.id.register_confirmedpassword);
        register_title = (TextView) findViewById(R.id.register_title);
        register_button = (Button) findViewById(R.id.register_button);

        register_title.setText(R.string.registerTitle);

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(register_password.getText().toString().equals(register_confirmedpassword.getText().toString()))){
                    Toast.makeText(getApplicationContext(), "Les mots de passes sont différents", Toast.LENGTH_LONG).show();
                } else {
                    sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("Logged", 1);
                    editor.commit();
                    Toast.makeText(getApplicationContext(), "Inscrit", Toast.LENGTH_LONG).show();
                    //register(register_firstname.getText().toString(), register_lastname.getText().toString(), register_mail.getText().toString(), register_password.getText().toString(), register_poste.toString(), register_teamid.toString(), register_telephone.toString());
                    Intent intent = new Intent(RegisterInviteActivity.this, NavMainActivity.class);
                    //intent.putExtra("enregistrement", enregistrement);
                    startActivity(intent);
                }

            }
        });

    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
