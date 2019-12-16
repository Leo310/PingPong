package net.ddns.leosoft.view;

import java.awt.Canvas;
import java.awt.Color;

import javax.swing.JFrame;

public class Display extends JFrame{
	private String title;
	private int width,height;
	private Canvas canvas = new Canvas();
	
	public Display(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
		
		setSize(width, height);
		setTitle(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		add(canvas);
		setVisible(true);
		
	}
	
	public Canvas getCanvas() {
		return canvas;
	}
}
