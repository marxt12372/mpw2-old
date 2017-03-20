package engine;

import fontMeshCreator.GUIText;
import fontRendering.TextMaster;
import gui.GuiTexture;
import org.lwjgl.util.vector.Vector2f;
import renderEngine.Loader;

import java.util.ArrayList;
import java.util.List;

public class MenuGenerator
{
	private List<GuiTexture> background = new ArrayList<GuiTexture>();
	private static List<GUIText> currentText = new ArrayList<GUIText>();

	public MenuGenerator(Loader loader)
	{
		GuiTexture bg = new GuiTexture(loader.loadGuiTexture("background"), new Vector2f(0, 0), new Vector2f(1, 1));
		background.add(bg);
	}

	public static void generateMainMenuText()
	{
	}

	public static void generateConnectMenuText()
	{
	}

	public static void generateSettingsMenuText()
	{
	}

	public static void generateStartupMenuText()
	{
	}

	public static void removeAllCurrentText()
	{
		for(GUIText tekst:currentText)
		{
			TextMaster.removeText(tekst);
		}
	}

	public List<GuiTexture> getBackgroundList()
	{
		return background;
	}
}