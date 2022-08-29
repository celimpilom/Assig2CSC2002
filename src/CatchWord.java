//package typingTutor;

import java.net.SocketAddress;
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
	public int lowest(){
		int yw=0;
		int ind =0;
		for(int i =0; i< words.length;i++){
			if (target.equals(words[i])) {
				if(words[i].getY()>yw){
					yw=words[i].getY();ind=i;}
			}
		}
		return ind;
	}
	
	public void run() {

		int i = 0;
		FallingWord lowest = null;

		while (i < noWords) {
			while (pause.get()) {
			}
			;
			//NEW
			if (words[i].getWord().equals(target)) {
				if (lowest == null) {
					lowest = words[i];
				} else if (lowest.getY() < words[i].getY()) {
					lowest = words[i];
				}
			}

			i++;
		}

		for(int xp =0; xp<noWordsx;xp++){
		if (lowest != null) {
			if (wordsx[xp].getWord().equals(target)) {
				if (lowest.getY() > wordsx[xp].getY()) {
					lowest.resetWord();
					score.caughtWord(target.length());
				} else {
					lowest = wordsx[xp]; 
					lowest.resetWordx();
					score.caughtWord(target.length());
				}
				
			}else {
				lowest.resetWord();
				score.caughtWord(target.length());
			}
		}
		else if (wordsx[xp].getWord().equals(target)) {
				lowest = wordsx[xp];
				lowest.resetWordx();
				score.caughtWord(target.length());
		}
	}

	}	
}
