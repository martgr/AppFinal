// Clase del Enemigo (Ordenador) 

package org.tomillo.appfinal;

import java.io.Serializable;

import android.os.Parcel;
import android.os.Parcelable;

public class Enemigo implements  Serializable  {

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
	// Atributos
	// ==========

	private int victorias_actuales;
	private int nivelVida = 100;
	private boolean bVivo = true;

	// ==============
	// Constructores
	// ==============

	public boolean isbVivo() {
		return bVivo;
	}

	public void setbVivo(boolean bVivo) {
		this.bVivo = bVivo;
	}

	private Enemigo(Parcel source) {
		this.victorias_actuales = source.readInt();
		this.nivelVida = source.readInt();

		byte byteauxVivo;
		byteauxVivo = source.readByte();
		this.bVivo = source.readByte() == 1 ? true : false;

	}

	public Enemigo() {
		this.victorias_actuales = 0;
	}

	// ==================
	// Getters y Setters
	// ==================

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

	// ===================================
	// Acciones realizadas con el objeto
	// ===================================

	public void AumentarVida(int valor) {
		int aux = this.nivelVida;
		aux = aux + valor;
		if (aux > 100)
			aux = 100;
		this.nivelVida = aux;
	}

	public void DisminuirVida(int valor) {
		int aux = this.nivelVida;
		aux = aux - valor;
		if (aux <= 0) {
			aux = 0;
			bVivo = false;
		}
		this.nivelVida = aux;
	}

	public void AumentarVictorias() {
		this.victorias_actuales++;
	}

	// ========================================
	// Funciones que controlan en Parcelado
	// =======================================

//	@Override
//	public int describeContents() {
//		return 0;
//	}
//
//	// Pasamos el modelo a su Parcel
//	@Override
//	public void writeToParcel(Parcel dest, int flags) {
//		// Victorias actuales
//		dest.writeInt(this.victorias_actuales);
//		// Nivel de vida
//		dest.writeInt(this.nivelVida);
//		// Si esta vivo en byte
//		dest.writeByte((byte) (this.bVivo == true ? 1 : 0));
//
//	}
}
