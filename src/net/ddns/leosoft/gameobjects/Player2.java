package net.ddns.leosoft.gameobjects;

import net.ddns.leosoft.main.Game;
import net.ddns.leosoft.view.Rectangle;
import net.ddns.leosoft.view.RenderHandler;

public class Player2 implements GameObject{
	private Rectangle rectangle;
	private RenderHandler renderHandler;
	private int speed = 5;
	private int score;
	
	public Player2(int x, int y, int width, int height) {
		rectangle = new Rectangle(x,y,width,height);
		rectangle.generateGraphics(12142113);
	}

	@Override
	public void render(RenderHandler renderHandler, int xZoom, int yZoom) {
		renderHandler.renderRectangle(rectangle, xZoom, yZoom);
		this.renderHandler = renderHandler;
	}

	@Override
	public void update(Game game) {
		if(game.getKeyboardListener().down1() && rectangle.y + rectangle.height <= renderHandler.getCamera().height)
			rectangle.y += speed;
		if(game.getKeyboardListener().up1()&& rectangle.y >= renderHandler.getCamera().y)
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