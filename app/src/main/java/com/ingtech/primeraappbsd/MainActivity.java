package com.ingtech.primeraappbsd;


import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import Utilidades.AyudaIU;


public class MainActivity extends ActionBarActivity {

    public static final String LOGTAG="TourSc";//clave
    public static final String NOMBREUSUARIO="pref_nombreusuario";
    public static final String VERIMAGENES="pref_verimagenes";

    //llamamos a la clase de sharedpreferences
    private SharedPreferences opciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        opciones = PreferenceManager.getDefaultSharedPreferences(this);//me estara retornando un objeto de mis preferencias a mi actividad


    }

    public void AceptarPref(View v) {
        Log.i(LOGTAG, "Boton de aceptar clicado");

        Intent intento = new Intent(this, OpcionesDeActividad.class);
        startActivity(intento);//ejecutar la actividad

    }


    public void RefrescarPantalla(View v) {
        Log.i(LOGTAG, "Boton de mostrar clicado");

        String ValorString = opciones.getString(NOMBREUSUARIO,"No Encontrado");
        AyudaIU.displayText(this,R.id.textView01,ValorString);//mostrar el texto q ha sido guardado
        AyudaIU.setCBChecked(this,R.id.checkBox01,opciones.getBoolean(VERIMAGENES,false));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
