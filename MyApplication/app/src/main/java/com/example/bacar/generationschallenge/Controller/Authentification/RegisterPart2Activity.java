package com.example.bacar.generationschallenge.Controller.Authentification;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bacar.generationschallenge.R;

/**
 * Created by Bacar on 05/07/2017.
 */

public class RegisterPart2Activity extends AppCompatActivity {

    private TextView register2Title;
    private TextView register2TextTeam;
    private TextView register2TextJoueur;
    private ImageView register2TeamImage;
    private ImageView register2JoueurImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_part2);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        register2Title = (TextView) findViewById(R.id.register2Title);
        register2TextTeam = (TextView) findViewById(R.id.register2TextTeam);
        register2TextJoueur = (TextView) findViewById(R.id.register2TextJoueur);
        register2TeamImage = (ImageView) findViewById(R.id.register2TeamImage);
        register2JoueurImage = (ImageView) findViewById(R.id.register2JoueurImage);

        register2Title.setText(R.string.register2Title);
        register2TextTeam.setText(R.string.register2Team);
        register2TextJoueur.setText(R.string.register2Joueur);
        register2TeamImage.setImageResource(R.drawable.team);
        register2JoueurImage.setImageResource(R.drawable.joueur);


        register2JoueurImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent act = new Intent(RegisterPart2Activity.this, RegisterActivity.class);
                startActivity(act);
                finish();
            }
        });

        register2TeamImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent act = new Intent(RegisterPart2Activity.this, RegisterTeamActivity.class);
                startActivity(act);
                finish();
            }
        });

    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
