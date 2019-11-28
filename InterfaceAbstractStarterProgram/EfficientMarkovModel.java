import java.util.Map;
import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;

public class EfficientMarkovModel extends AbstractMarkovModel{
    private int n;
    private HashMap<String, ArrayList<String>> map;

    public EfficientMarkovModel(int n) {
        myRandom = new Random();
        this.n = n;
    }

    public String toString(){
        return "MarkovModel of order " + n;
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }

    public void setTraining(String s){
        myText = s.trim();
    }

    //Creates hashMap of all keys, follow arraylists in text
    public HashMap<String, ArrayList<String>> buildMap(){
        HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
        //loop through all text
        for (int index = 0; index < myText.length() - n; index++){
            String hashkey = myText.substring(index, index+n);
            String value = myText.substring(index+n, index+n+1);
            //if key is in map, add the value to arraylist, otherwise add empty arraylist
            if (map.containsKey(hashkey)){
                map.get(hashkey).add(value);
            } else {
                ArrayList<String> val = new ArrayList<String>();
                val.add(value);
                map.put(hashkey, val);
            }
        }
        //Once the end is reached, look at the final key. If in map, add empty string. Else add empty arraylist.
        String finalKey = myText.substring(myText.length()-n, myText.length());
        String finalVal = "";
        if(map.containsKey(finalKey)){
            map.get(finalKey).add(finalVal);
        } else {
            ArrayList<String> val = new ArrayList<String>();
            val.add(finalVal);
            map.put(finalKey, val);
        }
        return map;
    }

    //Gives info about hashmap. Uncomment 1st line for small examples
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
        for(String key: map.keySet()){
            if(map.get(key).size() == biggest){
                System.out.println(key);
            }
        }
        System.out.print("\n");

    }

    //Returns follow arraylist for a given key
    public ArrayList<String> getFollows(String key){
        ArrayList<String> follows = new ArrayList<String>();
        if (map.containsKey(key)){
            return map.get(key);
        }
        return follows;
    }

    /*Used in MarkovRunnerWithInterface class to generate text.
    Builds a hashmap from abstract markov model text.
    Creates stringbuilder object which will be returned.
    Loops through text numChars times to create stringbuilder object,
    calling getFollows to keep adding new characters.
    */

    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        map = buildMap();
        //Uncomment below for testing hashmap
        //printHashMapInfo();
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-n);
        String key = myText.substring(index, index+n);
        sb.append(key);
        for(int k=0; k < numChars-n; k++){
            ArrayList<String> follows = getFollows(key);
            if(follows.size() == 0){
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1) + next;
        }

        return sb.toString();
    }
}
