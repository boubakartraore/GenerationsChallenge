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
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
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

import com.example.bacar.generationschallenge.Controller.MainActivity;
import com.example.bacar.generationschallenge.Model.Equipe;
import com.example.bacar.generationschallenge.Model.Network.RequestInterface;
import com.example.bacar.generationschallenge.Model.Network.ServerPlayerRequest;
import com.example.bacar.generationschallenge.Model.Network.ServerPlayerResponse;
import com.example.bacar.generationschallenge.Model.User;
import com.example.bacar.generationschallenge.R;
import com.example.bacar.generationschallenge.Test.NavMainActivity;
import com.mvc.imagepicker.ImagePicker;
import com.squareup.picasso.Picasso;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import android.net.Uri;

import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Bacar on 30/05/2017.
 */

public class RegisterActivity extends AppCompatActivity {

    /***/
    protected final int select_photo = 1;
    protected Uri profileUri = null;
    /***/


    /*****  Partie de test ******/

    protected SharedPreferences sharedPreferences;

    protected TextView register_title;
    protected TextView register_selectPhotoText;
    protected ImageView registerPhoto;
    protected EditText register_firstname;
    protected EditText register_lastname;
    protected EditText register_mail;
    protected EditText register_password;
    protected EditText register_confirmedpassword;
    protected Spinner register_poste;
    protected Spinner register_teamid;
    protected EditText register_username;
    protected TextView register_team;
    protected Button register_button;

    protected String register_role;
    protected int serverResponseCode = 0;

    protected String [] enregistrement;
    protected List<String> lesPostes = new ArrayList<String>();
    protected List<String> spin = new ArrayList<String>();

    protected List<Equipe> maListe = new ArrayList<Equipe>();
    protected Equipe equipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        init();

        R_poste();

        R_Team();

        //TeamMatch();

        setObs();

