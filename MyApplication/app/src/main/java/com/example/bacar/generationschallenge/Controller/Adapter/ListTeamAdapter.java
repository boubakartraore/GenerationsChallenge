package com.example.bacar.generationschallenge.Controller.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bacar.generationschallenge.Listener.ClickListener;
import com.example.bacar.generationschallenge.Model.Equipe;
import com.example.bacar.generationschallenge.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Bacar on 05/06/2017.
 */

public class ListTeamAdapter extends RecyclerView.Adapter<ListTeamAdapter.ListTeamViewHolder> {

    private List<Equipe> maListe;
    private final ListTeamAdapterListener mListener;
    private ClickListener clicklistener = null;
    private Context context;

    public ListTeamAdapter(Context applicationContext, ListTeamAdapterListener listener) {
        maListe = new ArrayList<Equipe>();
        context = applicationContext;
        mListener = listener;
    }

    public class ListTeamViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView imageView;
        public TextView textView;

        public ListTeamViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.list_team_image);
            textView = (TextView) itemView.findViewById(R.id.list_team_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
                mListener.onClick(getLayoutPosition());
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
        //holder.imageView.setImageResource(R.drawable.team);
        holder.textView.setText(equipe.getName());

        Picasso.with(context)
                .load(equipe.getPhoto())
                .placeholder(R.drawable.team)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return maListe.size();
    }

    public void addTeams (Equipe equipe) {
        maListe.add(equipe);
        notifyDataSetChanged();
    }

    public Equipe getSelectedTeam(int position) {
        return maListe.get(position);
    }

    public interface ListTeamAdapterListener {

        void onClick(int position);
    }
}
