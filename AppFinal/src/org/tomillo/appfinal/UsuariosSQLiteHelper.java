package org.tomillo.appfinal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class UsuariosSQLiteHelper extends SQLiteOpenHelper {

	SQLiteDatabase db;
	// Sentencia SQL para crear la tabla de Usuarios
	String sqlCreate = "CREATE TABLE Usuarios (Nombre STRING (50) PRIMARY KEY, record INTEGER, bankias INTEGER)";

	public UsuariosSQLiteHelper(Context contexto, String nombre,
			CursorFactory factory, int version) {
		super(contexto, nombre, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// Se ejecuta la sentencia SQL de creación de la tabla
		db.execSQL(sqlCreate);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int versionAnterior,
			int versionNueva) {
		// NOTA: Por simplicidad del ejemplo aquí utilizamos directamente
		// la opción de eliminar la tabla anterior y crearla de nuevo
		// vacía con el nuevo formato.
		// Sin embargo lo normal será que haya que migrar datos de la
		// tabla antigua a la nueva, por lo que este método debería
		// ser más elaborado.

		// Se elimina la versión anterior de la tabla
		db.execSQL("DROP TABLE IF EXISTS Usuarios");

		// Se crea la nueva versión de la tabla
		db.execSQL(sqlCreate);
	}

	public void actualizarRecord(String nombreJugador, int puntuacionTotal, int bankias) {

		int record;

		db = this.getWritableDatabase();

		record = obtenerRecord(nombreJugador);

		if (puntuacionTotal > record) {
			db.execSQL("UPDATE Usuarios SET record=" + puntuacionTotal
					+ " , bankias="+bankias+ " WHERE Nombre='" + nombreJugador + "'");
		}
		db.close();
	}

	private int obtenerRecord(String nombreJugador) {
		int record = -1;

		if (db != null) {
			// Insertamos 5 usuarios de ejemplo

			try {
				Cursor c = db.rawQuery(
						"select record from Usuarios where nombre='"
								+ nombreJugador + "'", null);
				c.moveToFirst();
				record = c.getInt(c.getColumnIndex("record"));

				//c.close();
				// Cerramos la base de datos
				

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return record;
	}
}
