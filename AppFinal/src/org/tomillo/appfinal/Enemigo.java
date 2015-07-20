package org.tomillo.appfinal;

public class Enemigo {
	private int victorias_actuales;
	private int nivelVida= 100;
	boolean bVivo = true;
	

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
}
