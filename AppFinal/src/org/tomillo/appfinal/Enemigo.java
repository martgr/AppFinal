package org.tomillo.appfinal;

public class Enemigo {
	private int victorias_actuales;

	public int getVictorias_actuales() {
		return victorias_actuales;
	}

	public void setVictorias_actuales(int victorias_actuales) {
		this.victorias_actuales = victorias_actuales;
	}
	
	public Enemigo() {
		this.victorias_actuales=0;
	}
}
