import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//import openGLTest.openGLTest;



public class Framework extends Applet implements Runnable
{

	Thread t = new Thread(this);
	
	static boolean test = false;
	
	//DECLARATIONS
	static int xSize;

	static int ySize;
	
	static int CAMERA_DISTANCE = 1000;
	
	static final int MIN_CAMERA_DISTANCE = 700;
	static int MAX_CAMERA_DISTANCE = 1000;
	
	static float CAMERA_X = 0;
	static float CAMERA_Y = 0;

	static Toolkit tk = Toolkit.getDefaultToolkit();
	
	static boolean S_pressed = false;
	static boolean A_pressed = false;
	static boolean D_pressed = false;
	
	static boolean SPACE_pressed = false;
	
	static boolean UP_pressed = false;
	static boolean DOWN_pressed = false;
	static boolean LEFT_pressed = false;
	static boolean RIGHT_pressed = false;
	
	//static GameObject[] objectToPaint;
	
	/*

	static ImageLayer blank = new ImageLayer( 0, 0, "blank");
	static ImageLayer tree1 = new ImageLayer( 0, 0, "tree");
	static ImageLayer tree2 = new ImageLayer( 950, 0, "tree");
	static ImageLayer floor = new ImageLayer( 0, 750, "floor");
	static ImageLayer floor2 = new ImageLayer( 0, 0, "floor");
	static Player chop = new Player( 110, 10, "ChopChop");
	
	static GameObject[] objectArray = {tree1, tree2, floor, floor2};
	
	*/
	static GameLevel Current_Level;
	
	//static Graphics offscreen_g;

	//static Image offScreen;
	
	public void init()
	{
		
		openGLTest.sup();
		
		
		
		requestFocus();
		
        xSize = ((int) tk.getScreenSize().getWidth());
        ySize = ((int) tk.getScreenSize().getHeight());
		
		//offScreen = this.createImage(1024, 1000);
		//offscreen_g = offScreen.getGraphics();
		
		t.start();
	}
	
	/*public void paint(Graphics g)
	{
		//Current_Level.Background.draw(g);
		
		//objectToPaint[0].draw(g);
		
		for(int i = 0; i < objectToPaint.length; i++)
		{
			objectToPaint[i].draw(g);
		}
		
		//Current_Level.chop.draw(g);
		
		blank.draw(g);
		tree2.draw(g);
		tree1.draw(g);
		floor.draw(g);
		floor2.draw(g);
		chop.draw(g);
		//chop.draw(g);
		
		
	}
	
	public void repaint()
	{
		Graphics g =getGraphics();
		
		update(g);
	}
	
	public void update(Graphics g)
	{
		offscreen_g.clearRect(0, 0, 700, 500);
		paint(offscreen_g);
		g.drawImage(offScreen, 0, 0, null);
	}*/
	
	public void run()
	{
		
		
		pre_game_Loop();
		
		main_game_Loop();
		
		post_game_Loop();
		
		
	}
	
	public void pre_game_Loop()
	{
		//PLAY SPLASH SCREEN ANIMATIONS, ETC
		//setup full screen, resolution, etc
		
		
		
		
		Physics.start_time = System.currentTimeMillis();
		Physics.current_time = Physics.start_time;
		
	}
	
	public void main_game_Loop()
	{
		start_up_screen_Loop();
		
		mode_select_Loop();
		
	}
	
	public void post_game_Loop()
	{
		//play exit animation, exit game
	}
	
	public void start_up_screen_Loop()
	{
		//screen where some shit happens until player presses a button
		
		
		MyImage start_up = new MyImage("startup", .8f);
		
		start_up.width = xSize;

		start_up.height = ySize;
		
		GameObject[] start_upA = {start_up};
		

		GameLevel.object = start_upA;
		
		while(!S_pressed)
		{
			//repaint();
			try{ t.sleep(16l); }catch(InterruptedException x){}
			
		}
	}
	
	public void mode_select_Loop()
	{
		//display menu showing play match, story, options, etc
		
		char_select_Loop();
		
		stage_select_Loop();
		
		match_Loop();
	}
	
	public void char_select_Loop()
	{
		//select character here
	}
	
	public void stage_select_Loop()
	{
		//select stage here
		Current_Level = new GameLevel("Level1");
		//objectToPaint = Current_Level.object;
	}
	
	
	
