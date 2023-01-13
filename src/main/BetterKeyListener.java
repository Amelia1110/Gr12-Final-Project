package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//Since this is a general class, I'm not going to have it move the player etc.
//We'll have to use a Timer to check if the keys are pressed.
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