package com.joshbgold.PirateSpanishFree;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class questsActivity extends MainActivity{

    private int rank;
    private TextView questText;
    boolean connected;
    protected AdView adview;

    private String mVoyage[] = {"Maiden Voyage", "Nombre de Dios", "Porto Bello",  "Panama City", "Santa Marta", "Cartegena",
            "Forteleza", "Granada","Nueva Cádiz", "Nueva Cádiz" };

    String mVoyageDescription[] =
            {"The Spanish ship Encarnación is headed to Havana on her maiden voyage.  Your mission is to intercept and capture the ship.",
            "Your orders are to blockade the city Nombre de Dios until the government submits and surrenders.",
            "The fortifications of Porto Bello face the sea, but the city is unprepared for a land-based attack. Land your crew 8 kilometers south of Porto Bello, " +
                    "trek through the jungle, and attack city from the rear.",
            "Voyage to Panama City and kidnap the archbishop of Panama. Ransom the archbishop for 10,000 gold doubloons.",
            "Your orders are to enlist the support of natives and escaped former slaves for a joint attack on Santa Marta.",
            "Locate Francis Drake's fleet, currently thought to be in the southwest of the Caribbean Sea.  Join forces with his fleet for a direct frontal attack on Cartegena.",
            "The Customs House of Forteleza is one of the richest establishments of the entire New World. Liberate the Customs House of its silver and gold.",
            "Your mission is to sail up the Rio San Juan, and plunder the town of Granada.",
            "Nueva Cádiz is well known for it's prosperous pearl production.  Under the cover of darkness, plunder a pirate's share of the pearls."
    };

    String mVoyageSuccess[] =
            {"Ye captured the Encarnación an' all her crew.",
            "The government of Nombre de Dios has surrendered.",
            "Yer surprise attack on Porto Bello succeeded.",
            "Ye earned 10,000 gold doubloons for ransom of the archbishop.",
            "Ye achieved victory at Santa Marta with the help of the Natives and escaped slaves.",
            "With the help of Francis Drake's fleet, ye captured the city of Cartegena.",
            "Ye plundered the Forteleza customs house of its gold and silver.",
            "Ye sailed the Rio San Juan, an' pillaged Granada.",
            "Ye obtained a fortune in pearls from Nueva Cádiz"};

    String mVoyageFailure[] =
            {"The Spanish ship Encarnación got away.",
            "Nombre de Dios broke through your blockade.",
            "The Porto Bello militia repelled your attack.",
            "The archbishop slipped away via an underground church passageway.",
            "Ye were not able to enlist the support of the natives an' escaped slaves.",
            "Ye found Francis Drake's fleet, but were not able to negotiate an alliance.",
            "Forteleza's twenty-one cannons prevented all of yer ships from landing.",
            "Low water level prevented ye from sailing up the Rio San Juan.",
            "Ye could not locate whar the Nueva Cádiz scoundrels hide thar pearls." };


    public String getVoyage(int rank) {
        return mVoyage[rank];
    }

    public String getVoyageDescription(int rank) {
        return mVoyageDescription[rank];
    }

    public String getVoyageSuccess(int rank) {
        return mVoyageSuccess[rank];
    }

    public String getVoyageFailure(int rank) {
        return mVoyageFailure[rank];
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quests);

        final Button exitButton = (Button) findViewById(R.id.exitButton);
        final Button continueButton = (Button) findViewById(R.id.continueButton);
        adview = (AdView) findViewById(R.id.adView);

        if(isConnected()) {
            AdRequest adRequest = new AdRequest.Builder().build();
            adview.loadAd(adRequest);
        }
        else {
            Toast.makeText(questsActivity.this, "Oh noes! No connection!", Toast.LENGTH_SHORT).show();
            adview.setVisibility(View.GONE);
        }

        //This line obtains shared preferences, which contain the rank of the user
        rank = LoadPreferences("UserRank", rank);
        questText = (TextView) findViewById(R.id.questText);
        questText.setText(getVoyageDescription(rank) + "");

        //continue button
        View.OnClickListener proceed = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startQuiz();
            }
        };

        //Exit button
        View.OnClickListener walkThePlank = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        };

        continueButton.setOnClickListener(proceed);
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

    //go to the quiz page
    private void startQuiz() {
        //get user rank
        rank = LoadPreferences("UserRank", rank);

        if (rank == 6){  //Free version does not allow user to access further content
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Pirate Spanish")
                    .setIcon(R.mipmap.icon)
                    .setMessage("Ye reached the end of yer free content.  Please purchase full version for $1.99 to continue.")
                    .setCancelable(false)
                    .setNegativeButton("OK",new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int id) {

                        //get network connection state
                        connected = isConnected();

                            //go to Google Play Store if connected
                            if (connected) {
                                Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.joshbgold.PirateSpanish");
                                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                startActivity(intent);
                            }
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
        else if (rank == 9){ //User is a captain, and has won the game!
            startCaptainActivity();
        }

        else {
            Intent intent = new Intent(questsActivity.this, quizActivity.class);
            startActivity(intent);
        }
    }

    //go to the settings page
    private void startCaptainActivity(){

        Intent intent = new Intent(questsActivity.this, CaptainActivity.class);
        startActivity(intent);
    }

    //Checks for mobile or wifi connectivity, returns true for connected, false otherwise
    private boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnectedOrConnecting()){
            return true;
        }
        else {
            return false;
        }
    }
}

