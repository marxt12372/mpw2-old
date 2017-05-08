package engine;

import entities.Camera;
import entities.Entity;
import entities.Light;
import entities.Player;
import fontMeshCreator.FontType;
import fontMeshCreator.GUIText;
import fontRendering.TextMaster;
import gui.GuiRenderer;
import gui.GuiTexture;
import inputListener.KeyboardListener;
import inputListener.MouseListener;
import models.RawModel;
import models.TexturedModel;
import objConverter.OBJFileLoader;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import terrains.Terrain;
import textures.ModelTexture;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class GameLoop
{
	public static boolean inMultiplayerSession = false;
	public static int menuLocation = MENU.Startup;
	public static int textOpen = MENU.None;
	public static InetAddress serverIP = null;
	public static int serverPort = 9667;
	public static DatagramSocket socket = null;
	byte[] sendBuf = new byte[256];

	public static FontType font_gentium;

	public static void main(String[] args)
	{
		DisplayManager.createDisplay();
		Loader loader = new Loader();
		GuiRenderer guiRenderer = new GuiRenderer(loader);
		TextMaster.init(loader);

		font_gentium = new FontType(loader.loadGuiTexture("gentium"), new File("guis/gentium.fnt"));

		MenuGenerator menuGenerator = new MenuGenerator(loader);
		MouseListener mouseListener = new MouseListener();
		KeyboardListener keyboardListener = new KeyboardListener();

		/**
		 *
		 * To be replaced by something that reads them from a file.
		 *
		 */

		List<Light> lights = new ArrayList<Light>();
		List<Entity> entities = new ArrayList<Entity>();
		lights.add(new Light(new Vector3f(0, 0, -20), new Vector3f(1, 1, 1)));

		entities.add(OBJFileLoader.createModel("barracksbuilding1", 1, 1, 1, 0, 0, 0, 1, 10, 1));

		for(int i = 10; i <= 500; i+=20)
		{
			lights.add(new Light(new Vector3f(i, 3, -90), new Vector3f(1, 1, 1), new Vector3f(1, 0.01f, 0.002f)));
			entities.add(OBJFileLoader.createModel("stall", i, 0, -100, 0, 180, 0, 1, 10, 1));
			entities.add(OBJFileLoader.createModel("townhall1", i, 3, -90, 0, 180, 0, 1, 10, 1));
		}

		RawModel playerModel = OBJFileLoader.loadModel("playerPed");
		TexturedModel playerTexture = new TexturedModel(playerModel, new ModelTexture(loader.loadTexture("playerPed")));
		Player player = new Player(playerTexture, new Vector3f(0, 0, -25), 0, 0, 0, 1);
		Camera camera = new Camera(player);

		/**
		 *
		 * End of replacement.
		 *
		 */

		MenuGenerator.generateStartupMenuText();
		try {
			socket = new DatagramSocket();
		} catch (SocketException e) {
			e.printStackTrace();
		}

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
				//MenuGenerator.generateStartupMenuText();
				mouseListener.checkInput();
			}
			else if(menuLocation == MENU.In_Game)
			{
				if(inMultiplayerSession == true)
				{
					List<Light> lightsToRender = new ArrayList<Light>();
					int i = 5;
					while(lightsToRender.size() < 15)
					{
						for(Light light:lights)
						{
							if(lightsToRender.size() >= 15)
							{
								break;
							}
							if((((light.getPosition().x - camera.getPosition().x) < i) && ((light.getPosition().x - camera.getPosition().x) > -i)) && (((light.getPosition().y - camera.getPosition().y) < i) && ((light.getPosition().y - camera.getPosition().y) > -i)) && (((light.getPosition().z - camera.getPosition().z) < i) && ((light.getPosition().z - camera.getPosition().z) > -i)))
							{
								if(lightsToRender.size() < 15 && !lightsToRender.contains(light))
								{
									lightsToRender.add(light);
								}
							}
						}
						i += 5;
					}


					camera.move();
					player.move();

					renderer.processEntity(player);

					for(Entity entity:entities) {
						renderer.processEntity(entity);
					}


					//renderer.processEntity(entity2);

					renderer.render(lightsToRender, camera);
					//guiRenderer.render(guis);
				}
			}
			else if(menuLocation == MENU.Main_Menu)
			{
				mouseListener.checkInput();
			}
			else if(menuLocation == MENU.Connect_Menu)
			{
				guiRenderer.render(menuGenerator.getBackgroundList());
				//MenuGenerator.generateConnectMenuText();
				mouseListener.checkInput();
			}
			else if(menuLocation == MENU.Settings_Menu)
			{
				guiRenderer.render(menuGenerator.getBackgroundList());
				//MenuGenerator.generateSettingsMenuText();
				mouseListener.checkInput();
			}
			else if(menuLocation == MENU.Connecting)
			{
				guiRenderer.render(menuGenerator.getBackgroundList());
				//MenuGenerator.generateSettingsMenuText();
				mouseListener.checkInput();
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

	public static void connectToServer(String ip, String name)
	{
		if(ip.contains(":"))
		{
			String[] ips = ip.split(":");
			try {
				serverIP = InetAddress.getByName(ips[0]);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
			serverPort = Integer.parseInt(ips[1]);
		}
		else
		{
			try {
				serverIP = InetAddress.getByName(ip);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		}
		/*String[] ips = ip.split(":");
		if(ips[1] == "") ips[1] = "9667";*/
		System.out.println("Connecting to " + serverIP + " at port " + serverPort + " with the name " + name);
		byte[] buf = new byte[256];
		buf = ("conn|" + name).getBytes();
		DatagramPacket packet = new DatagramPacket(buf, buf.length, serverIP, serverPort);
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}

		buf = ("move " + name + "  to 1.2,1.3,1.4,1.5,1.6,1.7").getBytes();
		packet = new DatagramPacket(buf, buf.length, serverIP, serverPort);
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
