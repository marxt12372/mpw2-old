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
	private boolean _pressedButton = false;

	public static boolean Startup_Button_Connect;
	public static boolean Startup_Button_Settings;
	public static boolean Startup_Button_Quit;

	public static boolean Settings_Button_Back;

	public static boolean Connect_Button_Back;
	public static boolean Connect_Button_Connect;
	public static boolean Connecting_Button_Back;

	//TODO: Connect menu.
	//TODO: Settings menu positsioon
	//TODO: Testimine.

	public void checkInput()
	{
			if (GameLoop.menuLocation == MENU.Startup) {
			Vector2f mp = getMouseFontPosition();
			if (mp.getX() >= 0.46484375 && mp.getY() >= 0.4458333 && mp.getX() <= 0.53515625 && mp.getY() <= 0.4763889) //Connect Button
			{
				if (Startup_Button_Connect == false) {
					Startup_Button_Connect = true;
					MenuGenerator.connecttekst.setColour(0, 0, 1);
				}
				if ((Mouse.isButtonDown(0) || Mouse.isButtonDown(1)) && _pressedButton == false)
				{
					_pressedButton = true;
					//Vajutas nupukesele
					GameLoop.menuLocation = MENU.Connect_Menu;
					MenuGenerator.generateConnectMenuText();
				}
			}
			else if (mp.getX() >= 0.4640625 && mp.getY() >= 0.5069444 && mp.getX() <= 0.5335938 && mp.getY() <= 0.54305553) //Settings Button
			{
				if (Startup_Button_Settings == false) {
					Startup_Button_Settings = true;
					MenuGenerator.settingstekst.setColour(0, 0, 1);
				}
				if ((Mouse.isButtonDown(0) || Mouse.isButtonDown(1)) && _pressedButton == false)
				{
					_pressedButton = true;
					//Vajutas nupukesele
					Startup_Button_Settings = false;
					GameLoop.menuLocation = MENU.Settings_Menu;
					MenuGenerator.generateSettingsMenuText();
				}
			}
			else if (mp.getX() >= 0.48046875 && mp.getY() >= 0.56805557 && mp.getX() <= 0.51953125 && mp.getY() <= 0.6013889) //Quit Button
			{
				if (Startup_Button_Quit == false) {
					Startup_Button_Quit = true;
					MenuGenerator.quittekst.setColour(0, 0, 1);
				}
				if ((Mouse.isButtonDown(0) || Mouse.isButtonDown(1)) && _pressedButton == false)
				{
					_pressedButton = true;
					//Vajutas nupukesele
					System.exit(0);
				}
			}
			else
			{
				if (Startup_Button_Connect == true)
				{
					Startup_Button_Connect = false;
					MenuGenerator.connecttekst.setColour(0, 0, 0);
				}
				if (Startup_Button_Settings == true)
				{
					Startup_Button_Settings = false;
					MenuGenerator.settingstekst.setColour(0, 0, 0);
				}
				if (Startup_Button_Quit == true)
				{
					Startup_Button_Quit = false;
					MenuGenerator.quittekst.setColour(0, 0, 0);
				}
			}
			//System.out.println("Mouse X: " + mp.getX() + ", Y: " + mp.getY());
		}
		else if (GameLoop.menuLocation == MENU.Connect_Menu)
		{
			Vector2f mp = getMouseFontPosition();
			if (mp.getX() >= 0.09765625 && mp.getY() >= 0.10833334 && mp.getX() <= 0.13900625 && mp.getY() <= 0.1361111) //Back Button
			{
				if (Connect_Button_Back == false)
				{
					Connect_Button_Back = true;
					MenuGenerator.backtekst.setColour(0, 0, 1);
				}
				if ((Mouse.isButtonDown(0) || Mouse.isButtonDown(1)) && _pressedButton == false)
				{
					_pressedButton = true;
					//Vajutas nupukesele
					Connect_Button_Back = false;
					GameLoop.menuLocation = MENU.Startup;
					MenuGenerator.generateStartupMenuText();
				}
			}
			else if (mp.getX() >= 0.46484375 && mp.getY() >= 0.56805557 && mp.getX() <= 0.5335938 && mp.getY() <= 0.5986111) //Connect Button
			{
				if (Connect_Button_Connect == false)
				{
					Connect_Button_Connect = true;
					MenuGenerator.connectbuttontekst.setColour(0, 0, 1);
				}
				if ((Mouse.isButtonDown(0) || Mouse.isButtonDown(1)) && _pressedButton == false)
				{
					_pressedButton = true;
					//Vajutas nupukesele
					Connect_Button_Connect = false;
					GameLoop.menuLocation = MENU.Connecting;
					MenuGenerator.generateConnectMenuConnectingText();
				}
			}
			else if (mp.getY() >= 0.4486111 && mp.getY() <= 0.4763889) //Name Textfield
			{
				if ((Mouse.isButtonDown(0) || Mouse.isButtonDown(1)) && _pressedButton == false)
				{
					_pressedButton = true;
					GameLoop.textOpen = MENU.Connect_Name;
					MenuGenerator.connectname.setTextString("");
				}
			}
			else if (mp.getY() >= 0.5083333 && mp.getY() <= 0.5347222) //IP Textfield
			{
				if ((Mouse.isButtonDown(0) || Mouse.isButtonDown(1)) && _pressedButton == false)
				{
					_pressedButton = true;
					GameLoop.textOpen = MENU.Connect_IP;
					MenuGenerator.connectip.setTextString("");
				}
			}
			else
			{
				if (Connect_Button_Back == true)
				{
					Connect_Button_Back = false;
					MenuGenerator.backtekst.setColour(0, 0, 0);
				}
				if (Connect_Button_Connect == true)
				{
					Connect_Button_Connect = false;
					MenuGenerator.connectbuttontekst.setColour(0, 0, 0);
				}
			}
			//System.out.println("Mouse X: " + mp.getX() + ", Y: " + mp.getY());
		}
		else if (GameLoop.menuLocation == MENU.Connecting)
		{
			Vector2f mp = getMouseFontPosition();
			if (mp.getX() >= 0.09765625 && mp.getY() >= 0.10833334 && mp.getX() <= 0.13900625 && mp.getY() <= 0.1361111) //Back Button
			{
				if (Connecting_Button_Back == false)
				{
					Connecting_Button_Back = true;
					MenuGenerator.backtekst.setColour(0, 0, 1);
				}
				if ((Mouse.isButtonDown(0) || Mouse.isButtonDown(1)) && _pressedButton == false)
				{
					_pressedButton = true;
					Connecting_Button_Back = false;
					GameLoop.menuLocation = MENU.Connect_Menu;
					MenuGenerator.generateConnectMenuText();
				}
			}
			else
			{
				if (Connecting_Button_Back == true)
				{
					Connecting_Button_Back = false;
					MenuGenerator.backtekst.setColour(0, 0, 0);
				}
			}
			//System.out.println("Mouse X: " + mp.getX() + ", Y: " + mp.getY());
		}
		else if (GameLoop.menuLocation == MENU.Settings_Menu)
		{
			Vector2f mp = getMouseFontPosition();
			if (mp.getX() >= 0.09765625 && mp.getY() >= 0.10833334 && mp.getX() <= 0.13900625 && mp.getY() <= 0.1361111) //Back Button
			{
				if (Settings_Button_Back == false)
				{
					Settings_Button_Back = true;
					MenuGenerator.backtekst.setColour(0, 0, 1);
				}
				if ((Mouse.isButtonDown(0) || Mouse.isButtonDown(1)) && _pressedButton == false)
				{
					_pressedButton = true;
					//Vajutas nupukesele
					if (GameLoop.inMultiplayerSession == true)
					{
						Settings_Button_Back = false;
						GameLoop.menuLocation = MENU.Main_Menu;
						MenuGenerator.generateMainMenuText();
					}
					else
					{
						Settings_Button_Back = false;
						GameLoop.menuLocation = MENU.Startup;
						MenuGenerator.generateStartupMenuText();
					}
				}
			}
			else
			{
				if (Settings_Button_Back == true)
				{
					Settings_Button_Back = false;
					MenuGenerator.backtekst.setColour(0, 0, 0);
				}
			}
			//System.out.println("Mouse X: " + mp.getX() + ", Y: " + mp.getY());
		}

		if(_pressedButton == true && !Mouse.isButtonDown(0) && !Mouse.isButtonDown(1))
		{
			_pressedButton = false;
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
