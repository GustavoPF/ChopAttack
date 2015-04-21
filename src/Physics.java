
public class Physics {

	static float G = .04f;
	
	static long start_time;
	static long elapsed_time;
	static long current_time;
	
	public static void applyTo(GameObject obj)
	{
		if(obj.willFall)  
		{ 
			obj.yVelocity = obj.yVelocity - G * elapsed_time;
		}
		if(obj.yVelocity > obj.MAX_VELOCITY) obj.yVelocity = obj.MAX_VELOCITY;

		if(obj.yVelocity < -obj.MAX_VELOCITY) obj.yVelocity = -obj.MAX_VELOCITY;
	}
	
	public static void update()
	{
		elapsed_time = System.currentTimeMillis() - current_time;
		current_time += elapsed_time;
	}
}
