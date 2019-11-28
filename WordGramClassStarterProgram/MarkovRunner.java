
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class MarkovRunner {
    //Runs a model
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 
    //Runs a model with seed
    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    //Runs a model from a file source
    public void runMarkov() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        EfficientMarkovWord mw= new EfficientMarkovWord(2);
        runModel(mw, st, 200, 65);
    }

    public static void main(String[] args) {
        MarkovRunner program = new MarkovRunner();
        program.runMarkov();
    }

    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println(); 
                psize = 0;
            } 
        } 
        System.out.println("\n----------------------------------");
    }

    //quick view to test results for hashMap
    public void printHashMapInfo(){
        String st = "this is a test yes this is really a test";
        st = st.replace('\n', ' ');
        EfficientMarkovWord mw= new EfficientMarkovWord(2);
        runModel(mw, st, 50, 65);
    }

    //Compares time of MarkovWord vs EfficientMarkovWord
    public void compareMethods(){
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovWord mw= new MarkovWord(2);
        long initTime = System.nanoTime();
        runModel(mw, st, 100, 42);
        long finTime = System.nanoTime();
        long totalTimeRegMarkov = finTime-initTime;
        System.out.println("Regular markov time: " + totalTimeRegMarkov);
        EfficientMarkovWord emw= new EfficientMarkovWord(2);
        long initTime2 = System.nanoTime();
        runModel(emw, st, 100, 42);
        long finTime2 = System.nanoTime();
        long totalTimeEMarkov = (finTime2-initTime2)/1000000;
        System.out.println("Efficient markov time: " + totalTimeEMarkov);
        System.out.println("Regular markov time: " + totalTimeRegMarkov);

    }

}
