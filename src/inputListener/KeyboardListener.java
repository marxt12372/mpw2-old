package inputListener;

import engine.*;
import org.lwjgl.input.Keyboard;

import static engine.GameLoop.menuLocation;

public class KeyboardListener
{
	private boolean _pressedEscape = false;

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
	}
}
