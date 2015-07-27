package org.tomillo.appfinal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Transicion_Heroe extends Activity {

	ImageView imgHeroe;
	TextView txtHeroe;

	
	Animation animrotate;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transicion__heroe);
		
		imgHeroe = (ImageView) findViewById(R.id.imgHeroe);
		txtHeroe = (TextView) findViewById(R.id.textHeroe);
		
		animrotate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
				
		animrotate.setRepeatMode(Animation.RESTART);
		animrotate.setRepeatCount(Animation.INFINITE);
		imgHeroe.startAnimation(animrotate);
		txtHeroe.startAnimation(animrotate);
		
		
		final Jugador jugador=(Jugador) getIntent().getSerializableExtra("PARCELABLE_Jugador");
		final Enemigo enemigo = (Enemigo) getIntent().getSerializableExtra("PARCELABLE_Enemigo");

		Log.i("Pasamos por transicion Heroe con las siguientes victorias", String.valueOf(jugador.getVictorias_actuales()));
		 new Handler().postDelayed(new Runnable(){
	            public void run(){
		
	            	Intent intent = new Intent(Transicion_Heroe.this,
	        				ActivityLuchaHeroe.class);
	        		intent.putExtra("PARCELABLE_Jugador", jugador);
	        		intent.putExtra("PARCELABLE_Enemigo", enemigo);

	        		startActivity(intent);
	        		finish();
	            };
	        }, 4000);
		
		
		
		
		
	


		
	}
}
