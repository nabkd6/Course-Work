import java.util.*;

public class MarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;

    public MarkovWord(int myOrder) {
        myRandom = new Random();
        this.myOrder = myOrder;
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String text){
        myText = text.split("\\s+");
    }

    //Searches text for first match of a WordGram given starting index. Returns -1 if not found.
    public int indexOf(String[] words, WordGram target, int start){
        for(int k = start; k<= words.length-target.length(); k++){
            WordGram wg = new WordGram(words,k,target.length());
            if(wg.equals(target)){
                return k;
            }
        }
        return -1;
    }

    //Creates an arraylist of words which follow a given WordGram
    private ArrayList<String> getFollows(WordGram kGram){
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        while(pos<(myText.length)){
            int start = indexOf(myText,kGram,pos);
            if(start<0){
                break;
            }
            if(start>=myText.length-myOrder){
                break;
            }
            follows.add(myText[start+myOrder]);
            pos = (start+myOrder);
        }
        return follows;
    }

    //Gets Random text to be used in MarkovRunner class
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
        WordGram key = new WordGram(myText,index,myOrder);
        sb.append(key);
        sb.append(" ");
        for(int k = 0; k<numWords-myOrder;k++){
            ArrayList<String> follows = getFollows(key);
            if(follows.size()==0){
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = key.shiftAdd(next);
        }
        return sb.toString().trim();
    }

}
