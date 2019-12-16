package net.ddns.leosoft.view;

public class Rectangle {
	public int x,y,width,height;
	private int[] pixels;
	
	public Rectangle(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public Rectangle() {
		this(0, 0, 0, 0);
	}
	
	public void generateGraphics(int color) {
		pixels = new int[width*height];
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = color;
		}
	}
	
	public int[] getPixels() {
		return pixels;
	}
	
}
