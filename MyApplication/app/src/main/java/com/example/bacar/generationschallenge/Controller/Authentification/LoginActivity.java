package com.example.bacar.generationschallenge.Controller.Authentification;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bacar.generationschallenge.Controller.MainActivity;
import com.example.bacar.generationschallenge.Model.Joueurs;
import com.example.bacar.generationschallenge.Model.Network.RequestInterface;
import com.example.bacar.generationschallenge.Model.Network.ServerPlayerRequest;
import com.example.bacar.generationschallenge.Model.Network.ServerPlayerResponse;
import com.example.bacar.generationschallenge.R;
import com.example.bacar.generationschallenge.Test.NavMainActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private EditText login_mail;
    private EditText login_password;
    private TextView login_title;
    private TextView login_register;
    private Button login_button;
    private ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        login_mail = (EditText) findViewById(R.id.login_mail);
        login_password = (EditText) findViewById(R.id.login_password);
        login_title = (TextView) findViewById(R.id.loginscreen_title);
        login_register = (TextView) findViewById(R.id.loginscreen_register);
        login_button = (Button) findViewById(R.id.login_button);

        login_title.setText(R.string.loginTitle);
        login_register.setText(R.string.loginRegister);


        login_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterPart1.class);
                startActivity(intent);
            }
        });

        login_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String email = login_mail.getText().toString().trim();
                String pass = login_password.getText().toString();

                if (!isValidEmail(email)) {
                    //Set error message for email field
                    login_mail.setError("Invalid Email");
                }

                if (!isValidPassword(pass)) {
                    login_password.setError("Password cannot be empty");
                }

                if (isValidEmail(email) && isValidPassword(pass))  {

                    //progress.setVisibility(View.VISIBLE);

                    loginProcess(email, pass);
                    /*Toast.makeText(getApplicationContext(), "Vous etes correctement loggé", Toast.LENGTH_SHORT).show();
                    sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("Logged", 1);
                    editor.commit();
                    Intent intent = new Intent(LoginActivity.this, NavMainActivity.class);
                    startActivity(intent);
                    finish();*/
                } else {
                    Toast.makeText(getApplicationContext(), "Données incorrecte", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isValidPassword(String pass) {
        if(pass != null && pass.length() >= 4) {
            return true;
        }
        return false;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    private void loginProcess(String email,String password){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://orgemontcitychallenge.esy.es/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface requestInterface = retrofit.create(RequestInterface.class);

        Joueurs joueurs = new Joueurs();
        joueurs.setMail(email);
        joueurs.setPassword(password);
        ServerPlayerRequest request = new ServerPlayerRequest();
        request.setOperation("login");
        request.setJoueur(joueurs);
        Call<ServerPlayerResponse> response = requestInterface.operation(request);

        response.enqueue(new Callback<ServerPlayerResponse>() {
            @Override
            public void onResponse(Call<ServerPlayerResponse> call, retrofit2.Response<ServerPlayerResponse> response) {

                ServerPlayerResponse resp = response.body();
                Toast.makeText(LoginActivity.this, resp.getMessage(), Toast.LENGTH_LONG).show();

                if(resp.getResult().equals("success")){
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("Logged", 1);
                    editor.putString("mail",resp.getUser().getMail());
                    editor.apply();
                    Intent intent = new Intent(LoginActivity.this, NavMainActivity.class);
                    startActivity(intent);
                    finish();

                }
                //progress.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<ServerPlayerResponse> call, Throwable t) {

                progress.setVisibility(View.INVISIBLE);
                Log.d("FAILURE","failed");
                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }


}
