package com.ingtech.primeraappbsd;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import Utilidades.AyudaIU;


public class MainActivity extends ActionBarActivity {

    public static final String LOGTAG="TourSc";//clave
    public static final String NOMBREUSUARIO="nombreusuario";//mayus hace referencia a la preferencia compartida en java, y la minus es la preferencia
    //en el disco

    //llamamos a la clase de sharedpreferences
    private SharedPreferences opciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        opciones = getPreferences(MODE_PRIVATE);//getshareprefences es para usarla en toda la aplicacion
        //getprefences es solo aca en la actividad


    }

    public void AceptarPref(View v) {
        Log.i(LOGTAG, "Boton de aceptar clicado");

        SharedPreferences.Editor editor = opciones.edit();//editor de los objetos de las preferencias compartidas
        //hara es retornar una referencia al editor para que trabaje con la actividad de estre momento
        String ValorString = AyudaIU.getText(this,R.id.editText01);
        editor.putString(NOMBREUSUARIO,ValorString);//valor tengo los estring q ingrese el usuario
        editor.commit();//todos los valores seran guardados en memoria

        //mostrar los cambios
        AyudaIU.displayText(this,R.id.textView01,"Preferencias Guardadas");//hace referencia a lo que queremos mostrar

    }


    public void RefrescarPantalla(View v) {
        Log.i(LOGTAG, "Boton de mostrar clicado");

        String ValorString = opciones.getString(NOMBREUSUARIO,"No Encontrado");
        AyudaIU.displayText(this,R.id.textView01,ValorString);//mostrar el texto q ha sido guardado

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
