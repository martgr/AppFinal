package org.tomillo.appfinal;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class Preferencias extends PreferenceActivity {

	protected void onCreate(Bundle savedInstanceState){
	super.onCreate(savedInstanceState);
	addPreferencesFromResource(R.xml.preferencias);
	}

}
