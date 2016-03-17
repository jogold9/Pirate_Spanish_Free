package com.joshbgold.PirateSpanishFree;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class quizActivity extends MainActivity {

    private PirateWords mPirateWords = new PirateWords();
    private Rank mRank = new Rank();
    private questsActivity mQuest = new questsActivity();
    private TextView mUserRank;
    private TextView mVoyage;
    private TextView mProgress;
    private TextView mScore;
    private TextView mPiecesOfEight;
    private TextView mTextView;
    private EditText mAnswer;
    private TextView mEndingText;
    private int quizWordCount = 0;
    private int score = 0;
    private boolean mAnswered = false;
    private boolean quizOver = false;
    private boolean isNewWord = false;

    private int rank;
    private int piecesOfEight;
    private String userAnswer = "";

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quizzes);

        //This line obtains shared preferences, which contain the rank and piecesOfEight of the user
        rank = LoadPreferences("UserRank", rank);
        piecesOfEight = LoadPreferences ("UserPoints", piecesOfEight);

        //Declare our view variables & assign views from the layout file
        mUserRank = (TextView) findViewById(R.id.Rank);
        mVoyage = (TextView) findViewById(R.id.Quest);
        mProgress = (TextView) findViewById(R.id.Progress);
        mScore = (TextView) findViewById(R.id.Score);
        mPiecesOfEight = (TextView) findViewById(R.id.PiecesOfEight);

        mAnswer = (EditText) findViewById(R.id.answerText);
        mTextView = (TextView) findViewById(R.id.textView);
        mEndingText = (TextView) findViewById(R.id.EndQuizText);
        final Button getQuizWordButton = (Button) findViewById(R.id.getQuizWordButton);
        final Button checkButton = (Button) findViewById(R.id.checkButton);
        final Button exitButton = (Button) findViewById(R.id.exitButton);

        mUserRank.setText("Rank: " +  mRank.getRankDescription(rank));  //displays user's current rank
        mVoyage.setText("Quest: " +  mQuest.getVoyage(rank));
        mPiecesOfEight.setText("Pieces of Eight: " + piecesOfEight);

        //*********For testing. Need to comment out this line later!!*************
        //rank = 8;
        //*********For testing. Need to comment out this line later!!*************

    //get a word button
    View.OnClickListener getQuizWord = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            getPirateQuizWord();
        }
    };

    //check Word button
    View.OnClickListener checkWord = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            checkAnswer();
        }
    };

        //Exit button
        View.OnClickListener walkThePlank = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savePrefs("UserPoints", piecesOfEight);
                savePrefs("UserRank", rank);
                Intent intent = new Intent(quizActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        };

    getQuizWordButton.setOnClickListener(getQuizWord);
    checkButton.setOnClickListener(checkWord);
    exitButton.setOnClickListener(walkThePlank);

    }

    public void getPirateQuizWord() {
        mAnswer.setText("");    //clear the text field

        if (rank == 9){ //User is a captain, and has won the game!
            startCaptainActivity();
        }

        if ((quizWordCount == 0)|| (quizWordCount <= 9 && mAnswered)) {  //mAnswered makes sure user cannot skip words
                    String fact = mPirateWords.getPirateWord(rank); //Update the screen with the Spanish word
                    mTextView.setText(fact);

                    mAnswer.setHint("Type ye answer");
                    quizWordCount++;
                    isNewWord = true;
                    mAnswered = false;

        }

        else if (quizWordCount > 9 && quizOver == false) {
              quizOver();
        }
    }

    public void checkAnswer() {
        //get user input
        userAnswer = mAnswer.getText().toString().toLowerCase();
        String correctAnswer = mPirateWords.getPirateAnswer(rank);

        if (rank == 9){ //User is a captain, and has won the game!
            startCaptainActivity();
        }

      /*  else if (quizWordCount > 9 && quizOver == false) {
            quizOver();
        }*/

        //compare to correct answer
        else if (!quizOver) {
            if (isNewWord) {  //user must have retrieved a new word before checking the answer
                if (userAnswer.equals(correctAnswer)) {
                    score++;
                    piecesOfEight++;
                    Toast.makeText(quizActivity.this, "Aye, correct " + mRank.getRankDescription(rank), Toast.LENGTH_SHORT).show();
                    mProgress.setText("Progress: " + quizWordCount * 10 + " %");
                    mScore.setText("Score: " + score + " out of "  + quizWordCount);
                    mPiecesOfEight.setText("Pieces of Eight: " + piecesOfEight);
                    mAnswered = true;
                    isNewWord = false;

                } else {
                    Toast.makeText(quizActivity.this, "Sorry, " + mRank.getRankDescription(rank) +
                             ", th' word be \"" + correctAnswer + "\"", Toast.LENGTH_SHORT).show();
                    mProgress.setText("Progress: " + quizWordCount * 10 + " %");
                    mScore.setText("Score: " + score + " out of "  + quizWordCount);
                    mAnswered = true;
                    isNewWord = false;
                }
            }
        }
    }

    //*********Code for audio feature testing*************
    /*Audio audio = Audio.getInstance();
    InputStream sound  = audio.getAudio("I am a bus", Language.ENGLISH);
    audio.play(sound);*/
    //*********Code for audio testing*************

    //go to the settings page
    private void startCaptainActivity(){
        Intent intent = new Intent(quizActivity.this, CaptainActivity.class);
        startActivity(intent);
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

    void quizOver(){  //this method promotes user & awards coins if user passes quiz

        questsActivity quests = new questsActivity();

        if (score >= 8) {

            if (rank != 9){  //If user is not a captain, promote them.
                rank++;
            }

            mUserRank.setText("Rank: " + mRank.getRankDescription(rank));
            mAnswer.setHint("");
            mAnswer.setText("");
            mTextView.setText("");
            mTextView.setTextAppearance(getApplicationContext(), R.style.AppTheme2);

            String success = quests.getVoyageSuccess(rank - 1) +  " Yer new rank is " +  mRank.getRankDescription(rank) + ".";
            String perfect = " An' ten bonus pieces of eight fer a perfect score.";

            if (score == 10) {
                mEndingText.setText(success + perfect);
                piecesOfEight = piecesOfEight + 10;
                mPiecesOfEight.setText("Pieces of Eight: " + piecesOfEight);
            }
            else {
                mEndingText.setText(success);
            }

            quizOver = true;
        }
        else {
            String loss = quests.getVoyageFailure(rank) + " Ye remain at the rank of " + mRank.getRankDescription(rank) + ".";
            mAnswer.setHint("");
            mAnswer.setText("");
            mTextView.setText("");
            mEndingText.setText(loss);
            quizOver = true;
        }
    }
}
