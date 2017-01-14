package engine;

import fontMeshCreator.FontType;
import fontMeshCreator.GUIText;
import fontRendering.TextMaster;
import gui.GuiRenderer;
import gui.GuiTexture;
import inputListener.KeyboardListener;
import inputListener.MouseListener;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import renderEngine.DisplayManager;
import renderEngine.Loader;

import java.awt.*;
import java.io.File;
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
		TextMaster.init(loader);

		FontType font = new FontType(loader.loadGuiTexture("gentium"), new File("guis/gentium.fnt"));
		GUIText text = new GUIText("Hello", 1, font, new Vector2f(0.0f, 0.0f), 1f, true);
		text.setColour(0, 0, 1);

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

			TextMaster.render();
			DisplayManager.updateDisplay();
		}

		TextMaster.cleanUp();
		guiRenderer.cleanUp();
	}
}
