package org.tomillo.appfinal;

import org.tomillo.appfinal.Escudo.GrupoEscudo;

import android.app.Activity;
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
	int puntosEnemigo=0;

	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_castillo);
		
		//Descomentar cuando se pruebe app final
		
		// Recogemos heroe y enemigo
//				jugador = getIntent().getParcelableExtra("PARCELABLE_Jugador");
//				enemigo = getIntent().getParcelableExtra("PARCELABLE_Enemigo");
//				
		
		//SÓLO PARA PRUEBAS
		jugador = new Jugador();
		enemigo = new Enemigo();
		
		
		
		
		Torre = jugador.getEscudoTorre();
		Rey = jugador.getEscudoRey();
		Nobleza = jugador.getEscudoNobleza();
		Pueblo = jugador.getEscudoPueblo();
		
		//Creación aleatoria de los escudos enemigos
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
		final int aux2 = (int) Math.random() * 11 + 1;
		ReyEnemigo = new Escudo(aux2, GrupoEscudo.rey);
		final int aux3 = (int) Math.random() * 11 + 1;
		NoblezaEnemigo = new Escudo(aux3, GrupoEscudo.nobleza);
		final int aux4 = (int) Math.random() * 5 + 1;
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
				
				if (jugador.LuchaEntreReyes(Rey, ReyEnemigo)==1)
					puntosHeroe+=3;
					
				else
					puntosEnemigo+=3;
				
				if (jugador.LuchaEntreNobles(Nobleza, NoblezaEnemigo)==1)
					puntosHeroe+=2;
				else
					puntosEnemigo+=2;
				
				if (jugador.LuchaEntrePueblo(Pueblo, PuebloEnemigo)==1)
					puntosHeroe++;
				else
					puntosEnemigo++;
	
				
				
				//Tanto si se gana como si se pierde se va a la actividad TransitoriaHeroe
		
					if (puntosHeroe>puntosEnemigo) {
						
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
			
				
						Toast.makeText(getApplicationContext(), "Prueba", Toast.LENGTH_LONG).show();
						// Lanzamos Heroe
						mostrarToast(aux2,aux3,aux4);
					
				
					   
						Intent intent = new Intent(ActivityCastillo.this,ActivityLuchaHeroe.class);
						intent.putExtra("PARCELABLE_Jugador",jugador);
						intent.putExtra("PARCELABLE_Enemigo",enemigo);
				
						startActivity(intent);
					
					
					}
				
			}
		);
		
		ImageView ivact = (ImageView) findViewById(R.id.ivCambiarCartas);
		ivact.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

				if (jugador.getNroActualizacionesCastillo()>0) {
					
					jugador.setNroActualizacionesCastillo(jugador.getNroActualizacionesCastillo()-1);
					
					jugador.CambiarCartasCastillo();
					
					MostrarDatos();
					
				}
			}
		});
		
	}

	private void MostrarDatos() {
		// Ponemos las imagenes de los escudos del jugador
		
	
		String ruta_imagen = Torre.getRuta();


		
		// Mostramos valores de las cartas del jugador
 
		TextView edt1 = (TextView) findViewById(R.id.tvTorre);
		TextView edt2 = (TextView) findViewById(R.id.tvRey);
		TextView edt3 = (TextView) findViewById(R.id.tvNobleza);
		TextView edt4 = (TextView) findViewById(R.id.tvPueblo);


		edt1.setText(String.valueOf(Torre.getValor()));
		edt2.setText(Integer.toString(Rey.getValor()));
		edt3.setText(String.valueOf(Nobleza.getValor()));
		edt4.setText(String.valueOf(Pueblo.getValor()));

	
		
		// Mostramos los escudos del Jugador
	  int id = getResources().getIdentifier(ruta_imagen, "drawable", getPackageName());	
	
		ImageView imv1 = (ImageView) findViewById(R.id.ivTorre);
		ImageView imv2 = (ImageView) findViewById(R.id.ivRey);
		ImageView imv3 = (ImageView) findViewById(R.id.ivNobleza);
		ImageView imv4 = (ImageView) findViewById(R.id.ivPueblo);


//		String ruta_imagenn = Torre.getRuta();
		 id = getResources().getIdentifier(ruta_imagen, "drawable", getPackageName());		
		imv1.setImageResource(id);
		

		
		ruta_imagen = Rey.getRuta();
		id = getResources().getIdentifier(ruta_imagen, "drawable", getPackageName());
		imv2.setImageResource(id);

		ruta_imagen = Nobleza.getRuta();
		id = getResources().getIdentifier(ruta_imagen, "drawable", getPackageName());
		imv3.setImageResource(id);

		ruta_imagen = Pueblo.getRuta();
		id = getResources().getIdentifier(ruta_imagen, "drawable", getPackageName());
		imv4.setImageResource(id);

		// Ponemos las imagenes del enemigo

		imv1 = (ImageView) findViewById(R.id.ivReyEnemigo);
		imv2 = (ImageView) findViewById(R.id.ivNoblezaEnemiga);
		imv3 = (ImageView) findViewById(R.id.ivPuebloEnemigo);

		ruta_imagen = ReyEnemigo.getRuta();
		id = getResources().getIdentifier(ruta_imagen, "drawable", getPackageName());		
		imv1.setImageResource(id);
			
		ruta_imagen = NoblezaEnemigo.getRuta();
		id = getResources().getIdentifier(ruta_imagen, "drawable", getPackageName());		
		imv2.setImageResource(id);
		
		ruta_imagen = PuebloEnemigo.getRuta();
		id = getResources().getIdentifier(ruta_imagen, "drawable", getPackageName());		
		imv3.setImageResource(id);


	}

	
	public void mostrarToast(int reyEnem,int noblezaEnem,int puebloEnem){
		

		Toast.makeText(getApplicationContext(),"Valor escudo Rey: "+" Valor escudo Nobleza:"+" Valor Escudo Pueblo",5000).show();
		 Toast toast=Toast.makeText(getApplicationContext(),"Valor escudo Rey: "+reyEnem+" Valor escudo Nobleza:"+noblezaEnem+" Valor Escudo Pueblo"+ puebloEnem,5000);
		 toast.setGravity(Gravity.CENTER, 15, 15); 
		 toast.show();
		 
	
		
		
	}
	
}