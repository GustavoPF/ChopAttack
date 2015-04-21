import java.awt.*;
public class Player extends GameObject
{

	String[] state = {"IDLE", "JUMPING", "WALL_RIGHT", "WALL_LEFT", "CEILING"};
	
	String current_state = "JUMPING";
	
	
	int jumpCount = 0;
	
	public Player(int x, int y, String filename, float color)
	{
		super(x, y, color);
		
		String[] state = new String[1];
		state[0] = "";
		Sprite sprite = new Sprite(filename, state);
		
		this.sprite = sprite;
		
		acceleration = 0.2f;
		
		willFall = true;
		
		MAX_VELOCITY = 50;
		
		isPlayer = true;

	}
	
	public void jump()
	{
		//Framework.SPACE_pressed = false;
		if(this.hasCollidedWithLevel)
			{

			
			if(this.current_state.equals("IDLE"))
			{
				this.yVelocity = 20;
				//CHECK STATES IN OBJECT CLASS!! MAYBER OVERWRITE HANDLE COLLISION FOR PLAYER CLASS
			}
			if(this.current_state.equals("WALL_RIGHT"))
			{
				this.xVelocity = -35;
				this.yVelocity = 20;
				//CHECK STATES IN OBJECT CLASS!! MAYBER OVERWRITE HANDLE COLLISION FOR PLAYER CLASS
			}
			if(this.current_state.equals("WALL_LEFT"))
			{
				this.xVelocity = 35;
				this.yVelocity = 20;
				//CHECK STATES IN OBJECT CLASS!! MAYBER OVERWRITE HANDLE COLLISION FOR PLAYER CLASS
			}
			if(this.current_state.equals("CEILING")){/*DO NOTHING*/}
			//current_state = 1;
			//Physics.elapsed_time = .0166f;
		}
		this.jumpCount = 0;
		this.current_state = "JUMPING";
	}

	public void handleCollisionWith(GameObject obj)
	{
		
		if (hasCollidedWith(obj))
		{
			//Physics.elapsed_time = .0166f;
			
			if (
					((y + height - obj.y) < ((obj.y + obj.height) - y)) && 
					((y + height - obj.y) < ((obj.x + obj.width) - x)) &&
					((y + height - obj.y) < ((x + width) - obj.x)))
					{

					
	
					//this.jumpCount = 1;
					
					//current_state = "IDLE";
					if(obj.isPlayer && this.current_state.equals("JUMPING") && obj.current_state.equals("JUMPING") )
					{
						this.yVelocity -= (yVelocity + obj.yVelocity) * .5f;

						y = obj.y - height;
					}
					else
					{
						yVelocity = 0;
						
						y = obj.y - height;
					}

			}
			
			if (
					((obj.y + obj.height) - y) < (y + height - obj.y)&& 
					((obj.y + obj.height) - y) < ((obj.x + obj.width) - x) &&
					((obj.y + obj.height) - y) < ((x + width) - obj.x))
					{

					
					
					if(obj.isPlayer && this.current_state.equals("JUMPING") && obj.current_state.equals("JUMPING") )
					{
						this.yVelocity += (yVelocity + obj.yVelocity) * .5f;
						//y = obj.y + obj.height;
					}
					else 
					{
						yVelocity = 0;
						current_state = "IDLE";
	
						y = obj.y + obj.height;
					}
					//this.jumpCount = 1;
					//current_state = "";
					//Physics.elapsed_time = .0166f;

				}
			/*if (
					((obj.y + obj.height) - y) < (y + height - obj.y)&& 
					((obj.y + obj.height) - y) < ((obj.x + obj.width) - x) &&
					((obj.y + obj.height) - y) < ((x + width) - obj.x) &&
					obj.isPlayer && this.current_state.equals("JUMPING") && obj.current_state.equals("JUMPING"))
					{

				y = obj.y + obj.height;
					
					if(obj.isPlayer && this.current_state.equals("JUMPING") && obj.current_state.equals("JUMPING") )
					{
						this.yVelocity += (yVelocity + obj.yVelocity) * .5f;
						//y = obj.y + obj.height;
					}
					else 
					{
						yVelocity = 0;
						
						current_state = "IDLE";
	
						//y = obj.y + obj.height;
						
					//this.jumpCount = 1;
					//current_state = "";
					//Physics.elapsed_time = .0166f;
					}
				}*/
			if (
					((x + width) - obj.x) < (y + height - obj.y) && 
					((x + width) - obj.x) < ((obj.x + obj.width) - x) &&
					((x + width) - obj.x) < ((obj.y + obj.height) - y))
					{

				if(obj.isPlayer)
				{
					this.xVelocity = (xVelocity + obj.xVelocity) * .5f;
				}
					x = obj.x - width;

					current_state = "WALL_RIGHT";
					
					//this.jumpCount = 1;
					

					//this.jumpCount = 1;

					
				}
			if (
					((obj.x + obj.width) - x) < (y + height - obj.y) && 
					((obj.x + obj.width) - x) < ((x + width) - obj.x) &&
					((obj.x + obj.width) - x) < ((obj.y + obj.height) - y))
				{

					if(obj.isPlayer)
					{
						this.xVelocity = (xVelocity + obj.xVelocity) * .5f;
					}
					x = obj.x + obj.width;

					current_state = "WALL_LEFT";

					//this.jumpCount = 1;

				}
		}
	}
	

	
	public void move() //MAYBE IMPLEMENT MOVELEFT/ETC VIA THIS FXN? THINK ABOUT IT YO
	{
		this.y += yVelocity;
		this.x += xVelocity;
	}
	
	public void moveRight() //MAYBE IMPLEMENT MOVELEFT/ETC VIA THIS FXN? THINK ABOUT IT YO
	{

		if(this.current_state.equals("IDLE")) acceleration = 2 * MAX_VELOCITY;
	
		this.xVelocity +=  this.acceleration * Physics.elapsed_time;
		
		if(xVelocity > MAX_VELOCITY) xVelocity = MAX_VELOCITY;
		
		acceleration = .2f;
	}
	
	public void moveLeft() //MAYBE IMPLEMENT MOVELEFT/ETC VIA THIS FXN? THINK ABOUT IT YO
	{
		if(this.current_state.equals("IDLE")) acceleration = 2 * MAX_VELOCITY;
		
		this.xVelocity -=  this.acceleration * Physics.elapsed_time;
		
		if(xVelocity < -MAX_VELOCITY) xVelocity = -MAX_VELOCITY;
		
		acceleration = .2f;
	}
	
	public float xDistance(GameObject obj)
	{
		return (this.x - obj.x);
	}
	
	public float yDistance(GameObject obj)
	{
		return (this.y - obj.y);
	}
	
	public float camMagDistance(GameObject obj)
	{
		float y = this.yDistance(obj);
		float x = this.xDistance(obj);
		
		float y2 = y * y;
		float x2 = x * x;
		
		return (float) Math.sqrt((double)(x2 + (2 * y2)));
		
	}
	
	public void attackRight(Player plr)
	{
		plr.xVelocity += 50;
	}
	
	public void attackLeft(Player plr)
	{
		plr.xVelocity += -50;
	}
	
	public void attackUp(Player plr)
	{
		plr.yVelocity += 50;
	}
	
	public void attackDown(Player plr)
	{
		plr.yVelocity += -50;
	}
	
}
