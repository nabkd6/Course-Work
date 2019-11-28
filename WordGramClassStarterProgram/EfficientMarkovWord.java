import java.util.*;

public class EfficientMarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<WordGram, ArrayList<String>> map;

    public EfficientMarkovWord(int myOrder) {
        myRandom = new Random();
        this.myOrder = myOrder;
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String text){
        myText = text.split("\\s+");
    }

    //Build a hashmap to increase efficiency
    public HashMap<WordGram, ArrayList<String>> buildMap(){
        //initialize map
        HashMap<WordGram, ArrayList<String>> map = new HashMap<WordGram, ArrayList<String>>();
        //start at beginning of text, iterate over whole text - the order length
        for (int index = 0; index < (myText.length - myOrder); index++) {
            //start slicing up wordgrams from the text.
            WordGram wg = new WordGram(myText, index,myOrder);
            //the value of the specific wg should be the index + wg.length()
            String value = myText[index + wg.length()];
            //if it's already in the map, add the value to it's key.
            if (map.containsKey(wg)) {
                map.get(wg).add(value);
            } else {
                //otherwise, add an empty arraylist
                ArrayList<String> ArrVal = new ArrayList<String>();
                ArrVal.add(value);
                map.put(wg, ArrVal);
            }
        }
        //if the last wg isn't in the map, add an empty follow set.
        WordGram finalWg = new WordGram(myText,myText.length - myOrder,myOrder);
        if(!map.containsKey(finalWg)){
            ArrayList<String> value = new ArrayList<String>();
            map.put(finalWg,value);
        }
        return map;
    }

    //Takes WordGram, returns arraylist of words which follow WordGram.
    public ArrayList<String> getFollows(WordGram key){
        ArrayList<String> follows = new ArrayList<String>();
        if (map.containsKey(key)){
            return map.get(key);
        }
        return follows;
    }
    //To be called by Model runners to randomly generate text.
    public String getRandomText(int numWords){
        //Init empty string builder object
        StringBuilder sb = new StringBuilder();
        map = buildMap();
        // Random word to start with
        int index = myRandom.nextInt(myText.length-myOrder);
        // Make initial WordGram from starting word and append to stringbuilder with space
        WordGram key = new WordGram(myText,index,myOrder);
        sb.append(key.toString());
        sb.append(" ");
        //Iterate numWords times to generate desired number of words
        for(int k = 0; k<numWords-myOrder;k++){
            //Get follows character from WordGram
            ArrayList<String> follows = getFollows(key);
            if(follows.size()==0){
                break;
            }
            //Choose a random word from the follows list and append to stringbuilder
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            //Shift the WordGram key for next loop
            key = key.shiftAdd(next);
        }
        return sb.toString().trim();
    }
    //Function to print hashmap to test hashmap. Uncomment first line for small tests.
    public void printHashMapInfo(){
        //System.out.println(map);
        System.out.println("Map size: " + map.size());
        int biggest = 0;
        for(ArrayList<String> val: map.values()){
            if (val.size()>biggest){
                biggest = val.size();
            }
        }
        System.out.println("Biggest: " + biggest);
        System.out.print("Biggest keys: " + "\n");
        System.out.print("\n");

    }

}
