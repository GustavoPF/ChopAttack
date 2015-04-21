import java.awt.*;

public class Sprite 
{
	Animation[] anim;
	String[] state;
	
	int current_State;
	
	public Sprite(String filename, String[] state)
	{
		this.state = state;
		current_State = 0;
		anim = new Animation[state.length];
		
		for(int j = 0; j < state.length; j++)
		{
			for (int i = 0; i < anim.length; i++) anim[i] = new Animation(filename + state[j], 1, 60);
		}
	}
	
	public Image getCurrentImage()
	{
		return anim[current_State].getCurrentImage();
	}
}


