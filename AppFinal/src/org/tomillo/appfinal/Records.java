package org.tomillo.appfinal;

import org.tomillo.appfinal.R.id;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;

public class Records extends Activity {
	String[] array;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.record);
		

		
		
		TextView N = (TextView)findViewById(id.Record1);
		
		TextView N1 = (TextView)findViewById(id.Record2);
		
		TextView N2 = (TextView)findViewById(id.Record3);

		TextView N3 = (TextView)findViewById(id.Record4);

		
		array = new String [4];
		

		
		UsuariosSQLiteHelper usdbh =
                new UsuariosSQLiteHelper(this, "DBUsuarios", null, 1);
     
            SQLiteDatabase db = usdbh.getReadableDatabase();
            
             int i=0;

            Cursor cursor = db.rawQuery("select * from Usuarios order by record desc limit(4)", null);
            cursor.moveToFirst();
            while (i<cursor.getCount()){            
            		array[i] = cursor.getString(cursor.getColumnIndex("Nombre"));
            		array[i] += "\t" +"\t"+"\t"+ cursor.getString(cursor.getColumnIndex("record"));
            		array[i] += "\t"+ "\t"+"\t"+"\t" +cursor.getString(cursor.getColumnIndex("bankias"));
            		Log.i("Hemos escrito: ", array[i]);
            		i++;
            		cursor.moveToNext();
            }

       
            N.setText(array[0]);
            N1.setText(array[1]);
            N2.setText(array[2]);
            N3.setText(array[3]);
            
            db.close();
}
}

