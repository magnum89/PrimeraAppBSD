package com.ingtech.primeraappbsd;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;

import com.ingtech.primeraappbsd.buscadortour.modelo.Tour;
import com.ingtech.primeraappbsd.database.PrimeraOpenHelper;
import com.ingtech.primeraappbsd.database.ToursDataSource;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import Utilidades.AyudaIU;


public class MainActivity extends ListActivity {

    public static final String LOGTAG="TourSc";//clave
    public static final String NOMBREUSUARIO="pref_nombreusuario";
    public static final String VERIMAGENES="pref_verimagenes";

    //llamamos a la clase de sharedpreferences
    private SharedPreferences opciones;

    private SharedPreferences.OnSharedPreferenceChangeListener oyente;

    private File archivo;
    private static final String NOMBREARCHIVO = "datadejson";

        //borramos esto para poner
    //SQLiteOpenHelper ayudaDb;
    //SQLiteDatabase baseDatos;
    ToursDataSource DataSource;


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


        File direcexterno = getExternalFilesDir(null);
        String camino = direcexterno.getAbsolutePath();
        AyudaIU.displayText(this,R.id.textView1, camino);

        archivo = new File(direcexterno,NOMBREARCHIVO);//enviar file q sera directorio externo y el nombre del archivo

        //usamos el metodo
        //ToursPullParser parser = new ToursPullParser();
        ToursJDOMParser parser = new ToursJDOMParser();//solo esto agregando la libreria nueva

        List<Tour> tours = parser.parseXML(this);//lista tendra como tipo de data la clase tours tendra como contexto tendra la referencia xml

       //ayudaDb = new PrimeraOpenHelper(this);
       //baseDatos = ayudaDb.getWritableDatabase();

        DataSource = new ToursDataSource(this);
        DataSource.abrir();//aseguramos que esta abierta
        CrearDatos();


        //creamos un array adapter y pasamos como tipo de dato tour
        ArrayAdapter<Tour> adapter = new ArrayAdapter<Tour>(this,android.R.layout.simple_list_item_1,tours);
        setListAdapter(adapter);

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

    public void CrearArchivo (View v) throws IOException, JSONException {

        //crear un array JSON
        JSONArray data = new JSONArray();//inicializado

        JSONObject tour;

        tour = new JSONObject();
        //ingresar data al tour
        tour.put("Tour", "Margarita");//nombre y valor
        tour.put("Precio", 1900);//tenemos el objeto
        //agregar el jsonObjec en el JsonArray
        data.put(tour);

        tour = new JSONObject();
        //ingresar data al tour
        tour.put("Tour", "Roques");//nombre y valor
        tour.put("Precio", 2500);//tenemos el objeto
        //agregar el jsonObjec en el JsonArray
        data.put(tour);

        tour = new JSONObject();
        //ingresar data al tour
        tour.put("Tour", "La Sabana");//nombre y valor
        tour.put("Precio", 1600);//tenemos el objeto
        //agregar el jsonObjec en el JsonArray
        data.put(tour);


        String texto = data.toString();


        FileOutputStream FoS = new FileOutputStream(archivo);//ahora esta creando el archivo de forma externa

        FoS.write(texto.getBytes());

        FoS.close();

        AyudaIU.displayText(this,R.id.textView1,"Archivo Creado:\n " + data.toString());

    }

    public void LeerArchivo (View v) throws IOException, JSONException {

        FileInputStream FiS = new FileInputStream(archivo);//apunta al almacenamiento externo

        BufferedInputStream EntradaBuffer = new BufferedInputStream(FiS);

        StringBuffer SB = new StringBuffer();

        while (EntradaBuffer.available() != 0) {

            char Caracter = (char) EntradaBuffer.read();
            SB.append(Caracter);

        }


        EntradaBuffer.close();
        FiS.close();

        //deserealizado - desordenado

        JSONArray data = new JSONArray(SB.toString());//le pasamos SB
        StringBuffer TourBuffer = new StringBuffer();


        //tomar la informacion y mostrarla al usuario de forma amigable
        for (int i = 0; i < data.length(); i++){

            String tour = data.getJSONObject(i).getString("Tour");//buscando en todos mis objetos y me retorne con referencia tours
            //unir toda la info en un string
            TourBuffer.append(tour + "\n ");

        }

        AyudaIU.displayText(this, R.id.textView1, TourBuffer.toString());//mostrar

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

    //crear un metodo para vereficar si nuestra aplicacion de acceder a los archivos externos
    public boolean ChekiarAlmacenamientoExterno(){
        String estado = Environment.getExternalStorageState();

        //en un condicional si el estado nos permite acceder o no
        if (estado.equals(Environment.MEDIA_MOUNTED)){// si tiene acceso

            return true;

        }else if(estado.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {//si solo es lectura

            //mostraremos al usuario lo que esta sucediendo
            AyudaIU.displayText(this,R.id.textView1,"Almacenamiento Externo es de solo lectura");

        }else{
            //no tenga acceso a la data
            AyudaIU.displayText(this,R.id.textView1,"Almacenamiento Externo no esta disponible");

        }
        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();

        DataSource.abrir();

    }

    @Override
    protected void onPause() {
        super.onPause();

        DataSource.cerrar();
    }

    private void CrearDatos(){

        Tour tour = new Tour();

        tour.setTitulo("Ciudad de Neiva");
        tour.setDescripcion("Tour a la ciudad de Neiva");
        tour.setPrecio(5000);
        tour.setImagen("ciudad_neiva");
        tour = DataSource.crear(tour);
        Log.i(LOGTAG,"El valor del ID ingresado es:" + tour.getId());

        tour.setTitulo("Ciudad de Bogota");
        tour.setDescripcion("Tour a la ciudad de Bogota");
        tour.setPrecio(20000);
        tour.setImagen("ciudad_bogota");
        tour = DataSource.crear(tour);
        Log.i(LOGTAG,"El valor del ID ingresado es:" + tour.getId());

        tour.setTitulo("Ciudad de Pitalito");
        tour.setDescripcion("Tour a la ciudad de Pitalito");
        tour.setPrecio(15000);
        tour.setImagen("ciudad_pitalito");
        tour = DataSource.crear(tour);
        Log.i(LOGTAG,"El valor del ID ingresado es:" + tour.getId());



    }

}

