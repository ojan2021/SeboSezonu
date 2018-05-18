/**
 * This class is the score board in the game which appears in the end
 */
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;

import acm.graphics.GCompound;
import acm.graphics.GImage;
import acm.graphics.GLabel;

public class EndGame extends GCompound {
	/**Blackboard for background*/
	GImage back;
	
	/**The final score of the collected heads*/
	GLabel finalCollected;	
	
	/**The final score of the missed heads*/
	GLabel finalMissed;

	public EndGame() {
		back = new GImage("EndGame.jpg");
		back.scale(0.5);
		
		add(back, (getWidth() - back.getWidth()) / 2, (getHeight() - back.getHeight()) / 2);
		
		
		finalCollected = new GLabel(Integer.toString(Apple.collected));
		finalCollected.setColor(Color.white);
		finalCollected.setFont("Didot-34"); 

		add(finalCollected, getWidth() / 2 - 240, getHeight() / 2 - 210);

		finalMissed = new GLabel(Integer.toString(Apple.missed - Apple.collected));
		finalMissed.setColor(Color.white);
		finalMissed.setFont("Didot-34");
		
		add(finalMissed, getWidth() / 2 - 280, getHeight() / 2 - 152);

		try {
			BufferedReader br = new BufferedReader(new FileReader("scores.txt"));
			
			GLabel highScore = new GLabel(br.readLine()); //Highest score from the file
			highScore.setColor(Color.white);
			highScore.setFont("Didot-34");
			add(highScore, getWidth() / 2 - 235, getHeight() / 2 - 77);
			
			br.close();
		} catch (Exception e) {
			e.getMessage();
		}

	}

}
