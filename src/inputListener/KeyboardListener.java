package inputListener;

import engine.*;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import renderEngine.DisplayManager;

import static engine.GameLoop.menuLocation;

public class KeyboardListener
{
	private boolean _pressedBackspace = false;
	private boolean _pressedTab = false;
	private boolean _pressedEscape = false;
	private boolean _pressedEnter = false;

	public KeyboardListener()
	{
		TextFieldFiller.populateKeyList();
	}

	public void checkInput()
	{
		if(GameLoop.menuLocation == MENU.Connect_Menu)
		{
			if(GameLoop.textOpen == MENU.Connect_Name)
			{
				if (Keyboard.isKeyDown(14) && _pressedBackspace == false) //Backspace
				{
					_pressedBackspace = true;
					String txt = MenuGenerator.connectname.getTextString();
					txt = txt.substring(0, txt.length()-1);
					MenuGenerator.connectname.setTextString(txt);
				}
				else
				{
					String txt = TextFieldFiller.checkInput();
					if(!(Keyboard.isKeyDown(42) || Keyboard.isKeyDown(54)))
					{
						txt = txt.toLowerCase();
					}
					MenuGenerator.connectname.setTextString(MenuGenerator.connectname.getTextString() + txt);
				}

				if(Keyboard.isKeyDown(15) && _pressedTab == false)
				{
					_pressedTab = true;
					GameLoop.textOpen = MENU.Connect_IP;
					MenuGenerator.connectip.setTextString("");
				}

				if(!Keyboard.isKeyDown(14) && _pressedBackspace == true)
				{
					_pressedBackspace = false;
				}
				if(!Keyboard.isKeyDown(15) && _pressedTab == true)
				{
					_pressedTab = false;
				}
			}
			else if(GameLoop.textOpen == MENU.Connect_IP)
			{
				if (Keyboard.isKeyDown(14) && _pressedBackspace == false) //Backspace
				{
					_pressedBackspace = true;
					String txt = MenuGenerator.connectip.getTextString();
					txt = txt.substring(0, txt.length()-1);
					MenuGenerator.connectip.setTextString(txt);
				}
				else
				{
					String txt = TextFieldFiller.checkInput();
					if(!(Keyboard.isKeyDown(42) || Keyboard.isKeyDown(54)))
					{
						txt = txt.toLowerCase();
					}
					MenuGenerator.connectip.setTextString(MenuGenerator.connectip.getTextString() + txt);
				}

				if(Keyboard.isKeyDown(Keyboard.KEY_RETURN) && _pressedEnter == false)
				{
					_pressedEnter = true;
					MouseListener.Connect_Button_Connect = false;
					GameLoop.menuLocation = MENU.Connecting;
					MenuGenerator.generateConnectMenuConnectingText();
					GameLoop.connectToServer(MenuGenerator.connectip.getTextString(), MenuGenerator.connectname.getTextString());
				}

				if(!Keyboard.isKeyDown(Keyboard.KEY_RETURN) && _pressedEnter == true)
				{
					_pressedEnter = false;
				}

				if(!Keyboard.isKeyDown(14) && _pressedBackspace == true)
				{
					_pressedBackspace = false;
				}
			}
		}
		else if(GameLoop.menuLocation == MENU.In_Game)
		{
			if(GameLoop.inMultiplayerSession)
			{
				if(GameLoop.textOpen == MENU.Chatbox)
				{
				}
			}
			else
			{
			}
		}

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
