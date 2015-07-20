package org.tomillo.appfinal;

public class Jugador {

	private int victorias_actuales;
	// Heroe del jugador
	private Heroe heroeJugador;

	// / Elementos del castillo
	// Torre del Castillo
	private Escudo escudoTorre;
	// Escudo del rey
	private Escudo escudoRey;
	// Escudo de la nobleza
	private Escudo escudoNobleza;
	// Escudo del pueblo
	private Escudo escudoPueblo;

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

	// Puntuacion total del jugador
	private int puntuacion;
	// Rango jugador segun victorias actuales
	private int TituloNobiliario = 0;

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

	public Jugador() {
		this.victorias_actuales = 0;
		this.puntuacion = 0;
		this.TituloNobiliario = 0;

		// Asignamos el escudo de la torre
		int aux = (int) Math.random() * 11 + 1;
		escudoTorre = new Escudo(aux, Escudo.GrupoEscudo.torre);
		// Asignamos el escudo del rey;
		aux = (int) Math.random() * 11 + 1;
		escudoRey = new Escudo(aux, Escudo.GrupoEscudo.rey);
		// Asignamos el escudo de la nobleza
		aux = (int) Math.random() * 11 + 1;
		escudoNobleza = new Escudo(aux, Escudo.GrupoEscudo.nobleza);
		// Asignamos el escudo del pueblo
		aux = (int) Math.random() * 5 + 1;
		escudoPueblo = new Escudo(aux, Escudo.GrupoEscudo.pueblo);

		// Creacion jugadores
		heroeJugador = new Heroe();

	}

	// Actualizamos la puntuacion actual del jugador
	// Se realizara al finalizar la partida
	public void ActualizarPuntuacion() {
		this.puntuacion = 0;
		this.puntuacion += this.heroeJugador.getCartasHeroes();
	}

	// Se le llama al aumentar victorias
	public void AumentarVictorias() {
		this.victorias_actuales++;
		if (this.victorias_actuales == 9) {
			this.TituloNobiliario = 3;
		} else if (this.victorias_actuales > 6) {
			this.TituloNobiliario = 2;
		} else if (this.victorias_actuales > 3) {
			this.TituloNobiliario = 1;
		} else {
			this.TituloNobiliario = 0;
		}
	}

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

		}
		return "Señor Feudal";

	}

	// Cambia las cartas del Castillo :
	// Torre,Rey,Nobleza,Pueblo.
	public void CambiarCartasCastillo() {

		// Asignamos el escudo de la torre
		int aux = (int) Math.random() * 11 + 1;
		escudoTorre = new Escudo(aux, Escudo.GrupoEscudo.torre);
		// Asignamos el escudo del rey;
		aux = (int) Math.random() * 11 + 1;
		escudoRey = new Escudo(aux, Escudo.GrupoEscudo.rey);
		// Asignamos el escudo de la nobleza
		aux = (int) Math.random() * 11 + 1;
		escudoNobleza = new Escudo(aux, Escudo.GrupoEscudo.nobleza);
		// Asignamos el escudo del pueblo
		aux = (int) Math.random() * 5 + 1;
		escudoNobleza = new Escudo(aux, Escudo.GrupoEscudo.pueblo);

	}

}
