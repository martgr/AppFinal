package org.tomillo.appfinal;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class ServicioMusica extends Service {
	 MediaPlayer reproductor;
	 public void onCreate(){
		 reproductor= MediaPlayer.create(this, R.raw.audio);
	 }
	 public int onStartCommand(Intent intent, int flags, int idArranque) {
		 reproductor.setLooping(true);
		 reproductor.start();
		 return START_STICKY;
	 };
	 
	 public void onDestroy() {
		 reproductor.stop();
	 };
	@Override
	public IBinder onBind(Intent intent) {
		
		return null;
	}

}
