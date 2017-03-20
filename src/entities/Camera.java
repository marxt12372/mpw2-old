package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.DisplayManager;

import java.awt.*;

public class Camera
{
    private Vector3f position = new Vector3f(0, 3.5f, 0);
    private float distanceFromPlayer = 50;
    private float angleAroundPlayer = 0;
    private float oldangleAroundPlayer = DisplayManager.WIDTH/2;
    private float pitch = 20;
    private float oldpitch = DisplayManager.HEIGHT/2;
    private float yaw;
    private float roll;

    private Player player;

    public Camera(Player player)
    {
        this.player = player;
    }

    public void move() {
		calculateZoom();
		calculateCameraPosition();
		float horizontalDistance = calculateHorizontalDistance();
		float verticalDistance = calculateVerticalDistance();
		calculateCameraPosition(horizontalDistance, verticalDistance);
		this.yaw = 180 - (player.getRotY() + angleAroundPlayer);
    }

    private void recenterMouse()
	{
		Mouse.setCursorPosition(DisplayManager.WIDTH/2, DisplayManager.HEIGHT/2);
	}

    public Vector3f getPosition() {
        return position;
    }

    public float getPitch() {
        return pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public float getRoll() {
        return roll;
    }

    private void calculateCameraPosition(float horizDistance, float verticDistance)
	{
		float theta = player.getRotY() + angleAroundPlayer;
		float offsetX = (float) (horizDistance * Math.sin(Math.toRadians(theta)));
		float offsetZ = (float) (horizDistance * Math.cos(Math.toRadians(theta)));
		position.x = player.getPosition().x - offsetX;
		position.z = player.getPosition().z - offsetZ;
		position.y = player.getPosition().y + verticDistance;

	}

	private float calculateHorizontalDistance()
	{
		return (float) (distanceFromPlayer * Math.cos(Math.toRadians(pitch)));
	}

	private float calculateVerticalDistance()
	{
		return (float) (distanceFromPlayer * Math.sin(Math.toRadians(pitch)));
	}

    private void calculateZoom()
	{
		float zoomLevel = Mouse.getDWheel() * 0.01f;
		distanceFromPlayer -= zoomLevel;
	}

	private void calculateCameraPosition()
	{
		float angleChange = Mouse.getX();
		float pitchChange = Mouse.getY();
		recenterMouse();

		angleChange = (angleChange/(DisplayManager.WIDTH/2))-1;
		pitchChange = (pitchChange/(DisplayManager.HEIGHT/2))-1;

		pitch -= pitchChange * 40f;
		angleAroundPlayer -= angleChange * 120f;
	}
}
