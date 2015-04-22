package com.ingtech.primeraappbsd.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.ingtech.primeraappbsd.buscadortour.modelo.Tour;

public class ToursDataSource {

    //traemos del main
    public static final String LOGTAG="TourSc";//clave
    SQLiteOpenHelper ayudaDb;
    SQLiteDatabase baseDatos;


    public ToursDataSource(Context context){

        ayudaDb = new PrimeraOpenHelper(context);


    }

    public void abrir(){//inicializara o abrira la base de datos

        Log.i(LOGTAG,"Base de datos abierta");
        baseDatos = ayudaDb.getWritableDatabase();

    }

    public void cerrar(){
        Log.i(LOGTAG,"Base de datos cerrada");
        ayudaDb.close();
    }

    public Tour crear (Tour tour){

        ContentValues valores = new ContentValues();

        valores.put(PrimeraOpenHelper.COLUMNA_TITULO, tour.getTitulo());
        valores.put(PrimeraOpenHelper.COLUMNA_DESC, tour.getDescripcion());
        valores.put(PrimeraOpenHelper.COLUMNA_PRECIO, tour.getPrecio());
        valores.put(PrimeraOpenHelper.COLUMNA_IMAGEN, tour.getImagen());

        long idInsertado = baseDatos.insert(PrimeraOpenHelper.TABLA_TOURS,null,valores);
        tour.setId(idInsertado);

        return tour;

    }

}