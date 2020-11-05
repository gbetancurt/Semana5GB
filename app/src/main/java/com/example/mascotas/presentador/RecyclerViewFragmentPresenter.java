package com.example.mascotas.presentador;

import android.content.Context;

import com.example.mascotas.DatosMascota;
import com.example.mascotas.db.ConstructorMascotas;
import com.example.mascotas.fragments.IRecyclerViewFragment;

import java.util.ArrayList;

public class RecyclerViewFragmentPresenter implements  IRecyclerViewFragmentPresenter{

    private ConstructorMascotas constructorMascotas;
    private IRecyclerViewFragment iRecyclerViewFragment;
    private Context context;
    private ArrayList<DatosMascota> mascotas;

    public RecyclerViewFragmentPresenter(IRecyclerViewFragment iRecyclerViewFragment, Context context){
        this.iRecyclerViewFragment = iRecyclerViewFragment;
        this.context = context;
        obtenerMascotasBaseDatos();
    }

    @Override
    public void obtenerMascotasBaseDatos() {
        constructorMascotas = new ConstructorMascotas(context);
        mascotas = constructorMascotas.obtenerDatos();
        mostrarMascotasRV();

    }
    @Override
    public void mostrarMascotasRV() {
        iRecyclerViewFragment.inicializarAdaptadorRV(iRecyclerViewFragment.crearAdaptador(mascotas));
        iRecyclerViewFragment.generarLinearLayoutVertical();
    }

}
