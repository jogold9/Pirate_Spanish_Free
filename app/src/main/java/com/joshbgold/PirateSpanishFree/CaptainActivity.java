package com.joshbgold.PirateSpanishFree;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by JoshG on 4/9/2015.
 */
public class CaptainActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.captain);

        final Button exitButton = (Button) findViewById(R.id.exitButton);

        View.OnClickListener walkThePlank = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CaptainActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        };

        exitButton.setOnClickListener(walkThePlank);
    }
}

