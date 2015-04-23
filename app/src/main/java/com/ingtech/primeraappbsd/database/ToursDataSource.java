package com.ingtech.primeraappbsd.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.ingtech.primeraappbsd.buscadortour.modelo.Tour;

import java.util.ArrayList;
import java.util.List;

public class ToursDataSource {

    //traemos del main
    public static final String LOGTAG="TourSc";//clave
    SQLiteOpenHelper ayudaDb;
    SQLiteDatabase baseDatos;

    //crear un vector de string
    public static final String[] todasColumnas = {//me servira para definir todo los nombres de todas las columnas que se encuentran en la tabla

        PrimeraOpenHelper.COLUMNA_ID,
        PrimeraOpenHelper.COLUMNA_TITULO,
        PrimeraOpenHelper.COLUMNA_DESC,
        PrimeraOpenHelper.COLUMNA_PRECIO,
        PrimeraOpenHelper.COLUMNA_IMAGEN


    };


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

    //crear metodo
    public List<Tour> encontrarTodos(){//buscar todas nuestras filas y todas las columnas en la base de datos
    //retornara una lista con el tipo de data siendo tour
        List<Tour> tours = new ArrayList<Tour>();

        Cursor cursor = baseDatos.query(PrimeraOpenHelper.TABLA_TOURS,todasColumnas,null,null,null,null,null);//objeto cursor con el cual
        //se haran todas las peticiones a la base de datos
        Log.i(LOGTAG," Contiene: " + cursor.getCount() + " Filas ");

        while(cursor.moveToNext())//para hacer todas las peticiones a cada una de las filas y almacenar todas las columnas
        {//retornara un valor verdadero siempre y cuando el cursor tenga un valor por delante

            Tour tour = new Tour();
            //pedir los valores de cada una de las columnas respectivamente
            tour.setId(cursor.getLong(cursor.getColumnIndex(PrimeraOpenHelper.COLUMNA_ID)));
            tour.setTitulo(cursor.getString(cursor.getColumnIndex(PrimeraOpenHelper.COLUMNA_TITULO)));
            tour.setDescripcion(cursor.getString(cursor.getColumnIndex(PrimeraOpenHelper.COLUMNA_DESC)));
            tour.setPrecio(cursor.getInt(cursor.getColumnIndex(PrimeraOpenHelper.COLUMNA_PRECIO)));
            tour.setImagen(cursor.getString(cursor.getColumnIndex(PrimeraOpenHelper.COLUMNA_IMAGEN)));

            //agregar esos valores de tour en el objeto tours
            tours.add(tour);

        }

        return tours;
    }

}