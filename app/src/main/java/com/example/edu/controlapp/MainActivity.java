package com.example.edu.controlapp;

import android.content.DialogInterface;
import android.hardware.ConsumerIrManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    ImageButton btnencd,chmas,chmenos,btn_salir,btn_audio,btn_info,btn_canales,btn_derecha,btn_izquierda,btn_abajo,btn_arriba,btn_silencio;
    Button btn_ok,btn_1,btn_2,btn_3,btn_4,btn_5,btn_6,btn_7,btn_8,btn_9,btn_0,btn_blue,volmas,volmenos;
    private int [] trasmit_info,trasmiton_off,trasmitvol_mas,trasmitvol_menos,trasmitcanal_mas,trasmit_canal_menos,trasmit_silencio,trasmit_ok,trasmit_arriba,trasmit_derecha,trasmit_izquierda,trasmit_abajo;
    private int carrier = 38028;
    Spinner selectorequipo;
    private ConsumerIrManager irManager;
    private int id_dispositivo;
    private int[] trasmit_canales,trasmit_audio,trasmit_salir;
    private  int [] trasmit_1,trasmit_2,trasmit_3,trasmit_4,trasmit_5,trasmit_6,trasmit_7,trasmit_8,trasmit_9,trasmit_0;
    private int[] trasmit_blue_button;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.actionmenu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        irManager = (ConsumerIrManager) getSystemService(CONSUMER_IR_SERVICE);
        selectorequipo = findViewById(R.id.spinnerequipos);
        btnencd = findViewById(R.id.encendido);
        volmenos = findViewById(R.id.vmenos);
        volmas = findViewById(R.id.vmas);
        chmas = findViewById(R.id.chmas);
        chmenos = findViewById(R.id.chmenos);
        btn_salir = findViewById(R.id.atras);
        btn_audio = findViewById(R.id.idioma);
        btn_canales = findViewById(R.id.lista_canales);
        btn_info = findViewById(R.id.info);
        btn_abajo = findViewById(R.id.abajo);
        btn_arriba = findViewById(R.id.arriba);
        btn_derecha = findViewById(R.id.derecha);
        btn_izquierda = findViewById(R.id.izquierda);
        btn_silencio = findViewById(R.id.silencio);
        btn_ok = findViewById(R.id.ok);
        btn_0 = findViewById(R.id.b0);
        btn_1 = findViewById(R.id.b1);
        btn_2 = findViewById(R.id.b2);
        btn_3 = findViewById(R.id.b3);
        btn_4 = findViewById(R.id.b4);
        btn_5 = findViewById(R.id.b5);
        btn_6 = findViewById(R.id.b6);
        btn_7 = findViewById(R.id.b7);
        btn_8 = findViewById(R.id.b8);
        btn_9 = findViewById(R.id.b9);
        btn_blue = findViewById(R.id.red_button);
        btnencd.setOnClickListener(this);
        volmas.setOnClickListener(this);
        volmenos.setOnClickListener(this);
        chmas.setOnClickListener(this);
        chmenos.setOnClickListener(this);
        btn_info.setOnClickListener(this);
        btn_canales.setOnClickListener(this);
        btn_audio.setOnClickListener(this);
        btn_salir.setOnClickListener(this);
        btn_izquierda.setOnClickListener(this);
        btn_derecha.setOnClickListener(this);
        btn_arriba.setOnClickListener(this);
        btn_abajo.setOnClickListener(this);
        btn_silencio.setOnClickListener(this);
        btn_ok.setOnClickListener(this);
        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_blue.setOnClickListener(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.dispositivos,R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        selectorequipo.setAdapter(adapter);
        selectorequipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("id",String.valueOf(adapterView.getSelectedItemPosition()));
                id_dispositivo = adapterView.getSelectedItemPosition();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        inicializar();

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.encendido:
                cod_trasmitt(trasmiton_off);
                break;
            case R.id.vmas:
               cod_trasmitt(trasmitvol_mas);
                break;
            case R.id.vmenos:
                cod_trasmitt(trasmitvol_menos);
                break;
            case R.id.chmas:
                cod_trasmitt(trasmitcanal_mas);
                break;
            case R.id.chmenos:
                cod_trasmitt(trasmit_canal_menos);
                break;
            case R.id.atras:
                cod_trasmitt(trasmit_salir);
                break;
            case R.id.lista_canales:
                cod_trasmitt(trasmit_canales);
                break;
            case R.id.idioma:
                cod_trasmitt(trasmit_audio);
                break;
            case R.id.info:
                cod_trasmitt(trasmit_info);
                break;
            case R.id.silencio:
                cod_trasmitt(trasmit_silencio);
                break;
            case R.id.abajo:
                cod_trasmitt(trasmit_abajo);
                break;
            case R.id.arriba:
                cod_trasmitt(trasmit_arriba);
                break;
            case R.id.derecha:
                cod_trasmitt(trasmit_derecha);
                break;
            case R.id.izquierda:
                cod_trasmitt(trasmit_izquierda);
                break;
            case R.id.ok:
                cod_trasmitt(trasmit_ok);
                break;
            case R.id.b0:
                cod_trasmitt(trasmit_0);
                break;
            case R.id.b1:
                cod_trasmitt(trasmit_1);
                break;
            case R.id.b2:
                cod_trasmitt(trasmit_2);
                break;
            case R.id.b3:
                cod_trasmitt(trasmit_3);
                break;
            case R.id.b4:
                cod_trasmitt(trasmit_4);
                break;
            case R.id.b5:
                cod_trasmitt(trasmit_5);
                break;
            case R.id.b6:
                cod_trasmitt(trasmit_6);
                break;
            case R.id.b7:
                cod_trasmitt(trasmit_7);
                break;
            case R.id.b8:
                cod_trasmitt(trasmit_8);
                break;
            case R.id.b9:
                cod_trasmitt(trasmit_9);
                break;
            case R.id.red_button:
                cod_trasmitt(trasmit_blue_button);
        }
    }
    private void cod_trasmitt(int [] patern_trasmit){
        Log.i("carrier",String.valueOf(carrier));
        Log.i("patern",String.valueOf(patern_trasmit));
        irManager.transmit(carrier,patern_trasmit);
    }
    private void inicializar(){
        int[] claro_encendido = {9000,4400,600,500,600,500,550,550,550,550,550,500,600,500,600,500,600,1650,550,500,600,500,600,500,600,500,600,500,600,500,600,500,600,1650,550,500,600,500,600,500,600,1650,550,500,600,500,600,500,600,500,550,1700,550,1650,550,1650,550,550,550,1650,600,1650,550,1650,550,1650,600,};
        int[] claro_vol_mas = {8900,4400,600,500,600,500,600,500,600,500,600,500,600,500,550,550,550,1650,600,500,550,550,550,550,550,550,550,500,600,500,600,500,600,1650,550,1650,550,550,550,550,550,1650,600,500,550,550,550,1650,600,500,600,500,550,1650,600,1600,600,500,600,1650,550,1650,600,500,550,1650,600,};
        int[] claro_vol_menos = {8950,4450,550,500,600,500,600,500,600,500,600,500,600,500,600,500,600,1600,600,500,550,550,550,550,550,500,650,450,650,450,650,450,650,1600,550,500,600,500,600,500,650,1550,600,500,600,500,600,1600,600,500,600,1650,550,1650,600,1600,600,500,600,1600,600,1600,600,500,600,1650,600,};
        int[] claro_ch_mas  = {8950,4400,550,550,550,500,600,500,600,500,600,500,600,500,600,500,600,1600,600,500,600,500,600,500,600,450,650,500,550,500,600,550,550,1600,600,1650,600,500,600,500,600,1600,600,500,600,500,600,500,550,550,550,500,600,1600,650,1600,600,500,600,1600,600,1650,550,1650,600,1600,600,};
        int [] claro_ch_menos ={9000,4350,600,500,600,500,550,500,600,550,550,500,600,500,600,500,600,1650,550,500,600,500,600,500,600,500,600,500,600,500,600,500,600,1600,600,500,600,1600,600,450,650,1650,550,500,600,500,600,500,600,500,600,1600,600,500,600,1600,600,550,550,1650,600,1600,600,1600,600,1650,550};
        int [] claro_info = {8950,4400,600,500,600,500,600,500,550,550,550,550,550,500,600,500,600,1650,550,550,550,500,600,500,600,500,600,500,600,500,600,500,550,1700,550,500,600,500,550,1650,600,500,600,500,600,500,550,1650,600,500,600,1600,600,1600,600,500,600,1650,550,1650,600,1600,600,500,600,1600,600};
        int [] claro_canales = {8950,4400,600,500,600,500,600,500,600,500,600,500,600,500,600,500,550,1700,550,500,600,500,550,550,550,550,550,550,550,500,600,500,600,1650,550,1650,600,1650,550,1650,550,550,550,500,600,500,600,1650,550,550,550,500,600,500,600,500,600,1650,550,1650,550,1650,600,500,600,1650,550};
        int [] claro_salir= {8950,4400,600,500,550,550,550,550,550,500,600,500,600,500,600,500,600,1650,550,500,600,500,600,500,600,500,600,500,550,550,550,550,550,1650,600,500,550,550,550,550,550,500,600,1650,550,550,550,550,550,500,600,1650,550,1650,600,1600,600,1600,600,500,600,1600,600,1650,600,1600,600,};
        int [] claro_audio = {9000,4350,600,500,600,500,600,500,600,500,600,500,600,500,550,500,600,1650,600,500,600,500,550,500,600,500,600,500,600,500,600,500,600,1650,550,1650,550,550,550,550,550,1600,650,1600,600,500,600,1600,600,500,600,500,600,1550,650,1650,600,500,550,500,600,1650,550,550,550,1650,600,};
        int [] claro_silencio = {8900,4400,600,500,600,500,600,500,600,500,600,500,600,500,550,550,550,1700,550,500,600,500,550,550,550,550,550,550,550,500,600,500,600,1650,550,500,600,1650,550,550,550,1650,600,1600,600,500,600,500,600,500,550,1650,600,500,600,1600,600,500,600,500,600,1600,600,1650,550,1600,650,};
        int [] claro_derecha = {9000,4350,600,500,600,500,600,500,550,550,550,550,550,550,550,500,600,1650,550,550,550,500,600,500,600,500,600,500,600,450,650,500,600,1600,600,1600,600,1650,550,1650,600,500,600,1600,600,500,600,500,600,500,600,450,600,550,550,500,600,1650,550,550,550,1650,600,1600,600,1650,550};
        int [] claro_izquierda = {9000,4350,600,500,600,500,600,450,600,550,550,500,600,500,600,500,600,1650,550,500,600,500,600,500,600,500,600,500,600,500,600,500,550,1650,600,450,650,1600,600,1650,550,1650,600,500,550,550,550,550,550,500,600,1650,550,550,550,500,600,500,600,1600,600,1650,600,1600,600,1650,550};
        int [] claro_arriba = {9000,4350,600,500,600,500,600,500,600,450,600,500,600,500,600,500,600,1650,550,550,550,500,600,500,600,500,600,500,600,500,600,500,600,1600,600,500,600,1600,600,1650,550,550,550,500,600,500,600,500,600,500,600,1600,600,500,600,500,600,1600,600,1650,550,1650,600,1600,600,1650,550,};
        int [] claro_abajo = {9000,4350,600,500,600,450,650,500,600,500,550,550,550,550,550,500,600,1600,600,550,550,500,600,500,600,500,600,500,600,500,600,500,600,1600,600,1600,600,1650,600,1600,600,500,600,500,600,450,650,500,550,550,550,500,600,500,600,500,600,1600,600,1650,600,1600,600,1600,600,1650,600,};
        int [] claro_ok =  {9000,4350,550,550,550,500,600,500,600,500,600,500,600,500,600,500,600,1600,600,500,600,500,600,500,600,450,600,550,550,500,600,500,600,1650,550,1650,600,1600,600,1600,600,1650,600,500,550,550,550,550,550,500,600,500,600,500,600,500,600,500,600,1600,600,1650,550,1650,600,1600,600,};
        int [] claro_1 = {8950,4400,600,500,600,500,600,500,600,500,600,500,550,550,550,550,550,1650,600,500,550,550,550,500,600,500,600,500,600,500,600,500,600,1650,550,1650,550,1650,600,500,600,500,550,550,550,550,550,500,600,500,600,500,600,500,600,1650,550,1650,550,1650,600,1600,600,1600,600,1600,650,};
        int [] claro_2 = {9000,4350,600,500,600,500,600,500,600,500,600,500,600,500,550,550,550,1650,600,450,650,500,550,500,600,550,550,500,600,500,600,500,600,1650,550,1650,600,1600,600,500,600,500,600,1600,600,500,600,500,600,500,600,500,600,450,600,1650,600,1600,600,500,600,1650,550,1650,600,1600,600,};
        int [] claro_3 = {9000,4350,600,500,600,500,600,500,600,500,550,550,550,500,600,500,600,1600,600,550,550,550,550,500,600,500,600,500,600,500,600,450,650,1600,600,1650,550,1650,550,550,550,1650,600,1600,600,500,600,500,600,500,600,500,600,500,550,1650,600,500,600,450,600,1650,600,1600,600,1600,600,};
        int [] claro_4 =  {9000,4350,600,500,550,550,550,500,600,500,600,500,600,500,600,500,600,1600,600,500,600,500,600,500,600,500,600,500,600,500,550,550,550,1650,600,450,650,500,600,1600,600,500,600,500,600,500,550,500,600,550,550,1650,600,1600,600,500,600,1600,600,1600,600,1650,600,1600,600,1650,550,};
        int [] claro_5 = {9000,4350,550,550,550,500,600,500,600,500,600,500,600,500,600,500,600,1600,600,500,600,500,600,450,650,450,600,550,550,550,550,500,600,1650,550,550,550,500,600,1650,600,500,550,1650,600,500,600,500,600,500,550,1650,600,1600,600,500,600,1650,550,500,600,1650,550,1650,600,1600,600,};
        int [] claro_6 = {9000,4350,600,500,600,500,600,500,600,450,650,500,550,550,550,500,600,1650,600,450,600,550,550,500,600,500,600,500,600,500,600,500,600,1600,600,500,600,500,600,1600,600,1600,650,1600,600,500,600,500,600,500,550,1650,600,1600,600,500,600,500,600,500,600,1600,600,1650,550,1600,600,};
        int [] claro_7 = {9050,4350,550,550,550,500,600,500,600,500,600,500,600,500,600,500,600,1600,600,500,600,500,600,500,600,500,600,450,600,500,600,550,550,1650,600,1600,600,500,600,1600,600,500,600,500,600,500,600,500,600,500,600,450,600,1650,600,500,600,1600,600,1600,600,1650,550,1650,600,1600,600,};
        int [] claro_8 = {9000,4350,600,500,600,500,600,500,600,450,600,500,600,550,550,500,600,1650,550,500,600,550,550,500,600,500,600,500,600,500,600,500,600,1600,600,1650,550,500,600,1600,600,550,550,1600,650,500,600,450,600,550,550,500,600,1650,550,500,600,1650,600,500,600,1600,600,1600,600,1600,600,};
        int [] claro_9 = {9000,4350,600,500,600,500,550,550,550,550,550,500,600,500,600,500,600,1600,600,500,600,500,600,500,600,500,600,500,600,500,600,450,600,1650,600,1600,600,500,600,1600,600,1650,550,1650,600,500,600,450,600,550,550,550,550,1650,600,450,600,500,600,500,600,1600,600,1650,600,1600,600,};
        int [] claro_0 = {8900,4450,600,500,600,450,600,500,600,500,600,500,600,500,650,400,650,1600,650,450,600,450,650,450,650,500,600,500,550,550,600,450,650,1600,550,550,550,1650,600,1600,600,1650,600,1600,550,550,550,550,550,500,650,1550,650,500,550,550,550,500,600,500,600,1650,600,1600,600,1600,600,};
        int [] claro_blue = {9000,4400,600,500,600,500,600,500,600,500,600,500,600,500,600,450,650,1600,550,550,550,500,650,450,650,450,650,450,600,500,600,500,600,1600,600,500,600,500,650,1550,600,500,600,1650,550,500,600,1600,600,550,550,1650,600,1600,600,500,600,1600,600,500,600,1600,600,500,600,1650,600,};


        switch (id_dispositivo){
            case 0:
                trasmiton_off = claro_encendido;
                trasmitvol_mas = claro_vol_mas;
                trasmitvol_menos = claro_vol_menos;
                trasmitcanal_mas = claro_ch_mas;
                trasmit_canal_menos = claro_ch_menos;
                trasmit_info = claro_info;
                trasmit_canales = claro_canales;
                trasmit_salir = claro_salir;
                trasmit_audio = claro_audio;
                trasmit_silencio = claro_silencio;
                trasmit_ok = claro_ok;
                trasmit_arriba = claro_arriba;
                trasmit_abajo = claro_abajo;
                trasmit_derecha = claro_derecha;
                trasmit_izquierda = claro_izquierda;
                trasmit_0 =claro_0;
                trasmit_1 = claro_1;
                trasmit_2 = claro_2;
                trasmit_3 = claro_3;
                trasmit_4 = claro_4;
                trasmit_5 = claro_5;
                trasmit_6 = claro_6;
                trasmit_7 = claro_7;
                trasmit_8 = claro_8;
                trasmit_9 = claro_9;
                trasmit_0 = claro_0;
                trasmit_blue_button = claro_blue;
                break;

        }

    }


    public void onComposeAction(MenuItem item) {
        Log.i("actionbar","holaaa");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton("cerrar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                dialog.cancel();
            }
        });

        builder.setMessage(("Eduardo Gonzalez\n2018\nVersion:0.0.1"))
                .setTitle(("Propiedades"));
        AlertDialog dialog = builder.create();
        dialog.show();

    }
}
