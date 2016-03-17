package com.joshbgold.PirateSpanishFree;

public class PirateWords {
    //Member variables (properties about the object)
    private int currentNumber = 0;
    private int wordIndex = 0;
    private boolean hasStarted = false;

    public int getWordIndex() {
        return wordIndex;
    }

    public void setWordIndex(int wordIndex) {
        this.wordIndex = wordIndex;
    }

    private String[] mPirateWord = {
            "pirata",  //word 0
            "tesoro",
            "barco",
            "oro",
            "plata",
            "bronce",
            "océano",
            "capitán",
            "loro",
            "cañón", //word 9
            "saqueo",
            "ataque",
            "bucanero",
            "quemar",
            "corsario",
            "marinero de agua dulce",
            "marinero de cubierta",
            "marinero de primera",
            "escorbuto",
            "canalla",  //word 19
            "pícaro",
            "botín",
            "abandonado",
            "ancla",
            "aventura",
            "en tierra",
            "asalto",
            "pañuelo",
            "bandido",
            "bandolera",  //word 29
            "barril",
            "battalla",
            "decapitar",
            "riña",
            "zozobrar",
            "captura",
            "carga",
            "orilla del mar",
            "monedas",
            "compás",  //word 39
            "conquista",
            "curso",
            "tripulación",
            "nivo de cuervo",
            "chafarote",
            "doblón",
            "parche en el ojo",
            "primer oficial",
            "bandera",
            "restos y desechos", //word 49
            "galeón",
            "pasarela",
            "pólvora",
            "atraco",
            "alta mar",
            "secuestro",
            "el casco del barco",
            "isla",
            "quilla",
            "latigazo",//word 59
            "mapa",
            "mástil",
            "mosquete",
            "motín",
            "náutico",
            "navegar",
            "pata de palo",
            "reales de ocho",
            "pistola",
            "oficial de intendencia",  //word 69
            "búsqueda",
            "redada",
            "raciones",
            "riqueza",
            "aparejo",
            "cuerda",
            "timón",
            "ron",
            "velamen",
            "cicatriz", //word 79
            "algas marinas",
            "orilla",
            "limpiar la cubierta",
            "espada",
            "mareas",
            "traición",
            "tregua",
            "fiebre amarilla",
            "X marca el lugar",
            "caminar por la plancha", //word 89
            "capa y espada",
            "cebo para tiburones",
            "chubasco",
            "tirón",
            "huesos",
            "esqueleto",
            "horca",
            "pandillas",
            "daga",
            "echar por la borda",
            "ominoso"
    };

    private String[] mPirateAnswer = {
            "pirate", //word 0
            "treasure",
            "ship",
            "gold",
            "silver",
            "bronze",
            "ocean",
            "captain",
            "parrot",
            "cannon", //word 9
            "plunder",
            "attack",
            "buccaneer",
            "burn",
            "privateer",
            "landlubber",
            "deckhand",
            "able seaman",
            "scurvy",
            "scoundrel",  //word 19
            "rogue",
            "booty",
            "marooned",
            "anchor",
            "adventure",
            "ashore",
            "assault",
            "bandanna",
            "bandit",
            "bandolier", //word 29
            "barrel",
            "battle",
            "behead",
            "brawl",
            "capsize",
            "capture",
            "cargo",
            "seashore",
            "coins",
            "compass",  //word 39
            "conquest",
            "course",
            "crew",
            "crow's nest",
            "cutlass",
            "doubloon",
            "eye patch",
            "first mate",
            "flag",
            "flotsam and jetsam", //word 49
            "galleon",
            "gangplank",
            "gunpowder",
            "heist",
            "high seas",
            "hijack",
            "ship's hull",
            "island",
            "keel",
            "lash", //word 59
            "map",
            "mast",
            "musket",
            "mutiny",
            "nautical",
            "navigate",
            "pegleg",
            "pieces of eight",
            "pistol",
            "quartermaster", //word 69
            "quest",
            "raid",
            "rations",
            "riches",
            "rigging",
            "rope",
            "rudder",
            "rum",
            "sail",
            "scar", //word 79
            "seaweed",
            "shore",
            "swab the deck",
            "sword",
            "tides",
            "treason",
            "truce",
            "yellow fever",
            "x marks the spot",
            "walk the plank", //word 89
            "swashbuckling",
            "shark bait",
            "squall",
            "heave",
            "bones",
            "skeleton",
            "gallows",
            "gangs",
            "dagger",
            "jettison", //word 99
            "ominous"
    };

    //returns Spanish word, used for Main Activity
    public String getPirateWord() {
        String pirateWord = mPirateWord[wordIndex];
        currentNumber = wordIndex;
        if (wordIndex < mPirateWord.length - 1) {
            wordIndex = wordIndex + 1;
        }
        hasStarted = true;
        return pirateWord;
    }

    //returns Spanish word, used for quizzes
    public String getPirateWord(int rank) {
        String pirateWord = mPirateWord[wordIndex + (rank * 10)];  //grabs the appropriate word list for their rank
        currentNumber = wordIndex;
        if (wordIndex < mPirateWord.length - 1) {
            wordIndex = wordIndex + 1;
        }
        hasStarted = true;
        return pirateWord;
    }

    //returns English definition, used for Main Activity
    public String getPirateAnswer(boolean hasStarted) {
        String emptyAnswer = "";
        if (hasStarted) {
            String pirateAnswer = mPirateAnswer[currentNumber];
            return pirateAnswer;
        }
        return emptyAnswer = "";
    }

    //returns English definition, used for quizzes
    public String getPirateAnswer(int rank) {
            String pirateAnswer = mPirateAnswer[currentNumber + (rank * 10)];  //grabs the appropriate word list for their rank
            return pirateAnswer;
    }

    //goes back to previous word, unless you are on the first word already
    public String getPrevious() {
        String previousWord = "";
        String emptyAnswer = "";

        if (hasStarted){
            if (currentNumber != 0) { //we are past the first word
                currentNumber = currentNumber - 1;
                wordIndex = wordIndex - 1;
                previousWord = mPirateWord[currentNumber];
                return previousWord;
            }
            else if (currentNumber == 0){  //we are at the first word, "pirata"
              previousWord = mPirateWord[currentNumber];
              return previousWord;
            }
         }
        return emptyAnswer;  //shows nothing when we haven't even displayed the first word
    }



}

