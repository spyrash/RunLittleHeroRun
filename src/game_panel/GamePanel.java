package game_panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{

	// 	SCREEN SETTINGS
	final int originalTileSize = 16; // 16x16 tile (retro game tile size)
	final int scale = 3;
	final int tileSize = originalTileSize * scale; // 48x48 tile ( actually tile size)
	final int maxScreenCol = 16;
	final int maxScreenRow = 12;
	final int screenWidth = tileSize * maxScreenCol; // 768 pixel
	final int screenHeight = tileSize * maxScreenRow; // 576 pixels
	
	// FPS
	int FPS = 60;
	
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	
	//set player default position
	int playerX = 100;
	int playerY = 100;
	int playerSpeed = 10;
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true); // so the panel can receive inputs
	}

	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	@Override // here we will call the loop, the core of the game
	public void run() {
		
		//double drawInterval = 1000000000/FPS; // 0.0166 seconds
		//double nextDrawTime = System.nanoTime() + drawInterval;
		/*
		 * try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime /1000000;
				if (remainingTime < 0) {
					remainingTime = 0; //just in case
				}
				Thread.sleep((long) remainingTime);
				nextDrawTime += drawInterval;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;
		while (gameThread != null) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;
			//System.out.println("the game loop is running");
		
			if (delta >= 1) {
			// we have to do it only 60 times for second, is too much now.
			// update the character position
			update();
			
			// draw the screen with updated information
			repaint();
			delta --;
			drawCount++;
			
			}
			if (timer > 1000000000) {
				System.out.println ("FPS: "+drawCount);
				drawCount = 0;
				timer = 0;
			}
			
		}
		
	}
	
	public void update() {
		if (keyH.upPressed == true) {
			playerY -= playerSpeed;
		}
		if (keyH.downPressed == true) {
			playerY += playerSpeed;
		}
		if (keyH.leftPressed == true) {
			playerX -= playerSpeed;
		}
		if (keyH.rightPressed == true) {
			playerX += playerSpeed;
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.white);
		
		g2.fillRect(playerX, playerY, tileSize, tileSize);
		g2.dispose(); // print
	}
	
}
