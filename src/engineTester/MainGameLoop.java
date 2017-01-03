package engineTester;


import entities.Camera;
import entities.Entity;
import entities.Light;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.*;
import terrains.Terrain;
import textures.ModelTexture;

public class MainGameLoop {
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
        Entity entity = OBJLoader.createModel("stall", 0, 0, -50, 0, 180, 0, 1, 10, 1);
        //Entity entity2 = OBJLoader.createModel("stall", 20, 0, -50, 0, 0, 0, 0.8f, 20, 0.75f);

        Light light = new Light(new Vector3f(0, 0, -20), new Vector3f(1, 1, 1));

        Terrain terrain = new Terrain(0, -1, loader, new ModelTexture(loader.loadTexture("sea")));
        Terrain terrain2 = new Terrain(1, -1, loader, new ModelTexture(loader.loadTexture("sea")));

        Camera camera = new Camera();

        //TODO: If nothing new happens, create a list with all entities and add a new one each time OBJLoader.createModel is called and loop trough them in the while loop.
        MasterRenderer renderer = new MasterRenderer();
        while(!Display.isCloseRequested())
        {
            camera.move();

            renderer.processTerrain(terrain);
            renderer.processTerrain(terrain2);
            renderer.processEntity(entity);

            renderer.render(light, camera);
            DisplayManager.updateDisplay();
        }

        renderer.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplay();
    }
}
