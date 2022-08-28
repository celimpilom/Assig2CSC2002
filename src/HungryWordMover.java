//package typingTutor;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class HungryWordMover extends Thread {
	private FallingWord myWord;
	private AtomicBoolean done;
	private AtomicBoolean pause; 
	private Score score;
	private FallingWord [] words;
	CountDownLatch startLatch; //so all can start at once

	
	HungryWordMover( FallingWord word) {
		myWord = word;
	}
	
	HungryWordMover( FallingWord word,WordDictionary dict, Score score,
			CountDownLatch startLatch, AtomicBoolean d, AtomicBoolean p,FallingWord[] words) {
		
		this(word);
		this.words= words;
		this.startLatch = startLatch;
		this.score=score;
		this.done=d;
		this.pause=p;
	}
	
	public void creset() {
		for(int i=0; i<words.length;i++){
			if(myWord.collide(words[i], myWord)){
				words[i].resetWord();
				score.missedWord();
			}
		}
	}
	
	
	
	public void run() {

		//System.out.println(myWord.getWord() + " falling speed = " + myWord.getSpeed());
		try {
			//System.out.println(myWord.getWord() + " waiting to start " );
			startLatch.await();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} //wait for other threads to start
		//System.out.println(myWord.getWord() + " started" );
		while (!done.get()) {				
			//animate the word
			while (!myWord.out() && !done.get()) {
				    myWord.move(10);
					creset();
					try {
						sleep(myWord.getSpeedx());
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					};		
                while(pause.get()&&!done.get()) {/*do nothing */};
			}
			if (!done.get() && myWord.out()) {
				score.missedWord();
				myWord.resetWordx();
			}
			myWord.resetWordx();
		}
	}
	
}
