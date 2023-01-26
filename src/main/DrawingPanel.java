package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;

public class DrawingPanel extends JPanel {
	// Fonts
	// Game font
	static Font pixeloidSans;
	
	Font dialogFont, promptFont, lockFont;

	Graphics2D g2;

	// Target map
	final Map targetMap;

	// inaccessible
	private DrawingPanel() {
		// cannot create drawing panel without a map
		targetMap = null;
	}

	// Panel constructor
	DrawingPanel(Map map) {
		targetMap = map;
		// Setup JPanel
		this.setPreferredSize(new Dimension(EscapeRoomieGame.PANW, EscapeRoomieGame.PANH));
		this.setBackground(Color.BLACK);
		this.addKeyListener(EscapeRoomieGame.bKeyL);
		this.setFocusable(true);

		// Create Font for dialogs
		try {
			pixeloidSans = Font.createFont(0, new File("gameFont.ttf"));
			dialogFont = pixeloidSans.deriveFont(20f);
			promptFont = pixeloidSans.deriveFont(10f);
			lockFont = pixeloidSans.deriveFont(100f);
		} catch (FontFormatException e) {
			System.out.println("Warning: font failed to load");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Warning: font failed to load");
			e.printStackTrace();
		}
	}

	// Draw components
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g); // draw background
		// Setup graphics component
		g2 = (Graphics2D) g;
		// antialiasing:
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// draw map
		loadMap();

		// draw shop icon in shop: (large shop image on floor)
		if (EscapeRoomieGame.activePanel.equals(EscapeRoomieGame.shopPanel)) {
			g2.drawImage(Texture.shopIcon.img, 64 * 7, 64 * 4, null);
		}

		// draw interactables
		loadInteractables();

		// draw player
		loadPlayer();

		g2.setColor(Color.WHITE);

		// if an interaction is possible
		if (EscapeRoomieGame.player.canInteractWith(targetMap.mapTopLayer) != 0) {
			g2.setFont(promptFont);
			g2.drawString("E to Interact", EscapeRoomieGame.player.x, EscapeRoomieGame.player.y - 10);
		}

		// draw vision restrictions
		Area outer = new Area(new Rectangle(0, 0, getWidth(), getHeight()));
		int radius = 80;
		int x = EscapeRoomieGame.player.x + EscapeRoomieGame.player.width / 2 - radius;
		int y = EscapeRoomieGame.player.y + EscapeRoomieGame.player.height / 2 - radius;
		// Rectangle inner = new Rectangle(x, y, 200, 200);
		Ellipse2D.Double inner = new Ellipse2D.Double(x, y, 2 * radius, 2 * radius);
		outer.subtract(new Area(inner));

		g2.setColor(Color.BLACK);
		g2.fill(outer);

		// draw dialog if there dialog is set to on
		if (Dialog.showDialog) {
			g2.setColor(Color.WHITE);
			g2.setFont(dialogFont);
			g2.drawImage(Dialog.img, EscapeRoomieGame.currentScene.x, EscapeRoomieGame.currentScene.y, null);
			drawDialog();
		}

		if (Door.typing) {
			g2.setColor(Color.WHITE);
			g2.setFont(lockFont);
			g2.drawString(EscapeRoomieGame.currentDoor.userInput, 100, EscapeRoomieGame.PANH / 2);
		}
	}

	// Load the map
	void loadMap() {
		int xPos;
		int yPos;

		Texture texture;

		// Iterate through and draw each element in the ground layer of the map
		for (int y = 0; y < targetMap.mapGround.length; y++) {
			for (int x = 0; x < targetMap.mapGround[0].length; x++) {
				// Declare the x and y coordinate of where the image will be drawn
				xPos = EscapeRoomieGame.PANW / targetMap.mapGround[0].length * x;
				yPos = EscapeRoomieGame.PANH / targetMap.mapGround.length * y;

				if (targetMap.mapGround[y][x] != 0) {
					// Determine which texture is being referenced
					texture = EscapeRoomieGame.textures.get(targetMap.mapGround[y][x]);

					// Draw a rotated image
					if (texture.rotation != 0.0) {
						g2.rotate(texture.rotation, xPos + texture.height / 2, yPos + texture.height / 2);
						g2.drawImage(texture.img, xPos, yPos, null);
						g2.rotate(-texture.rotation, xPos + texture.width / 2, yPos + texture.height / 2);

					}
					// Draw a normal image
					else
						g2.drawImage(texture.img, xPos, yPos, null);
				}
			}
		}
	}

	void loadInteractables() {
		int xPos;
		int yPos;

		Interactable interactable;

		// Iterate through and draw each element in the second layer of the map
		for (int y = 0; y < targetMap.mapTopLayer.length; y++) {
			for (int x = 0; x < targetMap.mapTopLayer[0].length; x++) {
				// Declare the x and y coordinate of where the image will be drawn
				xPos = EscapeRoomieGame.PANW / targetMap.mapTopLayer[0].length * x;
				yPos = EscapeRoomieGame.PANH / targetMap.mapTopLayer.length * y;

				if (targetMap.mapTopLayer[y][x] != 0) {
					// Determine which interactable is being referenced
					interactable = EscapeRoomieGame.interactables.get(targetMap.mapTopLayer[y][x]);

					// Draw a rotated image
					if (interactable.rotation != 0.0) {
						g2.rotate(interactable.rotation, xPos + interactable.width / 2, yPos + interactable.height / 2);
						g2.drawImage(interactable.img, xPos, yPos, null);
						g2.rotate(-interactable.rotation, xPos + interactable.width / 2,
								yPos + interactable.height / 2);

					}

					// Draw a normal image
					else
						g2.drawImage(interactable.img, xPos, yPos, null);
				}
			}
		}
	}

	// Draw player
	void loadPlayer() {
		g2.setColor(Color.RED); // color of hitbox
		g2.drawImage(EscapeRoomieGame.player.image, EscapeRoomieGame.player.x, EscapeRoomieGame.player.y, null);
		if (EscapeRoomieGame.player.health > 50)
			g2.setColor(Color.GREEN);
		g2.fillRect(EscapeRoomieGame.player.x, EscapeRoomieGame.player.y - 5, EscapeRoomieGame.player.health * EscapeRoomieGame.player.width / 100, 3);

		if (EscapeRoomieGame.player.showHitBox) {
			g2.drawRect(EscapeRoomieGame.player.x, EscapeRoomieGame.player.y, EscapeRoomieGame.player.width, EscapeRoomieGame.player.height);
		}
	}

	// Draw dialog
	void drawDialog() {
		String current = EscapeRoomieGame.currentScene.sceneDialog[EscapeRoomieGame.currentScene.currentText];
		String[] dialog = current.split("#", 0);

		for (int i = 0; i < dialog.length; i++) {
			g2.drawString(dialog[i], EscapeRoomieGame.currentScene.x + 45, EscapeRoomieGame.currentScene.y + 60 + (i * 30));
		}
	}
}
