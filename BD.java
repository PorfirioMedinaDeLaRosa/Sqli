package com.example.sqlitealumnos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class BD extends SQLiteOpenHelper {

    private Context context;

    private static final String NAME_DATABASE = "BookLibrary.db";
    private static final int VERSION_DB = 1;

    private static  final  String TABLE_NAME = "TContactos";
    private static  final  String ID = "ID";
    private static  final String Nombre = "Nombre";
    private static  final  String Apellidos = "Apellidos";
    private static final String Telefono = "Telefono";

    BD(@Nullable Context context){
        super(context, NAME_DATABASE, null,  VERSION_DB );
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Nombre + " TEXT, " +
                Apellidos + " TEXT, " +
                Telefono + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public  void addContacto(String ID, String Nombre, String Apellidos,
                             String Telefono) {
        SQLiteDatabase bd = this.getWritableDatabase();
        ContentValues valores = new ContentValues();
        //valores.put(this.ID, ID);
        valores.put(this.Nombre, Nombre);
        valores.put(this.Apellidos, Apellidos);
        valores.put(this.Telefono, Telefono);
        long resultado = -1;

        try {
            resultado = bd.insert(TABLE_NAME, null, valores);
            if (resultado == -1) {
                // Identifica posibles razones del error y envía un mensaje informativo
                Toast.makeText(context, "Registro no realizado. Posible duplicidad o error en datos.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Registro realizado con éxito.", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            // Si ocurre una excepción, muestra su mensaje
            Toast.makeText(context, "Error en registro: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    Cursor ConsultarContactos(){
            String query = "SELECT * FROM " + TABLE_NAME;
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = null;
            if(db != null){
                cursor = db.rawQuery(query, null);
            }
            return cursor;
        }

        public void UpdateContacto(String ID, String Nombre, String Apellidos,
                                   String Telefono){
            SQLiteDatabase bd = this.getWritableDatabase();
            ContentValues valores = new ContentValues();
            valores.put(this.Nombre, Nombre);
            valores.put(this.Apellidos, Apellidos);
            valores.put(this.Telefono, Telefono);
            long resultado = bd.update(TABLE_NAME, valores, "ID=?", new String[]{ID});
            if (resultado == -1) {
                Toast.makeText(context, "Actualizacion no realizada", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Actualizacion Realizada", Toast.LENGTH_SHORT).show();
            }
        }

        public void DeleteContacto(String ID){
            SQLiteDatabase bd = this.getWritableDatabase();
            long resultado = bd.delete(TABLE_NAME, "ID=?", new String[]{ID});
            if (resultado == -1) {
                Toast.makeText(context, "Registro no eliminado", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Registro eliminado", Toast.LENGTH_SHORT).show();
            }
        }

        public void Deletetable(){
            SQLiteDatabase bd = this.getWritableDatabase();
            bd.execSQL("DELETE FROM "+ TABLE_NAME);

        }

    }


