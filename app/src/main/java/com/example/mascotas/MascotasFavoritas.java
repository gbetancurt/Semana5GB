package com.example.mascotas;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mascotas.adapter.MascotaAdaptador;
import com.example.mascotas.fragments.IRecyclerViewFragment;
import com.example.mascotas.presentador.IRecyclerViewFragmentPresenter;
import com.example.mascotas.presentador.RecyclerViewFavoritoPresenter;

import java.util.ArrayList;

public class MascotasFavoritas extends AppCompatActivity implements IRecyclerViewFragment {
    private RecyclerView listaMascotas;
    private IRecyclerViewFragmentPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mascotas_favoritas);

        Toolbar actionBar = findViewById(R.id.second_actionbar);
        setSupportActionBar(actionBar);
        setOnClick();

        listaMascotas = (RecyclerView) findViewById(R.id.rvMascotas);
        presenter = new RecyclerViewFavoritoPresenter(this, this);

    }

    private void setOnClick(){
        final ImageButton botonatras = (ImageButton) findViewById(R.id.botonatras);
        botonatras.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void generarLinearLayoutVertical() {
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listaMascotas.setLayoutManager(llm);
    }

    @Override
    public MascotaAdaptador crearAdaptador(ArrayList<DatosMascota> mascotas) {
        return new MascotaAdaptador(mascotas,this);
    }

    @Override
    public void inicializarAdaptadorRV(MascotaAdaptador adaptador) {
        listaMascotas.setAdapter(adaptador);
    }

}