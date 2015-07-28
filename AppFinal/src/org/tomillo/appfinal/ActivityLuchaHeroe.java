package org.tomillo.appfinal;

import org.tomillo.appfinal.Escudo.GrupoEscudo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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

	boolean bMagia;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		// Iniciamos finales de Lucha
		bGanoHeroe = false;
		bGanoEnemigo = false;
		bMurioEnemigo = false;
		bMurioHeroe = false;
		bEmpate = false;
		bMagia = false;

		jugador = (Jugador) getIntent().getSerializableExtra(
				"PARCELABLE_Jugador");
		enemigo = (Enemigo) getIntent().getSerializableExtra(
				"PARCELABLE_Enemigo");

		// Vemos si murio alguien
		bVivoEnemigo = enemigo.isbVivo();
		bVivoHeroe = jugador.getHeroeJugador().isbVivo();

		// Algun componenente muerto
		if (!bVivoEnemigo || !bVivoHeroe) {

			Intent it = new Intent(this, MenuEscudos.class);
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
					jugador.ActualizarPuntuacionHeroe();
					bMurioEnemigo = true;
					enemigo.setbVivo(false);
					Toast toas = Toast.makeText(this,
							"Has derrotado al enemigo. Ha muerto", 3000);
					toas.setGravity(Gravity.CENTER, 0, 0);
					toas.show();

				} else {
					bGanoHeroe = true;
					jugador.ActualizarPuntuacionHeroe();
					Toast tos = Toast.makeText(this, "Ha vencido el Héroe!!!!",
							3000);
					tos.setGravity(Gravity.CENTER, 0, 0);
					tos.show();
				}
				MostrarDatos();

				Intent it = new Intent(this, MenuEscudos.class);
				Log.i("Despues de luchar heroe estas son las victorias",
						String.valueOf(jugador.getVictorias_actuales()));
				it.putExtra("PARCELABLE_Jugador", jugador);
				it.putExtra("PARCELABLE_Enemigo", enemigo);
				startActivity(it);
				finish();

			}

			if (resultado == 0) {
				Toast toas = Toast.makeText(this,
						"El ataque quedo empate. Se luchará la defensa", 2000);
				toas.show();
				bAtaque = false;
				// DEFENSA
				// poner imagen de boton de defensa
				ImageButton btn = (ImageButton) findViewById(R.id.ibAtaque);
				btn.setBackground(getResources().getDrawable(
						R.drawable.btdefensa));

				ImageButton btn1 = (ImageButton) findViewById(R.id.ibMagia);
				btn1.setBackground(getResources().getDrawable(
						R.drawable.btdefensamagia));

			}

			// Gano el enemigo
			if (resultado == -1) {
				Toast toas = Toast
						.makeText(this, "Ha vencido el enemigo", 3000);
				toas.setGravity(Gravity.CENTER, 0, 0);
				toas.show();
				enemigo.AumentarVictorias();

				// Cogemos el nivel de vida enemigo
				int vidaActualHeroe = jugador.getHeroeJugador().getNivelVida();
				// Atacamos
				int vidadevuelta = jugador.getHeroeJugador()
						.PostDerrotaDefender(vidaActualHeroe, fuerzaHeroe,
								fuerzaEjercito1, fuerzaEjercito2,
								fuerzaEnemigo, fuerzaEjercitoEnemigo1,
								fuerzaEjercitoEnemigo2);

				jugador.getHeroeJugador().setNivelVida(vidadevuelta);

				// Vida de enemigo a cero
				if (vidadevuelta == 0) {
					bVivoHeroe = false;
					// MURIO EL HEROE
					bMurioHeroe = true;
					jugador.getHeroeJugador().setbVivo(false);
					Toast t = Toast.makeText(this, "Has muerto", 3000);
					toas.setGravity(Gravity.CENTER, 0, 0);
					t.show();

				}
				// else {
				// bGanoEnemigo = true;
				// Toast tos = Toast.makeText(this, "Ha vencido el Enemigo",
				// 3000);
				// toas.setGravity(Gravity.CENTER, 0, 0);
				// tos.show();
				// }
				MostrarDatos();
				Intent it = new Intent(this, MenuEscudos.class);
				it.putExtra("PARCELABLE_Jugador", jugador);
				it.putExtra("PARCELABLE_Enemigo", enemigo);
				startActivity(it);
				finish();

			}

		} else {

			// Defensa
			int fuerzaTorre = jugador.getEscudoTorre().getValor();
			int resultado = jugador.getHeroeJugador().Defender(fuerzaTorre,
					fuerzaHeroe, fuerzaEjercito1, fuerzaEjercito2,
					fuerzaEnemigo, fuerzaEjercitoEnemigo1,
					fuerzaEjercitoEnemigo2);
			if (resultado == 1) {

				// Aumentamos victoria y valor de torre
				jugador.AumentarVictorias();
				jugador.AumentarBankias(2);
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
					jugador.ActualizarPuntuacionHeroe();
					bMurioEnemigo = true;
					Toast toas = Toast.makeText(this,
							"Has derrotado al enemigo. Ha muerto", 3000);
					toas.setGravity(Gravity.CENTER, 0, 0);
					toas.show();

				} else {
					bGanoHeroe = true;
					jugador.ActualizarPuntuacionHeroe();
					int aux = Torre.getValor();
					jugador.setPuntuacion(jugador.getPuntuacion() + aux);
					Toast tos = Toast.makeText(this, "Ha vencido el Héroe!!!!",
							3000);
					tos.setGravity(Gravity.CENTER, 0, 0);
					tos.show();
				}
				MostrarDatos();

				Intent it = new Intent(this, MenuEscudos.class);
				it.putExtra("PARCELABLE_Jugador", jugador);
				it.putExtra("PARCELABLE_Enemigo", enemigo);
				startActivity(it);
				finish();

			}

			if (resultado == 0) {
				Toast toas = Toast.makeText(this, "La defensa quedo empate.",
						2000);
				toas.setGravity(Gravity.CENTER, 0, 0);
				toas.show();

				jugador.AumentarBankias(2);
				Intent it = new Intent(this, MenuEscudos.class);
				it.putExtra("PARCELABLE_Jugador", jugador);
				it.putExtra("PARCELABLE_Enemigo", enemigo);
				startActivity(it);
				finish();

			}

			// Gano el enemigo
			if (resultado == -1) {
				Toast toas = Toast
						.makeText(this, "Ha vencido el enemigo", 3000);
				toas.setGravity(Gravity.CENTER, 0, 0);
				toas.show();
				enemigo.AumentarVictorias();

				// Restamos un punto a la torre
				jugador.getEscudoTorre().setValor(
						jugador.getEscudoTorre().getValor() - 1);

				// Cogemos el nivel de vida enemigo
				int vidaActualHeroe = jugador.getHeroeJugador().getNivelVida();
				// Atacamos
				int vidadevuelta = jugador.getHeroeJugador()
						.PostDerrotaDefender(vidaActualHeroe, fuerzaHeroe,
								fuerzaEjercito1, fuerzaEjercito2,
								fuerzaEnemigo, fuerzaEjercitoEnemigo1,
								fuerzaEjercitoEnemigo2);

				if (bMagia) {

					int aux = vidaActualHeroe - vidadevuelta;
					vidaActualHeroe += aux / 2;

				}

				jugador.getHeroeJugador().setNivelVida(vidadevuelta);

				// Vida de enemigo a cero
				if (vidadevuelta == 0) {
					bVivoHeroe = false;
					// MURIO EL HEROE
					bMurioHeroe = true;
					Toast t = Toast.makeText(this, "Tu héroe ha muerto", 3000);
					t.setGravity(Gravity.CENTER, 0, 0);
					t.show();

				} else {
					bGanoEnemigo = true;
					// Toast tos = Toast.makeText(this, "Ha vencido el Enemigo",
					// 3000);
					// tos.setGravity(Gravity.CENTER, 0, 0);
					// tos.show();
					// Intent it = new Intent(this, MenuEscudos.class);
					// Log.i("Despues de luchar heroe estas son las victorias",
					// String.valueOf(jugador.getVictorias_actuales()));
					// it.putExtra("PARCELABLE_Jugador", jugador);
					// it.putExtra("PARCELABLE_Enemigo", enemigo);
					// startActivity(it);
				}
				MostrarDatos();
				Intent it = new Intent(this, MenuEscudos.class);
				Log.i("Despues de luchar heroe estas son las victorias",
						String.valueOf(jugador.getVictorias_actuales()));
				it.putExtra("PARCELABLE_Jugador", jugador);
				it.putExtra("PARCELABLE_Enemigo", enemigo);
				startActivity(it);
				finish();

			}

		}
	}

	public void magia(View v) {

		// int fuerzaHeroe = escudoheroe.getValor();
		// int fuerzaEjercito1 = escudoEjercito1.getValor();
		// int fuerzaEjercito2 = escudoEjercito2.getValor();
		// int fuerzaEnemigo = escudoheroeenemigo.getValor();
		// int fuerzaEjercitoEnemigo1 = escudoenemigo1.getValor();
		// int fuerzaEjercitoEnemigo2 = escudoenemigo2.getValor();

		if (bAtaque) {

			// boton de magia de ataque

			if (jugador.getHeroeJugador().getMagias_Ataque() <= 0) {
				Toast to = Toast.makeText(this,
						"No te quedan magias de ataque", 1500);
				to.setGravity(Gravity.CENTER, 0, 0);
				to.show();
			} else {
				int magiasAtaque = jugador.getHeroeJugador().getMagias_Ataque();
				magiasAtaque--;
				jugador.getHeroeJugador().setMagias_Ataque(magiasAtaque);

			}
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

		} else {
			if (jugador.getHeroeJugador().getMagias_Defensa() > 0) {
				bMagia = true;
				int aux = jugador.getHeroeJugador().getMagias_Defensa();
				aux--;
				jugador.getHeroeJugador().setMagias_Defensa(aux);
				Toast to = Toast.makeText(this, "Has usado magia de defensa",
						2000);
				to.setGravity(Gravity.CENTER, 0, 0);
				to.show();
			} else {
				Toast to = Toast.makeText(this, "No tiene magias de defensa",
						1500);
				to.setGravity(Gravity.CENTER, 0, 0);
				to.show();
			}

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

		edt1.setText(String.valueOf(escudoheroe.getValor()));
		edt2.setText(String.valueOf(escudoEjercito1.getValor()));
		edt3.setText(String.valueOf(escudoEjercito2.getValor()));

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
