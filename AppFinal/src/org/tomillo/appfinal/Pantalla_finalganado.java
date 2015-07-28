package org.tomillo.appfinal;



import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;
import android.widget.VideoView;

public class Pantalla_finalganado extends Activity{

	private VideoView myVideoView;
	
	TextView puntuacionGanada, numPartidasGanadas, numBankias;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pantalla_finalganado);
		
		puntuacionGanada=(TextView)findViewById(R.id.txPuntuaciong);
		numPartidasGanadas=(TextView)findViewById(R.id.txNumPartidasg);
		numBankias=(TextView)findViewById(R.id.txBankiasg);
		
		myVideoView = (VideoView) findViewById(R.id.videoGanado);
		
		Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.castlevictoria);
		myVideoView.setVideoURI(uri);
		
		myVideoView.start();
		
		//para recibir el intent de puntuacion, nº partidas, nº bankias:
		//recibimos un valor y se lo asignamos a un string
		int PuntosGanados=getIntent().getExtras().getInt("clavePuntuacion");
		//le establecemos el valor recibido de puntuacion
		puntuacionGanada.setText("Puntuacion: "+String.valueOf(PuntosGanados));
		
		int numPartidas=getIntent().getExtras().getInt("claveNumPartidas");
		numPartidasGanadas.setText("Nº de partidas: "+String.valueOf(numPartidas));
		
		int bankia=getIntent().getExtras().getInt("claveBankia");
		numBankias.setText("Nº de bankias: "+String.valueOf(bankia));
		
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
