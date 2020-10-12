package com.example.notasqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class NotaAdapterRecyclerView extends RecyclerView.Adapter<NotaAdapterRecyclerView.MyViewHolder> {
    Context mContext;
    int mResource;
    ArrayList<Nota> mDataset = null;

    public NotaAdapterRecyclerView(Context mContext, int mResource, ArrayList<Nota> mDataset) {
        this.mContext = mContext;
        this.mResource = mResource;
        this.mDataset = mDataset;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Criando  propriedade LayoutInflater
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View convertView = layoutInflater.inflate(mResource,parent,false);

        MyViewHolder myViewHolder = new MyViewHolder(convertView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Nota nota = mDataset.get(position);
        // Populando item da view
        holder.textViewId.setText(nota.getId());
        holder.textViewBody.setText(nota.getBody());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textViewId;
        TextView textViewBody;
        public MyViewHolder(@NonNull View convertView) {
            super(convertView);
            textViewId = convertView.findViewById(R.id.listTextViewId);
            textViewBody = convertView.findViewById(R.id.listTextViewBody);
        }
    }
}
