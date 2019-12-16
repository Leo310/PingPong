package net.ddns.leosoft.view;

import net.ddns.leosoft.gameobjects.Player1;
import net.ddns.leosoft.gameobjects.Player2;
import net.ddns.leosoft.view.*;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;


public class RenderHandler {
	private BufferedImage view;
	private int[] pixels;
	private Rectangle camera;
	
	public RenderHandler(int width, int height) {
		view = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt)view.getRaster().getDataBuffer()).getData();
		camera = new Rectangle(0, 0, width, height);
	}
	
	public void render(Graphics graphics) {
		graphics.drawImage(view, 0, 0, view.getWidth(), view.getHeight(), null);
	}
	
	public void renderScore(Player1 player1, int x1, int y1, Player2 player2, int x2, int y2) {
		view.getGraphics().setFont(new Font("TimesRoman", Font.BOLD, 40)); 
		view.getGraphics().drawString(Integer.toString(player1.getScore()), x1, y1);
		view.getGraphics().drawString(Integer.toString(player2.getScore()), x2, y2);
	}
	
	public void renderRectangle(Rectangle rectangle, int xZoom, int yZoom) {
		if (rectangle.getPixels() != null) 
			renderArray(rectangle.getPixels(), rectangle.x, rectangle.y,rectangle.width , rectangle.height, xZoom, yZoom);
		else
			System.out.println("No generated Rectangle graphics ");
	}
	
	public void renderArray(int[] pixels, int xPosition, int yPosition, int width, int height, int xZoom, int yZoom) {
		for (int y = 0; y < height; y++) 
			for (int x = 0; x < width; x++) 
				for (int yPositionZoom = 0; yPositionZoom < yZoom; yPositionZoom++) 
					for (int xPositionZoom = 0; xPositionZoom < xZoom; xPositionZoom++) 
						setPixel(pixels[x + y*width],xPosition+xPositionZoom+x*xZoom, yPosition+yPositionZoom+y*yZoom);
				
	}
	
	public void setPixel(int pixel, int x, int y) {
		if (x>=camera.x && y>=camera.y && x <= camera.width+camera.x && y <=camera.height+camera.y) {
			int pixelIndex = (x+camera.x) + (y+camera.x) * view.getWidth(); 
			if(pixels.length>pixelIndex)
				pixels[pixelIndex] = pixel;
		}	
	}
	
	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}
	
	public Rectangle getCamera() {
		return camera;
	}
}
