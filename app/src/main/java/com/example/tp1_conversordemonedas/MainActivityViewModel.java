package com.example.tp1_conversordemonedas;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends AndroidViewModel {

    private MutableLiveData<Boolean> etDolares;
    private MutableLiveData<Boolean> etEuros;
    private MutableLiveData<Double> resultadoConversion;
    private Context context;

    private static double rateEuro = 0.92;
    private static double rateDolar = 1.08;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        this.context=application.getApplicationContext();
    }

    public MutableLiveData<Boolean> getEtDolares() {
        if(etDolares == null){
            etDolares = new MutableLiveData<>();
        }
        return etDolares;
    }

    public MutableLiveData<Boolean> getEtEuros() {
        if(etEuros == null){
            etEuros = new MutableLiveData<>();
        }
        return etEuros;
    }

    public MutableLiveData<Double> getResultadoConversion() {
        if(resultadoConversion == null){
            resultadoConversion = new MutableLiveData<>();
        }
        return resultadoConversion;
    }

    public void convertir(String etDolar, String etEuro){
        if(etDolares.getValue() == null){
            //hacer un toast
            Toast.makeText(context,"Usted debe seleccionar un tipo de conversion",Toast.LENGTH_LONG).show();
        }else {
            if (etDolares.getValue()){
                double valorDolar = Double.parseDouble(etDolar);
                resultadoConversion.setValue(valorDolar * rateEuro);
            }
            if(etEuros.getValue()){
                double valorEuro = Double.parseDouble(etEuro);
                resultadoConversion.setValue(valorEuro * rateDolar);
            }
        }

    }

    public void cambiarDolarAEuro(){
        this.etEuros.setValue(false);
        this.etDolares.setValue(true);


    }
    public void cambiarEuroADolar(){
        this.etEuros.setValue(true);
        this.etDolares.setValue(false);
    }

}
