//package typingTutor;

public class FallingWord {
	private String word; // the word
	private int x; //position - width
	private int y; // postion - height
	private int maxY; //maximum height
	private int maxX; // maximum width
	private boolean dropped; //flag for if user does not manage to catch word in time
	private boolean out;
	
	private int fallingSpeed; //how fast this word is
	private int MovingSpeed=300;
	private static int maxWait=1000;
	private static int minWait=100;

	public static WordDictionary dict;
	
	FallingWord() { //constructor with defaults
		word="computer"; // a default - not used
		x=0;
		y=0;	
		maxY=300;
		dropped=false;
		fallingSpeed=(int)(Math.random() * (maxWait-minWait)+minWait); 
	}
	
	FallingWord(String text) { 
		this();
		this.word=text;
	}
	
	FallingWord(String text,int x, int maxY) { //most commonly used constructor - sets it all.
		this(text);
		this.x=x; //only need to set x, word is at top of screen at start
		this.maxY=maxY;
		
	}
	FallingWord(String text,int x, int maxY, int maxX) { //most commonly used constructor - sets it all.
		this(text);
		this.y=TypingTutorApp.frameY/2; //only need to set x, word is at top of screen at start
		this.maxY=maxY;
		this.maxX=maxX;
	}
	
	public static void increaseSpeed( ) {
		minWait+=50;
		maxWait+=50;
	}
	
	public static void resetSpeed( ) {
		maxWait=1000;
		minWait=100;
	}
	

// all getters and setters must be synchronized
	public synchronized  void setY(int y) {
		if (y>maxY) {
			y=maxY;
			dropped=true; //user did not manage to catch this word
		}
		this.y=y;
	}
	public synchronized  void sethaX(int x) {
		if (x>maxX) {
			x=maxX;
			out=true; //user did not manage to catch this word
		}
		this.x=x;
	}
	
	
	public synchronized  void setX(int x) {
		this.x=x;
	}
	public synchronized  void sethaY(int y) {
		this.y=y;
	}
	
	public synchronized  void setWord(String text) {
		this.word=text;
	}

	public synchronized  String getWord() {
		return word;
	}
	
	public synchronized  int getX() {
		return x;
	}
	public synchronized  int gethaX() {
		return x;
	}
		
	
	public synchronized  int getY() {
		return y;
	}
	public synchronized  int gethaY() {
		return y;
	}
	
	public synchronized  int getSpeed() {
		return fallingSpeed;
	}
	public synchronized  int getSpeedx() {
		return MovingSpeed;
	}

	public synchronized void setPos(int x, int y) {
		setY(y);
		setX(x);
	}
	public synchronized void resetPos() {
		setY(0);
	}
	public synchronized void resetPosx() {
		sethaX(0);
	}

	public synchronized void resetWord() {
		resetPos();
		word=dict.getNewWord();
		dropped=false;
		fallingSpeed=(int)(Math.random() * (maxWait-minWait)+minWait); 
		//System.out.println(getWord() + " falling speed = " + getSpeed());
	}
	public synchronized void resetWordx() {
		resetPosx();
		word=dict.getNewWord();
		out=false;
		fallingSpeed=(int)(Math.random() * (maxWait-minWait)+minWait); 
		//System.out.println(getWord() + " falling speed = " + getSpeed());
	}
	
	public synchronized boolean matchWord(String typedText) {
		//System.out.println("Matching against: "+text);
		if (typedText.equals(this.word)) {
			resetWord();
			return true;
		}
		else
			return false;
	}
	public synchronized boolean matchWordx(String typedText) {
		//System.out.println("Matching against: "+text);
		if (typedText.equals(this.word)) {
			resetWordx();
			return true;
		}
		else
			return false;
	}

	public synchronized  void drop(int inc) {
		setY(y+inc);
	}
	public synchronized  void move(int inc) {
		sethaX(x+inc);
	}
	
	public synchronized  boolean dropped() {
		return dropped;
	}
	public synchronized  boolean out() {
		return out;
	}
	public synchronized boolean collide(FallingWord word, FallingWord wordx) {
		if(Math.abs(word.getY()-wordx.getY())<20 && Math.abs(word.getX()-wordx.getX())<60){
			return true;
		}
		return false;}
		
		
	

}
