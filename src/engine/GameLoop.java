package engine;

import renderEngine.DisplayManager;
import renderEngine.Loader;

public class GameLoop
{
	public static void main(String[] args)
	{
		DisplayManager.createDisplay();
		Loader loader = new Loader();
	}
}
