package net.ddns.leosoft.gameobjects;

import net.ddns.leosoft.main.Game;
import net.ddns.leosoft.view.Rectangle;
import net.ddns.leosoft.view.RenderHandler;

public class Ball implements GameObject{

	private Rectangle ball; 
	private int speedX = -10;
	private int speedY = 10;
	private Player1 player1;
	private Player2 player2;
	private Rectangle camera;
	
	public Ball(int x, int y, int width, int height) {
		ball = new Rectangle(x, y, width, height);
		ball.generateGraphics(124241);
	}
	
	@Override
	public void render(RenderHandler renderHandler, int xZoom, int yZoom) {
		renderHandler.renderRectangle(ball, xZoom, yZoom);
	}

	@Override
	public void update(Game game) {
		camera = game.getHandler().getCamera();
		player1 = game.getPlayer1();
		player2 = game.getPlayer2();

		if(ball.x>=  player2.getRectangle().x) {
			if (ball.y <= player2.getRectangle().y+player2.getRectangle().height && ball.y >= player2.getRectangle().y) {
				speedX *= -1;
			}			
		}else if (ball.x<= player1.getRectangle().x + player1.getRectangle().width) {
			if (ball.y <= player1.getRectangle().y+player1.getRectangle().height && ball.y >= player1.getRectangle().y) {
				speedX *= -1;
			}
		}else if (ball.y+ball.height >= camera.height) {
			speedY *= -1;
		}else if (ball.y < camera.y) {
			speedY *= -1;
		}if(ball.x+ball.width <= camera.x) {
			ball.x = camera.width/2;
			ball.y = 1;
			player2.setScore(+1);
		}else if (ball.x >= camera.width) {
			ball.x = camera.width/2;
			ball.y = 1;
			player1.setScore(+1);
		}
		ball.x += speedX;
		ball.y += speedY;
	}

}
