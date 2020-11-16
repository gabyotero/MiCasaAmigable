package com.gaby.micasaamigable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText user_log, pass_log, user_reg, pass_reg;
    Button btn_login, btn_reg, btn_reg2;
    LinearLayout login1, login2, login3, reg1, reg2;

    SharedPreferences pref ;//= getSharedPreferences("datos",MODE_PRIVATE); //documento
    SharedPreferences.Editor editor; //=pref.edit();   //Editar documento


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //pref = getSharedPreferences("datos",MODE_PRIVATE); //documento
        //editor=pref.edit();   //Editar documento


        //editor.commit();

        // String usr1=pref.getString("user1","-");

        user_log=(EditText) findViewById(R.id.edt_user_log);
        pass_log=(EditText) findViewById(R.id.edt_pass_log);
        user_reg=(EditText) findViewById(R.id.edt_user_reg);
        pass_reg=(EditText) findViewById(R.id.edt_pass_reg);
        btn_login=(Button)findViewById(R.id.btn_start);
        btn_reg=(Button)findViewById(R.id.btn_registro);
        btn_reg2=(Button)findViewById(R.id.btn_registro2);
        login1=findViewById(R.id.login1);
        login2=findViewById(R.id.login2);
        login3=findViewById(R.id.login3);
        reg1=findViewById(R.id.registro1);
        reg2=findViewById(R.id.registro2);

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
        reg1.setVisibility(View.VISIBLE);
        reg2.setVisibility(View.VISIBLE);
        login1.setVisibility(view.GONE);
        login2.setVisibility(view.GONE);
        login3.setVisibility(view.GONE);
    }
    public void NewRegistro(View view){
        new Restful(this, "registro",
                user_reg.getText().toString(),
                pass_reg.getText().toString()).execute();
    }
    public void CancelReg(View view){
        reg1.setVisibility(view.GONE);
        reg2.setVisibility(view.GONE);
        login1.setVisibility(view.VISIBLE);
        login2.setVisibility(view.VISIBLE);
        login3.setVisibility(view.VISIBLE);
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