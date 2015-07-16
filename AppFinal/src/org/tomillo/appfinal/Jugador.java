package org.tomillo.appfinal;

public class Jugador {

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

	private int victorias_actuales;
	private Heroe heroeJugador;
	private Escudo escudoTorre;
	
	public Jugador() {
		this.victorias_actuales=0;
		
		int aux = (int) Math.random()*11+1;
		escudoTorre = new Escudo(aux,Escudo.GrupoEscudo.torre);
		
		// Creacion jugadores
		heroeJugador = new Heroe();
		
	}
	

	
}
