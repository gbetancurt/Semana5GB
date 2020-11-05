package com.example.mascotas.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mascotas.DatosMascota;
import com.example.mascotas.R;
import com.example.mascotas.adapter.PerfilAdaptador;

import java.util.ArrayList;

public class PerfilFragment extends Fragment {
    ArrayList<DatosMascota> mascotas;
    private RecyclerView ListaMascotas;

    public PerfilFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_perfil, container, false);


        ListaMascotas = (RecyclerView) v.findViewById(R.id.rvMascotas2);
        ListaMascotas.setLayoutManager(new GridLayoutManager(getActivity(),3));

        inicializarListaMascotas();
        inicializarAdaptador();

        return v;
    }
    public void inicializarAdaptador() {
        PerfilAdaptador adaptador = new PerfilAdaptador(mascotas);
        ListaMascotas.setAdapter(adaptador);
    }

    public void inicializarListaMascotas() {
        mascotas = new ArrayList<DatosMascota>();
        mascotas.add(new DatosMascota(R.drawable.perro2, 12));
        mascotas.add(new DatosMascota(R.drawable.perro2, 1));
        mascotas.add(new DatosMascota(R.drawable.perro2, 6));
        mascotas.add(new DatosMascota(R.drawable.perro2, 4));
        mascotas.add(new DatosMascota(R.drawable.perro2, 7));
        mascotas.add(new DatosMascota(R.drawable.perro2, 10));
        mascotas.add(new DatosMascota(R.drawable.perro2, 8));
        mascotas.add(new DatosMascota(R.drawable.perro2, 25));
        mascotas.add(new DatosMascota(R.drawable.perro2, 11));

    }

}