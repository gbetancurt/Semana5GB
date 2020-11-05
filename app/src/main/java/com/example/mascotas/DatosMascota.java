package com.example.mascotas;

public class DatosMascota {
    private int id;
    private String nombre;
    private int foto;
    private int likes = 0;

    public DatosMascota(String nombre, int foto) {
        this.nombre = nombre;
        this.foto = foto;
    }

    public DatosMascota(int foto, int likes ) {
        this.foto = foto;
        this.likes = likes;
    }

    public DatosMascota() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

}
