package com.example.bacar.generationschallenge.Controller.Classement;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bacar.generationschallenge.Controller.Adapter.PlayersListAdapter;
import com.example.bacar.generationschallenge.Model.Network.AllPlayerInterface;
import com.example.bacar.generationschallenge.Model.User;
import com.example.bacar.generationschallenge.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Bacar on 15/06/2017.
 */

public class AllButeursClassementActivity extends AppCompatActivity {

    private TextView title;
    private RecyclerView rv;
    private User j1;
    private List<User> play = new ArrayList<User>();

    private PlayersListAdapter myAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);

        title = (TextView) findViewById(R.id.playersTeam);
        rv = (RecyclerView) findViewById(R.id.playersList);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        title.setText(R.string.allScorers);

        LinearLayoutManager myLayout = new LinearLayoutManager(this);
        rv.setLayoutManager(myLayout);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://bacar.000webhostapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Log.i("onResponse", "Etape 1");

        AllPlayerInterface allPlayerInterface = retrofit.create(AllPlayerInterface.class);

        Log.i("onResponse", "Etape 2");

        Call<ArrayList<User>> requete = allPlayerInterface.getUse();

        Log.i("onResponse", "Etape 3");

        requete.enqueue(new Callback<ArrayList<User>>() {


            @Override
            public void onResponse(Call<ArrayList<User>> call, retrofit2.Response<ArrayList<User>> response) {

                play = response.body();

                Collections.sort(play, new User.JoueursComparator());

                myAdapter = new PlayersListAdapter(play);
                rv.setAdapter(myAdapter);

            }

            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {

                Log.d("FAILURE","failed");

            }
        });


        /* *************** */




    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
