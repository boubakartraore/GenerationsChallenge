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
 * Created by Bacar on 04/07/2017.
 */

public class RegisterPart1 extends AppCompatActivity {

    private TextView register1Title;
    private TextView register1TextCaptain;
    private TextView register1TextJoueur;
    private TextView register1TextInvite;
    private ImageView register1CaptainImage;
    private ImageView register1JoueurImage;
    private ImageView register1InviteImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_part1);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        register1Title = (TextView) findViewById(R.id.register1Title);
        register1TextCaptain = (TextView) findViewById(R.id.register1TextCaptain);
        register1TextJoueur = (TextView) findViewById(R.id.register1TextJoueur);
        register1TextInvite = (TextView) findViewById(R.id.register1TextInvite);
        register1CaptainImage = (ImageView) findViewById(R.id.register1CaptainImage);
        register1JoueurImage = (ImageView) findViewById(R.id.register1JoueurImage);
        register1InviteImage = (ImageView) findViewById(R.id.register1InviteImage);

        register1Title.setText(R.string.register1Title);
        register1TextCaptain.setText(R.string.register1Captain);
        register1TextJoueur.setText(R.string.register1Joueur);
        register1TextInvite.setText(R.string.register1Invite);
        register1CaptainImage.setImageResource(R.drawable.capitaine);
        register1JoueurImage.setImageResource(R.drawable.joueur);
        register1InviteImage.setImageResource(R.drawable.invite);


        register1CaptainImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent act = new Intent(RegisterPart1.this, RegisterPart2Activity.class);
                startActivity(act);
            }
        });

        register1JoueurImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent act = new Intent(RegisterPart1.this, RegisterActivity.class);
                startActivity(act);
            }
        });

        register1InviteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent act = new Intent(RegisterPart1.this, RegisterInviteActivity.class);
                startActivity(act);
            }
        });

    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
