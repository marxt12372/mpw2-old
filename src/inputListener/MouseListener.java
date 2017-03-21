package inputListener;

import engine.GameLoop;
import engine.MENU;
import engine.MenuGenerator;
import fontRendering.TextMaster;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;

public class MouseListener
{
	public static boolean Startup_Button_Connect;
	public static boolean Startup_Button_Settings;
	public static boolean Startup_Button_Quit;

	public static boolean Settings_Button_Back;

	//TODO: Connect menu.
	//TODO: Settings menu positsioon
	//TODO: Testimine.

	public void checkInput()
	{
		if(GameLoop.menuLocation == MENU.Startup)
		{
			Vector2f mp = getMouseFontPosition();
			if(mp.getX() >= 0.46484375 && mp.getY() >= 0.4458333 && mp.getX() <= 0.53515625 && mp.getY() <= 0.4763889) //Connect Button
			{
				if(Startup_Button_Connect == false)
				{
					Startup_Button_Connect = true;
					MenuGenerator.connecttekst.setColour(0, 0, 1);
				}
				if(Mouse.isButtonDown(0) || Mouse.isButtonDown(1))
				{
					//Vajutas nupukesele
					System.out.println("Vajutus Connect");
					GameLoop.menuLocation = MENU.Connect_Menu;
					MenuGenerator.generateConnectMenuText();
				}
			}
			else if(mp.getX() >= 0.4640625 && mp.getY() >= 0.5069444 && mp.getX() <= 0.5335938 && mp.getY() <= 0.54305553) //Settings Button
			{
				if(Startup_Button_Settings == false)
				{
					Startup_Button_Settings = true;
					MenuGenerator.settingstekst.setColour(0, 0, 1);
				}
				if(Mouse.isButtonDown(0) || Mouse.isButtonDown(1))
				{
					//Vajutas nupukesele
					System.out.println("Vajutus Settings");
					GameLoop.menuLocation = MENU.Settings_Menu;
					MenuGenerator.generateSettingsMenuText();
				}
			}
			else if(mp.getX() >= 0.48046875 && mp.getY() >= 0.56805557 && mp.getX() <= 0.51953125 && mp.getY() <= 0.6013889) //Quit Button
				{
					if(Startup_Button_Quit == false)
					{
						Startup_Button_Quit = true;
						MenuGenerator.quittekst.setColour(0, 0, 1);
					}
					if(Mouse.isButtonDown(0) || Mouse.isButtonDown(1))
					{
						//Vajutas nupukesele
						System.out.println("Vajutus Quit");
						System.exit(0);
					}
				}
				else
				{
					if(Startup_Button_Connect == true)
					{
						Startup_Button_Connect = false;
						MenuGenerator.connecttekst.setColour(0, 0, 0);
					}
					if(Startup_Button_Settings == true)
					{
						Startup_Button_Settings = false;
						MenuGenerator.settingstekst.setColour(0, 0, 0);
					}
					if(Startup_Button_Quit == true)
					{
						Startup_Button_Quit = false;
						MenuGenerator.quittekst.setColour(0, 0, 0);
					}
				}
			//System.out.println("Mouse X: " + mp.getX() + ", Y: " + mp.getY());
		}
		else if(GameLoop.menuLocation == MENU.Connect_Menu)
		{
			Vector2f mp = getMouseFontPosition();
			if(mp.getX() >= 0.46484375 && mp.getY() >= 0.4458333 && mp.getX() <= 0.53515625 && mp.getY() <= 0.4763889) //Connect Button
			{
				if(Startup_Button_Connect == false)
				{
					Startup_Button_Connect = true;
					MenuGenerator.connecttekst.setColour(0, 0, 1);
				}
				if(Mouse.isButtonDown(0) || Mouse.isButtonDown(1))
				{
					//Vajutas nupukesele
					System.out.println("Vajutus Connect");
					GameLoop.menuLocation = MENU.Connect_Menu;
					MenuGenerator.generateConnectMenuText();
				}
			}
			else if(mp.getX() >= 0.4640625 && mp.getY() >= 0.5069444 && mp.getX() <= 0.5335938 && mp.getY() <= 0.54305553) //Settings Button
			{
				if(Startup_Button_Settings == false)
				{
					Startup_Button_Settings = true;
					MenuGenerator.settingstekst.setColour(0, 0, 1);
				}
				if(Mouse.isButtonDown(0) || Mouse.isButtonDown(1))
				{
					//Vajutas nupukesele
					System.out.println("Vajutus Settings");
					GameLoop.menuLocation = MENU.Settings_Menu;
					MenuGenerator.generateSettingsMenuText();
				}
			}
			else if(mp.getX() >= 0.48046875 && mp.getY() >= 0.56805557 && mp.getX() <= 0.51953125 && mp.getY() <= 0.6013889) //Quit Button
				{
					if(Startup_Button_Quit == false)
					{
						Startup_Button_Quit = true;
						MenuGenerator.quittekst.setColour(0, 0, 1);
					}
					if(Mouse.isButtonDown(0) || Mouse.isButtonDown(1))
					{
						//Vajutas nupukesele
						System.out.println("Vajutus Quit");
						System.exit(0);
					}
				}
				else
				{
					if(Startup_Button_Connect == true)
					{
						Startup_Button_Connect = false;
						MenuGenerator.connecttekst.setColour(0, 0, 0);
					}
					if(Startup_Button_Settings == true)
					{
						Startup_Button_Settings = false;
						MenuGenerator.settingstekst.setColour(0, 0, 0);
					}
					if(Startup_Button_Quit == true)
					{
						Startup_Button_Quit = false;
						MenuGenerator.quittekst.setColour(0, 0, 0);
					}
				}
			//System.out.println("Mouse X: " + mp.getX() + ", Y: " + mp.getY());
		}
		if(GameLoop.menuLocation == MENU.Settings_Menu)
		{
			Vector2f mp = getMouseFontPosition();
			if(mp.getX() >= 0.46484375 && mp.getY() >= 0.4458333 && mp.getX() <= 0.53515625 && mp.getY() <= 0.4763889) //Connect Button
			{
				if(Settings_Button_Back == false)
				{
					Settings_Button_Back = true;
					MenuGenerator.backtekst.setColour(0, 0, 1);
				}
				if(Mouse.isButtonDown(0) || Mouse.isButtonDown(1))
				{
					//Vajutas nupukesele
					System.out.println("Vajutus Back");
					if(GameLoop.inMultiplayerSession == true)
					{
						GameLoop.menuLocation = MENU.Main_Menu;
						MenuGenerator.generateMainMenuText();
					}
					else
					{
						GameLoop.menuLocation = MENU.Startup;
						MenuGenerator.generateStartupMenuText();
					}
				}
			}
			else
			{
				if(Settings_Button_Back == true)
				{
					Settings_Button_Back = false;
					MenuGenerator.connecttekst.setColour(0, 0, 0);
				}
			}
			//System.out.println("Mouse X: " + mp.getX() + ", Y: " + mp.getY());
		}
	}

	public Vector2f getMouseGLPosition()
	{
		return new Vector2f((2f * Mouse.getX()) / Display.getWidth() - 1f, (2f * Mouse.getY()) / Display.getHeight() - 1f);
	}

	public Vector2f getMouseFontPosition()
	{
		return new Vector2f((1f * Mouse.getX()) / Display.getWidth(), (1f * (Display.getHeight() - Mouse.getY())) / Display.getHeight());
	}
}
