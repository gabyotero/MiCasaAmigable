package com.gaby.micasaamigable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Formulario extends AppCompatActivity {
    EditText editText_r1, editText_r3, editText_r4, editText_r5, editText_r6;
    TextView textView_p1, textView_p2, textView_p3, textView_p4, textView_p5, textView_p6;
    Button btn_omitir, btn_enviar;
    LinearLayout habitantes, servicios, habitaciones, baños, tv, focos, botones;
    CheckBox checkBox1, checkBox2, checkBox3, checkBox4;

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        //EditTexts
        editText_r1=(EditText) findViewById(R.id.editText_r1);
        editText_r3=(EditText) findViewById(R.id.editText_r3);
        editText_r4=(EditText) findViewById(R.id.editText_r4);
        editText_r5=(EditText) findViewById(R.id.editText_r5);
        editText_r6=(EditText) findViewById(R.id.editText_r6);
        //TextViews
        textView_p1=(TextView) findViewById(R.id.textView_p1);
        textView_p2=(TextView) findViewById(R.id.textView_p2);
        textView_p3=(TextView) findViewById(R.id.textView_p3);
        textView_p4=(TextView) findViewById(R.id.textView_p4);
        textView_p5=(TextView) findViewById(R.id.textView_p5);
        textView_p6=(TextView) findViewById(R.id.textView_p6);
        //Buttons
        btn_omitir=(Button) findViewById(R.id.btn_omitir);
        btn_enviar=(Button) findViewById(R.id.btn_enviar);
        //Layouts
        habitantes=findViewById(R.id.habitantes);
        servicios=findViewById(R.id.servicios);
        habitaciones=findViewById(R.id.habitaciones);
        baños=findViewById(R.id.baños);
        tv=findViewById(R.id.tv);
        focos=findViewById(R.id.focos);
        botones=findViewById(R.id.botones);
        //CheckBoxes
        checkBox1=(CheckBox) findViewById(R.id.checkBox1);
        checkBox2=(CheckBox) findViewById(R.id.checkBox2);
        checkBox3=(CheckBox) findViewById(R.id.checkBox3);
        checkBox4=(CheckBox) findViewById(R.id.checkBox4);
    }

    public void Omitir(View view) {
        Intent x = new Intent(this,MenuPrincipal.class);
        startActivity(x);
    }
}