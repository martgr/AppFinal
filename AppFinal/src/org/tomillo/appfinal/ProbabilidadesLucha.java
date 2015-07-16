package org.tomillo.appfinal;

// TOMILLO 16/7/2015
// Clase para obtener probabilidades de diferentes tipos de 
// acciones en la pantalla de Heroes
public class ProbabilidadesLucha {

	final static int NUMERO_CLUSTERS = 12;

	// Tablas de Logica Difusa para cada tipo de acción
	static float[][] LogicaDifusa1 = {
			{ 0, 5, 12, 19, 24, 26, 27, 28, 30, 33, 34, 36 },
			{ 0.0f, 0.2f, 0.4f, 0.5f, 0.6f, 0.63f, 0.65f, 0.75f, 0.8f, 0.85f, 0.9f, 0.95f } };

	static float[][] LogicaDifusa2 = {
			{ 0, 2, 4, 7, 13, 18, 22, 27, 29, 32, 34, 36 },
			{ 0.0f, 0.2f, 0.3f, 0.4f, 0.5f, 0.6f, 0.65f, 0.7f, 0.8f, 0.85f, 0.9f, 0.95f } };

	static float[][] LogicaDifusa3 = {
			{ 0, 3, 6, 9, 12, 15, 18, 21, 24, 27, 30, 36 },
			{ 0.0f, 0.05f, 0.1f, 0.15f, 0.2f, 0.3f, 0.33f, 0.35f, 0.4f, 0.45f, 0.5f, 0.95f } };

	// Probabilidad de jugador de matar a un enemigo
	// El valor debe de ir entre 0 y 36
	public static float ProbabilidadLucha1(int Suma1) {

		for (int i = 0; i < NUMERO_CLUSTERS; i++) {

			if (LogicaDifusa1[0][i] >= Suma1) {
				return LogicaDifusa1[1][i];
			}
		}

		return 0.0F;
	}

	// Probabilidad de jugador de ganador al enemigo restante
	// tras matar al primero
	// El valor debe de ir entre 0 y 36
	public static float ProbabilidadLucha2(int Suma1) {

		for (int i = 0; i < NUMERO_CLUSTERS; i++) {

			if (LogicaDifusa2[0][i] >= Suma1) {
				return LogicaDifusa2[1][i];
			}
		}

		return 0.0F;
	}

	// Indica si al defender has sufrido daño del enemigo
	// El valor debe de ir entre 0 y 36
	public static float ProbabilidadHiereEnemigo(int Suma2) {

		for (int i = 0; i < NUMERO_CLUSTERS; i++) {

			if (LogicaDifusa3[0][i] >= Suma2) {
				return LogicaDifusa3[1][i];
			}
		}

		return 0.0F;
	}

}