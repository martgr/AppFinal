package org.tomillo.appfinal;

import android.os.Parcel;
import android.os.Parcelable;



public class Enemigo implements Parcelable {

	// CREATOR
	public static final Parcelable.Creator<Enemigo> CREATOR = new Parcelable.Creator<Enemigo>() {
	    @Override
	    public Enemigo createFromParcel(Parcel source) {
	        return new Enemigo(source);
	    }
	 
	    @Override
	    public Enemigo[] newArray(int size) {
	    	return new Enemigo[size];
	}};
	

	
	
	private int victorias_actuales;
	private int nivelVida= 100;
	boolean bVivo = true;

	// Constructor
	private Enemigo (Parcel source) {
		this.victorias_actuales = source.readInt();
		this.nivelVida = source.readInt();
		
		byte byteauxVivo;
		byteauxVivo = source.readByte();
		if (byteauxVivo==1) {
			this.bVivo=true;
		} else {
			this.bVivo=false;
		}
			
		
		
	}
	
	

	public int getNivelVida() {
		return nivelVida;
	}

	public void setNivelVida(int nivelVida) {
		this.nivelVida = nivelVida;
	}

	public int getVictorias_actuales() {
		return victorias_actuales;
	}

	public void setVictorias_actuales(int victorias_actuales) {
		this.victorias_actuales = victorias_actuales;
	}
	
	public Enemigo() {
		this.victorias_actuales=0;
	}
	
	public void AumentarVida (int valor) {
		int aux = this.nivelVida;
		aux = aux + valor;
		if (aux>100)
			aux = 100;
		this.nivelVida = aux;
	}
	
	public void DisminuirVida (int valor) {
		int aux = this.nivelVida;
		aux = aux - valor;
		if (aux<=0) {
			aux=0;
			bVivo=false;
		}
		this.nivelVida = aux;
	}
	
	public void AumentarVictorias () {
		this.victorias_actuales++;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	// Pasamos el modelo a su Parcel
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// Victorias actuales
		dest.writeInt(this.victorias_actuales);
		// Nivel de vida
		dest.writeInt(this.nivelVida);
		// Si esta vivo en byte
		dest.writeByte ((byte) (this.bVivo==true ? 1 : 0));
		
	}
}




