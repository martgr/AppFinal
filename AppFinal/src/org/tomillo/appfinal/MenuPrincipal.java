package org.tomillo.appfinal;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MenuPrincipal extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.jugar);
		// miJugador.setNumJugadas(mijJugador.getNumJugadas()+1);

		final String usuario = getIntent().getExtras().getString(
				"claveNombreJugador");

		final ImageButton preferencias = (ImageButton) findViewById(R.id.ImgBtnConfi);
		preferencias.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent configu = new Intent(MenuPrincipal.this,
						Preferencias.class);
				startActivity(configu);
			}
		});
		
		

		/*
		 * crear nuevo layout con puntuaciones final ImageButton records =
		 * (ImageButton)findViewById(id.ImgBtnRecords);
		 * records.setOnClickListener(new View.OnClickListener() {
		 * 
		 * /@Override public void onClick(View v) { Intent puntuacion= new
		 * Intent(MenuPrincipal.this, km.class); startActivity(puntuacion); }
		 * });
		 */
		// Terminar cuando tengamos pantalla principal
		final ImageButton juego = (ImageButton) findViewById(R.id.ImgBtnJugar);
		juego.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent jugar = new Intent(MenuPrincipal.this, MenuEscudos.class);
				Log.i("1", "lalalalala");
				jugar.putExtra("claveNombreJugador", usuario);
				Log.i("2", "lalalalala");
				startActivity(jugar);
			}
		});
		final ImageButton salir = (ImageButton) findViewById(R.id.ImgBtnSalir);
		salir.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
				System.runFinalization();
				System.exit(0);
				MenuPrincipal.this.finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.mainmenu, menu);

		return true;
	}


	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.Acerca:
			Intent Acerca_de = new Intent("");
			break;

		case R.id.Ayuda:
			Intent Ayuda = new Intent("");
			break;

		default:
			return super.onOptionsItemSelected(item);
		}
		return true;
	}
}