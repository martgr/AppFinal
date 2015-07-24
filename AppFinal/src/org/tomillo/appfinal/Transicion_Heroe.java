package org.tomillo.appfinal;

import android.app.Activity;
import android.os.Bundle;
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


		
	}
}
