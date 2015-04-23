package com.ingtech.primeraappbsd;

import java.text.NumberFormat;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.ingtech.primeraappbsd.buscadortour.modelo.Tour;

public class TourDetalleActividad extends Activity {

    Tour tour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tour_detalle);

        Bundle bun = getIntent().getExtras();//obtengo toda la informacion q envio desde la actividad principal
        tour = bun.getParcelable(".buscadortour.modelo.tour");

        /*tour = new Tour();
        tour.setId(1);
        tour.setTitulo("Tour titulo");
        tour.setDescripcion("Tour setDescripcion");
        tour.setPrecio(999);
        tour.setImagen("map_venezuela");*/

        refrescarPantalla();
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

}