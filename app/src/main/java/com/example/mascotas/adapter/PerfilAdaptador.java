package com.example.mascotas.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mascotas.DatosMascota;
import com.example.mascotas.R;

import java.util.ArrayList;

public class PerfilAdaptador extends RecyclerView.Adapter <PerfilAdaptador.MascotasViewHolder>{
    ArrayList<DatosMascota> mascotas;

    public PerfilAdaptador(ArrayList<DatosMascota> mascotas){
        this.mascotas = mascotas;
    }

    @Override
    public MascotasViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_perfil, parent, false);
        return new MascotasViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MascotasViewHolder mascotasViewHolder, int position) {
        final DatosMascota mascota = mascotas.get(position);
        mascotasViewHolder.imgFoto.setImageResource(mascota.getFoto());
        mascotasViewHolder.tvCont.setText(String.valueOf(mascota.getLikes()));
    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public static class MascotasViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgFoto;
        private TextView tvCont;

        public MascotasViewHolder(View itemView) {
            super(itemView);

            imgFoto = (ImageView) itemView.findViewById(R.id.imgFotoPerfil);
            tvCont = (TextView) itemView.findViewById(R.id.tvContPerfil);
        }
    }

}
