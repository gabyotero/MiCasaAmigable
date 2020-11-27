package com.gaby.micasaamigable;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText user_log, pass_log, user_reg, pass_reg, name, lastn;
    Button btn_login, btn_reg, btn_reg2, btn_inicio;
    LinearLayout login1, login2, login3, reg1, reg2, nombres, apellidos;

    SharedPreferences pref ;//= getSharedPreferences("datos",MODE_PRIVATE); //documento
    SharedPreferences.Editor editor; //=pref.edit();   //Editar documento


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);
       // getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        //pref = getSharedPreferences("datos",MODE_PRIVATE); //documento
        //editor=pref.edit();   //Editar documento

        //editor.commit();

        // String usr1=pref.getString("user1","-");

        //edit_texts
        user_log=(EditText) findViewById(R.id.edt_user_log);
        pass_log=(EditText) findViewById(R.id.edt_pass_log);
        user_reg=(EditText) findViewById(R.id.edt_user_reg);
        pass_reg=(EditText) findViewById(R.id.edt_pass_reg);
        name=(EditText) findViewById(R.id.edt_nombre);
        lastn=(EditText) findViewById(R.id.edt_apellido);

        //botones
        btn_login=(Button)findViewById(R.id.btn_start);
        btn_reg=(Button)findViewById(R.id.btn_registro);
        btn_reg2=(Button)findViewById(R.id.btn_registro2);
        btn_inicio=(Button)findViewById(R.id.btn_inicio);

        //Layouts
        login1=findViewById(R.id.login1);
        login2=findViewById(R.id.login2);
        login3=findViewById(R.id.login3);
        reg1=findViewById(R.id.registro1);
        reg2=findViewById(R.id.registro2);
        nombres=findViewById(R.id.nombres);
        apellidos=findViewById(R.id.apellidos);

        //Como inician
        reg1.setVisibility(View.GONE);
        reg2.setVisibility(View.GONE);
        btn_reg2.setVisibility(View.GONE);
        btn_inicio.setVisibility(View.GONE);
        nombres.setVisibility(View.GONE);
        apellidos.setVisibility(View.GONE);

    }

    public void Login(View view){
               new Restful(this, "login",
                user_log.getText().toString(),
                pass_log.getText().toString()).execute();
    }

    public void GetData(View view){
        new Restful(this, "GetData").execute();
    }


    public void registro(View view){
        name.setText("");
        lastn.setText("");
        user_reg.setText("");
        pass_reg.setText("");
        reg1.setVisibility(View.VISIBLE);
        reg2.setVisibility(View.VISIBLE);
        btn_reg2.setVisibility(View.VISIBLE);
        btn_inicio.setVisibility(View.VISIBLE);
        nombres.setVisibility(View.VISIBLE);
        apellidos.setVisibility(View.VISIBLE);
        name.setVisibility(view.VISIBLE);
        lastn.setVisibility(view.VISIBLE);
        login1.setVisibility(view.GONE);
        login2.setVisibility(view.GONE);
        login3.setVisibility(view.GONE);
    }
    public void NewRegistro(View view){
        new Restful(this, "registro",
                user_reg.getText().toString(),
                pass_reg.getText().toString(),
                name.getText().toString(),
                lastn.getText().toString()).execute();
    }
    public void CancelReg(View view){
        user_log.setText("");
        pass_log.setText("");
        reg1.setVisibility(view.GONE);
        reg2.setVisibility(view.GONE);
        btn_reg2.setVisibility(view.GONE);
        btn_inicio.setVisibility(view.GONE);
        name.setVisibility(view.GONE);
        lastn.setVisibility(view.GONE);
        login1.setVisibility(view.VISIBLE);
        login2.setVisibility(view.VISIBLE);
        login3.setVisibility(view.VISIBLE);
        nombres.setVisibility(View.GONE);
        apellidos.setVisibility(View.GONE);

    }


    public void registroOk(){
        mensaje("Tu registro fue exitoso");
    }

    public void mensaje(String s){
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    public void datosOk(){
        mensaje("Ingreso correcto");
        Intent i = new Intent(this,Formulario.class);
        startActivity(i);      //Abrir una actividad nueva
    }
}