        action();



    }

    public void init(){

        register_firstname = (EditText) findViewById(R.id.register_firstname);
        register_lastname = (EditText) findViewById(R.id.register_lastname);
        register_mail = (EditText) findViewById(R.id.register_mail);
        register_password = (EditText) findViewById(R.id.register_password);
        register_confirmedpassword = (EditText) findViewById(R.id.register_confirmedpassword);
        register_poste = (Spinner) findViewById(R.id.register_poste);
        register_teamid = (Spinner) findViewById(R.id.register_teamid);
        register_username = (EditText) findViewById(R.id.register_username);
        register_title = (TextView) findViewById(R.id.register_title);
        register_team = (TextView) findViewById(R.id.register_team);
        register_button = (Button) findViewById(R.id.register_button);
        register_selectPhotoText = (TextView) findViewById(R.id.register_selectPhotoText);
        registerPhoto = (ImageView) findViewById(R.id.register_photo);

    }

    public void setObs() {

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, lesPostes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter adapter1 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spin);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        register_poste.setAdapter(adapter);
        register_teamid.setAdapter(adapter1);

        register_role = "Joueur";
    }


    public void action() {

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isValidEmail(register_mail.getText().toString())) {
                    register_mail.setError("Email invalide");
                } else
                if (!isValidPassword(register_password.getText().toString())) {
                    register_password.setError("Le mot de passe doit contenir au moins 4 caractères");
                } else
                if (!(register_password.getText().toString().equals(register_confirmedpassword.getText().toString()))){
                    register_password.setError("Les mots de passe doivent être identique");
                    register_confirmedpassword.setError("Les mots de passe doivent être identique");
                } else
                if (register_firstname.getText().toString().equals("")) {
                    register_firstname.setError("Ce champs doit etre renseigné");
                } else
                if (register_lastname.getText().toString().equals("")) {
                    register_lastname.setError("Ce champs doit etre renseigné");
                } else
                if (register_username.getText().toString().equals("")) {
                    register_username.setError("Ce champs doit etre renseigné");
                }
                else {
                    registerProcess(register_firstname.getText().toString(), register_lastname.getText().toString(), register_username.getText().toString(), register_mail.getText().toString(), register_password.getText().toString(), register_poste.getSelectedItem().toString(), register_teamid.getSelectedItemPosition(), register_role);
                }

            }
        });

        registerPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _launchPickerIntent();
            }
        });

        new UploadFileAsync().execute("");

    }

    public void R_poste () {
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

    public void R_Team() {
        spin.add("Choix d'équipe");

        spin.add("AFC");
        spin.add("New Team");
        spin.add("Dortmund");
        spin.add("Les Anciens");
        spin.add("La Roja");
        spin.add("All Black");
        spin.add("La Toho");
        spin.add("Sporting O.R.G");
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

    private void registerProcess(final String firstname, final String lastname, final String username, final String mail, String password, final String poste, final Integer team_id, String role){

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
        user.setPoste(poste);
        user.setTeam_id(team_id.toString());
        user.setRole(role);
        user.setUsername(username);

        ServerPlayerRequest request = new ServerPlayerRequest();
        request.setOperation("register");
        request.setUser(user);

        Toast.makeText(RegisterActivity.this, "Lancement de la recherche", Toast.LENGTH_LONG).show();

        Call<ServerPlayerResponse> response = requestInterface.operation(request);

        response.enqueue(new Callback<ServerPlayerResponse>() {
            @Override
            public void onResponse(Call<ServerPlayerResponse> call, retrofit2.Response<ServerPlayerResponse> response) {

                ServerPlayerResponse resp = response.body();
                Toast.makeText(RegisterActivity.this, resp.getMessage(), Toast.LENGTH_LONG).show();

                if(resp.getResult().equals("success")){
                    sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("Logged", 1);
                    editor.putString("lastname",lastname);
                    editor.putString("firstname",firstname);
                    editor.putString("username",username);
                    editor.putString("team",register_teamid.getSelectedItem().toString());
                    editor.putString("poste",poste);
                    editor.putString("mail",mail);
                    editor.apply();
                    Intent intent = new Intent(RegisterActivity.this, NavMainActivity.class);
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

    public class UploadFileAsync extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            try {
                String sourceFileUri = profileUri.toString();

                HttpURLConnection conn = null;
                DataOutputStream dos = null;
                String lineEnd = "\r\n";
                String twoHyphens = "--";
                String boundary = "*****";
                int bytesRead, bytesAvailable, bufferSize;
                byte[] buffer;
                int maxBufferSize = 1 * 1024 * 1024;
                File sourceFile = new File(sourceFileUri);

                if (sourceFile.isFile()) {

                    try {
                        String upLoadServerUri = "https://bacar.000webhostapp.com/upload.file";

                        // open a URL connection to the Servlet
                        FileInputStream fileInputStream = new FileInputStream(
                                sourceFile);
                        URL url = new URL(upLoadServerUri);

                        // Open a HTTP connection to the URL
                        conn = (HttpURLConnection) url.openConnection();
                        conn.setDoInput(true); // Allow Inputs
                        conn.setDoOutput(true); // Allow Outputs
                        conn.setUseCaches(false); // Don't use a Cached Copy
                        conn.setRequestMethod("POST");
                        conn.setRequestProperty("Connection", "Keep-Alive");
                        conn.setRequestProperty("ENCTYPE",
                                "multipart/form-data");
                        conn.setRequestProperty("Content-Type",
                                "multipart/form-data;boundary=" + boundary);
                        conn.setRequestProperty("bill", sourceFileUri);

                        dos = new DataOutputStream(conn.getOutputStream());

                        dos.writeBytes(twoHyphens + boundary + lineEnd);
                        dos.writeBytes("Content-Disposition: form-data; name=\"bill\";filename=\""
                                + sourceFileUri + "\"" + lineEnd);

                        dos.writeBytes(lineEnd);

                        // create a buffer of maximum size
                        bytesAvailable = fileInputStream.available();

                        bufferSize = Math.min(bytesAvailable, maxBufferSize);
                        buffer = new byte[bufferSize];

                        // read file and write it into form...
                        bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                        while (bytesRead > 0) {

                            dos.write(buffer, 0, bufferSize);
                            bytesAvailable = fileInputStream.available();
                            bufferSize = Math
                                    .min(bytesAvailable, maxBufferSize);
                            bytesRead = fileInputStream.read(buffer, 0,
                                    bufferSize);

                        }

                        // send multipart form data necesssary after file
                        // data...
                        dos.writeBytes(lineEnd);
                        dos.writeBytes(twoHyphens + boundary + twoHyphens
                                + lineEnd);

                        // Responses from the server (code and message)
                        serverResponseCode = conn.getResponseCode();
                        String serverResponseMessage = conn
                                .getResponseMessage();

                        if (serverResponseCode == 200) {

                            // messageText.setText(msg);
                            //Toast.makeText(ctx, "File Upload Complete.",
                            //      Toast.LENGTH_SHORT).show();

                            // recursiveDelete(mDirectory1);

                        }

                        // close the streams //
                        fileInputStream.close();
                        dos.flush();
                        dos.close();

                    } catch (Exception e) {

                        // dialog.dismiss();
                        e.printStackTrace();

                    }
                    // dialog.dismiss();

                } // End else block


            } catch (Exception ex) {
                // dialog.dismiss();

                ex.printStackTrace();
            }
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
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

}

