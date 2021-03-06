package org.tomillo.appfinal;



import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
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
		//miJugador.setNumJugadas(mijJugador.getNumJugadas()+1);
		
		final String usuario;
		    Bundle extras = getIntent().getExtras();
		    if(extras == null) {
		    	usuario= null;
		    } else {
		    	usuario= extras.getString("claveNombreJugador");
		    }
		final ImageButton preferencias = (ImageButton)findViewById(R.id.ImgBtnConfi);
		preferencias.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			Intent configu= new Intent(MenuPrincipal.this, Preferencias.class);
			startActivity(configu);
			}
		});
		
		final ImageButton records = (ImageButton)findViewById(R.id.ImgBtnRecords);
		records.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			Intent puntuacion= new Intent(MenuPrincipal.this, Records.class);
			startActivity(puntuacion);
			}
		});
			
		//Terminar cuando tengamos pantalla principal
		final ImageButton juego = (ImageButton)findViewById(R.id.ImgBtnJugar);
		juego.setOnClickListener(new View.OnClickListener() {
			 
			@Override
			public void onClick(View v) {
			Intent jugar= new Intent(MenuPrincipal.this,MenuEscudos.class);
            jugar.putExtra( "claveNombreJugador", usuario);
            startActivity(jugar);
			}
		});
		
		final ImageButton salir = (ImageButton)findViewById(R.id.ImgBtnSalir);
		salir.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			stopService(new Intent(getApplicationContext(),ServicioMusica.class));			
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
		inflater.inflate(R.menu.mainmenu , menu);
		
		return true;
	}
//mirar
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.Acerca:
	        Toast toast1 =
            Toast.makeText(getApplicationContext(),
                    "App creada por el grupo de java y android.", Toast.LENGTH_SHORT);
        toast1.show();
			break;
		case R.id.Ayuda:
			 Intent Ayuda = new	 Intent(this,Ayuda.class);
			 startActivity(Ayuda);
			break;
		case R.id.Creditos:
			Intent nombreclase = new Intent (this,Creditos.class);
			startActivity(nombreclase);
			break;
		
		default:
			return super.onOptionsItemSelected(item);
		}
		return true;
}
}