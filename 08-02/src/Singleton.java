public class Singleton
{
	private static volatile Singleton _instance;
	private static volatile int _instances = 0;
	
	private Singleton()
	{
		_instances++;
	}
	
	public static Singleton getInstance()
	{
		if(_instance == null)
		{
			synchronized(Singleton.class)
			{
				if(_instance == null)
				{
					_instance = new Singleton();
				}
			}
		}
		
		return _instance;
	}
	
	public int getInstances()
	{
		return _instances;
	}
}