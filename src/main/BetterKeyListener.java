package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// This class makes player movement smoother 
public class BetterKeyListener implements KeyListener {
	private boolean keysDown[] = new boolean[256];

	public boolean isKeyDown(int key) {
		return keysDown[key];
	}

	@Override
	public void keyPressed(KeyEvent e) {			
		if (e.getKeyCode() < 256) keysDown[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() < 256) keysDown[e.getKeyCode()] = false;

	}
	@Override
	public void keyTyped(KeyEvent e) {}


}