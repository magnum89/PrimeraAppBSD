package com.ingtech.primeraappbsd.buscadortour.modelo;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.ingtech.primeraappbsd.MainActivity;

import java.text.NumberFormat;

public class Tour implements Parcelable {//implementar la interfaz parcelable
	private long id;
	private String titulo;
	private String descripcion;
	private double precio;
	private String imagen;
	
	public long getId() {//por el id long
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo2) {
		this.titulo= titulo2;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion= descripcion;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio= precio;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	@Override
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		return titulo + "\n(" + nf.format(precio) + ")";
	}


    public Tour() {//constructor predeterminado de la clase
    }

    public Tour(Parcel in) {//sera automatica cada vez q pase un objeto de tour en una actividad
        Log.i(MainActivity.LOGTAG, "Parcel constructor");

        id = in.readLong();//se pasan a la propiedad privada de la clase
        titulo = in.readString();
        descripcion = in.readString();
        precio = in.readDouble();
        imagen = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {//el orden que lo recibo debe ser igual al del enviar
        Log.i(MainActivity.LOGTAG, "writeToParcel");

        dest.writeLong(id);
        dest.writeString(titulo);
        dest.writeString(descripcion);
        dest.writeDouble(precio);
        dest.writeString(imagen);
    }

    public static final Parcelable.Creator<Tour> CREATOR =//cada vez  se implemente la interfaz debe tener
            new Parcelable.Creator<Tour>() {

                @Override
                public Tour createFromParcel(Parcel source) {
                    Log.i(MainActivity.LOGTAG, "createFromParcel");
                    return new Tour(source);
                }

                @Override
                public Tour[] newArray(int size) {
                    Log.i(MainActivity.LOGTAG, "newArray");
                    return new Tour[size];
                }

            };
}
