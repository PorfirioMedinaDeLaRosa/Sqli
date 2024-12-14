package com.example.sqlitealumnos;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
  FloatingActionButton addboton;
  RecyclerView recyclerView;
  BD myBD;
  ArrayList<String> IDC, NombreC, ApellidosC, TelefonoC;
  AdaptadorContacto adaptadorContacto;




    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclercontactos);
        addboton = findViewById(R.id.addcontactoD);
        addboton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent = new Intent(MainActivity.this,
                        AddContacto.class);
                startActivity(intent);

            }
        });
        myBD = new BD(MainActivity.this);
        IDC = new ArrayList<>();
        NombreC = new ArrayList<>();
        ApellidosC = new ArrayList<>();
        TelefonoC = new ArrayList<>();
        consultarcontactos();
        adaptadorContacto = new AdaptadorContacto(MainActivity.this,
                this, IDC, NombreC, ApellidosC,  TelefonoC);
        recyclerView.setAdapter(adaptadorContacto);
        recyclerView.setLayoutManager(new LinearLayoutManager
                (MainActivity.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    private void consultarcontactos() {
        Cursor cursor = myBD.ConsultarContactos();
        if(cursor.getCount() ==0){
            Toast.makeText(this, "NO EXISTEN REGISTROS",
                    Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
               IDC.add(cursor.getString(0));
                NombreC.add(cursor.getString(1));
                ApellidosC.add(cursor.getString(2));
                TelefonoC.add(cursor.getString(3));
            }
            Toast.makeText(this, "EXISTEN REGISTROS",
                    Toast.LENGTH_SHORT).show();
        }
    }
}