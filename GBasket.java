/**This Class is the Cup which heads are falling down into this*/
import acm.graphics.GCompound;
import acm.graphics.GImage;

public class GBasket extends GCompound{
	GImage basket;
	public GBasket()
	{
		basket = new GImage("Glass2.png");
		basket.setSize(100,100);
		add(basket);
	}
}
