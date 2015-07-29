package org.tomillo.appfinal;

import java.sql.SQLData;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);

	}

	public void Registrar(View v) {

		EditText txNombre = (EditText) findViewById(R.id.Nombre);
		String nombreBDD = txNombre.getText().toString();

		if (nombreBDD.length() > 0) {
			UsuariosSQLiteHelper usdbh = new UsuariosSQLiteHelper(this,
					"DBUsuarios", null, 1);

			SQLiteDatabase db = usdbh.getWritableDatabase();

			String nombre = null;

			// Si hemos abierto correctamente la base de datos
			if (db != null) {

				try { // select nombre from Usuarios where nombre='xxxxx'
					Cursor c = db.rawQuery(
							"select nombre from Usuarios where nombre='"
									+ nombreBDD + "'", null);
					c.moveToFirst();

					nombre = c.getString(c.getColumnIndex("Nombre"));

				} catch (Exception e) {
					e.printStackTrace();
				}
				if (nombre == null) {
					int record = 0;
					int bankias = 0;

					// Insertamos los datos en la tabla Usuarios
					db.execSQL("INSERT INTO Usuarios (Nombre, record, bankias) "
							+ "VALUES ('"
							+ nombreBDD
							+ "', "
							+ record
							+ ", "
							+ bankias + ")");
					Intent i = new Intent(this, MenuPrincipal.class);
					i.putExtra("claveNombreJugador", nombreBDD);
					startActivity(i);
					finish();
				} else {
					AlertDialog.Builder dialog = new AlertDialog.Builder(this);
					dialog.setTitle("Error de autenticación");
					dialog.setMessage("Ya existe ese usuario");
					dialog.create();
					dialog.show();
				}
			}

			// Cerramos la base de datos
			db.close();
		}
		else {
			AlertDialog.Builder dialog = new AlertDialog.Builder(this);
			dialog.setTitle("Error");
			dialog.setMessage("Introduce un usuario");
			dialog.create();
			dialog.show();
			
		}
	}

	public void OnClick(View v) {

		EditText txNombre = (EditText) findViewById(R.id.Nombre);

		String intro = txNombre.getText().toString();

		UsuariosSQLiteHelper usdbh = new UsuariosSQLiteHelper(this,
				"DBUsuarios", null, 1);

		SQLiteDatabase db = usdbh.getReadableDatabase();

		String nombre = null;

		try { 	Cursor c = db.rawQuery("select nombre from Usuarios where Nombre='"	+ intro + "'", null);
			c.moveToFirst();
			nombre = c.getString(c.getColumnIndex("Nombre"));
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (nombre != null) {
			// Salta a la ventana de bienvenida
			Intent i = new Intent(this, MenuPrincipal.class);
			i.putExtra("claveNombreJugador", nombre);
			startActivity(i);
			finish();
		} else {
			AlertDialog.Builder dialog = new AlertDialog.Builder(this);
			dialog.setTitle("Error de autenticación");
			dialog.setMessage("Nombre no encontrado");
			dialog.create();
			dialog.show();
		}
	}
}