	public void match_Loop()
	{
		
		//test = true;
		while(true)
		{
			moveCamera();

			Physics.update();
			
			handleInput(Current_Level.chop);
			
			Physics.applyTo(Current_Level.chop);
			Physics.applyTo(Current_Level.ugly);

			Current_Level.chop.move();
			Current_Level.ugly.move();
			
			Current_Level.ugly.handleCollisionWith(Current_Level.chop);
			Current_Level.chop.handleCollisionWith(Current_Level.ugly);
			
			Current_Level.chop.handleAllCollisions(Current_Level.object);		
			Current_Level.ugly.handleAllCollisions(Current_Level.object);
			
			if(Current_Level.ugly.current_state.equals("IDLE")){ Current_Level.ugly.jump();}
			
			//System.out.println(CAMERA_DISTANCE);
		
			//AI, HANDLE COLLISIONS, PHYSICS
			
			
			/*
			 * ||||||||||||||||||||||||||||||||||||||
			 * FIGURE OUT WHAT TO DO ABOUT INPUT. HOW TO TAKE CONTEXTUAL INPUT BASED ON WHETHER OR NOT INSIDE A MATCH
			 * 
			 * FIGURE OUT BETTER WAY TO LOAD LEVELS??? HOW TO LOAD LEVELS IN THE BACKGROUND AFTER PPICKING LEVEL + DISPLAY LOAD SCREEN + TRANSITION INTO LEVEL
			 * 
			 * 
			 * 
			 * 
			 */
			
			
			//repaint();
			
			try{ t.sleep(16); }catch(InterruptedException x){}
		
		}
		//play match
//		post_match_Loop();
	}
	
	public void post_match_Loop()
	{
		//display match results
	}
	
	
	

	public void handleInput(Player player)
	{
		if(A_pressed){ player.moveLeft(); }
		else if(D_pressed){ player.moveRight(); }
		else if(player.xVelocity != 0 && !(player.current_state.equals("IDLE"))) player.xVelocity = player.xVelocity * 0.7f;
		else player.xVelocity = 0;
		
		
		if(SPACE_pressed && player.hasCollidedWithLevel && player.jumpCount == 1){ player.jump();}
		if(!SPACE_pressed) player.jumpCount = 1;
		
		if(S_pressed){ Physics.G = .24f;}
		else         { Physics.G = .04f;}
		
		if(RIGHT_pressed && (player.current_state == "JUMPING") && player.xDistance(Current_Level.ugly) <= -90 && player.xDistance(Current_Level.ugly) > -150 && player.yDistance(Current_Level.ugly) <= 100 && player.yDistance(Current_Level.ugly) > -100 )
				{
			Current_Level.chop.attackRight(Current_Level.ugly);
				}
		else if(LEFT_pressed && (player.current_state == "JUMPING") && player.xDistance(Current_Level.ugly) < 150 && player.xDistance(Current_Level.ugly) > 90 && player.yDistance(Current_Level.ugly) <= 100 && player.yDistance(Current_Level.ugly) > -100 )
				{
			Current_Level.chop.attackLeft(Current_Level.ugly);
				}
		
		if(UP_pressed && (player.current_state == "JUMPING") && player.yDistance(Current_Level.ugly) <= -90 && player.yDistance(Current_Level.ugly) > -150 && player.xDistance(Current_Level.ugly) <= 100 && player.xDistance(Current_Level.ugly) > -100 )
				{
			Current_Level.chop.attackUp(Current_Level.ugly);
				}
		else if(DOWN_pressed && (player.current_state == "JUMPING") && player.yDistance(Current_Level.ugly) < 150 && player.yDistance(Current_Level.ugly) > 90 && player.xDistance(Current_Level.ugly) <= 100 && player.xDistance(Current_Level.ugly) > -100 )
				{
			Current_Level.chop.attackDown(Current_Level.ugly);
				}
		//else player.Hattack.x = -10000;
		
	}
	
	public void moveCamera()
	{
		float camX = (Current_Level.chop.x + Current_Level.ugly.x + 100) * .5f;
		float camY = (Current_Level.chop.y + Current_Level.ugly.y - 200) * .5f;
		
		setCameraX(camX);
		setCameraY(camY);
		setMatchCameraDistance();
	}
	
	public void setCameraX(float camX)
	{
		CAMERA_X = camX;
	}
	
	public void setCameraY(float camY)
	{
		CAMERA_Y = camY + 150;
	}
	
	private void setDefaultCameraDistance()
	{
		CAMERA_DISTANCE = MIN_CAMERA_DISTANCE;
	}
	
	
	private void setMatchCameraDistance()
	{
		int distance = (int) (Current_Level.chop.camMagDistance(Current_Level.ugly) + 200);
		
		if (distance < MIN_CAMERA_DISTANCE || distance  == 0)
		{
			CAMERA_DISTANCE = MIN_CAMERA_DISTANCE;
		}
		else if(distance  < MAX_CAMERA_DISTANCE)
		{
			CAMERA_DISTANCE = distance;
		}
		else CAMERA_DISTANCE = MAX_CAMERA_DISTANCE;
	}

}
