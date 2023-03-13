package console;

import java.util.Scanner;
import java.util.Vector;

import logic.BrickBreaker;
import logic.BrickBreaker.Input;
import logic.GameObject;

/** Main game class for the console interface */
public class ConsoleBrickBreaker {

	BrickBreaker brickBreaker;
	
	ConsoleBuffer consoleBuffer;
	
	Scanner scan;
	
	/** Default constructor */
	public ConsoleBrickBreaker() {
		
		brickBreaker = new BrickBreaker();
				
		consoleBuffer = new ConsoleBuffer((int)brickBreaker.getField().getSize().x, (int)brickBreaker.getField().getSize().y);
		
		scan = new Scanner(System.in);
	}
	
	/** Starts the main game loop */
	public void start() {
		while (brickBreaker.getStatus()) {  // main game loop
			
			draw();
			
			handleInput();
			
			update();
		
		}
	}
	
	/** Handle user input, sends messages to logic */
	private void handleInput() {
		String input = scan.next();
		if (input.equals("a") || input.equals("A")) {
			brickBreaker.processInput(Input.MOVE_PLATFORM_LEFT);
		}
		else if (input.equals("d") || input.equals("D")) {
			brickBreaker.processInput(Input.MOVE_PLATFORM_RIGHT);
		}
	}
	
	/** Prints to console the current status of game logic */
	private void draw() {
		consoleBuffer.clear();
		
		Vector<GameObject> gameObjects = brickBreaker.getGameObjects();
		for (int i = 0; i < gameObjects.size(); i++) {
			consoleBuffer.draw(gameObjects.get(i));
		}
		
		consoleBuffer.present();
	}

	/** Calls update on game logic */
	private void update() {
		brickBreaker.update();		
	}
	
}
