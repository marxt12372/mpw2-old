package engine;

import gui.GuiRenderer;
import gui.GuiTexture;
import inputListener.KeyboardListener;
import inputListener.MouseListener;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;
import renderEngine.DisplayManager;
import renderEngine.Loader;

import java.util.ArrayList;
import java.util.List;

public class GameLoop
{
	public static boolean inMultiplayerSession = false;
	public static String serverIP = null;
	public static int serverPort = 9667;
	public static List<GuiTexture> guis = new ArrayList<GuiTexture>();

	public static void main(String[] args)
	{
		DisplayManager.createDisplay();
		Loader loader = new Loader();
		GuiRenderer guiRenderer = new GuiRenderer(loader);

		GuiTexture background = new GuiTexture(loader.loadGuiTexture("background"), new Vector2f(0, 0), new Vector2f(1, 1));
		guis.add(background);
		//guis.add(new GuiTexture(loader.loadTexture("stall"), new Vector2f(0.5f, 0.5f), new Vector2f(0.25f, 0.25f)));

		//Thread thread = new Thread(new MultiPlayerThread());
		//thread.start();

		while(!Display.isCloseRequested())
		{
			MouseListener mouse = new MouseListener();
			KeyboardListener keyboard = new KeyboardListener();

			if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
				break;
			}

			if(!inMultiplayerSession) //In main manu
			{
				guiRenderer.render(guis);
			}
			else //In a game.
			{
				guiRenderer.render(guis);
			}
			DisplayManager.updateDisplay();
		}

		guiRenderer.cleanUp();
	}
}
