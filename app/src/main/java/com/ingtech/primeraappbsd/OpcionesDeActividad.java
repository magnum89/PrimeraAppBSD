package com.ingtech.primeraappbsd;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by sena on 15/04/2015.
 */
public class OpcionesDeActividad extends PreferenceActivity {

    @SuppressWarnings("deprecation")//suprimir este error
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);//el error era por no poner esto primero q esto
        //llamamos al metodo
        addPreferencesFromResource(R.xml.opciones);


    }
}