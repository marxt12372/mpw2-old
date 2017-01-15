package engine;

import fontMeshCreator.GUIText;
import gui.GuiTexture;
import inputListener.MouseListener;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector2f;
import renderEngine.Loader;

import java.util.ArrayList;
import java.util.List;

import static engine.GameLoop.*;

public class MainMenuGenerator
{
	private static MouseListener mouseListener = new MouseListener();
	private static Vector2f mousePos;

	public static void updateMainMenu()
	{
		mousePos = mouseListener.getMouseFontPosition();
		if(menuLocation == 0) //Main Menu.
		{
			if((mousePos.getX() >= 0.4398f && mousePos.getY() >= 0.404f) && (mousePos.getX() <= 0.560f && mousePos.getY() <= 0.4305f)) //Connect button
			{
				connectText.setColour(0, 0, 1);
				if(Mouse.isButtonDown(0))
				{
					menuLocation = 1;

					connectText.remove();
					settingsText.remove();
					quitText.remove();

					generateConnectMenu();
				}
			}
			else
			{
				connectText.setColour(0, 0, 0);
			}

			if((mousePos.getX() >= 0.4734f && mousePos.getY() >= 0.452f) && (mousePos.getX() <= 0.5265f && mousePos.getY() <= 0.480f)) //Settings button
			{
				settingsText.setColour(0, 0, 1);
				if(Mouse.isButtonDown(0))
				{
					menuLocation = 2;

					connectText.remove();
					settingsText.remove();
					quitText.remove();

					generateSettingsMenu();
				}
			}
			else
			{
				settingsText.setColour(0, 0, 0);
			}

			if((mousePos.getX() >= 0.4835f && mousePos.getY() >= 0.50f) && (mousePos.getX() <= 0.5140f && mousePos.getY() <= 0.5319f)) //Quit button
			{
				quitText.setColour(0, 0, 1);
				if(Mouse.isButtonDown(0))
				{
					System.exit(1);
				}
			}
			else
			{
				quitText.setColour(0, 0, 0);
			}
		}
		else if(menuLocation == 1) //Connect to server
		{
			if((mousePos.getX() >= 0.4726f && mousePos.getY() >= 0.7027f) && (mousePos.getX() <= 0.5257f && mousePos.getY() <= 0.7291f)) //Connect button
			{
				joinText.setColour(0, 0, 1);
				if(Mouse.isButtonDown(0))
				{
				}
			}
			else
			{
				joinText.setColour(0, 0, 0);
			}

			if((mousePos.getX() >= 0.4835f && mousePos.getY() >= 0.7555f) && (mousePos.getX() <= 0.5164f && mousePos.getY() <= 0.7777f)) //Back button
			{
				backText.setColour(0, 0, 1);
				if(Mouse.isButtonDown(0))
				{
					menuLocation = 0;

					joinText.remove();
					backText.remove();

					generateMainMenu();
				}
			}
			else
			{
				backText.setColour(0, 0, 0);
			}

			if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
			{
				menuLocation = 0;

				joinText.remove();
				backText.remove();

				generateMainMenu();
			}
		}
		else if(menuLocation == 2) //Settings menu
		{
			if((mousePos.getX() >= 0.4835f && mousePos.getY() >= 0.7555f) && (mousePos.getX() <= 0.5164f && mousePos.getY() <= 0.7777f)) //Back button
			{
				backText.setColour(0, 0, 1);
				if(Mouse.isButtonDown(0))
				{
					menuLocation = 0;

					backText.remove();

					generateMainMenu();
				}
			}
			else
			{
				backText.setColour(0, 0, 0);
			}

			if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
			{
				menuLocation = 0;

				joinText.remove();
				backText.remove();

				generateMainMenu();
			}
		}
	}

	public static void generateMainMenu()
	{
		connectText = new GUIText("Connect to a server", 1, font, new Vector2f(0f, 0.4f), 1f, true);
		connectText.setColour(0, 0, 0);

		settingsText = new GUIText("Settings", 1, font, new Vector2f(0f, 0.45f), 1f, true);
		settingsText.setColour(0, 0, 0);

		quitText = new GUIText("Quit", 1, font, new Vector2f(0f, 0.5f), 1f, true);
		quitText.setColour(0, 0, 0);
	}

	public static void generateConnectMenu()
	{
		joinText = new GUIText("Connect", 1, font, new Vector2f(0f, 0.7f), 1f, true);
		joinText.setColour(0, 0, 0);

		backText = new GUIText("Back", 1, font, new Vector2f(0f, 0.75f), 1f, true);
		backText.setColour(0, 0, 0);
	}

	public static void generateSettingsMenu()
	{
		backText = new GUIText("Back", 1, font, new Vector2f(0f, 0.75f), 1f, true);
		backText.setColour(0, 0, 0);
	}
}
