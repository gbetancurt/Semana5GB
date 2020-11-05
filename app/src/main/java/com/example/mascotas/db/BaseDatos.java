package com.example.mascotas.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.mascotas.DatosMascota;

import java.util.ArrayList;

public class BaseDatos extends SQLiteOpenHelper {
    private Context context;
    public BaseDatos(@Nullable Context context) {
        super(context,ConstantesBaseDatos.DATABASE_NAME, null, ConstantesBaseDatos.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCrearTablaMascota = "CREATE TABLE " + ConstantesBaseDatos.TABLE_MASCOTAS+ "(" +
                                        ConstantesBaseDatos.TABLE_MASCOTAS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                        ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE + " TEXT, " +
                                        ConstantesBaseDatos.TABLE_MASCOTAS_FOTO + " INTEGER" +
                                        ")";
        String queryCrearTablaLikesMascota = "CREATE TABLE " + ConstantesBaseDatos.TABLE_LIKES_MASCOTA + "(" +
                                        ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                        ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID_MASCOTA + " INTEGER, " +
                                        ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID_MASCOTA_NUMERO_LIKES + " INTEGER, " +
                                        "FOREIGN KEY (" + ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID_MASCOTA +  ")" +
                                        "REFERENCES " + ConstantesBaseDatos.TABLE_MASCOTAS + "("+ConstantesBaseDatos.TABLE_MASCOTAS_ID+")" +
                                         ")";

        db.execSQL(queryCrearTablaMascota);
        db.execSQL(queryCrearTablaLikesMascota);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ ConstantesBaseDatos.TABLE_MASCOTAS);
        db.execSQL("DROP TABLE IF EXISTS "+ ConstantesBaseDatos.TABLE_LIKES_MASCOTA);
        onCreate(db);
    }

    public ArrayList<DatosMascota> obtenerTodasLasMascotas(){
        ArrayList<DatosMascota> listamascotas = new ArrayList<>();
        String query = "SELECT * FROM " + ConstantesBaseDatos.TABLE_MASCOTAS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query,null);

        while (registros.moveToNext()){
            DatosMascota mascotaActual = new DatosMascota();
            mascotaActual.setId(registros.getInt(0));
            mascotaActual.setNombre(registros.getString(1));
            mascotaActual.setFoto(registros.getInt(2));
            String queryLikes = "SELECT COUNT("+ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID_MASCOTA_NUMERO_LIKES+") as likes"+
                                " FROM " + ConstantesBaseDatos.TABLE_LIKES_MASCOTA +
                                " WHERE " + ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID_MASCOTA + "=" + mascotaActual.getId();

            Cursor registrosLikes = db.rawQuery(queryLikes, null);
            if (registrosLikes.moveToNext()){
                mascotaActual.setLikes(registrosLikes.getInt(0));
            }else{
                mascotaActual.setLikes(0);
            }
            listamascotas.add(mascotaActual);
        }
        db.close();
        return listamascotas;
    }

    public void insertarMascotas(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_MASCOTAS,null, contentValues);
        db.close();
    }

    public void insertarLikesMascotas(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_LIKES_MASCOTA,null, contentValues);
        db.close();
    }

    public int obtenerLikesMascota(DatosMascota mascota){
        int likes = 0;

        String query = "SELECT COUNT("+ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID_MASCOTA_NUMERO_LIKES + ")" +
                " FROM " + ConstantesBaseDatos.TABLE_LIKES_MASCOTA +
                " WHERE " + ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID_MASCOTA + "=" + mascota.getId();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        if (registros.moveToNext()){
            likes = registros.getInt(0);
        }

        db.close();
        return likes;
    }


    public ArrayList<DatosMascota> mascotasFavoritas(Integer cantidad) {
        String query = "SELECT " + ConstantesBaseDatos.TABLE_MASCOTAS + "." + ConstantesBaseDatos.TABLE_MASCOTAS_ID + "," + ConstantesBaseDatos.TABLE_MASCOTAS_FOTO + "," + ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE + ",count(" + ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID_MASCOTA_NUMERO_LIKES + ")"  +
                        " FROM " + ConstantesBaseDatos.TABLE_MASCOTAS + "," + ConstantesBaseDatos.TABLE_LIKES_MASCOTA +
                        " WHERE " + ConstantesBaseDatos.TABLE_LIKES_MASCOTA + "." + ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID_MASCOTA + "=" + ConstantesBaseDatos.TABLE_MASCOTAS + "." +ConstantesBaseDatos.TABLE_MASCOTAS_ID +
                        " GROUP BY " +  ConstantesBaseDatos.TABLE_MASCOTAS + "." + ConstantesBaseDatos.TABLE_MASCOTAS_ID + "," + ConstantesBaseDatos.TABLE_MASCOTAS_FOTO + "," + ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE  +
                        " ORDER BY " + "count(" + ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID_MASCOTA_NUMERO_LIKES + ") DESC" +
                        " LIMIT " + cantidad.toString();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor registros = db.rawQuery(query, null);
        ArrayList<DatosMascota> lista = new ArrayList<>();
        while (registros.moveToNext()){
            DatosMascota mascotaActual = new DatosMascota();
            mascotaActual.setId(registros.getInt(0));
            mascotaActual.setFoto(registros.getInt(1));
            mascotaActual.setNombre(registros.getString(2));
            mascotaActual.setLikes(registros.getInt(3));
            lista.add(mascotaActual);
        }
        db.close();
        return lista;

    }

}
