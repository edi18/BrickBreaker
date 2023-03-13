package console;

/** Entry point of console program */
public class MainClass {

	static ConsoleBrickBreaker consoleBrickBreaker;  
	
	public static void main(String[] args) {
		
		consoleBrickBreaker = new ConsoleBrickBreaker();
		
		consoleBrickBreaker.start();
		
	}
	
}
