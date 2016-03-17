package com.joshbgold.PirateSpanishFree;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FAQActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faq);

        final Button exitButton = (Button) findViewById(R.id.exitButton);
        final TextView faq = (TextView) findViewById(R.id.FAQTextView);

        faq.setText("What are the quests in the game? \n"
                        + "The quests are Maiden Voyage, Nombre de Dios, " +
                        "Porto Bello,  Panama City, Santa Marta, Cartegena, Forteleza, Granada, and Nueva Cádiz. \n\n" +
                        "What are the ranks in the game? \n" +
                        "The ranks in ascending order are Powder Monkey, sailor, rigger, gunner's mate, gunner, " +
                        "boatswain, carpenter, sailing master, quartermaster, and captain. \n\n" +
                        "How many correct answers are required to pass a quiz?\n" +
                        "Eight out of ten or better."
        );

        View.OnClickListener walkThePlank = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        };

        exitButton.setOnClickListener(walkThePlank);
    }

}
