package org.tomillo.appfinal;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class MenuEscudos extends Activity {

	// Atributos
	ImageView[] escudos = new ImageView[9];
	RatingBar estrellas;
	TextView titulo, victoriasEnemigo;
	Jugador miJugador;
	Enemigo miEnemigo;
	int estadoJuego;
	ImageButton ibBatalla; 
	TextView txtContadorBankias;
	int puntuacionTotal, bankias, numPartidas;
	String nombreJugador;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i("Antes del setContent","Antes ");
		setContentView(R.layout.activity_menu_escudos);
		 ibBatalla = (ImageButton) findViewById(R.id.Batalla);
		  txtContadorBankias = (TextView) findViewById(R.id.txtContadorBankias);
		
		escudos[0] = (ImageView) findViewById(R.id.Verado);
		escudos[1] = (ImageView) findViewById(R.id.Losanjado);
		escudos[2] = (ImageView) findViewById(R.id.Fuselado);
		escudos[3] = (ImageView) findViewById(R.id.Cantonado);
		escudos[4] = (ImageView) findViewById(R.id.Fretado);
		escudos[5] = (ImageView) findViewById(R.id.Papelonado);
		escudos[6] = (ImageView) findViewById(R.id.Aplumado);
		escudos[7] = (ImageView) findViewById(R.id.Flanqueado);
		escudos[8] = (ImageView) findViewById(R.id.Cortinado);
		estrellas = (RatingBar) findViewById(R.id.ratingBarTitulo);
		titulo = (TextView) findViewById(R.id.txtTitulo);
		victoriasEnemigo = (TextView) findViewById(R.id.txtContadorVictoriasOrdenador);

		// pone todos los escudos a invisible, cada vez que se cree la actividad
		for (int i = 0; i < 9; i++) {
			escudos[i].setVisibility(View.INVISIBLE);
		}

		// Para crear un jugador cada vez que se inicie la partida y recoger el
		// nombre jugador
		if (miJugador == null) {
			miJugador = new Jugador();
		
			nombreJugador = getIntent().getExtras().getString(
					"claveNombreJugador");
			miJugador.setNombreJugador(nombreJugador);
		} else {
			// Obtenemos nuestro Parcelable desde el Intent
			miJugador = (Jugador) getIntent().getExtras().getSerializable("PARCELABLE_Jugador");
			// Para aumentar numero de jugadas en curso:
			miJugador.setNumJugadas(miJugador.getNumJugadas() + 1);
		}
		// Para crear un enemigo cada vez que se inicie la partida
		if (miEnemigo == null) {
			miEnemigo = new Enemigo();
		} else {
			miEnemigo = (Enemigo) getIntent().getExtras().getSerializable("PARCELABLE_Enemigo");
		}

		// se muestra cada vez el nº de escudos ganados
		for (int i = 0; i < miJugador.getVictorias_actuales(); i++) {
			escudos[i].setVisibility(View.VISIBLE);
		}

		mostrarEstrellasYRango();
		//----------------------------------------comprobaciones
		//estrellas.setRating(2);

		// Para que aparezcan los bankias:
		txtContadorBankias.setText(String.valueOf(miJugador.getNroBankias()));

		// Para mostrar las victorias del enemigo:
		victoriasEnemigo.setText(String.valueOf(miEnemigo.getVictorias_actuales()));

		// Para cambiar el estado del juego(ganado, perdido, continuar) y
		// al final del juego cambiar de boton Batalla a boton Fin de Juego
		// y rellenar puntuacionTotal, bankias y numPartidas q se pasaran como
		// putextras a pantalla ganado o pantalla perdido. Tb se consulta a la BBDD el
		// record y si es mayor la puntuacion se actualiza el record.
		if (miJugador.getVictorias_actuales() == 9) {
			estadoJuego = 1;

			puntuacionTotal = miJugador.getPuntuacion()
					/ miJugador.getNumJugadas();
			bankias = miJugador.getNroBankias();
			numPartidas = miJugador.getNumJugadas();

			UsuariosSQLiteHelper usdbh = new UsuariosSQLiteHelper(this,
					"DBUsuarios", null, 1);

			usdbh.actualizarRecord(nombreJugador, puntuacionTotal);

			////DESCOMENTAR CUANDO ESTE EL RECURSO
			ibBatalla.setBackgroundResource(R.drawable.btfin);
		} else if (miEnemigo.getVictorias_actuales() == 9
				|| miJugador.getEscudoTorre().getValor() <= 0) {
			estadoJuego = -1;

			puntuacionTotal = miJugador.getPuntuacion()
					/ miJugador.getNumJugadas();
			bankias = miJugador.getNroBankias();
			numPartidas = miJugador.getNumJugadas();

			//DESCOMENTAR CUANDO ESTE EL RECURSO
			ibBatalla.setBackgroundResource(R.drawable.btfin);
		} else {
			estadoJuego = 0;
			//-----------------------------------comprobaciones
			//ibBatalla.setBackgroundResource(R.drawable.btfin);
			
		}

	}

	public void mostrarEstrellasYRango() {
		// Para que se marquen las estrellas y mostrar el rango:
		if (miJugador.getVictorias_actuales() <= 2) {
			estrellas.setRating(0);
			titulo.setText("Feudal");
		} else {
			if (miJugador.getVictorias_actuales() > 2
					&& miJugador.getVictorias_actuales() <= 5) {
				estrellas.setRating(1);
				titulo.setText(miJugador.DescripcionRango());
			} else {
				if (miJugador.getVictorias_actuales() > 5
						&& miJugador.getVictorias_actuales() <= 8) {
					estrellas.setRating(2);
					titulo.setText(miJugador.DescripcionRango());
				} else {
					estrellas.setRating(3);
					titulo.setText(miJugador.DescripcionRango());
				}
			}
		}
	}

	// Metodo salir
	public void salir(View v) {
		System.exit(0);
		
	}

	
	//DESCOMENTAR CUANDO ESTEN LAS PANTALLAS DEL FINAL
	
	// Metodo batalla
	public void batallaOFinDeJuego(View v) {
		if (estadoJuego == 1) {
			Intent i = new Intent(this, Pantalla_finalganado.class);
			i.putExtra("clavePuntuacion", puntuacionTotal);
			i.putExtra("claveBankia", bankias);
			i.putExtra("claveNumPartidas", numPartidas);
			startActivity(i);
		} else {
			if (estadoJuego == -1) {
				Intent i = new Intent(this, Pantalla_finalperdido.class);
				i.putExtra("clavePuntuacion", puntuacionTotal);
				i.putExtra("claveBankia", bankias);
				i.putExtra("claveNumPartidas", numPartidas);
				startActivity(i);
			} else {
				if (estadoJuego == 0) {
					Intent i = new Intent(this, Transicion_castillo.class);
					i.putExtra("PARCELABLE_Jugador", miJugador);
					i.putExtra("PARCELABLE_Enemigo", miEnemigo);
					startActivity(i);
				}
			}
		}

	}
}
