package com.ingtech.primeraappbsd.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.SQLException;

public class PrimeraOpenHelper extends SQLiteOpenHelper
{

    private static final String LOGTAG = "TOURSC";

    private static final String DATABASE_NAME = "tours.db";
    private static final int DATABASE_VERSION = 1;//y aca

    public static final String TABLA_TOURS= "tours";//el error esta aca
    public static final String COLUMNA_ID = "tourId";
    public static final String COLUMNA_TITULO = "titulo";
    public static final String COLUMNA_DESC = "descripcion";
    public static final String COLUMNA_PRECIO = "precio";
    public static final String COLUMNA_IMAGEN = "imagen";

    private static final String CREAR_TABLA =
            "CREAR TABLA" + TABLA_TOURS + " (" +
                    COLUMNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMNA_TITULO + " TEXT, " +
                    COLUMNA_DESC + " TEXT, " +
                    COLUMNA_IMAGEN + " TEXT, " +
                    COLUMNA_PRECIO + " NUMERIC " +
                    ")";

    //String name, SQLiteDatabase.CursorFactory factory, int version

    public PrimeraOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(LOGTAG, "antes del try");
        //try {
            Log.i(LOGTAG, "antes de crear");
            db.execSQL(CREAR_TABLA);
            Log.i(LOGTAG, "La tabla se creo");
        //}catch (Exception e){
         //   e.printStackTrace();
        //}

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(LOGTAG,"dentro de onUpgrade");
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_TOURS);
        Log.i(LOGTAG,"despues de drop");
        onCreate(db);
        Log.i(LOGTAG,"borrado");

    }
}
