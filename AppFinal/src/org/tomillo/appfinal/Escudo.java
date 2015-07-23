// Clase de un escudo en concreto

package org.tomillo.appfinal;

import android.os.Parcel;
import android.os.Parcelable;


public class Escudo implements Parcelable {
	
	// ===========
	// CONSTANTES
	// ===========
	
	final int NRO_ESCUDOS_PUEBLO = 6;
	final int NRO_ESCUDOS_RESTO = 12;

	// ==========
	// Atributos
	// ==========
	
	// Valor que indica el nro. de escudo cargado
	private int valor_inicial;
	// Valor del escudo tratado por código
	private int valor;
	// Tipo del escudo
	private GrupoEscudo tipoEscudo;
	// Ruta del escudo .png
	private String ruta;
	

	public static final Parcelable.Creator<Escudo> CREATOR = new Parcelable.Creator<Escudo>() {

		@Override
		public Escudo createFromParcel(Parcel source) {
			return new Escudo(source);
		}

		@Override
		public Escudo[] newArray(int size) {
			return new Escudo[size];
		}
		
	};
	
	
	// ================
	// Tipos de escudos
	// 
	// El nombre del recurso es el string del Grupo + nro. + ".png"
	//
	// ================
	
	public enum GrupoEscudo  {torre,rey,nobleza,pueblo,puntuacion,defensa,
		ataque,cruz,natural,artificial}

	// =================
	// Constructores
	// =================

	public Escudo (int valor_inicial,GrupoEscudo tipo) {
		
		this.valor_inicial=valor_inicial;
		this.tipoEscudo = tipo;
		this.valor = valor_inicial;
		
		// Aquí hay que hallar la ruta del .png
		
		//ruta= tipo.name()+valor_inicial+".png";
		ruta= tipo.name()+valor_inicial;
	}
	
	
	private Escudo (Parcel source) {
		this.valor_inicial = source.readInt();
		this.valor = source.readInt();
		int aux = source.readInt();
		switch (aux) {
			case 0:
				this.tipoEscudo = GrupoEscudo.torre;
				break;
			case 1:
				this.tipoEscudo = GrupoEscudo.rey;
				break;
			case 2:
				this.tipoEscudo = GrupoEscudo.nobleza;
				break;
			case 3:
				this.tipoEscudo = GrupoEscudo.pueblo;
				break;
			case 4:
				this.tipoEscudo = GrupoEscudo.puntuacion;
				break;
			case 5:
				this.tipoEscudo = GrupoEscudo.defensa;
				break;
			case 6:
				this.tipoEscudo = GrupoEscudo.ataque;
				break;
			case 7:
				this.tipoEscudo = GrupoEscudo.cruz;
				break;
			case 8:
				this.tipoEscudo = GrupoEscudo.natural;
				break;
			case 9:
				this.tipoEscudo = GrupoEscudo.artificial;
				break;
		}
		this.ruta = source.readString();
	}
	
	// ==================
	// Getters y Setter
	// ==================
	
	public int getValor_inicial() {
		return valor_inicial;
	}

	public void setValor_inicial(int valor_inicial) {
		this.valor_inicial = valor_inicial;
	}

	public GrupoEscudo getTipoEscudo() {
		return tipoEscudo;
	}

	public void setTipoEscudo(GrupoEscudo tipoEscudo) {
		this.tipoEscudo = tipoEscudo;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}


	//========================================
	// Funciones que controlan en Parcelado
	//=======================================
	

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {

		dest.writeInt(this.valor_inicial);
		dest.writeInt(this.valor);
		// Empieza en cero
		dest.writeInt(this.tipoEscudo.ordinal());
		dest.writeString(this.ruta);
		
	}

}
