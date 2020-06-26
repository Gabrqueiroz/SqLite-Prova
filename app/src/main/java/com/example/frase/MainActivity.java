package com.example.frase;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
    private EditText texto;
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

            //inserção do mínimo 5 (cinco) frases;
            //bancodados.execSQL("INSERT INTO frases (frase) VALUES ('Versão Cupcake 1.5 ')");
            //bancodados.execSQL("INSERT INTO frases (frase) VALUES ('Versão KitKat 4.4 ')");
            //bancodados.execSQL("INSERT INTO frases (frase) VALUES ('Versão Lollipop 5.0 ')");
            //bancodados.execSQL("INSERT INTO frases (frase) VALUES ('Versão Jelly Bean 4.1 ')");
            //bancodados.execSQL("INSERT INTO frases (frase) VALUES ('Versão Oreo 8.0 ')");

            //recuperar dados
            // bancodados.rawQuery("SELECT frase FROM frases", null);

            //Desenvolver a consulta sql iniciais para o uso no Cursor;
            String consulta =  "SELECT frase FROM frases WHERE 1=1 "; // a resolver WHERE
            Cursor cursor = bancodados.rawQuery(consulta, null);

            texto (findViewById(R.id.text_resultado)) ;

            //Indice da Tabela

            int IndiceFrase = cursor.getColumnIndex("frase");
            cursor.moveToFirst();

            //Desenvolver o loop para a impressão no console do resultado;
            while (cursor != null){
                Log.i("RESULTADO - frase", cursor.getString(0));
                //cursor.moveToPosition(IndiceFrase);
                cursor.moveToNext();

            }

            texto.setText(cursor.getString(IndiceFrase));

        }    catch (Exception e){
            e.printStackTrace();
        }
    }

    private void texto(View viewById) {
    }
}



