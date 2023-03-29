package com.example.tp1_conversordemonedas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;

import com.example.tp1_conversordemonedas.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel vm;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // creacion del viewmodel
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);

        //listener para rb

        //inicializo los mutables y colocar observers
        vm.getEtDolares().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                binding.etDolar.setEnabled(aBoolean);
            }
        });
        vm.getEtEuros().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                binding.etEuro.setEnabled(aBoolean);
            }
        });
        vm.getResultadoConversion().observe(this, new Observer<Double>() {
            @Override
            public void onChanged(Double conversion) {
                binding.tvConversionResultado.setText(conversion + "");
            }
        });

        //incializar binding y luego vincularlo con la vista
        binding = ActivityMainBinding.inflate(getLayoutInflater()); // incializacion
        setContentView(binding.getRoot());

        //colocar listener al boton
        binding.btConvertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vm.convertir(binding.etDolar.getText().toString(),binding.etEuro.getText().toString());
            }
        });
        //listener para rbUSDtoEURO
        binding.rbUSDtoEURO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vm.cambiarDolarAEuro();
            }
        });
        //listener para rbEUROtoUSD
        binding.rbEUROtoUSD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vm.cambiarEuroADolar();
            }
        });
    }
}