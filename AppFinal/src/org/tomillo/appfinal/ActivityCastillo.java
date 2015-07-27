package org.tomillo.appfinal;

import org.tomillo.appfinal.Escudo.GrupoEscudo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityCastillo extends Activity {

	boolean bAtaque = true;

	Jugador jugador;
	Enemigo enemigo;
	Heroe heroe;

	// Escudos en el juego
	Escudo Torre;
	Escudo Rey;
	Escudo Nobleza;
	Escudo Pueblo;
	Escudo ReyEnemigo;
	Escudo NoblezaEnemigo;
	Escudo PuebloEnemigo;

	// Puntuaciones de cada jugador o enemigo
	int puntosHeroe = 0;
	int puntosEnemigo = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_castillo);

		// Descomentar cuando se pruebe app final

		// Recogemos heroe y enemigo
		 jugador = (Jugador) getIntent().getSerializableExtra("PARCELABLE_Jugador");
		 enemigo = (Enemigo) getIntent().getSerializableExtra("PARCELABLE_Enemigo");
		

	// SÓLO PARA PRUEBAS
		//jugador = new Jugador();
		//enemigo = new Enemigo();

		Torre = jugador.getEscudoTorre();
		Rey = jugador.getEscudoRey();
		Nobleza = jugador.getEscudoNobleza();
		Pueblo = jugador.getEscudoPueblo();

		// Creación aleatoria de los escudos enemigos
		int aux = (int) Math.random() * 11 + 1;
		ReyEnemigo = new Escudo(aux, Escudo.GrupoEscudo.rey);
		// Asignamos el escudo de la nobleza
		aux = (int) Math.random() * 11 + 1;
		NoblezaEnemigo = new Escudo(aux, Escudo.GrupoEscudo.nobleza);
		// Asignamos el escudo del pueblo
		aux = (int) Math.random() * 5 + 1;
		PuebloEnemigo = new Escudo(aux, Escudo.GrupoEscudo.pueblo);

		// Cambiamos las cartas
		jugador.CambiarCartasCastillo();

		// cargar escudos enemigo
		//
		final int aux2 = (int) (Math.random() * 12) + 1;
		ReyEnemigo = new Escudo(aux2, GrupoEscudo.rey);
		final int aux3 = (int) (Math.random() * 12) + 1;
		NoblezaEnemigo = new Escudo(aux3, GrupoEscudo.nobleza);
		final int aux4 = (int) (Math.random() * 6) + 1;
		PuebloEnemigo = new Escudo(aux4, GrupoEscudo.pueblo);

		Torre = jugador.getEscudoTorre();
		Rey = jugador.getEscudoRey();
		Nobleza = jugador.getEscudoNobleza();
		Pueblo = jugador.getEscudoPueblo();

		// Puntuaciones de cada jugador y ordenador

		MostrarDatos();

		ImageView iv = (ImageView) findViewById(R.id.ivAccionCastillo);
		iv.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				if (jugador.LuchaEntreReyes(Rey, ReyEnemigo) == 1)
					puntosHeroe += 3;

				else
					puntosEnemigo += 3;

				if (jugador.LuchaEntreNobles(Nobleza, NoblezaEnemigo) == 1)
					puntosHeroe += 2;
				else
					puntosEnemigo += 2;

				if (jugador.LuchaEntrePueblo(Pueblo, PuebloEnemigo) == 1)
					puntosHeroe++;
				else
					puntosEnemigo++;

				// Tanto si se gana como si se pierde se va a la actividad
				// TransitoriaHeroe
				
				

				Log.i("Puntos heroe", String.valueOf(puntosHeroe));
				Log.i("Puntos enemigo", String.valueOf(puntosEnemigo));
				if (puntosHeroe > puntosEnemigo) {

					int aux = jugador.getEscudoTorre().getValor();
					aux++;
					jugador.getEscudoTorre().setValor(aux);

					jugador.AumentarVictorias();
					

				} else {

					int aux = jugador.getEscudoTorre().getValor();
					aux--;
					jugador.getEscudoTorre().setValor(aux);

					enemigo.AumentarVictorias();
				}

	
				// Lanzamos Heroe
				mostrarDialogo(aux2, aux3, aux4);

				Intent intent = new Intent(ActivityCastillo.this,
						Transicion_Heroe.class);
				intent.putExtra("PARCELABLE_Jugador", jugador);
				intent.putExtra("PARCELABLE_Enemigo", enemigo);

				startActivity(intent);

			}

		});

		ImageView ivact = (ImageView) findViewById(R.id.ivCambiarCartas);
		ivact.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
					Log.i("Hemos llamado a remover", "Hemos llamado a remover");
				
				if (jugador.getNroActualizacionesCastillo() > 0) {

					jugador.setNroActualizacionesCastillo(jugador
							.getNroActualizacionesCastillo() - 1);

					Log.i("Pueblo sin cambiada", jugador.getEscudoPueblo().getRuta());
					jugador.CambiarCartasCastillo();

					Log.i("Pueblo cambiada", jugador.getEscudoPueblo().getRuta());
					MostrarDatos();

				}
			}
		});

	}

	private void MostrarDatos() {
		// Ponemos las imagenes de los escudos del jugador

		String ruta_imagen = jugador.getEscudoTorre().getRuta();

		// Mostramos valores de las cartas del jugador

		TextView edt1 = (TextView) findViewById(R.id.tvTorre);
		TextView edt2 = (TextView) findViewById(R.id.tvRey);
		TextView edt3 = (TextView) findViewById(R.id.tvNobleza);
		TextView edt4 = (TextView) findViewById(R.id.tvPueblo);

		edt1.setText(String.valueOf(jugador.getEscudoTorre().getValor()));
		edt2.setText(Integer.toString(jugador.getEscudoRey().getValor()));
		edt3.setText(String.valueOf(jugador.getEscudoNobleza().getValor()));
		edt4.setText(String.valueOf(jugador.getEscudoPueblo().getValor()));

		// Mostramos los escudos del Jugador
		int id = getResources().getIdentifier(ruta_imagen, "drawable",
				getPackageName());

		ImageView imv1 = (ImageView) findViewById(R.id.ivTorre);
		ImageView imv2 = (ImageView) findViewById(R.id.ivRey);
		ImageView imv3 = (ImageView) findViewById(R.id.ivNobleza);
		ImageView imv4 = (ImageView) findViewById(R.id.ivPueblo);

		// String ruta_imagenn = Torre.getRuta();
		id = getResources().getIdentifier(ruta_imagen, "drawable",
				getPackageName());
		imv1.setImageResource(id);

		ruta_imagen = Rey.getRuta();
		id = getResources().getIdentifier(ruta_imagen, "drawable",
				getPackageName());
		imv2.setImageResource(id);

		ruta_imagen = jugador.getEscudoNobleza().getRuta();
		id = getResources().getIdentifier(ruta_imagen, "drawable",
				getPackageName());
		imv3.setImageResource(id);

		ruta_imagen = jugador.getEscudoPueblo().getRuta();
		id = getResources().getIdentifier(ruta_imagen, "drawable",
				getPackageName());
		imv4.setImageResource(id);

		// Ponemos las imagenes del enemigo

		imv1 = (ImageView) findViewById(R.id.ivReyEnemigo);
		imv2 = (ImageView) findViewById(R.id.ivNoblezaEnemiga);
		imv3 = (ImageView) findViewById(R.id.ivPuebloEnemigo);

		ruta_imagen = ReyEnemigo.getRuta();
		id = getResources().getIdentifier(ruta_imagen, "drawable",
				getPackageName());
		imv1.setImageResource(id);

		ruta_imagen = NoblezaEnemigo.getRuta();
		id = getResources().getIdentifier(ruta_imagen, "drawable",
				getPackageName());
		imv2.setImageResource(id);

		ruta_imagen = PuebloEnemigo.getRuta();
		id = getResources().getIdentifier(ruta_imagen, "drawable",
				getPackageName());
		imv3.setImageResource(id);

	}
	
	
	public void mostrarDialogo (int rey , int noble , int pueblo) {
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(
		        ActivityCastillo.this);
		alertDialog.setTitle("ATAQUE ENEMIGO!!");
		alertDialog.setMessage("Ataque rey enemigo : " + rey + "\n Ataque noble enemigo : " + noble + "\n Ataque pueblo enemigo : " + pueblo);

		alertDialog.setPositiveButton("Continuar Asedio",
		        new DialogInterface.OnClickListener() {
		          
		            public void onClick(DialogInterface dialogInterface, int i) {
		                
		                Intent myIntent = new Intent(getApplicationContext(),
		                		Transicion_Heroe.class);
		                Bundle b = new Bundle();
		             
		                myIntent.putExtras(b);

		                startActivity(myIntent);
		                
		            }
		        });
		AlertDialog dialog = alertDialog.create();
		alertDialog.show();
	}

	
	



}