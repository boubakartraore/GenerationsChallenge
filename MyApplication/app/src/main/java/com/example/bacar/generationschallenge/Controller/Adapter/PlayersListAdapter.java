package com.example.bacar.generationschallenge.Controller.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bacar.generationschallenge.Model.Joueurs;
import com.example.bacar.generationschallenge.R;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Bacar on 06/06/2017.
 */

public class PlayersListAdapter extends RecyclerView.Adapter<PlayersListAdapter.PlayersListViewHolder> {


    private List<Joueurs> players;

    public PlayersListAdapter(List<Joueurs> item) {
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
        Joueurs joueurs = players.get(position);
        parent.playerImage.setImageResource(R.drawable.players);
        parent.playerFirstname.setText(joueurs.getFirstname());
        parent.playerLastname.setText(joueurs.getLastname());
        parent.playerPoste.setText(joueurs.getPoste());
        parent.playerGoal.setText(joueurs.getGoal().toString());

        switch (joueurs.getPoste()) {
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

/*public class PlayersListAdapter extends RecyclerView.Adapter<PlayersListAdapter.PlayersListViewHolder> {

    private List<Joueurs> players;

    public class PlayersListViewHolder extends RecyclerView.ViewHolder {
        public ImageView playerImage;
        public TextView playerFirstname;
        public TextView playerLastname;
        public TextView playerGoal;
        public TextView playerPoste;

        public PlayersListViewHolder(View itemPlayer) {
            super(itemPlayer);
            playerFirstname = (TextView) itemPlayer.findViewById(R.id.playerFirstname);
            playerLastname = (TextView) itemPlayer.findViewById(R.id.playerLastname);
            playerPoste = (TextView) itemPlayer.findViewById(R.id.playerPoste);
            playerGoal = (TextView) itemPlayer.findViewById(R.id.playerGoal);
            playerImage = (ImageView) itemPlayer.findViewById(R.id.playerImage);
        }
    }

    public PlayersListAdapter(List<Joueurs> item) {
        this.players = item;
    }

    @Override
    public PlayersListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.players_list_items, parent, false);

        return new PlayersListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PlayersListViewHolder parent, int position) {
        Joueurs joueurs = players.get(position);
        parent.playerImage.setImageResource(R.drawable.gris);
        parent.playerFirstname.setText(joueurs.getFirstname());
        parent.playerLastname.setText(joueurs.getLastname());
        parent.playerPoste.setText(joueurs.getPoste());
        parent.playerGoal.setText(joueurs.getGoal());
    }

    @Override
    public int getItemCount() {
        return players.size();
    }
}*/


/*public class PlayersListAdapter extends RecyclerView.Adapter<PlayersListAdapter.ViewHolder> {

    private List<Joueurs> players;
    private int itemLayout;

    public PlayersListAdapter(List<Joueurs> items, int itemLayout) {
        this.players = items;
        this.itemLayout = itemLayout;
    }

    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        return new ViewHolder(v);
    }

    @Override public void onBindViewHolder(ViewHolder parent, int position) {
        Joueurs joueurs = players.get(position);
        parent.playerImage.setImageResource(R.drawable.gris);
        parent.playerFirstname.setText(joueurs.getFirstname());
        parent.playerLastname.setText(joueurs.getLastname());
        parent.playerPoste.setText(joueurs.getPoste());
        parent.playerGoal.setText(joueurs.getGoal().toString());
    }

    @Override public int getItemCount() {
        return players.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView playerImage;
        public TextView playerFirstname;
        public TextView playerLastname;
        public TextView playerGoal;
        public TextView playerPoste;

        public ViewHolder(View itemPlayer) {
            super(itemPlayer);
            playerFirstname = (TextView) itemPlayer.findViewById(R.id.playerFirstname);
            playerLastname = (TextView) itemPlayer.findViewById(R.id.playerLastname);
            playerPoste = (TextView) itemPlayer.findViewById(R.id.playerPoste);
            playerGoal = (TextView) itemPlayer.findViewById(R.id.playerGoal);
            playerImage = (ImageView) itemPlayer.findViewById(R.id.playerImage);
        }
    }
}*/
