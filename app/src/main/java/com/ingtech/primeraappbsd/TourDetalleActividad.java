package com.ingtech.primeraappbsd;

import java.text.NumberFormat;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import android.widget.TextView;

import com.ingtech.primeraappbsd.buscadortour.modelo.Tour;
import com.ingtech.primeraappbsd.database.ToursDataSource;

public class TourDetalleActividad extends Activity {

    Tour tour;
    ToursDataSource datasource;
    boolean sonMisTours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tour_detalle);

        Bundle bun = getIntent().getExtras();//obtengo toda la informacion q envio desde la actividad principal
        tour = bun.getParcelable(".buscadortour.modelo.Tour");
        sonMisTours = bun.getBoolean("sonMisTours");

        refrescarPantalla();
        datasource = new ToursDataSource(this);
    }

    public boolean onCreateOptionsMenu(Menu menu)//permite agregar botones al menu
    {
        getMenuInflater().inflate(R.menu.tour_detalle, menu);
        // Muestra menu borrar si el elemento viene de mis tours
        menu.findItem(R.id.menu_borrar).setVisible(sonMisTours);

        // Muestra menu add si el elemento no viene de mis tours
        menu.findItem(R.id.menu_add).setVisible(!sonMisTours);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())   {
            case R.id.menu_add:

                if (datasource.añadirMisTours(tour)) {

                    Log.i(MainActivity.LOGTAG,"Se agrego correctamente un tour");

                }else {

                    Log.i(MainActivity.LOGTAG,"Este tour ya fue agregado");
                }
            break;

            case R.id.menu_borrar:
                if (datasource.removerMisTours(tour)) {
                    setResult(-1);//una fila menos
                    finish();//esta cerrada y se regresa a la actividad anterior
                }
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void refrescarPantalla() {


        TextView tv = (TextView) findViewById(R.id.tituloText2);
        tv.setText(tour.getTitulo());

        NumberFormat nf = NumberFormat.getCurrencyInstance();
        tv = (TextView) findViewById(R.id.precioText2);
        tv.setText(nf.format(tour.getPrecio()));

        tv = (TextView) findViewById(R.id.descText);
        tv.setText(tour.getDescripcion());

        ImageView iv = (ImageView) findViewById(R.id.imageView2);
        int recursoImagen = getResources().getIdentifier(
                tour.getImagen(), "drawable", getPackageName());
        if (recursoImagen != 0) {
            iv.setImageResource(recursoImagen);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        datasource.abrir();
    }

    @Override
    protected void onPause() {
        super.onPause();
        datasource.cerrar();
    }
}