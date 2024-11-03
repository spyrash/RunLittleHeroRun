package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import game_panel.GamePanel;
import game_panel.KeyHandler;

public class Player extends Entity {
	GamePanel gp;
	KeyHandler keyH;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		x = 100;
		y = 100;
		speed = 4;
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

		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		
		if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
			if (keyH.upPressed == true) {
				direction = "up";
				y -= speed;
			}
			if (keyH.downPressed == true) {
				direction = "down";
				y += speed;
			}
			if (keyH.leftPressed == true) {
				direction = "left";
				x -= speed;
			}
			if (keyH.rightPressed == true) {
				direction = "right";
				x += speed;
			}
			
			// remember method called 60 times per second
			// if you like character moving when key not pressed 
			// move this part out of the "if keyH pressed"
			spriteCounter++;
			if (spriteCounter > 13) {
				if (1 == spriteNum) {
					spriteNum = 2;
				} else if(2 == spriteNum) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
		}
	
	}
	
	public void draw(Graphics2D g2) {
//		g2.setColor(Color.white);
		// rectangle
	//	g2.fillRect(x, y, gp.tileSize, gp.tileSize);
		
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
		
		g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
	}
}
