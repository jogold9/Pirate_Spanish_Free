package com.joshbgold.PirateSpanishFree;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WordPracticeActivity extends MainActivity {

    private PirateWords mPirateWords = new PirateWords();
    private boolean hasStarted = false;  //stores whether a word has been retrieved yet
    private int wordIndex;  //user's current place in the word list


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_practice);

        //This line obtains shared preferences, which contain the user's place in the word list
        wordIndex = LoadPreferences("WordIndex", wordIndex);

        //Set the word index with the value we have retrieved, unless we are already at the start
        if (wordIndex != 0) {
            wordIndex--;
            mPirateWords.setWordIndex(wordIndex);
        }

        //Declare our view variables & assign views from the layout file
        final TextView pirateLabel = (TextView) findViewById(R.id.pirateView);
        final Button pirateButton = (Button) findViewById(R.id.pirateButton);
        final Button pirateAnswerButton = (Button) findViewById(R.id.pirateAnswerButton);
        final Button previousButton = (Button) findViewById(R.id.previousButton);
        final Button exitButton = (Button) findViewById(R.id.exitButton);

        //Get Spanish word button
        View.OnClickListener listener = new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                String fact = mPirateWords.getPirateWord();
                //Update the screen with the Spanish word
                pirateLabel.setText(fact);

/*
               Audio audio = Audio.getInstance();
                InputStream sound  = null;
                try {
                    sound = audio.getAudio(fact, Language.SPANISH);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    audio.play(sound);
                } catch (JavaLayerException e) {
                    e.printStackTrace();
                }
*/

                hasStarted = true;
            }

        };


        //Get English definition button
        View.OnClickListener listenerAnswer = new View.OnClickListener(){
            @Override
            public void onClick (View view) {

                String fact = mPirateWords.getPirateAnswer(hasStarted);
                //Update the screen with the word definition
                pirateLabel.setText(fact);

            }
        };

        //Get previous word button
        View.OnClickListener previous = new View.OnClickListener(){
            @Override
            public void onClick (View view){
                String fact = mPirateWords.getPrevious();
                //Update the screen with the previous Spanish word
                pirateLabel.setText(fact);
            }
        };

        //Exit button
        View.OnClickListener walkThePlank = new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                //Next 2 lines save the  word index to retain the user's place
                wordIndex = mPirateWords.getWordIndex();
                savePrefs("WordIndex", wordIndex);
                finish();
            }
        };

        pirateButton.setOnClickListener(listener);
        pirateAnswerButton.setOnClickListener(listenerAnswer);
        previousButton.setOnClickListener(previous);
        exitButton.setOnClickListener(walkThePlank);
    }

    //save prefs
    public void savePrefs(String key, int value){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }
    //get prefs
    private int LoadPreferences(String key, int value){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        int data = sharedPreferences.getInt(key, value);
        return data;
    }
}

