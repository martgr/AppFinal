package org.tomillo.appfinal;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.VideoView;

public class Pantalla_finalperdido extends Activity {

	private VideoView myVideoView;
	TextView puntuacionPerdido, numPartidasPerdidas, numBankias;

	@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_pantalla_finalperdido);
			myVideoView = (VideoView) findViewById(R.id.videoPerdido);
			puntuacionPerdido=(TextView)findViewById(R.id.txPuntuacionp);
			numPartidasPerdidas=(TextView)findViewById(R.id.txNumPartidasp);
			numBankias=(TextView)findViewById(R.id.txBankiasp);
			
			//Crear video y visualizarlo
			Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.castlederrota);
			myVideoView.setVideoURI(uri);
			//Lanzar video
			myVideoView.start();	
			
			// para recibir valores de puntuacion, nº partidas y bankias:
			
			//Recibimos y asignamos a un String el valor recibido
			int puntosPerdidos=getIntent().getExtras().getInt("clavePuntuacion");
			//le establecemos el valor recibido de puntuacion:
			puntuacionPerdido.setText("Puntuacion: "+String.valueOf(puntosPerdidos));
			
			int bankia=getIntent().getExtras().getInt("claveBankia");
			numBankias.setText("Nº de bankias: "+String.valueOf(bankia));
			
			int numPartidas=getIntent().getExtras().getInt("claveNumPartidas");
			numPartidasPerdidas.setText("Nº de partidas: "+String.valueOf(numPartidas));
			
			final String nombreJugador=getIntent().getExtras().getString("claveNombreJugador");
			
			myVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

				@Override
				public void onCompletion(MediaPlayer mp) {
					// TODO Auto-generated method stub
					Intent i = new Intent(getApplicationContext(), MenuPrincipal.class);
					i.putExtra("claveNombreJugador", nombreJugador);
					startActivity(i);
					finish();
				}
				
			});
			
	}
	
	
}
	