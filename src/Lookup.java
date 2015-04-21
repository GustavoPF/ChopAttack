public class Lookup {
	final static double sin[] = generateSin();
	final static double cos[] = generateCos();
	
	public static double[] generateSin()
	{
		double[] sin = new double [360];
		
		for(int i = 0; i< 360; i++)
		{
			sin[i] = Math.sin(i * Math.PI / 180);
			
		}
		
		return sin;
	}
	
	public static double[] generateCos()
	{
		double[] cos = new double [360];
		
		for(int i = 0; i< 360; i++)
		{
			cos[i] = Math.cos(i * Math.PI / 180);
		}
		
		return cos;
	}
	
	

}