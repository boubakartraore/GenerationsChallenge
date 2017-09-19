package com.example.bacar.generationschallenge.Controller.Fragment;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.bacar.generationschallenge.Controller.Authentification.LoginActivity;
import com.example.bacar.generationschallenge.Controller.Season.CalendarActivity;
import com.example.bacar.generationschallenge.Controller.Season.ResultActivity;
import com.example.bacar.generationschallenge.Controller.Season.TeamCalendarActivity;
import com.example.bacar.generationschallenge.Controller.myDate;
import com.example.bacar.generationschallenge.Model.AllMatch;
import com.example.bacar.generationschallenge.Model.Match;
import com.example.bacar.generationschallenge.Model.Network.MatchInterface;
import com.example.bacar.generationschallenge.R;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Bacar on 15/06/2017.
 */

public class SeasonFragment extends Fragment {

    private TextView seasonCalendar;
    private TextView seasonResults;
    private TextView seasonTeamCalendar;
    private TextView seasonNextMatch;
    private TextView seasonNextDate;
    private TextView seasonNextTeamDom;
    private TextView seasonNextTeamExt;
    private TextView seasonSeparator;
    private ImageView seasonResultsImage;
    private ImageView seasonCalendarImage;
    private ImageView seasonTeamCalendarImage;

    private View view;
    private SharedPreferences sharedPreferences;
    private Integer logged;

    private Match nextMatch;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        sharedPreferences = this.getActivity().getSharedPreferences("MyData", Context.MODE_PRIVATE);
        logged = sharedPreferences.getInt("Logged", 0);

        if (logged == 0) {

            view = inflater.inflate(R.layout.not_logged_layout, container, false);

            Button connectionButton = (Button) view.findViewById(R.id.connectionButton);

            connectionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent act = new Intent(getActivity(), LoginActivity.class);
                    startActivity(act);
                }
            });

            return view;

        } else {

            view = inflater.inflate(R.layout.season_fragment, container, false);


            ProgressDialog progressDialog = new ProgressDialog(getActivity());
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setTitle("Chargement");
            progressDialog.setMessage("Veuillez patienter pendant le chargement des données...");
            progressDialog.show();

            seasonCalendar = (TextView) view.findViewById(R.id.seasonCalendar);
            seasonResults = (TextView) view.findViewById(R.id.seasonResults);
            seasonTeamCalendar = (TextView) view.findViewById(R.id.seasonTeamCalendar);
            seasonNextMatch = (TextView) view.findViewById(R.id.seasonNextMatch);
            seasonNextDate = (TextView) view.findViewById(R.id.seasonNextDate);
            seasonNextTeamDom = (TextView) view.findViewById(R.id.seasonNextTeamDom);
            seasonNextTeamExt = (TextView) view.findViewById(R.id.seasonNextTeamExt);
            seasonSeparator = (TextView) view.findViewById(R.id.seasonSeparator);
            seasonResultsImage = (ImageView) view.findViewById(R.id.seasonResultsImage);
            seasonCalendarImage = (ImageView) view.findViewById(R.id.seasonCalendarImage);
            seasonTeamCalendarImage = (ImageView) view.findViewById(R.id.seasonTeamCalendarImage);


            seasonResults.setText(R.string.results);
            seasonCalendar.setText(R.string.calendar);
            seasonTeamCalendar.setText(R.string.teamCalendar);
            seasonSeparator.setText("VS");
            seasonNextMatch.setText(R.string.nextMatch);


            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://bacar.000webhostapp.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            MatchInterface requestTeamInterface = retrofit.create(MatchInterface.class);

            final Call<AllMatch> requete = requestTeamInterface.getMatch();

            requete.enqueue(new Callback<AllMatch>() {

                @Override
                public void onResponse(Call<AllMatch> call, retrofit2.Response<AllMatch> response) {

                    if (response.isSuccessful()) {

                        AllMatch test = response.body();

                        nextMatch = test.getNextMatch("AFC");

                        seasonNextDate.setText("Le " + nextMatch.getDate());
                        seasonNextTeamDom.setText(nextMatch.getEquipeDomicile());
                        seasonNextTeamExt.setText(nextMatch.getEquipeExterieur());

                    }
                }

                @Override
                public void onFailure(Call<AllMatch> call, Throwable t) {

                    Toast.makeText(getActivity(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

                }
            });

            seasonCalendarImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent act = new Intent(getActivity(), CalendarActivity.class);
                    startActivity(act);
                }
            });

            seasonTeamCalendarImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent act = new Intent(getActivity(), TeamCalendarActivity.class);
                    startActivity(act);
                }
            });

            seasonResultsImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent act = new Intent(getActivity(), ResultActivity.class);
                    startActivity(act);
                }
            });

            progressDialog.dismiss();

            return view;
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(R.string.calendarResults);
    }


}
