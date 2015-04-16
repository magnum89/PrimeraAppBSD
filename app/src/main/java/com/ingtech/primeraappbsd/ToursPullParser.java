package com.ingtech.primeraappbsd;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;


import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.util.Log;

import com.ingtech.primeraappbsd.buscadortour.modelo.Tour;


public class ToursPullParser {

	private static final String LOGTAG = "TOURSC";
	
	private static final String TOUR_ID = "tourId";
	private static final String TOUR_TITULO = "tituloTour";
	private static final String TOUR_DESC = "Descripcion";
	private static final String TOUR_PRECIO = "precio";
	private static final String TOUR_IMAGEN = "imagen";
	
	private Tour TourActual  = null;
	private String EtiquetaActual = null;
	List<Tour> tours = new ArrayList<Tour>();

	public List<Tour> parseXML(Context context) {

		try {
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			XmlPullParser xpp = factory.newPullParser();
			
			InputStream stream  = context.getResources().openRawResource(R.raw.tours);
			xpp.setInput(stream , null);

			int TipoEvento = xpp.getEventType();
			while (TipoEvento != XmlPullParser.END_DOCUMENT) {
				if (TipoEvento== XmlPullParser.START_TAG) {
					manejarEtiquetaActual(xpp.getName());
				} else if (TipoEvento== XmlPullParser.END_TAG) {
					EtiquetaActual = null;
				} else if (TipoEvento == XmlPullParser.TEXT) {
					manejarTexto(xpp.getText());
				}
				TipoEvento = xpp.next();
			}

		} catch (NotFoundException e) {
			Log.d(LOGTAG, e.getMessage());
		} catch (XmlPullParserException e) {
			Log.d(LOGTAG, e.getMessage());
		} catch (IOException e) {
			Log.d(LOGTAG, e.getMessage());
		}

		return tours;
	}

	private void manejarTexto(String texto) {
		String xmlText = texto;
		if (TourActual  != null && EtiquetaActual != null) {
			if (EtiquetaActual.equals(TOUR_ID)) {
				Integer id = Integer.parseInt(xmlText);
				TourActual.setId(id);
			} 
			else if (EtiquetaActual.equals(TOUR_TITULO)) {
				TourActual.setTitulo(xmlText);
			}
			else if (EtiquetaActual.equals(TOUR_DESC)) {
				TourActual.setDescripcion(xmlText);
			}
			else if (EtiquetaActual.equals(TOUR_IMAGEN)) {
				TourActual.setImagen(xmlText);
			}
			else if (EtiquetaActual.equals(TOUR_PRECIO)) {
				double precio = Double.parseDouble(xmlText);
				TourActual.setPrecio(precio );
			}
		}
	}

	private void manejarEtiquetaActual(String nombre) {
		if (nombre.equals("tour")) {
			TourActual  = new Tour();
			tours.add(TourActual);
		}
		else {
			EtiquetaActual = nombre;
		}
	}
}
