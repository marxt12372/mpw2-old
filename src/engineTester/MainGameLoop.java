package engineTester;


import entities.Camera;
import entities.Entity;
import entities.Light;
import entities.Player;
import models.RawModel;
import models.TexturedModel;
import objConverter.OBJFileLoader;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.*;
import terrains.Terrain;
import textures.ModelTexture;

import java.awt.*;

public class MainGameLoop {
    public static void main(String[] args) {
        DisplayManager.createDisplay();
        Loader loader = new Loader();

        /*RawModel model = OBJLoader.loadObjModel("stall", loader);
        TexturedModel texturedModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("images/stall")));
        ModelTexture texture = texturedModel.getTexture();
        texture.setShineDamper(10);
        texture.setReflectivity(1);
        Entity entity = new Entity(texturedModel, new Vector3f(0, 0 ,-50), 0, 0, 0, 1);*/
        Entity entity = OBJFileLoader.createModel("stall", 0, 0, -50, 0, 180, 0, 1, 10, 1);
        //Entity entity2 = OBJFileLoader.createModel("stall", 1, 0, -50, 0, 180, 0, 1, 10, 1);

        Light light = new Light(new Vector3f(0, 0, -20), new Vector3f(1, 1, 1));

        Terrain terrain = new Terrain(0, -1, loader, new ModelTexture(loader.loadTexture("sea")));
        Terrain terrain2 = new Terrain(1, -1, loader, new ModelTexture(loader.loadTexture("sea")));

        RawModel playerModel = OBJFileLoader.loadModel("playerPed");
		TexturedModel playerTexture = new TexturedModel(playerModel, new ModelTexture(loader.loadTexture("playerPed")));
        Player player = new Player(playerTexture, new Vector3f(0, 0, -25), 0, 0, 0, 1);
		Camera camera = new Camera(player);

        //TODO: If nothing new happens, create a list with all entities and add a new one each time OBJLoader.createModel is called and loop trough them in the while loop.
        MasterRenderer renderer = new MasterRenderer();
        while (!Display.isCloseRequested()) {
        	if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
        		break;
			}

            camera.move();
            player.move();

            renderer.processEntity(player);
            renderer.processTerrain(terrain);
            renderer.processTerrain(terrain2);
			renderer.processEntity(entity);
			//renderer.processEntity(entity2);

            renderer.render(light, camera);
            DisplayManager.updateDisplay();
        }

        renderer.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplay();
    }
}
