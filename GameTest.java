/**
* This game is the graphically changed
* and improved version of the game "Apple Season".
* @author Orhan Jan
*/
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import acm.graphics.GImage;
import acm.graphics.GPoint;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;

public class GameTest extends GraphicsProgram implements GSoundEffects {
	private static final long serialVersionUID = 1L;

	public final static int APPLICATION_WIDTH = 600;
	public final static int APPLICATION_HEIGHT = 800;
	/**Number of apples*/
	public final static int NUM_OF_APPLES = 100; 
	
	public final static int WAIT = 100;
	
	/**This constant is for delaying the sequence of the apples falling from the air*/
	public final static int SEQUENCE_DELAY = 700;
	
	/**With this boolean we start the game with button*/
	boolean start = false; 
	
	/**This flag is used for pausing the game*/
	static boolean pause = false;
	
	/**This flag is used for restarting the game*/
	boolean restart = false;
	
	/**The cup in the game*/
	GBasket basket;
	
	/** Start page of the game*/
	TitlePage opening;
	
	/**Background of the game*/
	GBackground back;
	
	/**GameOver Menu of the game*/
	EndGame gameOver;
	
	/**Play button*/
	GImage startSebo;
	
	/**Replay button*/
	GImage replay;
	
	/**This rectangle checks the missed apples*/
	static GRect missFinder;

	public void init() {

		opening = new TitlePage();
		add(opening, 100, 10);

		startSebo = new GImage("TitleSebo.png");
		startSebo.setSize(125, 150);
		add(startSebo, (getWidth() - 150) / 2, (getHeight() + 150) / 2);

		addMouseListeners();

		while (!start) {
			pause(WAIT);
		}

		addKeyListeners();

		whooEffect();

		int dx = 1;
		for (int i = 0; i < 100; i++) {
			startSebo.move(dx, 0);
			opening.move(dx, 0);
			pause(10);
			dx++;
		}

		removeAll();
		backMusic();
		missFinder = new GRect(-3, 800, 650, 50);
		add(missFinder);

		back = new GBackground(Apple.collected);
		add(back);

		basket = new GBasket();
		basket.setLocation(300, 800 - 165);
		add(basket);
		
	}

	public void run() {
		closeWriter();
		startRain();
		
		pause(2500);		
		removeAll();

		updateFile();

		add(GBackground.back);
		gameOver = new EndGame();
		add(gameOver, 300, 400);

		replay = new GImage("replay.png");
		replay.scale(0.07);
		add(replay, 300, 530);

		while (!restart)
			pause(WAIT);

		removeAll();
		start = false;
		restart = false;
		Apple.collected = 0;
		Apple.missed = 0;
		init();
		run();

	}
	
	/**
	* This method starts the falling of the heads.
	*/
	private void startRain() {
		Random rgen = new Random();
		Apple sebo[] = new Apple[NUM_OF_APPLES];
		Thread tH[] = new Thread[NUM_OF_APPLES];
		for (int i = 0; i < NUM_OF_APPLES; i++) {

			while (pause) {
				pause(WAIT);
			}

			sebo[i] = new Apple(basket);
			tH[i] = new Thread(sebo[i]);
			if(i==30)
				Apple.SPEED=27;  //In this part of the code, we are increasing the speed of the heads
			if(i==60)
				Apple.SPEED=23;
			if(i==80)
				Apple.SPEED=20;

			double randomWidth = rgen.nextInt(getWidth() - (int) sebo[0].getWidth());

			add(sebo[i], randomWidth, -sebo[i].getHeight());
			pause(SEQUENCE_DELAY);
			tH[i].start();

		}//for

	}//startRain()
	
	/**
	* If the current score is highest score,
	* we are saving this score into "scores.txt" file as the high score
	* when we close the game window.
	*/
	public void closeWriter() {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				updateFile();
			}
		});
	}
	
	/**
	 * If the current score is highest score,
	 * we are recording this score as the high score 
	 * and update the "scores.txt" file.
	 */
	public void updateFile() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("scores.txt"));

			int a = Integer.parseInt(br.readLine());
			if (a < Apple.collected) {
				System.out.println("heyo");
				PrintWriter pr = new PrintWriter(new FileWriter("scores.txt", false));
				pr.println(Apple.collected);
				pr.close();
			}

			br.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method is for moving the cup in the game.
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		try {
			if (start == true && pause == false)
				if (e.getX() + basket.getWidth() < 600) {
					GPoint last = basket.getLocation();
					basket.move(e.getX() - last.getX(), 0);
					last = null;
				}
		} catch (NullPointerException err) {
			//This try-catch block prevents the errors in start menu of the game
		}
	}

	/**
	 * This method is for starting and restarting the game.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		
		if (startSebo.contains(e.getX(), e.getY())) {
			start = true;
		}

		try {
			if (replay.contains(e.getX(), e.getY())) {
				restart = true;
			}
		} catch (NullPointerException err) {
			//This try-catch block prevents the errors in start menu of the game
		}

	}
	
	/**
	 * This method is for pausing the game with "P" button.
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		
		if (e.getKeyCode() == KeyEvent.VK_P)
		{
			pause = !pause;
		}
	}

	public static void main(String[] args) {
		new GameTest().start();
	}

}
