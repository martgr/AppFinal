// Clase del Heroe.
// Contiene los tres escudos con los que lucha

package org.tomillo.appfinal;

import java.io.Serializable;

import org.tomillo.appfinal.Escudo.GrupoEscudo;

import android.os.Parcel;
import android.os.Parcelable;


public class Heroe implements  Serializable {

	// ============
	// Constantes
	// ============
	
	final static int MINIMO_VIDA= 5;	
	
	
	
	public static final Parcelable.Creator<Heroe> CREATOR = new Parcelable.Creator<Heroe>() {

		@Override
		public Heroe createFromParcel(Parcel source) {
			
			return new Heroe(source);
		}

		@Override
		public Heroe[] newArray(int size) {
			
			return new Heroe[size];
		}
		
	};
	
	// ==========
	// Atributos
	// ==========
	private boolean bVivo = true;
	private int nivelVida;
	private Escudo escudo_Cruz;
	private Escudo escudo_Natural;
	private Escudo escudo_Artificial;
	private int magias_Ataque;
	private int magias_Defensa;

	// ======================
    // 	Variables Auxiliares
	// =======================
	
	// Suma de cartas para aumentar la puntuacion del jugador
	public int getCartasHeroe() {
		int suma = escudo_Cruz.getValor() + escudo_Natural.getValor()  + 
				escudo_Artificial.getValor();
		return suma;
	}

	// ===============
	// Constructores
	// ===============
	
	private Heroe (Parcel source) {
		this.bVivo = (source.readByte() == 1  ? true : false);
		this.nivelVida = source.readInt();
		this.escudo_Cruz = source.readParcelable(Escudo.class.getClassLoader());
		this.escudo_Natural = source.readParcelable(Escudo.class.getClassLoader());
		this.escudo_Artificial = source.readParcelable(Escudo.class.getClassLoader());
		this.magias_Ataque = 3;
		this.magias_Defensa = 3;
	}
	
	public Heroe () {
		bVivo = true;
		nivelVida = 100;
		// Ej�rcito
		int valor = (int) (Math.random()*11+1);
		escudo_Cruz = new Escudo(valor,Escudo.GrupoEscudo.cruz);
		valor = (int) (Math.random()*11+1);
		escudo_Natural = new Escudo(valor,Escudo.GrupoEscudo.natural);
		valor = (int) (Math.random()*11+1);
		escudo_Artificial = new Escudo(valor,Escudo.GrupoEscudo.artificial);
		this.magias_Ataque=3;
		this.magias_Defensa=3;
		
	}

	// ===================
	// Getters y Setters
	// ===================

	public int getNivelVida() {
		return nivelVida;
	}

	public void setNivelVida(int nivelVida) {
		this.nivelVida = nivelVida;
	}
	
	public boolean isbVivo() {
		return bVivo;
	}

	public void setbVivo(boolean bVivo) {
		this.bVivo = bVivo;
	}

	public Escudo getEscudo_Cruz() {
		return escudo_Cruz;
	}

	public void setEscudo_Cruz(Escudo escudo_Cruz) {
		this.escudo_Cruz = escudo_Cruz;
	}

	public Escudo getEscudo_Natural() {
		return escudo_Natural;
	}

	public void setEscudo_Natural(Escudo escudo_Natural) {
		this.escudo_Natural = escudo_Natural;
	}

	public Escudo getEscudo_Artificial() {
		return escudo_Artificial;
	}

	public void setEscudo_Artificial(Escudo escudo_Artificial) {
		this.escudo_Artificial = escudo_Artificial;
	}

	
	public int getMagias_Ataque() {
		return magias_Ataque;
	}

	public void setMagias_Ataque(int magias_Ataque) {
		this.magias_Ataque = magias_Ataque;
	}

	public int getMagias_Defensa() {
		return magias_Defensa;
	}

	public void setMagias_Defensa(int magias_Defensa) {
		this.magias_Defensa = magias_Defensa;
	}
	
	
	// ==========================
	// Acciones sobre el objeto
	// ==========================
	
	// Aumenta vida de heroe hasta un m�xmimo de 100
	public void AumentarVida (int cantidad) {
		
		int aux = this.nivelVida + cantidad;
		if (aux>100) {
			aux =100;
		}
		this.nivelVida = aux;
		
	}
	
	// Disminuye vida. Si es menor de 0 ha muerto el Heroe
	public void DisminuirVida (int cantidad) {
		int aux = this.nivelVida - cantidad;
		if (aux<=0)  {
			this.bVivo = false;
			this.nivelVida = 0;
		}
		else {
			this.nivelVida = aux;
		}
		
	}
	
	// Aumentamos experiencia
	public void AumentarExperiencia (int cantidad) {
		
		int aux = this.escudo_Cruz.getValor();
		aux = aux + cantidad;
		if (aux>12)
			aux = 12;
		this.escudo_Cruz.setValor(aux);
		
	}

	// Disminuimos experiencia
	public void DisminuirExperiencia (int cantidad) {
		
		int aux = this.escudo_Cruz.getValor();
		aux = aux - cantidad;
		if (aux<1)
			aux=1;
		this.escudo_Cruz.setValor(aux);
		
	}
	
