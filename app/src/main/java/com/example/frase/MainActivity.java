package com.example.frase;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;

import java.util.Random;
public class MainActivity extends AppCompatActivity {
    private EditText texto;
     Random random;

     @SuppressLint("WrongViewCast")
     @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{

            //criar banco
            SQLiteDatabase bancodados = openOrCreateDatabase("app", MODE_PRIVATE, null);
            bancodados.execSQL("CREATE TABLE IF NOT EXISTS frases (id INTEGER PRIMARY KEY AUTOINCREMENT, frase VARCHAR) ");

            // frases e com os atributos ( id do tipo INTEGER , PRIMARY KEY e AUTOINCREMENT, frase do tipo VARCHAR);
            texto = findViewById(R.id.text_resultado) ;
            //inserção do mínimo 5 (cinco) frases;
            bancodados.execSQL("INSERT INTO frases (frase) VALUES ('Versão Cupcake 1.5 ')");
            bancodados.execSQL("INSERT INTO frases (frase) VALUES ('Versão KitKat 4.4 ')");
            bancodados.execSQL("INSERT INTO frases (frase) VALUES ('Versão Lollipop 5.0 ')");
            bancodados.execSQL("INSERT INTO frases (frase) VALUES ('Versão Jelly Bean 4.1 ')");
            bancodados.execSQL("INSERT INTO frases (frase) VALUES ('Versão Oreo 8.0 ')");

            //recuperar dados
            // bancodados.rawQuery("SELECT frase FROM frases", null);

            //Desenvolver a consulta sql iniciais para o uso no Cursor;
            String consulta =  "SELECT frase FROM frases WHERE 1=1 ";
            Cursor cursor = bancodados.rawQuery(consulta, null);
            int quantidade = cursor.getCount();
            int aleatorio = random.nextInt(quantidade);


            //Indice da Tabela
            int indeceId = cursor.getColumnIndex("id");
            int IndiceFrase = cursor.getColumnIndex("frase");
            cursor.moveToFirst();

            Cursor cursorok = bancodados.rawQuery("SELECT frase FROM frases WHERE id ==" + aleatorio, null);

            //Desenvolver o loop para a impressão no console do resultado;
            while (cursor != null){
                Log.i("Frase:",  cursor.getString(indeceId) + " : " + cursor.getString(IndiceFrase));
                //cursor.moveToPosition(IndiceFrase);
                cursor.moveToNext();

            }

            texto.setText(cursorok.getString(IndiceFrase));

        }    catch (Exception e){
            e.printStackTrace();
        }
    }



}



