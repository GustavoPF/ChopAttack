import java.awt.*;


public class GameLevel {
	
	String level_name;
	
	int start_x;
	int start_y;
	
	
	
	static GameObject[] object = {};
	
	static Player[] player = {};
	
	ImageLayer Background;
	
	Player chop;
	Player ugly;
	
	public GameLevel(String name)
	{
		level_name = name;
		
		if(name.equals("Level1")){ loadLevel1(); }
	}
	
	private void loadLevel1()
	{
		this.start_x = -100;
		this. start_y = 30;
		
		this.chop = new Player(start_x, start_y, "ChopChop", .2f);
		
		this.ugly = new Player(100, start_y, "ChopChop", .2f);
		
		chop.width = 50;
		chop.height = 50;
		
		ugly.width = 50;
		ugly.height = 50;
		
		
		
		this.Background = new ImageLayer( -700, -400, "blank", .7f);
		
		Background.width = Framework.xSize;
		Background.height = Framework.ySize;
		
		ImageLayer tree1 = new ImageLayer( -700, -400, "tree", .1f);
		ImageLayer tree2 = new ImageLayer( 600, -400, "tree", .1f);
		
		ImageLayer floor = new ImageLayer( -600, -400, "floor", .4f);
		ImageLayer floor2 = new ImageLayer( -600, 350, "floor", .4f);
		ImageLayer floor3 = new ImageLayer( 400, -400, "floor", .4f);
		ImageLayer floor4 = new ImageLayer( 400, 350, "floor", .4f); 
		
		Player[] player = {
				chop,
				ugly,
		};
		
		this.player = player;
		
		
		GameObject[] obj = {
							Background,
							tree1,
							tree2,
							floor,
							floor2,
							floor3,
							floor4
						   };
		
		this.object = obj;
				
	}

}
