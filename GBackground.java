/**
 * This class is the background of the game
 */
import acm.graphics.GCompound;
import acm.graphics.GImage;
import acm.graphics.GLabel;

public class GBackground extends GCompound {
	/** Score board tab in the game*/
	static GLabel score;
	
	/**Wooden background for score tab*/
	static GImage back;
	
	/**A number of the collected Heads*/
	int collected;
	
	public GBackground(int collected) {
		
		back = new GImage("Background.jpg");
		back.setSize(600, 800);
		add(back);
		
		GImage scoreBoard = new GImage("scoreBoard.jpg");
		scoreBoard.scale(0.5);
		add(scoreBoard,-1,-15);
		
		this.collected=collected;
		score = new GLabel("Collected: " + Integer.toString(this.collected)+"   Missed: "+Integer.toString(Apple.missed));
		score.setFont("Century Schoolbook-24-Bold");	
		add(score,290,getHeight()/2-380);
		
		
		
	}
	
}
