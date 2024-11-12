package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import game_panel.GamePanel;
import game_panel.KeyHandler;

public class Player extends Entity {
	GamePanel gp;
	KeyHandler keyH;

	public final int screenX;
	public final int screenY;

	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;

		screenX = gp.screenWidth / 2;
		screenY = gp.screenHeight / 2;
		setDefaultValues();
		getPlayerImage();
	}

	public void setDefaultValues() {
		// starting position
		worldX = gp.tileSize * 21 - (gp.tileSize / 2);
		worldY = gp.tileSize * 23 - (gp.tileSize / 2);
		speed = 10;
		direction = "down";
	}

	public void getPlayerImage() {
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void update() {

		if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
			if (keyH.upPressed == true) {
				direction = "up";
				worldY -= speed;
			}
			if (keyH.downPressed == true) {
				direction = "down";
				worldY += speed;
			}
			if (keyH.leftPressed == true) {
				direction = "left";
				worldX -= speed;
			}
			if (keyH.rightPressed == true) {
				direction = "right";
				worldX += speed;
			}

			// remember method called 60 times per second
			// if you like character moving when key not pressed
			// move this part out of the "if keyH pressed"
			spriteCounter++;
			if (spriteCounter > 13) {
				if (1 == spriteNum) {
					spriteNum = 2;
				} else if (2 == spriteNum) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
		}

	}

	public void draw(Graphics2D g2) {
//		g2.setColor(Color.white);
		// rectangle
		// g2.fillRect(x, y, gp.tileSize, gp.tileSize);

		BufferedImage image = null;
		switch (direction) {
		case "up":
			image = spriteNum == 1 ? up1 : up2;
			break;
		case "down":
			image = spriteNum == 1 ? down1 : down2;
			break;
		case "left":
			image = spriteNum == 1 ? left1 : left2;
			break;
		case "right":
			image = spriteNum == 1 ? right1 : right2;
			break;
		}

		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
	}
}
