package com.joshbgold.PirateSpanishFree;

/**
 * Created by JoshG on 3/4/2015.
 */

public class Rank {

    //private int rank = 0;
    private String[] rankDescription = {
            "Powder Monkey",  //rank = 0
            "Sailor",
            "Rigger",
            "Gunner's Mate",
            "Gunner",
            "Boatswain",
            "Carpenter",
            "Sailing Master",
            "Quartermaster",
            "Captain"  //rank = 9
    };

    public String getRankDescription(int i) {
        return rankDescription[i];
    }

}
