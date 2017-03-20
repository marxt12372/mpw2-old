package engine;

import fontMeshCreator.FontType;
import fontMeshCreator.GUIText;
import fontRendering.TextMaster;
import gui.GuiRenderer;
import gui.GuiTexture;
import inputListener.KeyboardListener;
import inputListener.MouseListener;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.MasterRenderer;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class GameLoop
{
	public static boolean inMultiplayerSession = false;
	public static int menuLocation = MENU.Startup;
	public static String serverIP = null;
	public static int serverPort = 9667;

	public static FontType font;

	public static void main(String[] args)
	{
		DisplayManager.createDisplay();
		Loader loader = new Loader();
		GuiRenderer guiRenderer = new GuiRenderer(loader);
		TextMaster.init(loader);

		MenuGenerator menuGenerator = new MenuGenerator(loader);
		MouseListener mouseListener = new MouseListener();
		KeyboardListener keyboardListener = new KeyboardListener();

		font = new FontType(loader.loadGuiTexture("gentium"), new File("guis/gentium.fnt"));

		//mainMenuGenerator.generateMainMenu();


		/*background = new GuiTexture(loader.loadGuiTexture("background"), new Vector2f(0, 0), new Vector2f(0.5f, 0.5f));
		guis2.add(background);
		guis2.add(new GuiTexture(loader.loadTexture("stall"), new Vector2f(0.5f, 0.5f), new Vector2f(0.25f, 0.25f)));*/

		//Thread mpthread = new Thread(new MultiPlayerThread());
		//mpthread.start();

		MasterRenderer renderer = new MasterRenderer(loader);
		while(!Display.isCloseRequested())
		{
			keyboardListener.checkInput();

			if(menuLocation == MENU.Startup) {

				guiRenderer.render(menuGenerator.getBackgroundList());
				//menuGenerator.generateStartupMenuText();
				mouseListener.checkInput(Mouse.getX(), Mouse.getY());
			}
			else if(menuLocation == MENU.In_Game)
			{
				if(inMultiplayerSession == true)
				{
					//guiRenderer.render(guis2);
				}
			}
			else if(menuLocation == MENU.Main_Menu)
			{
				mouseListener.checkInput(Mouse.getX(), Mouse.getY());
			}
			else if(menuLocation == MENU.Connect_Menu)
			{
				guiRenderer.render(menuGenerator.getBackgroundList());
				//menuGenerator.generateConnectMenuText();
				mouseListener.checkInput(Mouse.getX(), Mouse.getY());
			}
			else if(menuLocation == MENU.Settings_Menu)
			{
				guiRenderer.render(menuGenerator.getBackgroundList());
				//menuGenerator.generateSettingsMenuText();
				mouseListener.checkInput(Mouse.getX(), Mouse.getY());
			}

			TextMaster.render();
			DisplayManager.updateDisplay();
		}

		guiRenderer.cleanUp();
		renderer.cleanUp();
		loader.cleanUp();
		TextMaster.cleanUp();
		guiRenderer.cleanUp();
	}
}
