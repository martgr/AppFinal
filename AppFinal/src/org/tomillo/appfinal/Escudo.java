package org.tomillo.appfinal;


public class Escudo {
	
	final int NRO_ESCUDOS_PUEBLO = 6;
	final int NRO_ESCUDOS_RESTO = 12;

	// Tipos de escudos
	public enum GrupoEscudo  {torre,rey,nobleza,pueblo,puntuacion,defensa,
		ataque,cruz,natural,artificial}

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

	// Valor que indica el nro. de escudo cargado
	private int valor_inicial;
	// Tipo del escudo
	private GrupoEscudo tipoEscudo;
	// Valor del escudo tratado por código
	private int valor;
	
	// Ruta del escudo .png
	private String ruta;
	
	public Escudo (int valor_inicial,GrupoEscudo tipo) {
		
		this.valor_inicial=valor_inicial;
		this.tipoEscudo = tipo;
		this.valor = valor_inicial;
		
		// Aquí hay que hallar la ruta del .png
		
		ruta= tipo.name()+valor_inicial+".png";

	}
	
	
	

}
