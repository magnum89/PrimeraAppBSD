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
    public static final String[] todasColumnas = {

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


    public List<Tour> encontrarTodos(){


        Cursor cursor = baseDatos.query(PrimeraOpenHelper.TABLA_TOURS,todasColumnas,null,null,null,null,null);

        Log.i(LOGTAG," Contiene: " + cursor.getCount() + " Filas ");

        List<Tour> tours = cursorALista(cursor);

        return tours;
    }

    public List<Tour> encontrarFiltrados(String filtrado, String ordenado){


        Cursor cursor = baseDatos.query(PrimeraOpenHelper.TABLA_TOURS,todasColumnas,filtrado,null,null,null,ordenado);

        Log.i(LOGTAG," Contiene: " + cursor.getCount() + " Filas ");

        List<Tour> tours = cursorALista(cursor);

        return tours;
    }

    private List<Tour> cursorALista(Cursor cursor) {//porque voy a usar en dos metodos
        List<Tour> tours = new ArrayList<Tour>();
        while(cursor.moveToNext())
        {
            Tour tour = new Tour();

            tour.setId(cursor.getLong(cursor.getColumnIndex(PrimeraOpenHelper.COLUMNA_ID)));
            tour.setTitulo(cursor.getString(cursor.getColumnIndex(PrimeraOpenHelper.COLUMNA_TITULO)));
            tour.setDescripcion(cursor.getString(cursor.getColumnIndex(PrimeraOpenHelper.COLUMNA_DESC)));
            tour.setPrecio(cursor.getInt(cursor.getColumnIndex(PrimeraOpenHelper.COLUMNA_PRECIO)));
            tour.setImagen(cursor.getString(cursor.getColumnIndex(PrimeraOpenHelper.COLUMNA_IMAGEN)));


            tours.add(tour);

        }
        return tours;
    }

    //añadir filas a la nueva tabla

    public boolean añadirMisTours(Tour tour) {
        ContentValues values = new ContentValues();
        values.put(PrimeraOpenHelper.COLUMNA_ID, tour.getId());
        long resultado = baseDatos.insert(PrimeraOpenHelper.TABLA_MISTOURS, null, values);
        return (resultado != -1);
    }

    public boolean removerMisTours(Tour tour){

        String where = PrimeraOpenHelper.COLUMNA_ID + "=" + tour.getId();
        int resultado = baseDatos.delete(PrimeraOpenHelper.TABLA_MISTOURS, where, null);
        return (resultado==1);//borrara es un lista sino me dara cero
    }

    public List<Tour> encontrarMisTours() {

        String query = "SELECT tours.* FROM " +
                "tours JOIN mistours ON " +
                "tours.tourId = mistours.tourId";
        Cursor cursor = baseDatos.rawQuery(query, null);

        Log.i(LOGTAG, "Retornado  " + cursor.getCount() + " filas");

        List<Tour> tours = cursorALista(cursor);
        return tours;
    }

}