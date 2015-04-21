import java.awt.Image;
import java.awt.Toolkit;


public class Animation 
{

	Image[] image;
	
	int delay;
	int waittime;
	
	int current = 0;
	
	public Animation(String name, int count, int delay)
	{
		image = new Image[count];
		
		for(int i = 0; i < image.length; i++)			image[i] = Toolkit.getDefaultToolkit().getImage("images/" + name + ".png");
		
		this.delay = delay;
		waittime = delay;
	}
	
	public Image getCurrentImage()
	{
		delay--;
		
		if(delay == 0)	
		{
			current++;
			delay = waittime;
		}
		
		if(current >= image.length) current = 0;
		return image[current];
	}
}
