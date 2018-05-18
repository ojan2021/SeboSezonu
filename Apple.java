/**
* This class is for the Apples that falling from the air.
* Also, in there, we are checking 
* if the head is inside of the cup or not.
*/
import acm.graphics.GImage;
import acm.graphics.GRectangle;

public class Apple extends GImage implements GSoundEffects, Runnable {
	/**The cup in the GameTest class*/
	static GBasket obj;
	
	/**Speed of the heads falling from the air*/
	public static int SPEED = 30;
	
	/**A number of collected heads*/
	public static int collected = 0;
	
	/**A number of missed heads*/
	public static int missed = 0;
	
	double direction = 1;
	
	public Apple(GBasket basket) {
		super("sebo2.gif");
		obj = basket;
		setSize(60, 80);
	}

	/**
	 * In this method, heads falling from the air.
	 */
	public void run() {
		int colCount = 0; //for counting the collission for once
		int misCount = 0; //for checking missed apples

		while (true) {
			move(0, direction);
			pause(SPEED);
			direction++;

			while (GameTest.pause) {
				try {
					Thread.sleep(SPEED);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			GRectangle bas_bounds = obj.getBounds();
			double bas_boundsX = bas_bounds.getX() + 50;
			double bas_boundsY = bas_bounds.getY();
			double bas_boundsWidth = bas_bounds.getWidth() - 80;
			double bas_boundsHeight = bas_bounds.getHeight();
			bas_bounds.setBounds(bas_boundsX, bas_boundsY, bas_boundsWidth, bas_boundsHeight);

			if (this.getBounds().intersects(bas_bounds)) {
				++colCount;
				this.setVisible(false);

				if (colCount == 1) {
					++collected;
					obj.move(0, 3);
					pause(50);
					obj.move(0, -3);
					collectedEffect();
				}

			} 
			else if (this.getBounds().intersects(GameTest.missFinder.getBounds())) {
				++misCount;
				if (misCount == 1) {
					++missed;
				}//if
				
				GBackground.score.setLabel("Collected: " + Apple.collected + 
				"   Missed: " + Math.abs(Apple.collected - Apple.missed));
			}//else if

		}//while(true)

	}//run

}//Class
