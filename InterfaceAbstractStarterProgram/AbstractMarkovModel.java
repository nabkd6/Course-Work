
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;

    public AbstractMarkovModel() {
        myRandom = new Random();
    }

    public void setTraining(String s) {
        myText = s.trim();
    }

    abstract public String getRandomText(int numChars);

    //Return arraylist of characters which follow a given key
    protected ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<String>();
        for (int index = 0; index < myText.length() - key.length(); index++) {
            if (myText.substring(index, index + key.length()).equals(key)) {
                follows.add(myText.substring((index + key.length()), ((index + key.length() + 1))));
            }
        }
        return follows;
    }
}