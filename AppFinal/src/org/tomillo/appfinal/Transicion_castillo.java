package org.tomillo.appfinal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Transicion_castillo extends Activity {

	ImageView imgCastillo;
	TextView txAsalto;


	Animation animFadein;
	Animation animFadeout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transicion_castillo);

		imgCastillo = (ImageView) findViewById(R.id.imgCastillo);
		txAsalto = (TextView) findViewById(R.id.txAsalto);


		animFadein = AnimationUtils.loadAnimation(getApplicationContext(),
				R.anim.fade_in);

		animFadein.setRepeatMode(Animation.RESTART);
		animFadein.setRepeatCount(Animation.INFINITE);
		imgCastillo.startAnimation(animFadein);
		txAsalto.startAnimation(animFadein);
		
		final Jugador jugador=(Jugador) getIntent().getSerializableExtra("PARCELABLE_Jugador");
		final Enemigo enemigo = (Enemigo) getIntent().getSerializableExtra("PARCELABLE_Enemigo");

		Log.i("Pasamos por transicion Castillo con las siguientes victorias", String.valueOf(jugador.getVictorias_actuales()));
		
		 new Handler().postDelayed(new Runnable(){
	            public void run(){
			
	            	Intent intent = new Intent(Transicion_castillo.this, ActivityCastillo.class);
	        		intent.putExtra("PARCELABLE_Jugador", jugador);
	        		intent.putExtra("PARCELABLE_Enemigo", enemigo);
	        		startActivity(intent);
	        		finish();
	            };
	        }, 4000);


	}

}