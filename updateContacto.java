package com.example.sqlitealumnos;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class updateContacto extends AppCompatActivity {
    EditText AIc_C, ANombre_C, AApellidos_C, ATelefono_C;
    Button UpdateButton, DeleteButton;
    String idcontacto, nombrecontacto, apellidoscontacto, telefonocontacto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update_contacto);
        AIc_C = findViewById(R.id.txtIDA);
        ANombre_C = findViewById(R.id.txtNombreA);
        AApellidos_C = findViewById(R.id.txtApellidosA);
        ATelefono_C = findViewById(R.id.txttelfonoA);
        UpdateButton = findViewById(R.id.btnupdate);
        DeleteButton = findViewById(R.id.btndelete);
        getValores();

        UpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BD myBd = new BD(updateContacto.this);
                idcontacto = AIc_C.getText().toString().trim();
                nombrecontacto = ANombre_C.getText().toString().trim();
                apellidoscontacto = AApellidos_C.getText().toString().trim();
                telefonocontacto = ATelefono_C.getText().toString().trim();
                myBd.UpdateContacto(idcontacto,nombrecontacto,apellidoscontacto,
                        telefonocontacto);
            }
        });

        DeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmardelete();
            }
        });


    }

    private void confirmardelete() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Eliminar Contacto" + nombrecontacto);
        builder.setMessage("Deseas eliminar el contacto" + nombrecontacto);
        builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                 BD myBD = new BD(updateContacto.this);
                 myBD.DeleteContacto(idcontacto);
                 finish();
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();

    }

    public void getValores(){
        if(getIntent().hasExtra("ID")
                && getIntent().hasExtra("NOMBRE")
                && getIntent().hasExtra("APELLIDOS")
                && getIntent().hasExtra("TELEFONO")){

            idcontacto = getIntent().getStringExtra("ID");
            nombrecontacto = getIntent().getStringExtra("NOMBRE");
            apellidoscontacto = getIntent().getStringExtra("APELLIDOS");
            telefonocontacto = getIntent().getStringExtra("TELEFONO");

            AIc_C.setText(idcontacto);
            ANombre_C.setText(nombrecontacto);
            AApellidos_C.setText(apellidoscontacto);
            ATelefono_C.setText(telefonocontacto);

        }else{
            Toast.makeText(this, "VALORES VACIOS", Toast.LENGTH_SHORT).show();
        }
    }
}