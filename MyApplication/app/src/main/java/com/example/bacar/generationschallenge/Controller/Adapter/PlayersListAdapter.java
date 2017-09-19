package com.example.bacar.generationschallenge.Controller.Adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bacar.generationschallenge.Model.User;
import com.example.bacar.generationschallenge.R;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import retrofit2.Response;

/**
 * Created by Bacar on 06/06/2017.
 */

public class PlayersListAdapter extends RecyclerView.Adapter<PlayersListAdapter.PlayersListViewHolder> {


    private List<User> players;

    public PlayersListAdapter(List<User> item) {
        players = item;
    }

    public static class PlayersListViewHolder extends RecyclerView.ViewHolder {

        View view;
        ImageView playerImage;
        ImageView playerPosteImage;
        TextView playerFirstname;
        TextView playerLastname;
        TextView playerGoal;
        TextView playerPoste;

        public PlayersListViewHolder(View itemPlayer) {
            super(itemPlayer);
            playerFirstname = (TextView) itemPlayer.findViewById(R.id.playerFirstname);
            playerLastname = (TextView) itemPlayer.findViewById(R.id.playerLastname);
            playerPoste = (TextView) itemPlayer.findViewById(R.id.playerPoste);
            playerGoal = (TextView) itemPlayer.findViewById(R.id.playerGoal);
            playerImage = (ImageView) itemPlayer.findViewById(R.id.playerImage);
            playerPosteImage = (ImageView) itemPlayer.findViewById(R.id.playerPosteImage);
        }
    }

    @Override
    public PlayersListViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.players_list_items, parent, false);
        return new PlayersListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PlayersListViewHolder parent, int position) {
        User user = players.get(position);
        parent.playerImage.setImageResource(R.drawable.players);
        parent.playerFirstname.setText(user.getFirstname());
        parent.playerLastname.setText(user.getLastname());
        parent.playerPoste.setText(user.getPoste());
        parent.playerGoal.setText(user.getGoal().toString());

        switch (user.getPoste()) {
            case "Attaquant":
                parent.playerPosteImage.setImageResource(R.drawable.poste_attaquant);
                break;
            case "Milieu":
                parent.playerPosteImage.setImageResource(R.drawable.poste_milieu);
                break;
            case "Defenseur":
                parent.playerPosteImage.setImageResource(R.drawable.poste_defenseur);
                break;
            case "Gardien de but":
                parent.playerPosteImage.setImageResource(R.drawable.poste_gardien);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

}
