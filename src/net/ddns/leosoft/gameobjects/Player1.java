package net.ddns.leosoft.gameobjects;

import net.ddns.leosoft.main.Game;
import net.ddns.leosoft.view.Rectangle;
import net.ddns.leosoft.view.RenderHandler;

public class Player1 implements GameObject{
	private Rectangle rectangle;
	private RenderHandler renderHandler;
	private int speed = 5;
	private int score;
	
	public Player1(int x, int y, int width, int height) {
		rectangle = new Rectangle(x,y,width,height);
		rectangle.generateGraphics(1214211);
	}

	@Override
	public void render(RenderHandler renderHandler, int xZoom, int yZoom) {
		this.renderHandler = renderHandler;
		renderHandler.renderRectangle(rectangle, xZoom, yZoom);
		
	}

	@Override
	public void update(Game game) {
		if(game.getKeyboardListener().down2() && rectangle.y + rectangle.height <= renderHandler.getCamera().height)
			rectangle.y += speed;
		if(game.getKeyboardListener().up2()&& rectangle.y >= renderHandler.getCamera().y)
			rectangle.y -= speed;
	}
	
	public Rectangle getRectangle() {
		return rectangle;
	}
	
	public void setScore(int point) {
		score += point;
	}
	
	public int getScore() {
		return score;
	}
}