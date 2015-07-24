package org.tomillo.appfinal;



import android.app.Activity;
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
		
		Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.castillo);
		myVideoView.setVideoURI(uri);
		
		myVideoView.start();
		
		//para recibir el intent de puntuacion, nº partidas, nº bankias:
		//recibimos un valor y se lo asignamos a un string
		String PuntosGanados=getIntent().getExtras().getString("clavePuntuacion");
		//le establecemos el valor recibido de puntuacion
		puntuacionGanada.setText(PuntosGanados);
		
		String numPartidas=getIntent().getExtras().getString("claveNumPartidas");
		numPartidasGanadas.setText(numPartidas);
		
		String bankia=getIntent().getExtras().getString("claveBankia");
		numBankias.setText(bankia);
		
				
	}
	
	

	
}
