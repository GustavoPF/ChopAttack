import java.awt.*;

public abstract class GameObject {

	Sprite sprite;

	float color;

	String current_state = "";
	//int current_state = 0;

	boolean willFall; // TO BE USED BY PHYSICS CLASS TO DETERMINE WHETHER OR NOT
						// GRAVITY AFFECT OBJ

	boolean isPlayer = false;
	
	boolean hasCollided;

	int MAX_VELOCITY;

	// ORDERED PAIR FOR COORDS
	float x;
	float y;

	boolean hasCollidedWithLevel = false;
	
	Image current_Image;

	float acceleration = 10f;

	int width;
	int height;

	float xVelocity;

	float yVelocity;

	public GameObject(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public GameObject(int x, int y, float color) {
		this.x = x;
		this.y = y;
		
		this.color = color;
	}
	
	public void draw(Graphics g) {
		current_Image = sprite.getCurrentImage();

		this.height = current_Image.getHeight(null);
		this.width = current_Image.getWidth(null);

		g.drawImage(current_Image,

		Math.round(x),

		Math.round(y),

		null);

		// g.drawImage(current_Image, x, y, null);
	}
	
	public void set() {
		current_Image = sprite.getCurrentImage();

		this.height = current_Image.getHeight(null);
		this.width = current_Image.getWidth(null);

		// g.drawImage(current_Image, x, y, null);
	}

	public void move() // MAYBE IMPLEMENT MOVELEFT/ETC VIA THIS FXN? THINK ABOUT
						// IT YO
	{
		this.y += yVelocity;
		this.x += xVelocity;
	}

	public void moveRight() // MAYBE IMPLEMENT MOVELEFT/ETC VIA THIS FXN? THINK
							// ABOUT IT YO
	{
		this.x += xVelocity;
	}

	public void moveLeft() // MAYBE IMPLEMENT MOVELEFT/ETC VIA THIS FXN? THINK
							// ABOUT IT YO
	{
		this.x -= xVelocity;
	}

	/*
	 * public boolean hasCollidedWith(GameObject obj) { double d =
	 * this.distanceTo(obj.x, obj.y);
	 * 
	 * return (d < 1); }
	 * 
	 * 
	 * public double distanceTo(int x, int y) { return (x - this.x) * this.yN -
	 * (y - this.y) * this.xN; }
	 */

	public void handleAllCollisions(GameObject[] object) {
		if(this.hasCollided(object))
		{
			for (int i = 1; i < object.length; i++) {
				this.handleCollisionWith(object[i]);
			}
		}
	}

	public void handleCollisionWith(GameObject obj) {

		if (hasCollidedWith(obj)) {

			if ( // COLLIDE BOTTOM
			((y + height - obj.y) < ((obj.y + obj.height) - y))
					&& ((y + height - obj.y) < ((obj.x + obj.width) - x))
					&& ((y + height - obj.y) < ((x + width) - obj.x))) {

				yVelocity = 0;

				y = obj.y - height;

				// Physics.elapsed_time = .0166f;

			}

			if ( // COLLIDE TOP
			((obj.y + obj.height) - y) < (y + height - obj.y)
					&& ((obj.y + obj.height) - y) < ((obj.x + obj.width) - x)
					&& ((obj.y + obj.height) - y) < ((x + width) - obj.x)) {

				yVelocity = 0;

				y = obj.y + obj.height;

			}
			if ( // COLLIDE RIGHT
			((x + width) - obj.x) < (y + height - obj.y)
					&& ((x + width) - obj.x) < ((obj.x + obj.width) - x)
					&& ((x + width) - obj.x) < ((obj.y + obj.height) - y)) {

				x = obj.x - width;

			}
			if ( // COLLIDE LEFT
			((obj.x + obj.width) - x) < (y + height - obj.y)
					&& ((obj.x + obj.width) - x) < ((x + width) - obj.x)
					&& ((obj.x + obj.width) - x) < ((obj.y + obj.height) - y)) {

				x = obj.x + obj.width;

			}
		}
	}

	public boolean contains(int mx, int my) {
		return (mx > x) && (mx < x + width) && (my > y) && (my < y + height);
	}

	public boolean hasCollidedWith(GameObject obj) {
		
		if ((obj.x + obj.width >= x) && (obj.x <= x + width)
				&& (obj.y + obj.height >= y) && (obj.y <= y + height))
		{
		return true;
		}
		
		return false;
		

	}

	public boolean hasCollided(GameObject[] object) {

		for (int i = 0; i < object.length; i++)
		{
			if (this.hasCollidedWith(object[i]))
			{
				this.hasCollidedWithLevel = true;
				return true;
			}
			else
			{
				this.hasCollidedWithLevel = false;
			}
		}
		return false;
		
	}
}
