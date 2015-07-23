// Clase del Enemigo (Ordenador) 
// Versión del 22/7/2015

package org.tomillo.appfinal;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class Enemigo implements Parcelable {

	// ===========
	// 	Constantes
	// ===========
	
	final static int NUMERO_ESCUDOS_EXITO = 9;

	// CREATOR
	public static final Parcelable.Creator<Enemigo> CREATOR = new Parcelable.Creator<Enemigo>() {
		@Override
		public Enemigo createFromParcel(Parcel source) {
			return new Enemigo(source);
		}

		@Override
		public Enemigo[] newArray(int size) {
			return new Enemigo[size];
		}
	};

	// ==========
	// 	Atributos
	// ==========

	private int victorias_actuales;
	private int nivelVida;
	boolean bVivo = true;

	// ==============
	// 	Constructores
	// ==============

	private Enemigo(Parcel source) {
		this.victorias_actuales = source.readInt();
		Log.i("enemigo","leido VictoriasActuales:" + Integer.toString(victorias_actuales));
		this.nivelVida = source.readInt();
		Log.i("enemigo","leido NivelVida : " + Integer.toString(nivelVida));		
		this.bVivo = source.readByte() == 1 ? true : false;
		if (this.bVivo)
			Log.i("enemigo","leido bVivo : TRUE");
		else
			Log.i("enemigo","leido bVivo : FALSE");

	}

	public Enemigo() {
		this.victorias_actuales = 0;
		this.nivelVida = 100;
		this.bVivo = true;
	}

	// ==================
	// 	Getters y Setters
	// ==================

	public int getNivelVida() {
		return nivelVida;
	}

	public void setNivelVida(int nivelVida) {

		if (this.bVivo) {
			if (this.nivelVida <= 0) {
				this.nivelVida = 0;
				this.bVivo = false;
			} else
				this.nivelVida = nivelVida;
		}
	}

	public int getVictorias_actuales() {
		return victorias_actuales;
	}

	public void setVictorias_actuales(int victorias_actuales) {
		this.victorias_actuales = victorias_actuales;
		if (this.victorias_actuales>NUMERO_ESCUDOS_EXITO)
			this.victorias_actuales = NUMERO_ESCUDOS_EXITO;
	}

	// ===================================
	// 	Acciones realizadas con el objeto
	// ===================================

	// Post : Aumenta la vida del enemigo hasta un máximo de 100
	// si esta vivo.
	// Si no esta vivo no hace nada
	public void AumentarVida(int valor) {
		if (this.bVivo) {
			int aux = this.nivelVida;
			aux = aux + valor;
			if (aux > 100)
				aux = 100;
			this.nivelVida = aux;
		}
	}

	// Pre : Un valor entre 1 y 100
	// Se disminuye la vida siempre que
	// este vivo.
	// Si el resultado de disminuir es menor que 0
	// se pone el flag de vivo a false
	public void DisminuirVida(int valor) {
		if ((valor<1) || (valor>100))
			return;
		
		int aux = this.nivelVida;
		if (this.bVivo) {
			aux = aux - valor;
			if (aux <= 0) {
				aux = 0;
				bVivo = false;
			}
			this.nivelVida = aux;
		}
	}

	// Aumenta una victoria al enemigo
	public void AumentarVictorias() {
		this.victorias_actuales++;
		if (this.victorias_actuales > NUMERO_ESCUDOS_EXITO)
			this.victorias_actuales = NUMERO_ESCUDOS_EXITO;
	}

	// ========================================
	// 	Funciones que controlan en Parcelado
	// ========================================

	@Override
	public int describeContents() {
		return 0;
	}

	// Pasamos el modelo a su Parcel
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// Victorias actuales
		dest.writeInt(this.victorias_actuales);
		Log.i("enemigo","Escrito VictoriasActuales : " + Integer.toString(victorias_actuales));
		// Nivel de vida
		dest.writeInt(this.nivelVida);
		Log.i("enemigo","Escrito NivelVida : " + Integer.toString(nivelVida));		
		// Si esta vivo en byte
		dest.writeByte((byte) (this.bVivo == true ? 1 : 0));
		if (this.bVivo==true)
			Log.i("enemigo","Escrito bVivo TRUE");
		else
			Log.i("enemigo","Escrito bVivo FALSE");

	}
}
