package com.example.bacar.generationschallenge.Controller.Fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.bacar.generationschallenge.Controller.Classement.ButeursClassementFragment;
import com.example.bacar.generationschallenge.Controller.Classement.TeamClassementFragment;
import com.example.bacar.generationschallenge.R;

/**
 * Created by Bacar on 09/06/2017.
 */

public class ClassementFragment extends Fragment implements View.OnClickListener {

    private ImageView classementButton;
    private ImageView buteursButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.classement_fragment, container, false);

        classementButton = (ImageView) view.findViewById(R.id.classementButton);
        buteursButton = (ImageView) view.findViewById(R.id.buteursButton);

        classementButton.setOnClickListener(this);
        buteursButton.setOnClickListener(this);


        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(R.string.classement);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.classementButton:
                Intent et = new Intent(getActivity(), TeamClassementFragment.class);
                startActivity(et);
                break;
            case R.id.buteursButton:
                Intent az = new Intent(getActivity(), ButeursClassementFragment.class);
                startActivity(az);
                break;
        }
    }
}
