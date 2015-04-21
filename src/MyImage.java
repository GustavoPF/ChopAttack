import java.awt.*;

public class MyImage extends GameObject{
	
	//String[] state = {""};
	
	public MyImage(String filename, float color)
	{	
		super(-200, -200, color);
		
		String[] state = new String[1];
		state[0] = "";
		Sprite sprite = new Sprite(filename, state);
		
		this.sprite = sprite;
	}
	
	

}
