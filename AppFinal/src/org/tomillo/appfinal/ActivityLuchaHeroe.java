package org.tomillo.appfinal;

import org.tomillo.appfinal.Escudo.GrupoEscudo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ActivityLuchaHeroe extends Activity {

	Jugador jugador;
	Enemigo enemigo;
	Escudo escudoheroeenemigo;
	Escudo escudoenemigo1;
	Escudo escudoenemigo2;
	Escudo escudoheroe;
	Escudo escudoEjercito1;
	Escudo escudoEjercito2;

	// booleano para el ataque
	boolean bAtaque = true;

	// Indica si estan vivos los contrincantes
	boolean bVivoEnemigo = true;
	boolean bVivoHeroe = true;

	// Opciones finales
	boolean bGanoHeroe;
	boolean bGanoEnemigo;
	boolean bMurioEnemigo;
	boolean bMurioHeroe;
	boolean bEmpate;

	int icantidadqueresta;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		// Iniciamos finales de Lucha
		bGanoHeroe = false;
		bGanoEnemigo = false;
		bMurioEnemigo = false;
		bMurioHeroe = false;
		bEmpate = false;


		jugador=(Jugador) getIntent().getSerializableExtra("PARCELABLE_Jugador");
		enemigo = (Enemigo) getIntent().getSerializableExtra("PARCELABLE_Enemigo");


		// Vemos si murio alguien
		bVivoEnemigo = enemigo.isbVivo();
		bVivoHeroe = jugador.getHeroeJugador().isbVivo();
		
		// Algun componenente muerto
		if (!bVivoEnemigo || !bVivoHeroe) {

			Intent it = new Intent(this, Transicion_Heroe.class);
			it.putExtra("PARCELABLE_Jugador", jugador);
			it.putExtra("PARCELABLE_Enemigo", enemigo);

			startActivity(it);
			finish();
		}

		setContentView(R.layout.layoutluchaheroe);

		jugador.getHeroeJugador().CambiarCartasEjercito();

		// cargar escudos heroe

		escudoheroe = jugador.getHeroeJugador().getEscudo_Cruz();
		escudoEjercito1 = jugador.getHeroeJugador().getEscudo_Natural();
		escudoEjercito2 = jugador.getHeroeJugador().getEscudo_Artificial();

		// cargar escudos enemigo
		int aux = (int) (Math.random() * 11) + 1;
		escudoheroeenemigo = new Escudo(aux, GrupoEscudo.cruz);
		int aux2 = (int) (Math.random() * 11) + 1;
		escudoenemigo1 = new Escudo(aux2, GrupoEscudo.natural);
		int aux3 = (int) (Math.random() * 11) + 1;
		escudoenemigo2 = new Escudo(aux3, GrupoEscudo.artificial);

		// Hay que mostrar los valores y escudos en la actividad

		MostrarDatos();

	}

	// MENUS ATAQUE Y DEFENSA

	// FALTAN LAS MAGIAS DE ATAQUE Y DEFENSA.
	// Y CAMBIAR LOS BOTONES ATAQUE Y DEFENSA.
	public void ataquedefensa(View v) {
		// añadir escudos en ataque
		int fuerzaHeroe = escudoheroe.getValor();
		int fuerzaEjercito1 = escudoEjercito1.getValor();
		int fuerzaEjercito2 = escudoEjercito2.getValor();
		int fuerzaEnemigo = escudoheroeenemigo.getValor();
		int fuerzaEjercitoEnemigo1 = escudoenemigo1.getValor();
		int fuerzaEjercitoEnemigo2 = escudoenemigo2.getValor();

		// ATAQUE
		if (bAtaque) {

			int resultado = jugador.getHeroeJugador().Atacar(fuerzaHeroe,
					fuerzaEjercito1, fuerzaEjercito2, fuerzaEnemigo,
					fuerzaEjercitoEnemigo1, fuerzaEjercitoEnemigo2);
			if (resultado == 1) {

				// Aumentamos victoria y valor de torre
				jugador.AumentarVictorias();
				Escudo Torre = jugador.getEscudoTorre();
				int valor = Torre.getValor();
				if (valor < 12) {
					valor++;
					Torre.setValor(valor);
				}

				// Cogemos el nivel de vida enemigo
				int vidaActualEnemigo = enemigo.getNivelVida();
				// Atacamos
				int vidadevuelta = jugador.getHeroeJugador()
						.PostVictoriaAtacar(vidaActualEnemigo, fuerzaHeroe,
								fuerzaEjercito1, fuerzaEjercito2,
								fuerzaEnemigo, fuerzaEjercitoEnemigo1,
								fuerzaEjercitoEnemigo2);

				enemigo.setNivelVida(vidadevuelta);

				// Vida de enemigo a cero
				if (vidadevuelta == 0) {
					bVivoEnemigo = false;
					// MURIO EL ENEMIGO
					bMurioEnemigo = true;
				} else {
					bGanoHeroe = true;
					jugador.ActualizarPuntuacionHeroe();
				}

			} else {
				bAtaque = false;
			}

		}
		// DEFENSA
		else {
			// poner imagen de boton de defensa
			Button btn = (Button) findViewById(R.id.ibAtaque);
			btn.setBackground(getResources().getDrawable(R.drawable.btdefensa));

			Button btn1 = (Button) findViewById(R.id.ibMagia);
			btn1.setBackground(getResources().getDrawable(
					R.drawable.btdefensamagia));

			int resultadoDefensa = jugador.getHeroeJugador().Defender(
					fuerzaHeroe, fuerzaEjercito1, fuerzaEjercito2,
					fuerzaEnemigo, fuerzaEjercitoEnemigo1,
					fuerzaEjercitoEnemigo2);

			if (resultadoDefensa == -1) {
				enemigo.AumentarVictorias();
				int vidaActualHeroe = jugador.getHeroeJugador().getNivelVida();
				int nivelVidaTrasLucha = jugador.getHeroeJugador()
						.PostDerrotaDefender(vidaActualHeroe, fuerzaHeroe,
								fuerzaEjercito1, fuerzaEjercito2,
								fuerzaEnemigo, fuerzaEjercitoEnemigo1,
								fuerzaEjercitoEnemigo2);

				icantidadqueresta = nivelVidaTrasLucha - vidaActualHeroe;
				jugador.getHeroeJugador().setNivelVida(nivelVidaTrasLucha);
				if (nivelVidaTrasLucha == 0)
				// MURIO EL HEROE
				{
					bVivoHeroe = false;
					bMurioHeroe = true;
				} else
					bGanoEnemigo = true;

			} else {
				bEmpate = true;
				jugador.AumentarBankias(2);
				// BANKIAS
			}
		}

		Intent it = new Intent(this, MenuEscudos.class);
		Log.i("Despues de luchar heroe estas son las victorias", String.valueOf(jugador.getVictorias_actuales()));
		it.putExtra("PARCELABLE_Jugador", jugador);
		it.putExtra("PARCELABLE_Enemigo", enemigo);
		startActivity(it);

	}

	public void magia(View v) {

		int fuerzaHeroe = escudoheroe.getValor();
		int fuerzaEjercito1 = escudoEjercito1.getValor();
		int fuerzaEjercito2 = escudoEjercito2.getValor();
		int fuerzaEnemigo = escudoheroeenemigo.getValor();
		int fuerzaEjercitoEnemigo1 = escudoenemigo1.getValor();
		int fuerzaEjercitoEnemigo2 = escudoenemigo2.getValor();

		if (bAtaque) {

			// boton de magia de ataque

			escudoheroe = jugador.getHeroeJugador().getEscudo_Cruz();
			int aux = escudoheroe.getValor() * 2;
			if (aux > 12)
				aux = 12;
			escudoheroe.setValor(aux);

			escudoEjercito1 = jugador.getHeroeJugador().getEscudo_Natural();
			aux = escudoEjercito1.getValor() * 2;
			if (aux > 12)
				aux = 12;
			escudoEjercito1.setValor(aux);

			escudoEjercito2 = jugador.getHeroeJugador().getEscudo_Artificial();
			aux = escudoEjercito2.getValor() * 2;
			if (aux > 12)
				aux = 12;
			escudoEjercito2.setValor(aux);

			TextView hero = (TextView) findViewById(R.id.tvAtaqueHeroe);
			TextView ejer1 = (TextView) findViewById(R.id.tvAtaqueEjercito1);
			TextView ejer2 = (TextView) findViewById(R.id.tvAtaqueEjercito2);
			hero.setText(String.valueOf(escudoheroe.getValor()));
			ejer1.setText(String.valueOf(escudoEjercito1.getValor()));
			ejer2.setText(String.valueOf(escudoEjercito2.getValor()));
			MostrarDatos();

		}

		// boton de magia de defensa
		else {
			icantidadqueresta /= 2;
			jugador.getHeroeJugador().setNivelVida(
					jugador.getHeroeJugador().getNivelVida()
							+ icantidadqueresta);

			MostrarDatos();

		}

	}

	// Valores
	// Imagenes
	// Barras de vida
	private void MostrarDatos() {

		// Mostramos los valores

		TextView edt1 = (TextView) findViewById(R.id.tvAtaqueHeroe);
		TextView edt2 = (TextView) findViewById(R.id.tvAtaqueEjercito1);
		TextView edt3 = (TextView) findViewById(R.id.tvAtaqueEjercito2);

		
		edt1.setText( String.valueOf(escudoheroe.getValor()));
		edt2.setText( String.valueOf(escudoEjercito1.getValor()));
		edt3.setText( String.valueOf(escudoEjercito2.getValor()));

		edt1 = (TextView) findViewById(R.id.tvAtaqueHeroeEnemigo);
		edt2 = (TextView) findViewById(R.id.tvAtaqueEjercitoEnemigo1);
		edt3 = (TextView) findViewById(R.id.tvAtaqueEjercitoEnemigo2);

		edt1.setText(String.valueOf(escudoheroeenemigo.getValor()));
		edt2.setText(String.valueOf(escudoenemigo1.getValor()));
		edt3.setText(String.valueOf(escudoenemigo2.getValor()));

		// Mostramos barras de vida

		ProgressBar prHeroe = (ProgressBar) findViewById(R.id.progressHeroe);
		ProgressBar prEnemigo = (ProgressBar) findViewById(R.id.progressEnemigo);

		prHeroe.setProgress(jugador.getHeroeJugador().getNivelVida());
		prEnemigo.setProgress(enemigo.getNivelVida());

		// Ponemos los escudos

		// Mostramos los escudos del Jugador

		ImageView imv1 = (ImageView) findViewById(R.id.ivImagenHeroe);
		ImageView imv2 = (ImageView) findViewById(R.id.ivImagenEjercito1);
		ImageView imv3 = (ImageView) findViewById(R.id.ivImagenEjercito2);

		String ruta_imagen = escudoheroe.getRuta();
		int id = getResources().getIdentifier(ruta_imagen, "drawable",
				getPackageName());
		imv1.setImageResource(id);
		;

		ruta_imagen = escudoEjercito1.getRuta();
		id = getResources().getIdentifier(ruta_imagen, "drawable",
				getPackageName());
		imv2.setImageResource(id);

		ruta_imagen = escudoEjercito2.getRuta();
	
		id = getResources().getIdentifier(ruta_imagen, "drawable",
				getPackageName());
		imv3.setImageResource(id);

		// Mostramos los escudos del Enemigo

		imv1 = (ImageView) findViewById(R.id.ivImagenHeroeEnemigo);
		imv2 = (ImageView) findViewById(R.id.ivImagenEjercitoEnemigo1);
		imv3 = (ImageView) findViewById(R.id.ivImagenEjercitoEnemigo2);

		ruta_imagen = escudoheroeenemigo.getRuta();
		id = getResources().getIdentifier(ruta_imagen, "drawable",
				getPackageName());
		imv1.setImageResource(id);

		ruta_imagen = escudoenemigo1.getRuta();
		id = getResources().getIdentifier(ruta_imagen, "drawable",
				getPackageName());
		imv2.setImageResource(id);

		ruta_imagen = escudoenemigo2.getRuta();
		id = getResources().getIdentifier(ruta_imagen, "drawable",
				getPackageName());
		imv3.setImageResource(id);

	}

}
