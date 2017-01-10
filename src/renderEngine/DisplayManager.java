package renderEngine;


import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.*;

public class DisplayManager
{
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    public static final int FPS_CAP = 400;

    private static long lastFrameTime;
    private static float delta;

    public static void createDisplay()
    {
        ContextAttribs attribs = new ContextAttribs(3,2).withForwardCompatible(true).withProfileCore(true);

        try {
            Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
            Display.setResizable(true);
            Display.create(new PixelFormat(), attribs);
            Display.setTitle("Multiplayer World 2");
        }
        catch(LWJGLException e)
        {
            e.printStackTrace();
        }

        GL11.glViewport(0,0, Display.getWidth(), Display.getHeight());
        lastFrameTime = getCurrentTime();
    }

    public static void updateDisplay()
    {
		GL11.glViewport(0,0, Display.getWidth(), Display.getHeight());
        Display.sync(FPS_CAP);
        Display.update();
        long currentFrameTime = getCurrentTime();
        delta = currentFrameTime - lastFrameTime;
        lastFrameTime = currentFrameTime;
    }

    public static float getFrameTimeMillisec()
    {
        return delta;
    }

    public static void closeDisplay()
    {
        Display.destroy();
    }

    private static long getCurrentTime()
    {
        return Sys.getTime()*1000/Sys.getTimerResolution();
    }
}
