package com.example.bacar.generationschallenge.Controller.Authentification;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bacar.generationschallenge.Model.Equipe;
import com.example.bacar.generationschallenge.Model.Network.RequestInterface;
import com.example.bacar.generationschallenge.Model.Network.ServerPlayerRequest;
import com.example.bacar.generationschallenge.Model.Network.ServerPlayerResponse;
import com.example.bacar.generationschallenge.Model.User;
import com.example.bacar.generationschallenge.R;
import com.example.bacar.generationschallenge.Test.NavMainActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
                    Toast.makeText(getApplicationContext(), "Inscrit", Toast.LENGTH_LONG).show();
                    registerProcess(register_firstname.getText().toString(), register_lastname.getText().toString(), register_mail.getText().toString(), register_password.getText().toString());
                }

            }
        });

    }


    private void registerProcess(final String firstname, final String lastname, final String mail, String password){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://bacar.000webhostapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface requestInterface = retrofit.create(RequestInterface.class);

        final User user = new User();

        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setMail(mail);
        user.setPassword(password);
        user.setUsername(" ");
        user.setRole("Invite");
        user.setPoste(" ");
        user.setTeam_id(" ");

        ServerPlayerRequest request = new ServerPlayerRequest();
        request.setOperation("register");
        request.setUser(user);

        Call<ServerPlayerResponse> response = requestInterface.operation(request);

        response.enqueue(new Callback<ServerPlayerResponse>() {
            @Override
            public void onResponse(Call<ServerPlayerResponse> call, retrofit2.Response<ServerPlayerResponse> response) {

                ServerPlayerResponse resp = response.body();
                Toast.makeText(RegisterInviteActivity.this, resp.getMessage(), Toast.LENGTH_LONG).show();

                if(resp.getResult().equals("success")){
                    sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("Logged", 2);
                    editor.putString("lastname",lastname);
                    editor.putString("firstname",firstname);
                    editor.putString("mail",mail);
                    editor.apply();
                    Intent intent = new Intent(RegisterInviteActivity.this, NavMainActivity.class);
                    startActivity(intent);
                    finish();

                }
                //progress.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<ServerPlayerResponse> call, Throwable t) {

                //progress.setVisibility(View.INVISIBLE);
                Log.d("FAILURE","failed");
                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
