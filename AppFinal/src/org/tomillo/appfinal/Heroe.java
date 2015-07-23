// Clase del Heroe.
// Contiene los tres escudos con los que lucha
// Versión del 22/7/2015

package org.tomillo.appfinal;

import org.tomillo.appfinal.Escudo.GrupoEscudo;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;


public class Heroe implements Parcelable {

	// ============
	// 	Constantes
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
	// 	Atributos
	// ==========
	private boolean bVivo = true;
	private int nivelVida;
	private Escudo escudo_Cruz;
	private Escudo escudo_Natural;
	private Escudo escudo_Artificial;

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
	// 	Constructores
	// ===============
	
	private Heroe (Parcel source) {
		this.bVivo = (source.readByte() == 1  ? true : false);
		if (this.bVivo)
			Log.i("heroe","Leido bVivo : TRUE");
		else
			Log.i("heroe","Leido bVivo : FALSE");
		this.nivelVida = source.readInt();
		Log.i("heroe","Leido nivelVida : " + Integer.toString(this.nivelVida));
		this.escudo_Cruz = source.readParcelable(Escudo.class.getClassLoader());
		Log.i("heroe","Leido EscudoCruz");
		this.escudo_Natural = source.readParcelable(Escudo.class.getClassLoader());
		Log.i("heroe","Leido EscudoNatural");		
		this.escudo_Artificial = source.readParcelable(Escudo.class.getClassLoader());
		Log.i("heroe","Leido EscudoArtificial");		
	}
	
	public Heroe () {

		bVivo = true;
		nivelVida = 100;
		// Ejército
		int valor = (int) (Math.random()*11+1);
		escudo_Cruz = new Escudo(valor,Escudo.GrupoEscudo.cruz);
		valor = (int) (Math.random()*11+1);
		escudo_Natural = new Escudo(valor,Escudo.GrupoEscudo.natural);
		valor = (int) (Math.random()*11+1);
		escudo_Artificial = new Escudo(valor,Escudo.GrupoEscudo.artificial);
		
	}

	// ===================
	// 	Getters y Setters
	// ===================

	public int getNivelVida() {
		if (!bVivo)
			return 0;
		else
			return nivelVida;
	}

	public void setNivelVida(int nivelVida) {
		
		if (nivelVida>100)
			nivelVida=100;
		this.nivelVida = nivelVida;
		if (this.nivelVida<=0) {
			this.bVivo=false;
		}
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

	// ==========================
	// 	Acciones sobre el objeto
	// ==========================

	// Pre : cantidad entre 1 y 100
	// Post : Aumenta vida de heroe hasta un máxmimo de 100
	public void AumentarVida (int cantidad) {
		if ((cantidad<1) || (cantidad>100))
			return;
		
		int aux = this.nivelVida + cantidad;
		if (aux>100) {
			aux =100;
		}
		this.nivelVida = aux;
		
	}

	// Pre : cantidad entre 1 y 100
	// Disminuye vida. Si es menor de 0 ha muerto el Heroe
	public void DisminuirVida (int cantidad) {
		
		if ((cantidad<1) || (cantidad>100))
			return;
		
		int aux = this.nivelVida - cantidad;
		if (aux<=0)  {
			this.bVivo = false;
			this.nivelVida = 0;
		}
		else {
			this.nivelVida = aux;
		}
		
	}
	
	// Aumentamos experiencia en un valor
	public void AumentarExperiencia (int cantidad) {
		
		int aux = this.escudo_Cruz.getValor();
		aux = aux + cantidad;
		if (aux>12)
			aux = 12;
		this.escudo_Cruz.setValor(aux);
		
	}

	// Disminuimos experiencia en un valor
	public void DisminuirExperiencia (int cantidad) {
		
		int aux = this.escudo_Cruz.getValor();
		aux = aux - cantidad;
		if (aux<1)
			aux=1;
		this.escudo_Cruz.setValor(aux);
		
	}
	
	// =====================================
	// 	Funciones de la lucha entre Heroes
	// =====================================
	
	// El héroe del jugador ataca en la lucha de Heroes
	
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
	// la vida de éste.
	
	// Tambien hay que sumar una victoria al Jugador
	
	public int PostVictoriaAtacar (int vidaActualEnemigo,int fuerzaHeroe,int fuerzaEjercito1, int fuerzaEjercito2,
			int fuerzaEnemigo,int fuerzaEjercitoEnemigo1,int fuerzaEjercitoEnemigo2) {
		
		int S1 = fuerzaHeroe + fuerzaEjercito1 + fuerzaEjercito2;
		int S2 = fuerzaEnemigo + fuerzaEjercitoEnemigo1 + fuerzaEjercitoEnemigo2;
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
		
		int S1 = fuerzaHeroe + fuerzaEjercito1 + fuerzaEjercito2;
		int S2 = fuerzaEnemigo + fuerzaEjercitoEnemigo1 + fuerzaEjercitoEnemigo2;
		
		float probabilidadHeroe, probabilidadEnemigo;

		float valoresHeroe,valoresEnemigo;
		boolean bGanaHeroe, bGanaEnemigo;

		// Calculamos las probabilidades de ganar de Heroe y Enemigo
		// Es mas fuerte el jugador
		probabilidadHeroe = ProbabilidadesLucha.ProbabilidadHiereEnemigo(S1);
		probabilidadEnemigo = ProbabilidadesLucha.ProbabilidadHiereEnemigo(S2);
		
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
		
		int S1 = fuerzaHeroe + fuerzaEjercito1 + fuerzaEjercito2;
		int S2 = fuerzaEnemigo + fuerzaEjercitoEnemigo1 + fuerzaEjercitoEnemigo2;
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
	// se la llama para tener actualizacion del ejército
	public void CambiarCartasEjercito() {
		int nro1,nro2;
		
		nro1 = (int)(Math.random() * 11+ 1);
		nro2 = (int)(Math.random() * 11 + 1);
		
		escudo_Natural = new Escudo(nro1,GrupoEscudo.natural);
		escudo_Artificial = new Escudo(nro2,GrupoEscudo.artificial);
		
	}

	//========================================
	// 	Funciones que controlan en Parcelado
	//=======================================

	
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		/*private Escudo escudo_Cruz;
		private Escudo escudo_Natural;
		private Escudo escudo_Artificial; */		

		dest.writeByte((byte) (this.bVivo ? 1 : 0));
		if (this.bVivo==true)
			Log.i("heroe","TRUE");
		else
			Log.i("heroe","FALSE");
		
		dest.writeInt(this.nivelVida);
		Log.i("heroe","Escrito NivelVida : " + Integer.toString(this.nivelVida));
		dest.writeParcelable (this.escudo_Cruz,flags);
		Log.i("heroe","Insertado escudo_Cruz");
		dest.writeParcelable(this.escudo_Natural, flags);
		Log.i("heroe","Insertado escudo_Natural");		
		dest.writeParcelable(this.escudo_Artificial,flags);
		Log.i("heroe","Insertado escudo_Artificial");		
		
	}
	
	
}
