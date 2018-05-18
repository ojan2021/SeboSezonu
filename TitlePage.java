/**This class is the Starting Page of the Game*/
import acm.graphics.GCompound;
import acm.graphics.GImage;

public class TitlePage extends GCompound implements GSoundEffects{
	
	public TitlePage()
	{
		welcomeToGame();
		
		GImage title = new GImage("title2.png"); //The name of the Game
		title.setSize(600, 800);
		add(title,-100,-150);
			
		GImage click = new GImage("clicktostart.png"); // Click to start
		click.scale(0.5);
		add(click,-70,450);
		
	}
}
