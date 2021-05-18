package com.example.vistasapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class Salud extends AppCompatActivity {

    EditText etAltura, etPeso, etResIMC;
    Button btnCalcular, btnLimpiar, btnRecomendaciones;
    RadioButton rbHombre, rbMujer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salud);

        etAltura = (EditText)findViewById(R.id.etAltura);
        etPeso = (EditText)findViewById(R.id.etPeso);
        etResIMC = (EditText)findViewById(R.id.etResIMC);

        btnCalcular = (Button)findViewById(R.id.btnCalcular);
        btnLimpiar = (Button)findViewById(R.id.btnLimpiar);
        btnRecomendaciones = (Button)findViewById(R.id.btnRecomendaciones);

        rbHombre = (RadioButton)findViewById(R.id.rbHombre);
        rbMujer = (RadioButton)findViewById(R.id.rbMujer);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { CalcularIMC(); }
        });

        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etAltura.setText("");
                etPeso.setText("");
                etResIMC.setText("");
            }
        });

        btnRecomendaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double dato1 = Double.valueOf(etAltura.getText().toString());
                Double dato2 = Double.valueOf(etPeso.getText().toString());
                double Resultado = dato2 / Math.pow(dato1, 2);

                if (Resultado < 18.50)
                    LlamarRecomendacion1();
                else if (Resultado < 25)
                        Toast.makeText(Salud.this,"TU PESO ES NORMAL NO NECESITAS RECOMENDACIONES",Toast.LENGTH_LONG).show();
                else
                    LlamarRecomendacion2();
            }
        });
    }

    private void LlamarRecomendacion2() {
        Intent vReco2 = new Intent(Salud.this, Recomendacion2.class);
        startActivity(vReco2);
    }

    private void LlamarRecomendacion1() {
        Intent vReco1 = new Intent(Salud.this, Recomendacion1.class);
        startActivity(vReco1);
    }

    private void CalcularIMC() {
        Double dato1 = Double.valueOf(etAltura.getText().toString());
        Double dato2 = Double.valueOf(etPeso.getText().toString());
        double Resultado = dato2 / Math.pow(dato1, 2);
        etResIMC.setText(String.valueOf(Resultado));

        if (btnCalcular.isClickable())
            Toast.makeText(this,"Tu IMC es: "+ String.format("%.2f",Resultado) + " kg/m2\n"+ MensajeIMC(Resultado)+"\n\n", Toast.LENGTH_SHORT).show();

        if (rbHombre.isChecked() == true)
            Toast.makeText(this,"Eres Hombre",Toast.LENGTH_SHORT).show();
        else if (rbMujer.isChecked() == true)
            Toast.makeText(this, "Eres Mujer",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "No eligio sexo", Toast.LENGTH_SHORT).show();
    }

    private String MensajeIMC(Double IMC){
        String mensaje = "";
        if (IMC < 16.00)
            mensaje = "Peso bajo muy grave";
        else  if (IMC < 17.00)
            mensaje = "Peso bajo grave";
        else  if (IMC < 18.50)
            mensaje = "Peso bajo ";
        else  if (IMC < 25.00)
            mensaje = "Peso normal";
        else  if (IMC < 30.00)
            mensaje = "Sobrepeso";
        else  if (IMC < 35.00)
            mensaje = "Obesidad Grado 1";
        else  if (IMC < 40.00)
            mensaje = "Obesidad Grado 2";
        else
            mensaje = "Obesidad Grado 3";
        return mensaje;
    }
}