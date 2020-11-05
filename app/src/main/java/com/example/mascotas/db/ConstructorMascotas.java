package com.example.mascotas.db;

import android.content.ContentValues;
import android.content.Context;

import com.example.mascotas.DatosMascota;
import com.example.mascotas.R;

import java.util.ArrayList;

public class ConstructorMascotas {
    private static final  int LIKE = 1;
    ArrayList<DatosMascota> mascotas;

    private Context context;
    public ConstructorMascotas(Context context){
        this.context = context;
    }

    public ArrayList<DatosMascota> obtenerDatos(){
        BaseDatos db = new BaseDatos(context);
        insertarSieteMascotas(db);
        return db.obtenerTodasLasMascotas();
    }

    public void insertarSieteMascotas(BaseDatos db){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "Rex");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.perro1);
        db.insertarMascotas(contentValues);

        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "Tiki");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.perro2);
        db.insertarMascotas(contentValues);

        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "Toreto");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.perro3);
        db.insertarMascotas(contentValues);

        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "Joaquin");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.perro7);
        db.insertarMascotas(contentValues);

        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "Bethoven");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.perro5);
        db.insertarMascotas(contentValues);

        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "Alighieri");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.perro6);
        db.insertarMascotas(contentValues);

        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "Nagasaki");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.perro4);
        db.insertarMascotas(contentValues);
    }

    public void darLikeMascota(DatosMascota mascota){
        BaseDatos db = new BaseDatos(context);
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID_MASCOTA, mascota.getId());
        contentValues.put(ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID_MASCOTA_NUMERO_LIKES, LIKE);
        db.insertarLikesMascotas(contentValues);

    }
    public int obtenerLikesMascota(DatosMascota mascota){
        BaseDatos db = new BaseDatos(context);
        return db.obtenerLikesMascota(mascota);
    }

    public ArrayList<DatosMascota> mascotasFavoritas(){
        BaseDatos db = new BaseDatos(context);
        return db.mascotasFavoritas(5);
    }
}
