package com.example.mascotas.fragments;

import com.example.mascotas.DatosMascota;
import com.example.mascotas.adapter.MascotaAdaptador;

import java.util.ArrayList;

public interface IRecyclerViewFragment {

    public void generarLinearLayoutVertical();
    public MascotaAdaptador crearAdaptador(ArrayList<DatosMascota> mascotas);
    public void inicializarAdaptadorRV(MascotaAdaptador adaptador);
}
