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

	public static GUIText connecttekst;
	public static GUIText settingstekst;
	public static GUIText quittekst;
	public static GUIText backtekst;

	public MenuGenerator(Loader loader)
	{
		GuiTexture bg = new GuiTexture(loader.loadGuiTexture("background"), new Vector2f(0, 0), new Vector2f(1, 1));
		background.add(bg);
	}

	public static void generateMainMenuText()
	{
		removeAllCurrentText();
	}

	public static void generateConnectMenuText()
	{
		removeAllCurrentText();

		backtekst = new GUIText("Back", 1.35f, GameLoop.font_gentium, new Vector2f(0.1f, 0.1f), 1, false);
		TextMaster.loadText(backtekst);
		currentText.add(backtekst);
	}

	public static void generateSettingsMenuText()
	{
		removeAllCurrentText();

		backtekst = new GUIText("Back", 1.35f, GameLoop.font_gentium, new Vector2f(0.1f, 0.1f), 1, false);
		TextMaster.loadText(backtekst);
		currentText.add(backtekst);
	}

	public static void generateStartupMenuText()
	{
		removeAllCurrentText();

		connecttekst = new GUIText("Connect", 1.35f, GameLoop.font_gentium, new Vector2f(0f, 0.44f), 1, true);
		TextMaster.loadText(connecttekst);
		currentText.add(connecttekst);

		settingstekst = new GUIText("Settings", 1.35f, GameLoop.font_gentium, new Vector2f(0f, 0.5f), 1, true);
		TextMaster.loadText(settingstekst);
		currentText.add(settingstekst);

		quittekst = new GUIText("Quit", 1.35f, GameLoop.font_gentium, new Vector2f(0f, 0.56f), 1, true);
		TextMaster.loadText(quittekst);
		currentText.add(quittekst);
	}

	public static void removeAllCurrentText()
	{
		for(GUIText tekst:currentText)
		{
			TextMaster.removeText(tekst);
			currentText.remove(tekst);
		}
	}

	public List<GuiTexture> getBackgroundList()
	{
		return background;
	}
}