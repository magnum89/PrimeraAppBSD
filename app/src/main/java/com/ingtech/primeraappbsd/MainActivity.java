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

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.IOException;

import Utilidades.AyudaIU;


public class MainActivity extends ActionBarActivity {

    public static final String LOGTAG="TourSc";//clave
    public static final String NOMBREUSUARIO="pref_nombreusuario";
    public static final String VERIMAGENES="pref_verimagenes";

    //llamamos a la clase de sharedpreferences
    private SharedPreferences opciones;

    private SharedPreferences.OnSharedPreferenceChangeListener oyente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        opciones = PreferenceManager.getDefaultSharedPreferences(this);//me estara retornando un objeto de mis preferencias a mi actividad

        oyente = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

                MainActivity.this.RefrescarPantalla(null);

            }
        };

        opciones.registerOnSharedPreferenceChangeListener(oyente);


        File archivo = getFilesDir();//se refiere a la direccion de los archivos

        String camino = archivo.getAbsolutePath();//retornara en donde tiene mi dispositvo la direccion en donde se guarda los archivos

        //mostrar lo que obtuve en el camino
        AyudaIU.displayText(this,R.id.textView1, camino);
    }

    public void AceptarPref(View v) {
        Log.i(LOGTAG, "Boton de aceptar clicado");

        Intent intento = new Intent(this, OpcionesDeActividad.class);
        startActivity(intento);//ejecutar la actividad

    }


    public void RefrescarPantalla(View v) {
        Log.i(LOGTAG, "Boton de mostrar clicado");

        //String ValorString = opciones.getString(NOMBREUSUARIO,"No Encontrado");
        //AyudaIU.displayText(this,R.id.textView01,ValorString);//mostrar el texto q ha sido guardado
        //AyudaIU.setCBChecked(this,R.id.checkBox01,opciones.getBoolean(VERIMAGENES,false));

    }

    public void CrearArchivo (View v) throws IOException {//genera exepciones
        //primero acceso a lo que el usuario esta ingresando por teclado
        String texto = AyudaIU.getText(this,R.id.editText1);
        //grabarlas internamente en el dispositivo
        FileOutputStream FoS = openFileOutput("Miarchivo.txt",MODE_PRIVATE);//nombre - modo
        //crear el archivo enviamos un buffer
        FoS.write(texto.getBytes());
        //cerrar FileOutp
        FoS.close();
        //mostrar que el archivo fue creado correctamente
        AyudaIU.displayText(this,R.id.textView1,"El archivo fue creado correctamente");

    }

    public void LeerArchivo (View v) throws IOException {
        //leer el archivo
        FileInputStream FiS = openFileInput("Miarchivo.txt");
        //crear el buffer
        BufferedInputStream EntradaBuffer = new BufferedInputStream(FiS);
        //String buffer
        StringBuffer SB = new StringBuffer();//lee un caracter a la vez

        while (EntradaBuffer.available()!=0){//condicional que este mirando el buffer q si sea diferente de cero

            char Caracter = (char)EntradaBuffer.read();//retorna un caracter a la vez y esta grabando en Caracter
            //para mostrar toda los caracteres a la vez
            SB.append(Caracter);

        }
        //actualizar al usuario y mostrar lo que esta guardado
        AyudaIU.displayText(this,R.id.textView1,SB.toString());//de esta forma le muestro al usuario lo grabado
        //cerrar entradabuffer
        EntradaBuffer.close();
        FiS.close();

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

