package com.gaby.micasaamigable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class Registro extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        Spinner combosex =findViewById(R.id.cmbSexo);
        ArrayAdapter<CharSequence>adapter=ArrayAdapter.createFromResource(this, R.array.Sexo,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        combosex.setAdapter(adapter);
        combosex.setOnItemClickListener((AdapterView.OnItemClickListener) this);
        Spinner comboemail =findViewById(R.id.cmbCorreo);
        ArrayAdapter<CharSequence>adapter1=ArrayAdapter.createFromResource(this, R.array.Correo,android.R.layout.simple_spinner_item);
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();

    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}