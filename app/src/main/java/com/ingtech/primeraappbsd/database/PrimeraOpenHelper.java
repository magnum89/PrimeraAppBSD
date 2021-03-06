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
    private static final int DATABASE_VERSION = 5;

    public static final String TABLA_TOURS= "tours";
    public static final String COLUMNA_ID = "tourId";
    public static final String COLUMNA_TITULO = "titulo";
    public static final String COLUMNA_DESC = "descripcion";
    public static final String COLUMNA_PRECIO = "precio";
    public static final String COLUMNA_IMAGEN = "imagen";

    private static final String CREAR_TABLA =//"CREATE TABLE " uno de los errores mas frecuentes
            "CREATE TABLE " + TABLA_TOURS + " (" +
                    COLUMNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMNA_TITULO + " TEXT, " +
                    COLUMNA_DESC + " TEXT, " +
                    COLUMNA_IMAGEN + " TEXT, " +
                    COLUMNA_PRECIO + " NUMERIC " +
                    ")";

    public static final String TABLA_MISTOURS = "mistours";
    private static final String CREAR_TABLA_MISTOURS =
            "CREATE TABLE " + TABLA_MISTOURS + " (" +
                    COLUMNA_ID + " INTEGER PRIMARY KEY)";//agregando id manualmente



    public PrimeraOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

            Log.i(LOGTAG, "antes de crear");
            db.execSQL(CREAR_TABLA);
            db.execSQL(CREAR_TABLA_MISTOURS);
            Log.i(LOGTAG, "La tabla 2 se creo");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(LOGTAG,"dentro de onUpgrade");
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_TOURS);//cosas q hay q revisar bien "DROP TABLE IF EXISTS "
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_MISTOURS);
        Log.i(LOGTAG,"despues del 2 drop");
        onCreate(db);
        Log.i(LOGTAG,"borrado");

    }
}
