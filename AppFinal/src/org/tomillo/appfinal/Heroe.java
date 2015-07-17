package org.tomillo.appfinal;

import java.util.Random;

public class Heroe {

	public Escudo getEscudo_Magia_Ataque() {
		return escudo_Magia_Ataque;
	}

	public void setEscudo_Magia_Ataque(Escudo escudo_Magia_Ataque) {
		this.escudo_Magia_Ataque = escudo_Magia_Ataque;
	}

	public Escudo getEscudo_Magia_Defensa() {
		return escudo_Magia_Defensa;
	}

	public void setEscudo_Magia_Defensa(Escudo escudo_Magia_Defensa) {
		this.escudo_Magia_Defensa = escudo_Magia_Defensa;
	}

	private boolean bVivo = true;
	private int nivelVida = 100;
	public int getNivelVida() {
		return nivelVida;
	}

	public void setNivelVida(int nivelVida) {
		this.nivelVida = nivelVida;
	}

	private Escudo escudo_Cruz;
	private Escudo escudo_Natural;
	private Escudo escudo_Artificial;
	private Escudo escudo_Magia_Ataque;
	private Escudo escudo_Magia_Defensa;
	
	
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

	public Heroe () {
		bVivo = true;
		
		// Ejército
		int valor = (int) (Math.random()*11+1);
		escudo_Cruz = new Escudo(valor,Escudo.GrupoEscudo.cruz);
		valor = (int) (Math.random()*11+1);
		escudo_Natural = new Escudo(valor,Escudo.GrupoEscudo.natural);
		valor = (int) (Math.random()*11+1);
		escudo_Artificial = new Escudo(valor,Escudo.GrupoEscudo.artificial);

		// Escudos de las magias
		valor = (int) (Math.random()*11+1);
		escudo_Magia_Defensa = new Escudo(valor,Escudo.GrupoEscudo.defensa);
		valor = (int) (Math.random()*11+1);
		escudo_Magia_Ataque = new Escudo(valor,Escudo.GrupoEscudo.ataque);
		
		
	}
	
	// Aumenta vida de heroe hasta un máxmimo de 100
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
	
	
	
}
