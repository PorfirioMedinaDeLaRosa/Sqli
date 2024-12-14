package com.example.sqlitealumnos;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AdaptadorContacto
        extends RecyclerView.Adapter<AdaptadorContacto.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList ID_C, Nombre_C, Apellidos_C, Telefono_C;

    public AdaptadorContacto(Context context, Activity activity, ArrayList ID_C, ArrayList nombre_C, ArrayList apellidos_C, ArrayList telefono_C) {
        this.context = context;
        this.activity = activity;
        this.ID_C = ID_C;
        this.Nombre_C = nombre_C;
        this.Apellidos_C = apellidos_C;
        this.Telefono_C = telefono_C;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.listacontactos,
                parent, false);
            return new MyViewHolder(view);
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.lblID_C.setText(String.valueOf(ID_C.get(position)));
        holder.lblNombre_C.setText(String.valueOf(Nombre_C.get(position)));
        holder.lblApellidos_C.setText(String.valueOf(Apellidos_C.get(position)));
        holder.lblTelefono_C.setText(String.valueOf(Telefono_C.get(position)));
       holder.linearLayout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(context, updateContacto.class);
               intent.putExtra("ID", String.valueOf(ID_C.get(position)));
               intent.putExtra("NOMBRE", String.valueOf(Nombre_C.get(position)));
               intent.putExtra("APELLIDOS", String.valueOf(Apellidos_C.get(position)));
               intent.putExtra("TELEFONO", String.valueOf(Telefono_C.get(position)));
               activity.startActivityForResult(intent, 1);
           }
       });
    }

    @Override
    public int getItemCount() {
        return ID_C.size();
    }


    public class MyViewHolder  extends  RecyclerView.ViewHolder{

        TextView lblID_C, lblNombre_C, lblApellidos_C, lblTelefono_C;
        LinearLayout linearLayout;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            lblID_C = itemView.findViewById(R.id.lblid);
            lblNombre_C = itemView.findViewById(R.id.lblnombre);
            lblApellidos_C = itemView.findViewById(R.id.lblapellidos);
            lblTelefono_C = itemView.findViewById(R.id.lbltelefono);
            linearLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
