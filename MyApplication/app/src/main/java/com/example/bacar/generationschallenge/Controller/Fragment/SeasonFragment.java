package com.example.bacar.generationschallenge.Controller.Fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.bacar.generationschallenge.Controller.Season.CalendarActivity;
import com.example.bacar.generationschallenge.Controller.Season.ResultActivity;
import com.example.bacar.generationschallenge.Controller.Season.TeamCalendarActivity;
import com.example.bacar.generationschallenge.Controller.myDate;
import com.example.bacar.generationschallenge.Model.Match;
import com.example.bacar.generationschallenge.R;

import java.util.Date;

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

    private Match nextMatch;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.season_fragment, container, false);

        nextMatch = new Match(2, new Date(), "Equipe 1", "Equipe 5", 0, 0);

        String dateNextMatch = new myDate().myDate(nextMatch.getDate());
        String heureNextMatch = new myDate().myHeure(nextMatch.getDate());

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
        seasonNextDate.setText(dateNextMatch + " à " + heureNextMatch);
        seasonNextTeamDom.setText(nextMatch.getEquipeDomicile());
        seasonNextTeamExt.setText(nextMatch.getEquipeExterieur());
        seasonSeparator.setText("VS");
        seasonNextMatch.setText(R.string.nextMatch);


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


        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(R.string.calendarResults);
    }


}
