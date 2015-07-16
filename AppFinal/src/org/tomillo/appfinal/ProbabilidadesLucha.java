package org.tomillo.appfinal;


// TOMILLO 16/7/2015
// Clase para obtener probabilidades de diferentes tipos de 
// acciones en la pantalla de Heroes
public class ProbabilidadesLucha {
	
	final static int NUMERO_CLUSTERS = 12;
	
	// Tablas de Logica Difusa para cada tipo de acción
	static double [][] LogicaDifusa1 = {{0,5,12,19,24,26,27,28,30,33,34,36},
			{0.0,0.2,0.4,0.5,0.6,0.63,0.65,0.75,0.8,0.85,0.9,0.95}};
	
	static double [][] LogicaDifusa2 = {{0,2,4,7,13,18,22,27,29,32,34,36},
			{0.0,0.2,0.3,0.4,0.5,0.6,0.65,0.7,0.8,0.85,0.9,0.95}};
	
	static double [][] LogicaDifusa3 = {{0,3,6,9,12,15,18,21,24,27,30,36},
			{0.0,0.05,0.1,0.15,0.2,0.3,0.33,0.35,0.4,0.45,0.5,0.95}};
	
	
	// Probabilidad de jugador de matar a un enemigo
	// El valor debe de ir entre 0 y 36
	public static float ProbabilidadLucha1 (int Suma1) {
		
		for (int i=0;i<NUMERO_CLUSTERS;i++) {
			
			if (LogicaDifusa1[0][i]>=Suma1) {
				return (float) LogicaDifusa1[1][i];
			}
		}
		
		return 0.0F;
	}
	
	// Probabilidad de jugador de ganador al enemigo restante
	// tras matar al primero 
	// El valor debe de ir entre 0 y 36	
	public static float ProbabilidadLucha2 (int Suma1) {
		
		for (int i=0;i<NUMERO_CLUSTERS;i++) {
			
			if (LogicaDifusa2[0][i]>=Suma1) {
				return (float) LogicaDifusa2[1][i];
			}
		}
		
		
		return 0.0F;
	}

	// Indica si al defender has sufrido daño del enemigo
	// El valor debe de ir entre 0 y 36	
	public static float ProbabilidadHiereEnemigo (int Suma2) {

		for (int i=0;i<NUMERO_CLUSTERS;i++) {
			
			if (LogicaDifusa3[0][i]>=Suma2) {
				return (float) LogicaDifusa3[1][i];
			}
		}
		
		
		return 0.0F;
	}
	
/*	private static void main (String args[]) {
		int aux;
		float faux;
		
		// Prueba probabilidad Lucha 1
		aux = 12;
		faux = ProbabilidadLucha1(aux);
		aux = 32;
		faux = ProbabilidadLucha1(aux);
		aux = 42;
		faux = ProbabilidadLucha1(aux);
		
		// Prueba probabilidad Lucha2
		aux = 5;
		faux = ProbabilidadLucha2(aux);
		aux = 12;
		faux = ProbabilidadLucha2(aux);
		aux = 25;
		faux = ProbabilidadLucha2(aux);

		// Prueba probabilidad Enemigo
		aux = 5;
		faux = ProbabilidadHiereEnemigo(aux);
		aux = 0;
		faux = ProbabilidadHiereEnemigo(aux);
		aux = 20;
		faux = ProbabilidadHiereEnemigo(aux);

		
	} */
	
}
