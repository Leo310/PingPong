package net.ddns.leosoft.main;

import net.ddns.leosoft.gameobjects.Ball;
import net.ddns.leosoft.gameobjects.GameObject;
import net.ddns.leosoft.gameobjects.Player1;
import net.ddns.leosoft.gameobjects.Player2;
import net.ddns.leosoft.input.KeyboardListener;
import net.ddns.leosoft.input.MouseEventListener;
import net.ddns.leosoft.view.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game implements Runnable{
	
	private Display display;
	private int width = 2000;
	private int height = 2000;

	private Thread thread;
	private boolean running = false;
	
	private BufferStrategy bufferStrategy;
	private RenderHandler renderHandler;
	
	private KeyboardListener keyboardListener;
	private MouseEventListener mouseEventListener;
	
	private int xZoom = 1;
	private int yZoom = 1;
	
	Rectangle middleLine;
	
	GameObject[] gameObjects;
	
	private Player1 player1;
	private Player2 player2;
	private Ball ball;

	
	public Game() {
		display = new Display("Ping Pong", width, height);
		renderHandler = new RenderHandler(display.getWidth(), display.getHeight());
		display.getCanvas().createBufferStrategy(3);
		
		keyboardListener = new KeyboardListener();
		display.getCanvas().addKeyListener(keyboardListener);
		display.getCanvas().addFocusListener(keyboardListener);
		
		mouseEventListener = new MouseEventListener();
		display.getCanvas().addMouseListener(mouseEventListener);
		display.getCanvas().addMouseMotionListener(mouseEventListener);
		
		middleLine = new Rectangle(renderHandler.getCamera().width/2 - 10 /2, renderHandler.getCamera().y, 10, renderHandler.getCamera().height);
		middleLine.generateGraphics(1242112);
		
		gameObjects = new GameObject[3];
		player1 = new Player1((int) (renderHandler.getCamera().width * 0.02), (int) (renderHandler.getCamera().height*0.5), (int) (renderHandler.getCamera().width*0.025), (int) (renderHandler.getCamera().height*0.125));
		player2 = new Player2((int) (renderHandler.getCamera().width*0.98 - renderHandler.getCamera().width*0.025), (int) (renderHandler.getCamera().height*0.5), (int) (renderHandler.getCamera().width*0.025), (int) (renderHandler.getCamera().height*0.125));
		ball = new Ball((int) (renderHandler.getCamera().width*0.5-renderHandler.getCamera().width*0.02),renderHandler.getCamera().y, (int) (renderHandler.getCamera().width*0.02), (int) (renderHandler.getCamera().height*0.02));
		gameObjects[0] = player1;
		gameObjects[1] = player2;
		gameObjects[2] = ball;
		
	}
	
	public KeyboardListener getKeyboardListener() {
		return keyboardListener;
	}
	
	public Player1 getPlayer1() {
		return player1;
	}
	
	public Player2 getPlayer2() {
		return player2;
	}
	
	public RenderHandler getHandler() {
		return renderHandler;
	}
	
	public void render() {
		bufferStrategy = display.getCanvas().getBufferStrategy();
		Graphics graphics = bufferStrategy.getDrawGraphics();
		display.paint(graphics);

		for (GameObject gameObject : gameObjects) {
			gameObject.render(renderHandler, xZoom, yZoom);
		}
		renderHandler.renderRectangle(middleLine, xZoom, yZoom);
		renderHandler.renderScore(player1, 500, 100, player2, 1500, 100);
		renderHandler.render(graphics);
		
		graphics.dispose();
		bufferStrategy.show();
		renderHandler.clear();
	}
	
	public void update() {
		for (GameObject gameObject : gameObjects) {
			gameObject.update(this);
		}
	}
	
	@Override
	public void run() {
		long lastTime = System.nanoTime();
		int fps = 60;
		
		while (running) {
			if (System.nanoTime() - lastTime >= 1/fps * 1000000000) {
				update();
				lastTime = System.nanoTime();
			}
			render();
		}
		stopGame();
	}
	
	public synchronized void startGame() {
		if (running) {
			System.out.println("Already running");
			return;
		}
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stopGame() {
		if (!running) {
			System.out.println("Isnt running");
			return;
		}
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
