import java.awt.*;

public class ImageLayer extends GameObject
{
	
	public ImageLayer(int x, int y, String filename, float color)
	{
		super(x, y, color);
		
		String[] state = new String[1];
		state[0] = "";
		Sprite sprite = new Sprite(filename, state);
		
		this.sprite = sprite;
	}
	/*
	public void draw(Graphics g)
	{
		Image current_Image = sprite.getCurrentImage();
		
		this.height = current_Image.getHeight(null);
		this.width = current_Image.getWidth(null);
		
		
		g.drawImage(current_Image, x, y, null);
	}
*/
}
