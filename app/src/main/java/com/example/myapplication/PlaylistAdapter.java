package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.ViewHolder> {

    List<String> links;

    public PlaylistAdapter(List<String> links) {
        this.links = links;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView linkText;

        public ViewHolder(View itemView) {
            super(itemView);
            linkText = itemView.findViewById(R.id.linkText);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_link, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.linkText.setText(links.get(position));
    }

    @Override
    public int getItemCount() {
        return links.size();
    }
}

