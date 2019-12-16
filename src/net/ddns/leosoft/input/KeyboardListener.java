package net.ddns.leosoft.input;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener implements KeyListener, FocusListener{

	public boolean[] keys = new boolean[120];

	public boolean up1() {
		return keys[KeyEvent.VK_UP];
	}
	
	public boolean up2() {
		return keys[KeyEvent.VK_W];
	}
	
	public boolean down1() {
		return keys[KeyEvent.VK_DOWN];
	}
	
	public boolean down2() {
		return keys[KeyEvent.VK_S];
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		if(keyCode < keys.length)
			keys[keyCode] = true;
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		if(keyCode < keys.length)
			keys[keyCode] = false;
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		for(int i = 0; i < keys.length; i++)
			keys[i] = false;		
	}
	
}
