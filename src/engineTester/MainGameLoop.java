package engineTester;


import entities.Camera;
import entities.Entity;
import entities.Light;
import entities.Player;
import gui.GuiRenderer;
import gui.GuiTexture;
import models.RawModel;
import models.TexturedModel;
import objConverter.OBJFileLoader;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.*;
import terrains.Terrain;
import textures.ModelTexture;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainGameLoop
{
	public static void main(String[] args)
	{
	    DisplayManager.createDisplay();
	    Loader loader = new Loader();

        /*RawModel model = OBJLoader.loadObjModel("stall", loader);
        TexturedModel texturedModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("images/stall")));
        ModelTexture texture = texturedModel.getTexture();
        texture.setShineDamper(10);
        texture.setReflectivity(1);
        Entity entity = new Entity(texturedModel, new Vector3f(0, 0 ,-50), 0, 0, 0, 1);*/
        //Entity entity = OBJFileLoader.createModel("townhall1", 0, 0, -50, 0, 180, 0, 1, 10, 1);
        //Entity entity2 = OBJFileLoader.createModel("stall", 1, 0, -50, 0, 180, 0, 1, 10, 1);

        //Light light = new Light(new Vector3f(0, 0, -20), new Vector3f(1, 1, 1));
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

        Terrain terrain = new Terrain(0, -1, loader, new ModelTexture(loader.loadTexture("sea")));
        Terrain terrain2 = new Terrain(1, -1, loader, new ModelTexture(loader.loadTexture("sea")));

        RawModel playerModel = OBJFileLoader.loadModel("playerPed");
		TexturedModel playerTexture = new TexturedModel(playerModel, new ModelTexture(loader.loadTexture("playerPed")));
        //Player player = new Player(playerTexture, new Vector3f(0, 0, -25), 0, 0, 0, 1);
		Player player = new Player(playerTexture, new Vector3f(0, 0, 0), 0, 0, 0, 1);
		Camera camera = new Camera(player);

		List<GuiTexture> guis = new ArrayList<GuiTexture>();
		//guis.add(new GuiTexture(loader.loadTexture("stall"), new Vector2f(0.5f, 0.5f), new Vector2f(0.25f, 0.25f)));

		GuiRenderer guiRenderer = new GuiRenderer(loader);


        //TODO: Teha Gui, mis ütleb mis staatuses mäng on(Main Menu, Pause Menu, Chatbox open jne.).
		//TODO: Teha multiplayeri mapi jms asjade download ja uuendus.
		//TODO: Teha hiire automaatne muutmine, et ei peaks hoidma all vasakut nuppu.
		//TODO: Teha object collision detection mis suppordiks ka kaamera liigutusi.
        MasterRenderer renderer = new MasterRenderer(loader);
        while(!Display.isCloseRequested())
		{
        	if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
        		break;
			}

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
			renderer.processTerrain(terrain);
			renderer.processTerrain(terrain2);

			for(Entity entity:entities) {
				renderer.processEntity(entity);
			}


			//renderer.processEntity(entity2);

			renderer.render(lightsToRender, camera);
			guiRenderer.render(guis);
			DisplayManager.updateDisplay();
    	}

    	guiRenderer.cleanUp();
        renderer.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplay();
    }
}
