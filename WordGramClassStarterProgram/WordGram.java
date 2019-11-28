
public class WordGram {
    private String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
    }

    //Implement hashCode so that we can use WordGram objects in hashMap
    public int hashCode() {
        return toString().hashCode();
    }

    //Returns word at given index in text arraylist
    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }

    public int length(){
        return myWords.length;
    }

    public String toString(){
        String ret = "";
        for(String item:myWords){
            ret = ret.concat(" " + item + " ");
        }
        return ret.trim();
    }

    //Test if WordGram objects are equal
    public boolean equals(Object o) {
        WordGram other = (WordGram) o;
        //If lengths are not equal, cannot be equal
        if(length() != other.length()){
            return false;
        }
        //Otherwise, check each string at respective indices matches
        for(int index = 0; index<myWords.length;index++){
            if(!myWords[index].equals(other.wordAt(index))){
                return false;
            }
        }
        return true;
    }

    //Returns a new WordGram with given string added to end, drops first word in WordGram
    public WordGram shiftAdd(String word) {	
        WordGram out = new WordGram(myWords, 0, myWords.length);
        // shift all words one towards 0 and add word at the end. 
        // you lose the first word
        for(int k = 0; k<(myWords.length-1); k++){
            out.myWords[k] = myWords[k+1];
        }
        out.myWords[myWords.length-1] = word;
        return out;
    }

}