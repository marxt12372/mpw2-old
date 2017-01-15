package inputListener;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;

public class MouseListener
{
	public void checkInput()
	{
	}

	public Vector2f getMouseGLPosition()
	{
		return new Vector2f((2f * Mouse.getX()) / Display.getWidth() - 1f, (2f * Mouse.getY()) / Display.getHeight() - 1f);
	}

	public Vector2f getMouseFontPosition()
	{
		return new Vector2f((1f * Mouse.getX()) / Display.getWidth(), (1f * (Display.getHeight() - Mouse.getY())) / Display.getHeight());
	}
}
