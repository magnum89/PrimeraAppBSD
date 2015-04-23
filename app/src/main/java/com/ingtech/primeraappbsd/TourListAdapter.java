package com.ingtech.primeraappbsd;

import java.text.NumberFormat;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ingtech.primeraappbsd.buscadortour.modelo.Tour;

public class TourListAdapter extends ArrayAdapter<Tour> {
    Context context;
    List<Tour> tours;

    public TourListAdapter(Context context, List<Tour> tours) {
        super(context,android.R.id.content, tours);//el copila con el error
        this.context = context;
        this.tours = tours;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup padre) {
        LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = vi.inflate(R.layout.listitem_tour, null);

        Tour tour = tours.get(position);

        TextView tv = (TextView) view.findViewById(R.id.tituloText);
        tv.setText(tour.getTitulo());

        tv = (TextView) view.findViewById(R.id.precioText);
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        tv.setText(nf.format(tour.getPrecio()));

        ImageView iv = (ImageView) view.findViewById(R.id.imageView1);
        int imageResource = context.getResources().getIdentifier(
                tour.getImagen(), "drawable", context.getPackageName());
        if (imageResource != 0) {
            iv.setImageResource(imageResource);
        }

        return view;
    }

}