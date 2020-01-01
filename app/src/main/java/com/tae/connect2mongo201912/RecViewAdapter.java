package com.tae.connect2mongo201912;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

public class RecViewAdapter extends RecyclerView.Adapter<RecViewAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<String> motos;

    RecViewAdapter(Context context, List<String> motos) {
        this.motos = motos;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public RecViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.motocycle, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecViewAdapter.ViewHolder holder, int position) {
        holder.motorcycle.setText(motos.get(position));
    }

    @Override
    public int getItemCount() {
        return motos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView motorcycle;

        ViewHolder(View view){
            super(view);
            motorcycle = (TextView) view.findViewById(R.id.motoedit);
        }
    }
}
