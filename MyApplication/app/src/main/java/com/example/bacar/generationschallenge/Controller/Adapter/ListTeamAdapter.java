package com.example.bacar.generationschallenge.Controller.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bacar.generationschallenge.Listener.ClickListener;
import com.example.bacar.generationschallenge.Model.Equipe;
import com.example.bacar.generationschallenge.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Bacar on 05/06/2017.
 */

public class ListTeamAdapter extends RecyclerView.Adapter<ListTeamAdapter.ListTeamViewHolder> {

    private List<Equipe> maListe;
    private ClickListener clicklistener = null;

    public ListTeamAdapter(List<Equipe> items) {
        maListe = items;
    }

    public class ListTeamViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView imageView;
        public TextView textView;

        public ListTeamViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            imageView = (ImageView) itemView.findViewById(R.id.list_team_image);
            textView = (TextView) itemView.findViewById(R.id.list_team_name);
        }

        @Override
        public void onClick(View v) {
            if (clicklistener != null) {
                clicklistener.itemClicked(v, getAdapterPosition());
            }
        }
    }

    public void setClickListener(ClickListener clicklistener) {
        this.clicklistener = clicklistener;
    }

    @Override
    public ListTeamViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_team_items, parent, false);
        return new ListTeamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListTeamViewHolder holder, int position) {
        Equipe equipe = maListe.get(position);
        holder.imageView.setImageResource(R.drawable.team);
        holder.textView.setText(equipe.getName());
    }

    @Override
    public int getItemCount() {
        return maListe.size();
    }
}
