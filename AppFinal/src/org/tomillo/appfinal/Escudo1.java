package org.tomillo.appfinal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class Escudo1 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_escudo);

        Thread timer = new Thread(){         
            public void run(){
                try{
                    sleep(5000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    Intent actividaPrincipal = new Intent(Escudo1.this, LoginActivity.class);
                    startActivity(actividaPrincipal);
                    finish();
                }                
            }
        };
        timer.start();	
	}	
}
