// Clase del jugador
// Tiene un atribuo del Heroe del Jugador
// Versión del 22/7/2015

package org.tomillo.appfinal;

import java.io.Serializable;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class Jugador implements  Serializable  {

	// ============
	// 	Constantes
	// ============
	
	// Nro. máximo de actualizaciones de cartas en pantalla Castillo
	public static final int MAXIMOACTUALIZACIONES = 3;
	
	public static final Parcelable.Creator<Jugador> CREATOR = new Parcelable.Creator<Jugador>() {

		@Override
		public Jugador createFromParcel(Parcel source) {
			//
	
			return new Jugador(source);
		}

		@Override
		public Jugador[] newArray(int size) {
			return new Jugador[size];

		}

	};
	
	// ========================
	// 	Atributos
	// ========================

	// Nombre del jugador
	private String nombreJugador="NombreJug";
	// Numero de jugadas
	private int numJugadas;
	// Numero de victorias
	private int victorias_actuales;
	// / Elementos del castillo
	// Cantidad de bankias
	private int nroBankias;
	// Nro de posibles cambios de cartas en castillo
	private int nroActualizacionesCastillo;
	// Puntuacion total del jugador
	private int puntuacion;
	// Rango jugador segun victorias actuales
	private int TituloNobiliario;
	// Torre del Castillo
	private Escudo escudoTorre;
	// Escudo del rey
	private Escudo escudoRey;
	// Escudo de la nobleza
	private Escudo escudoNobleza;
	// Escudo del pueblo
	private Escudo escudoPueblo;
	// Heroe del jugador
	private Heroe heroeJugador;

	// ========================
	// 	Getters y setters
	// ========================
	
	public String getNombreJugador() {
		return nombreJugador;
	}

	public void setNombreJugador(String nombreJugador) {
		this.nombreJugador = nombreJugador;
	}
	
	public Escudo getEscudoRey() {
		return escudoRey;
	}

	public void setEscudoRey(Escudo escudoRey) {
		this.escudoRey = escudoRey;
	}


	public int getNroActualizacionesCastillo() {
		return nroActualizacionesCastillo;
	}

	public void setNroActualizacionesCastillo(int nroActualizacionesCastillo) {
		this.nroActualizacionesCastillo = nroActualizacionesCastillo;
	}

	public int getNroBankias() {
		return nroBankias;
	}

	public void setNroBankias(int nroBankias) {
		this.nroBankias = nroBankias;
	}

	public Escudo getEscudoNobleza() {
		return escudoNobleza;
	}

	public void setEscudoNobleza(Escudo escudoNobleza) {
		this.escudoNobleza = escudoNobleza;
	}

	public Escudo getEscudoPueblo() {
		return escudoPueblo;
	}

	public void setEscudoPueblo(Escudo escudoPueblo) {
		this.escudoPueblo = escudoPueblo;
	}


	public int getTituloNobiliario() {
		return TituloNobiliario;
	}

	public void setTituloNobiliario(int tituloNobiliario) {
		TituloNobiliario = tituloNobiliario;
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	public int getVictorias_actuales() {
		return victorias_actuales;
	}

	public void setVictorias_actuales(int victorias_actuales) {
		this.victorias_actuales = victorias_actuales;

	}

	public Heroe getHeroeJugador() {
		return heroeJugador;
	}

	public void setHeroeJugador(Heroe heroeJugador) {
		this.heroeJugador = heroeJugador;
	}

	public Escudo getEscudoTorre() {
		return escudoTorre;
	}

	public void setEscudoTorre(Escudo escudoTorre) {
		this.escudoTorre = escudoTorre;
	}

	public int getNumJugadas() {
		return numJugadas;
	}

	public void setNumJugadas(int numJugadas) {
		this.numJugadas = numJugadas;
	}
	
	
	// ====================
	// 	Constructores
	// ====================
	
	
	public Jugador() {
		this.numJugadas = 0;
		this.victorias_actuales = 0;
		this.puntuacion = 0;
		this.TituloNobiliario = 0;
		this.nroBankias = 0;
		this.nroActualizacionesCastillo = MAXIMOACTUALIZACIONES;

		// Asignamos el escudo de la torre
		int aux = (int) (Math.random() * 12) + 1;
		escudoTorre = new Escudo(aux, Escudo.GrupoEscudo.torre);
		// Asignamos el escudo del rey;
		aux = (int) (Math.random() * 12) + 1;
		escudoRey = new Escudo(aux, Escudo.GrupoEscudo.rey);
		// Asignamos el escudo de la nobleza
		aux = (int) (Math.random() * 12) + 1;
		escudoNobleza = new Escudo(aux, Escudo.GrupoEscudo.nobleza);
		// Asignamos el escudo del pueblo
		aux = (int) (Math.random() * 6) + 1;
		escudoPueblo = new Escudo(aux, Escudo.GrupoEscudo.pueblo);

		// Creacion jugadores
		heroeJugador = new Heroe();

	}
	
	
	

	//================================
	// 	Resuelve el título del Jugador
	//================================

	// Devuelve la descripocion del rango que tenemos actualmente
	public String DescripcionRango() {

		switch (this.TituloNobiliario) {
		case 0:
			return "Señor Feudal";
		case 1:
			return "Conde";
		case 2:
			return "Marqués";
		case 3:
			return "Duque";
		default :
			return "Señor Feudal";
		}
	}

	//===================================
	//	 Acciones realizadas con el objeto
	//===================================

	// Actualizamos la puntuación actual del jugador
	// en la pantalla de Heroes
	public void ActualizarPuntuacionHeroe() {
		this.puntuacion += this.heroeJugador.getCartasHeroe();
	}
	
	// Actualizamos la puntuación actual del jugador
	// en la pantalla de Castillo
	public void ActualizarPuntuacionCastillo() {
		this.puntuacion+=getCartasJugador();
	}

	// Suma de las cartas de castillo del jugador
	public int getCartasJugador() {
		int suma = this.escudoTorre.getValor() + this.escudoRey.getValor()  + 
				this.escudoNobleza.getValor() + this.escudoRey.getValor();
		return suma;
	}
	
	
	// Se le llama al aumentar victorias
	public void AumentarVictorias() {
		this.victorias_actuales=9;
		//this.victorias_actuales++;
		if (this.victorias_actuales >= 9) {
			this.TituloNobiliario = 3;
		} else if (this.victorias_actuales > 6) {
			this.TituloNobiliario = 2;
		} else if (this.victorias_actuales > 3) {
			this.TituloNobiliario = 1;
		} else {
			this.TituloNobiliario = 0;
		}
	}	

	// Cambia las cartas del Castillo :
	// Torre,Rey,Nobleza,Pueblo.
	public void CambiarCartasCastillo() {

		int aux;
		// Asignamos el escudo del rey;
		aux = (int) (Math.random() * 12) + 1;
		escudoRey = new Escudo(aux, Escudo.GrupoEscudo.rey);
		// Asignamos el escudo de la nobleza
		aux = (int) (Math.random() * 12) + 1;
		escudoNobleza = new Escudo(aux, Escudo.GrupoEscudo.nobleza);
		// Asignamos el escudo del pueblo
		aux = (int) (Math.random() * 6) + 1;
		escudoPueblo = new Escudo(aux, Escudo.GrupoEscudo.pueblo);

	}

	public void AumentarBankias(int nroBankias) {
		this.nroBankias += nroBankias;
	}

	//========================================
	// 	Funciones que controlan en Parcelado
	//=======================================
	
//	@Override
//	public int describeContents() {
//		return 0;
//	}
//
//	@Override
//	public void writeToParcel(Parcel dest, int flags) {
//
//		//dest.writeString(this.nombreJugador);
//		dest.writeString("Nombre");
//		Log.i("jugador","Escrito NombreJugador : " + this.nombreJugador);
//		dest.writeInt(this.numJugadas);
//		Log.i("jugador","Escrito NumJugadas : " + Integer.toString(this.numJugadas));
//		dest.writeInt(this.victorias_actuales);
//		Log.i("jugador","Escrito VictoriasActuales : " + Integer.toString(this.victorias_actuales));		
//		dest.writeInt(this.nroBankias);
//		Log.i("jugador","Escrito NroBankias : " + Integer.toString(this.nroBankias));		
//		dest.writeInt(this.nroActualizacionesCastillo);
//		Log.i("jugador","Escrito NroActualizacionesCastillo : " + Integer.toString(this.nroActualizacionesCastillo));		
//		dest.writeInt(this.puntuacion);
//		Log.i("jugador","Escrito Puntuacion " + Integer.toString(this.puntuacion));		
//		dest.writeInt(this.TituloNobiliario);
//		Log.i("jugador","Escrito Titulo Nobiliario : " + Integer.toString(this.TituloNobiliario));		
//		dest.writeParcelable(escudoTorre, flags);
//		Log.i("jugador","Escrito escudoTorre");
//		dest.writeParcelable(escudoRey, flags);
//		Log.i("jugador","Escrito escudoRey");		
//		dest.writeParcelable(escudoNobleza, flags);
//		Log.i("jugador","Escrito escudoNobleza");		
//		dest.writeParcelable(escudoPueblo, flags);
//		Log.i("jugador","Escrito escudoPueblo");		
//		dest.writeParcelable(heroeJugador, flags);
//		Log.i("jugador","Escrito HeroeJugador");
//	
//	}
	
	
	
	
	// Constructor con Parcelado
		private Jugador(Parcel source) {
			Log.i("En el metodo de lectura", "Lectura");
			this.nombreJugador = source.readString();
			Log.i ("jugador","Leido nombreJugador : " + this.nombreJugador);
			this.numJugadas = source.readInt();
			Log.i ("jugador","Leido numJugadas : " + Integer.toString(this.numJugadas));
			this.victorias_actuales = source.readInt();
			Log.i ("jugador","Leido VictoriasActuales : " + Integer.toString(this.victorias_actuales));		
			this.nroBankias = source.readInt();
			Log.i ("jugador","Leido numBankias : " + Integer.toString(this.nroBankias));		
			this.nroActualizacionesCastillo = source.readInt();
			Log.i ("jugador","Leido nroActualizacionesCastillo : " + Integer.toString(this.nroActualizacionesCastillo));		
			this.puntuacion = source.readInt();
			Log.i ("jugador","Leido puntuación : " + Integer.toString(this.puntuacion));		
			this.TituloNobiliario = source.readInt();
			Log.i ("jugador","Leido TituloNobiliario : " + Integer.toString(this.TituloNobiliario));		
			this.escudoTorre = source.readParcelable(Escudo.class.getClassLoader());
			Log.i ("jugador","Leido EscudoTorre");		
			this.escudoRey = source.readParcelable(Escudo.class.getClassLoader());
			Log.i ("jugador","Leido EscudoRey");
			this.escudoNobleza = source.readParcelable(Escudo.class.getClassLoader());
			Log.i ("jugador","Leido EscudoNobleza");		
			this.escudoPueblo = source
					.readParcelable(Escudo.class.getClassLoader());
			Log.i("jugador","Leido EscudoPueblo");
			this.heroeJugador = source.readParcelable(Heroe.class.getClassLoader());
			Log.i("jugador","Leido Heroe del Jugador");
			
			

			
		}
	
	
	//========================================================================
	// 	Luchas entre los diferentes tipos de ejercitos : Rey, Nobleza, Pueblo
	//========================================================================

	// La dinámica de lucha es lucha
	// entre reyes, nobleza y pueblo
	// La victoria en rey da 3 puntos
	// La victoria entre nobles da 2 puntos
	// La victoria entre pueblo da 1 punto
	// 
	// Se puede ganar , empatar y perder
	//
	// Se sumara o restará 1 a la torre desde la actividad segun
	// la lógica de victorias y derrotas
	// 
	// Se añade un punto de victoria al ganador sea jugador o enemigo
	//
	// Rey
	// 1 Ganamos
	// 0 Perdemos
	public int LuchaEntreReyes(Escudo escudoReyHeroe,Escudo escudoReyEnemigo) {
		boolean bGanaHeroe,bGanaEnemigo;
		
		float prob1 = ProbabilidadesLucha.ProbabilidadCastillo(escudoReyHeroe.getValor());
		float prob2 = ProbabilidadesLucha.ProbabilidadCastillo(escudoReyEnemigo.getValor());
		
		float valorHeroe = (float) Math.random();
		float valorEnemigo = (float) Math.random();
		
		if (valorHeroe<=prob1)
			bGanaHeroe=true;
		else
			bGanaHeroe=false;
		
		if (valorEnemigo<=prob2)
			bGanaEnemigo=true;
		else
			bGanaEnemigo=false;
		
		if (bGanaHeroe && !bGanaEnemigo)
			return 1;
		
		if (bGanaEnemigo && !bGanaHeroe)
			return 0;
		
		float fresolver = (float) Math.random();
		if (fresolver<=0.5) 
			return 1;
		else
			return 0;

	}

	// Nobleza
	// 1 Ganamos
	// 0 Perdemos
	public int LuchaEntreNobles(Escudo escudoNoblezaHeroe,
			Escudo escudoNoblezaEnemigo) {
		boolean bGanaHeroe,bGanaEnemigo;

		float prob1 = ProbabilidadesLucha.ProbabilidadCastillo(escudoNoblezaHeroe.getValor());
		float prob2 = ProbabilidadesLucha.ProbabilidadCastillo(escudoNoblezaEnemigo.getValor());
		
		float valorHeroe = (float) Math.random();
		float valorEnemigo = (float) Math.random();
		
		if (valorHeroe<=prob1)
			bGanaHeroe=true;
		else
			bGanaHeroe=false;
		
		if (valorEnemigo<=prob2)
			bGanaEnemigo=true;
		else
			bGanaEnemigo=false;
		
		if (bGanaHeroe && !bGanaEnemigo)
			return 1;
		
		if (bGanaEnemigo && !bGanaHeroe)
			return 0;
		
		// Ninguno gano lo resolvemos aleatoriamente
		float fresolver = (float) Math.random();
		if (fresolver<=0.5) 
			return 1;
		else
			return 0;

		
	}

	// Pueblo
	// 1 Ganamos
	// 0 Perdemos
	public int LuchaEntrePueblo(Escudo escudoPuebloHeroe,
			Escudo escudoPuebloEnemigo) {

		boolean bGanaHeroe,bGanaEnemigo;
		
		float prob1 = ProbabilidadesLucha.ProbabilidadCastillo(escudoPuebloHeroe.getValor());
		float prob2 = ProbabilidadesLucha.ProbabilidadCastillo(escudoPuebloEnemigo.getValor());
		
		float valorHeroe = (float) Math.random();
		float valorEnemigo = (float) Math.random();
		
		if (valorHeroe<=prob1)
			bGanaHeroe=true;
		else
			bGanaHeroe=false;
		
		if (valorEnemigo<=prob2)
			bGanaEnemigo=true;
		else
			bGanaEnemigo=false;
		
		if (bGanaHeroe && !bGanaEnemigo)
			return 1;
		
		if (bGanaEnemigo && !bGanaHeroe)
			return 0;
		
		// Ninguno gano lo resolvemos aleatoriamente		
		float fresolver = (float) Math.random();
		if (fresolver<=0.5) 
			return 1;
		else
			return 0;

	}

}
