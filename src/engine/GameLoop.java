package engine;

import gui.GuiRenderer;
import gui.GuiTexture;
import inputListener.KeyboardListener;
import inputListener.MouseListener;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;
import renderEngine.DisplayManager;
import renderEngine.Loader;

import java.util.ArrayList;
import java.util.List;

public class GameLoop
{
	public int gameStatus;

	public static void main(String[] args)
	{
		DisplayManager.createDisplay();
		Loader loader = new Loader();



		GuiRenderer guiRenderer = new GuiRenderer(loader);

		List<GuiTexture> guis = new ArrayList<GuiTexture>();
		GuiTexture background = new GuiTexture(loader.loadGuiTexture("background"), new Vector2f(1, 1), new Vector2f(1, 1));
		guis.add(background);
		//guis.add(new GuiTexture(loader.loadTexture("stall"), new Vector2f(0.5f, 0.5f), new Vector2f(0.25f, 0.25f)));

		MouseListener mouse = new MouseListener();
		KeyboardListener keyboard = new KeyboardListener();

		while(!Display.isCloseRequested())
		{
			mouse.checkInput();
			keyboard.checkInput();
			guiRenderer.render(guis);
			//Sleep meetood või midagi! Muidu 100% CPU!!!
		}

		guiRenderer.cleanUp();
	}
}
