package org.tomillo.appfinal;

import android.app.Activity;
import android.content.Intent;
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
			Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.derrota);
			myVideoView.setVideoURI(uri);
			//Lanzar video
			myVideoView.start();	
			
			// para recibir valores de puntuacion, nº partidas y bankias:
			
			//Recibimos y asignamos a un String el valor recibido
			String puntosPerdidos=getIntent().getExtras().getString("clavePuntuacion");
			//le establecemos el valor recibido de puntuacion:
			puntuacionPerdido.setText(puntosPerdidos);
			
			String bankia=getIntent().getExtras().getString("claveBankia");
			numBankias.setText(String.valueOf(bankia));
			
			String numPartidas=getIntent().getExtras().getString("claveNumPartidas");
			numPartidasPerdidas.setText(String.valueOf(numPartidas));
			
			
		}
	}
	