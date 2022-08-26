//package typingTutor;

import java.util.concurrent.atomic.AtomicBoolean;

//Thread to monitor the word that has been typed.
public class CatchWord extends Thread {
	String target;
	static AtomicBoolean done ; //REMOVE
	static AtomicBoolean pause; //REMOVE
	
	private static  FallingWord[] words; //list of words
	private static  FallingWord[] wordsx;
	private static int noWords; //how many
	private static int noWordsx;
	private static Score score; //user score
	
	CatchWord(String typedWord) {
		target=typedWord;
	}
	
	public static void setWords(FallingWord[] wordList,FallingWord[] wordListx) {
		words=wordList;
		wordsx=wordListx;	
		noWords = words.length;
		noWordsx = wordsx.length;
		System.out.println("awe");
	}
	
	public static void setScore(Score sharedScore) {
		score=sharedScore;
	}
	
	public static void setFlags(AtomicBoolean d, AtomicBoolean p) {
		done=d;
		pause=p;
	}
	
	public void run() {
		int i=0;
		int j=0;
		while (i<noWords) {		
			while(pause.get()) {};
			if (words[i].matchWord(target)) {
				//System.out.println( " score! '" + target); //for checking
				score.caughtWord(target.length());	
				//FallingWord.increaseSpeed();
				break;
			}
		   i++;
		}
		while (j<noWordsx) {		
			while(pause.get()) {};
			if (wordsx[j].matchWord(target)) {
				System.out.println( " score! '" + target); //for checking
				score.caughtWord(target.length());	
				//FallingWord.increaseSpeed();
				break;
			}
		   j++;
		}
		
	}	
}
