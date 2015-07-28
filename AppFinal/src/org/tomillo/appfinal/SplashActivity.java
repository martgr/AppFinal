package org.tomillo.appfinal;

import org.tomillo.appfinal.R.id;

import android.app.Activity;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


public class SplashActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
	
		final ImageButton ftomillo = (ImageButton)findViewById(id.BtnTomillo);
		ftomillo.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent linkTomillo = new Intent (Intent.ACTION_VIEW);
				linkTomillo.setData(Uri.parse("http://www.tomillo.org/v_portal/apartados/apartado.asp"));
				startActivity(linkTomillo);
				
			}
		});
		final ImageButton fmontemadrid = (ImageButton)findViewById(id.BtnMonteMadrid);
		fmontemadrid.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent linkMontemadrid = new Intent (Intent.ACTION_VIEW);
				linkMontemadrid.setData(Uri.parse("http://www.fundacionmontemadrid.es/"));
				startActivity(linkMontemadrid);
				
			}
		});
		final ImageButton Bankia = (ImageButton)findViewById(id.BtnBankia);
		Bankia.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent linkBankia = new Intent (Intent.ACTION_VIEW);
				linkBankia.setData(Uri.parse("http://www.bankia.com"));
				startActivity(linkBankia);
				
			}
		});
		final ImageButton logo = (ImageButton)findViewById(id.ImgBtnLogo);
		logo.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent Logo = new Intent (SplashActivity.this,Escudo.class);
				startActivity(Logo);
				finish();
				
			}
		});
	}

}
