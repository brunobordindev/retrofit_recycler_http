package br.com.retrofithttprecyclerview.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import br.com.retrofithttprecyclerview.R;
import br.com.retrofithttprecyclerview.model.Foto;

public class AdapterFoto extends RecyclerView.Adapter<AdapterFoto.MyViewHolder> {

    private List<Foto> listaFotos;
    private Context context;

    public AdapterFoto(List<Foto> listaFotos, Context context) {
        this.listaFotos = listaFotos;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_foto, parent, false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            Foto foto = listaFotos.get(position);

        Uri uriFoto = Uri.parse(foto.getUrl());
        Glide.with(context).load(uriFoto).into(holder.foto);

        holder.title.setText(foto.getTitle());
    }

    @Override
    public int getItemCount() {
        return listaFotos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

         ImageView foto;
         TextView title;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            foto = itemView.findViewById(R.id.image_adapter);
            title = itemView.findViewById(R.id.title_adapter);
        }
    }
}
