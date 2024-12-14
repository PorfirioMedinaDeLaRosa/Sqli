package com.example.sqlitealumnos;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddContacto extends AppCompatActivity {
   EditText IDtxt, Nombretxt, Apellidostxt, Telefonotxt;
   Button buttonAdd;
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_contacto);
        IDtxt = findViewById(R.id.txtID);
        Nombretxt = findViewById(R.id.txtNombre);
        Apellidostxt = findViewById(R.id.txtApellidos);
        Telefonotxt = findViewById(R.id.txttelfono);
        buttonAdd = findViewById(R.id.btnadd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BD myBD = new BD(AddContacto.this);
                myBD.addContacto(IDtxt.getText().toString().trim(),
                        Nombretxt.getText().toString().trim(),
                        Apellidostxt.getText().toString().trim(),
                        Telefonotxt.getText().toString().trim());
            }
        });
    }
}