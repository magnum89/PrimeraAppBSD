package com.ingtech.primeraappbsd.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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
}