	// =====================================
	// Funciones de la lucha entre Heroes
	// =====================================
	
	// El h�roe del jugador ataca en la lucha de Heroes
	
	// -1 : Derrota
	// 0 : Empate
	// 1 : Victoria
	public int Atacar(int fuerzaHeroe,int fuerzaEjercito1, int fuerzaEjercito2,
			int fuerzaEnemigo,int fuerzaEjercitoEnemigo1,int fuerzaEjercitoEnemigo2) {
		
		int S1 = fuerzaHeroe + fuerzaEjercito1 + fuerzaEjercito2;
		int S2 = fuerzaEnemigo + fuerzaEjercitoEnemigo1 + fuerzaEjercitoEnemigo2;
		
		float probabilidadHeroe, probabilidadEnemigo;

		float valoresHeroe,valoresEnemigo;
		boolean bGanaHeroe, bGanaEnemigo;

		// Calculamos las probabilidades de ganar de Heroe y Enemigo
		// Es mas fuerte el jugador
		if (fuerzaHeroe>=fuerzaEnemigo) {
			probabilidadHeroe = ProbabilidadesLucha.ProbabilidadLucha2(S1);
			probabilidadEnemigo = ProbabilidadesLucha.ProbabilidadLucha1(S2);
			
			
		} 
		// Es mas fuerte el enemigo
		else
		{
			probabilidadHeroe = ProbabilidadesLucha.ProbabilidadLucha2(S2);
			probabilidadEnemigo = ProbabilidadesLucha.ProbabilidadLucha1(S1);
		}
		
		// Calculamos valores aleatorios y vemos si
		// hemos ganado o perdido
		
		valoresHeroe = (float) Math.random();
		if (valoresHeroe<=probabilidadHeroe) {
			bGanaHeroe=true;
		}
		else {
			bGanaHeroe = false;
		}
			
		
		valoresEnemigo = (float) Math.random();
		if (valoresEnemigo<=probabilidadEnemigo) {
			bGanaEnemigo=true;
		}
		else {
			bGanaEnemigo = false;
		}
		
		if (bGanaHeroe && !bGanaEnemigo) {
			return 1;
		}
		
		if (bGanaEnemigo && !bGanaHeroe) {
			return -1;
		}
		
		return 0;
		
	}
	
	
	// Devuelve el valor en que quedaria el Enemigo tras haber
	// perdido una batalla. 
	// Tras esto, se debera actualizar el objeto Enemigo actualizando
	// la vida de �ste.
	
	// Tambien hay que sumar una victoria al Jugador
	
	public int PostVictoriaAtacar (int vidaActualEnemigo,int fuerzaHeroe,int fuerzaEjercito1, int fuerzaEjercito2,
			int fuerzaEnemigo,int fuerzaEjercitoEnemigo1,int fuerzaEjercitoEnemigo2) {
		
		float S1 = fuerzaHeroe + fuerzaEjercito1 + fuerzaEjercito2;
		float S2 = fuerzaEnemigo + fuerzaEjercitoEnemigo1 + fuerzaEjercitoEnemigo2;
		float porcentajePerdidaVida;
		
		
		porcentajePerdidaVida = S2/S1;
		if (porcentajePerdidaVida>=1.0)  {
			porcentajePerdidaVida = S1/S2;
		}
		
		porcentajePerdidaVida = 1-porcentajePerdidaVida;
		
		int vidaEnemigo = (int)(vidaActualEnemigo * porcentajePerdidaVida);	
		if (vidaEnemigo<=MINIMO_VIDA) { 
			vidaEnemigo = 0;
			
		}

		return vidaEnemigo;
		
	}
			
	// Esta es la funcion de defensa del jugador
	
