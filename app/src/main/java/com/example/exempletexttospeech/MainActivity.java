package com.example.exempletexttospeech;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Context context;
    TextToSpeech textToSpeech;
    Spinner sprLangues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        // Initialisation du module
        initSpinnerLangues();
        initTextToSpeech(0);

        Button btnMessageParle = findViewById(R.id.btnMessageParle);

        btnMessageParle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                jeParle();

            }
        });
    }

    private void initTextToSpeech(final int position)
    {
        textToSpeech = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS)
                {
                    switch (position)
                    {
                        case 1 : textToSpeech.setLanguage(Locale.FRANCE);
                        break;

                        case 2 : textToSpeech.setLanguage(Locale.ENGLISH);
                        break;

                        case 3 : textToSpeech.setLanguage(Locale.GERMAN);
                        break;

                        case 4 : textToSpeech.setLanguage(Locale.CHINA);
                        break;

                        default:
                            textToSpeech.setLanguage(Locale.FRANCE);
                            break;
                    }

                }
            }
        });
    }

    private void initSpinnerLangues()
    {
        sprLangues = findViewById(R.id.sprLangues);

        ArrayList<String> langues = new ArrayList<>();

        langues = new ArrayList<>();
        langues.add(0,"Sélectionner votre langue");
        langues.add(1,"Français");
        langues.add(2,"Anglais");
        langues.add(3,"Allemand");
        langues.add(4,"Chinois");

        ArrayAdapter arrayAdapter = new ArrayAdapter(context,android.R.layout.simple_spinner_item,langues);
        sprLangues.setAdapter(arrayAdapter);

        sprLangues.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                initTextToSpeech(position);
                jeParle();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(context,"Veuillez saisir votre langue",Toast.LENGTH_LONG);
            }
        });
    }

    private void jeParle()
    {
        EditText txtMessage = findViewById(R.id.txtSaisie);
        String message = txtMessage.getText().toString().trim();

        if(!message.isEmpty())
        {
            textToSpeech.speak(message,TextToSpeech.QUEUE_FLUSH,null,"");
        }
        else
            {
                Toast.makeText(context,"Veuillez saisir du texte",Toast.LENGTH_LONG);
            }
    }
}
