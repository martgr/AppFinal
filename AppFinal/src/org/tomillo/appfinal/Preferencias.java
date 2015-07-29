package org.tomillo.appfinal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.widget.Toast;

public class Preferencias extends PreferenceActivity {

	@SuppressWarnings("deprecation")
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferencias);
		
		@SuppressWarnings("deprecation")
		final CheckBoxPreference chk = (CheckBoxPreference) findPreference("pref_sonido");
		chk.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
			
			public boolean onPreferenceChange(Preference preference, Object newValue) {
				// TODO Auto-generated method stub

	
				if ( (newValue.toString().equals("false")) ) {
					stopService(new Intent(getApplicationContext(),ServicioMusica.class));
				}
				else
				{
					startService(new Intent(getApplicationContext(),ServicioMusica.class));
				}
				return true;
				
			}
		});

	}
}