	// -1 : Derrota
	// 0 : Empate
	// 1 : Victoria
	public int Defender(int fuerzaHeroe,int fuerzaEjercito1, int fuerzaEjercito2,
			int fuerzaEnemigo,int fuerzaEjercitoEnemigo1,int fuerzaEjercitoEnemigo2) {
		
		float S1 = fuerzaHeroe + fuerzaEjercito1 + fuerzaEjercito2;
		float S2 = fuerzaEnemigo + fuerzaEjercitoEnemigo1 + fuerzaEjercitoEnemigo2;
		
		float probabilidadHeroe, probabilidadEnemigo;

		float valoresHeroe,valoresEnemigo;
		boolean bGanaHeroe, bGanaEnemigo;

		// Calculamos las probabilidades de ganar de Heroe y Enemigo
		// Es mas fuerte el jugador
		probabilidadHeroe = ProbabilidadesLucha.ProbabilidadHiereEnemigo((int)S1);
		probabilidadEnemigo = ProbabilidadesLucha.ProbabilidadHiereEnemigo((int)S2);
		
		// Calculamos valores aleatorios y vemos si
		// hemos ganado o perdido
		
		valoresHeroe = (float) Math.random();
		if (valoresHeroe<=probabilidadHeroe) {
			bGanaHeroe=true;
		}
		else {
			bGanaHeroe = false;
		}
			
		
		valoresEnemigo = (float) Math.random();
		if (valoresEnemigo<=probabilidadEnemigo) {
			bGanaEnemigo=true;
		}
		else {
			bGanaEnemigo = false;
		}
		
		if (bGanaHeroe && !bGanaEnemigo) {
			return 1;
		}
		
		if (bGanaEnemigo && !bGanaHeroe) {
			return -1;
		}
		
		return 0;
		
	}

	
	// Defender 2 . Defensa en empate
	// -1 : Derrota
	// 0 : Empate
	// 1 : Victoria
	public int Defender(int fuerzaTorre,int fuerzaHeroe,int fuerzaEjercito1, int fuerzaEjercito2,
			int fuerzaEnemigo,int fuerzaEjercitoEnemigo1,int fuerzaEjercitoEnemigo2) {
		
		float S1 = fuerzaHeroe + fuerzaEjercito1 + fuerzaEjercito2+fuerzaTorre;
		
		float S2 = fuerzaEnemigo + fuerzaEjercitoEnemigo1 + fuerzaEjercitoEnemigo2;
		float aux = (float) Math.random() * 11 + 1;
		S2+=aux;
		
		float probabilidadHeroe, probabilidadEnemigo;

		float valoresHeroe,valoresEnemigo;
		boolean bGanaHeroe, bGanaEnemigo;

		// Calculamos las probabilidades de ganar de Heroe y Enemigo
		// Es mas fuerte el jugador
		probabilidadHeroe = ProbabilidadesLucha.ProbabilidadHiereEnemigo((int)S1);
		probabilidadEnemigo = ProbabilidadesLucha.ProbabilidadHiereEnemigo((int)S2);
		
		// Calculamos valores aleatorios y vemos si
		// hemos ganado o perdido
		
		valoresHeroe = (float) Math.random();
		if (valoresHeroe<=probabilidadHeroe) {
			bGanaHeroe=true;
		}
		else {
			bGanaHeroe = false;
		}
			
		
		valoresEnemigo = (float) Math.random();
		if (valoresEnemigo<=probabilidadEnemigo) {
			bGanaEnemigo=true;
		}
		else {
			bGanaEnemigo = false;
		}
		
		if (bGanaHeroe && !bGanaEnemigo) {
			return 1;
		}
		
		if (bGanaEnemigo && !bGanaHeroe) {
			return -1;
		}
		
		return 0;
		
	}
	
	
	
	
	
	// Devuelve el valor en que quedaria el Heroe
	// tras haber perdido una defensa
	// Tras esto, se debera actualizar el objeto Heroe del Jugador
	
	// Tambien tenemos que sumar una victoria al Enemigo
	
	public int PostDerrotaDefender (int vidaActualHeroe,int fuerzaHeroe,int fuerzaEjercito1, int fuerzaEjercito2,
			int fuerzaEnemigo,int fuerzaEjercitoEnemigo1,int fuerzaEjercitoEnemigo2) {
		
		float S1 = fuerzaHeroe + fuerzaEjercito1 + fuerzaEjercito2;
		float S2 = fuerzaEnemigo + fuerzaEjercitoEnemigo1 + fuerzaEjercitoEnemigo2;
		float porcentajePerdidaVida;
		
		
		porcentajePerdidaVida = S1/S2;
		if (porcentajePerdidaVida>=1.0)  {
			porcentajePerdidaVida = S2/S1;
		}
		
		porcentajePerdidaVida = 1-porcentajePerdidaVida;
		
		int vidaHeroe = (int)(vidaActualHeroe * porcentajePerdidaVida);	
		if (vidaHeroe<=MINIMO_VIDA) { 
			vidaHeroe = 0;
			bVivo = false;
		}
		
		return vidaHeroe;
		
	}
	

	
	
	
	// Al entrar en la actividad de Lucha de Heroes
	// se la llama para tener actualizacion del ej�rcito
	public void CambiarCartasEjercito() {
		int nro1,nro2;
		
		nro1 = (int)(Math.random() * 11+ 1);
		nro2 = (int)(Math.random() * 11 + 1);
		
		escudo_Natural = new Escudo(nro1,GrupoEscudo.natural);
		escudo_Artificial = new Escudo(nro2,GrupoEscudo.artificial);
		
	}

	//========================================
	// Funciones que controlan en Parcelado
	//=======================================

	
//	@Override
//	public int describeContents() {
//		return 0;
//	}
//
//	@Override
//	public void writeToParcel(Parcel dest, int flags) {
//		/*private Escudo escudo_Cruz;
//		private Escudo escudo_Natural;
//		private Escudo escudo_Artificial; */		
//
//		dest.writeByte((byte) (this.bVivo ? 1 : 0));
//		dest.writeInt(this.nivelVida);
//		dest.writeParcelable (this.escudo_Cruz,flags);
//		dest.writeParcelable(this.escudo_Natural, flags);
//		dest.writeParcelable(this.escudo_Artificial,flags);
//		
//	}
	
	
}
