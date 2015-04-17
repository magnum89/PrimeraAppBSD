package com.ingtech.primeraappbsd;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import android.content.Context;
import android.util.Log;

import com.ingtech.primeraappbsd.buscadortour.modelo.Tour;

public class ToursJDOMParser {

	private static final String LOGTAG = "EXPLORECA";

	private static final String TOUR_TAG = "tour";
	private static final String TOUR_ID = "tourId";
	private static final String TOUR_TITULO = "tituloTour";
	private static final String TOUR_DESC = "Descripcion";
	private static final String TOUR_PRECIO = "precio";
	private static final String TOUR_IMAGEN = "imagen";
	

	public List<Tour> parseXML(Context context) {

		InputStream stream = context.getResources().openRawResource(R.raw.tours);
		SAXBuilder builder = new SAXBuilder();
		List<Tour> tours = new ArrayList<Tour>();

		try {

			Document documento = (Document) builder.build(stream);
			Element rootNode = documento.getRootElement();
			List<Element> list = rootNode.getChildren(TOUR_TAG);

			for (Element node : list) {
				Tour tour = new Tour();
				tour.setId(Integer.parseInt(node.getChildText(TOUR_ID)));
				tour.setTitulo(node.getChildText(TOUR_TITULO ));
				tour.setDescripcion(node.getChildText(TOUR_DESC));
				tour.setPrecio(Double.parseDouble(node.getChildText(TOUR_PRECIO )));
				tour.setImagen(node.getChildText(TOUR_IMAGEN));
				tours.add(tour);
			}

		} catch (IOException e) {
			Log.i(LOGTAG, e.getMessage());
		} catch (JDOMException e) {
			Log.i(LOGTAG, e.getMessage());
		}
		return tours;
	}

}