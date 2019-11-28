
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*; 

public class MarkovRunnerWithInterface {
	//Runs a markov model.
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
        markov.setRandom(seed);
        System.out.println("running with " + markov);
        for(int k=0; k < 3; k++){
			String st= markov.getRandomText(size);
			printOut(st);
		}
    }

    //Runs a markov model with file resource. Change commenting to run different models.
    public void runMarkov() {
        FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		/*
        MarkovZero mz = new MarkovZero();
        runModel(mz, st, size, 30);
    
        MarkovOne mOne = new MarkovOne();
        runModel(mOne, st, size, 30);
        
        MarkovModel mThree = new MarkovModel(3);
        runModel(mThree, st, size, 30);

        MarkovFour mFour = new MarkovFour();
        runModel(mFour, st, 200, 30);
		 */
		EfficientMarkovModel eMark = new EfficientMarkovModel(2);
		runModel(eMark, st, 200, 30);


    }

    //Runs a quick test to see if hashmap is working as intended.
    public void testHashMap(){
		//FileResource fr = new FileResource();
		//String st = fr.asString();
		String st = "yes-this-is-a-thin-pretty-pink-thistle";
		st = st.replace('\n', ' ');
		int size = 50;
		EfficientMarkovModel mThree = new EfficientMarkovModel(2);
		runModel(mThree, st, size, 42);
	}

	//Compares efficient vs regular markovModels
	public void compareMethods(){
		FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		int size = 1000;
		long currenttime = System.nanoTime();
		MarkovModel mTwo = new MarkovModel(2);
		runModel(mTwo, st, size, 42);
		long model2time = System.nanoTime();
		System.out.println("total time is " + ((model2time - currenttime)/1000000000));
		long timeagain = System.nanoTime();
		EfficientMarkovModel m = new EfficientMarkovModel(5);
		runModel(m, st, size, 615);
		long efficienttime = System.nanoTime();
		System.out.println("total time is " + ((efficienttime - timeagain)/1000000000));


	}

	public static void main(String[] args) {
		MarkovRunnerWithInterface program = new MarkovRunnerWithInterface();
		//adjust below to run different methods
		program.compareMethods();
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
}