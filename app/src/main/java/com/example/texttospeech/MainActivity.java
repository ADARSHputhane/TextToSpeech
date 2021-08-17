package com.example.texttospeech;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private Button convertButton;
    private EditText textEditText;
    private String value;
    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textEditText = findViewById(R.id.textEditText);
        convertButton = findViewById(R.id.convertButton);
       textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
           @Override
           public void onInit(int status) {
               if(status!= TextToSpeech.ERROR){
                   textToSpeech.setLanguage(Locale.US);
               }
           }
       });
       convertButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               value = textEditText.getText().toString();
               Toast.makeText(MainActivity.this,value,Toast.LENGTH_SHORT).show();
               textToSpeech.speak(value,TextToSpeech.QUEUE_FLUSH,null);
           }
       });
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }

}