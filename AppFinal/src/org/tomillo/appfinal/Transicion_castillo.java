package org.tomillo.appfinal;

import android.app.Activity;
import android.os.Bundle;
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

		

	}

}