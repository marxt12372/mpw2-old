package inputListener;

import engine.*;
import org.lwjgl.input.Keyboard;

import static engine.GameLoop.menuLocation;

public class KeyboardListener
{
	private boolean _pressedEscape = false;
	private boolean _pressedEnter = false;

	public KeyboardListener()
	{
	}

	public void checkInput()
	{
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
		{
			if(_pressedEscape == false)
			{
				_pressedEscape = true;
				if(menuLocation == MENU.In_Game)
				{
					menuLocation = MENU.Main_Menu;
					MenuGenerator.removeAllCurrentText();
					MenuGenerator.generateMainMenuText();
				}
				else if(menuLocation == MENU.Main_Menu)
				{
					menuLocation = MENU.In_Game;
				}
			}
		}
		else if(!Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
		{
			if(_pressedEscape == true)
			{
				_pressedEscape = false;
			}
		}

		if(Keyboard.isKeyDown(Keyboard.KEY_RETURN))
		{
			if(_pressedEnter == false)
			{
				_pressedEnter = true;
				if(menuLocation == MENU.In_Game)
				{
				}
				else if(menuLocation == MENU.Main_Menu)
				{
				}
				else if(menuLocation == MENU.Startup)
				{
					if(MouseListener.Startup_Button_Connect)
					{
						GameLoop.menuLocation = MENU.Connect_Menu;
						MenuGenerator.generateConnectMenuText();
					}
					else if(MouseListener.Startup_Button_Settings)
					{
						GameLoop.menuLocation = MENU.Settings_Menu;
						MenuGenerator.generateSettingsMenuText();
					}
					else if(MouseListener.Startup_Button_Quit)
					{
						System.exit(0);
					}
				}
			}
		}
		else if(!Keyboard.isKeyDown(Keyboard.KEY_RETURN))
		{
			if(_pressedEnter == true)
			{
				_pressedEnter = false;
			}
		}
	}
}
