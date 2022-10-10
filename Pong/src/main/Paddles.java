package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Paddles {
	
	private int x, y; // position
	private int dir; // speed and direction of paddle
	private int speed = 10; // speed of the paddle movement
	private int width = 22; // dimensions
	static int height = 85;
	private int score = 0; // score for the player
	private Color color; // color of the paddle
	private boolean green; // true if it's the green player
	public static boolean vsCpu = true;
	
	public Paddles(Color c, boolean green) {
		// initial properties
		color = c;
		this.green = green;

		if (green) // different x values if right or left paddle
			x = 0;
		else
			x = Gameplay.WIDTH - width;

		y = (Gameplay.HEIGHT / 2) - (height / 2);


	}
	
	public void addPoint() {
		score++;
	}

	public void draw(Graphics g) {
		//paddle
		g.setColor(color);
		g.fillRect(x, y, width, height);
		
		//score
		int strX;
		int padding = 25;
		String scoreTxt = Integer.toString(score);
		Font font = new Font("Roboto", Font.PLAIN, 50);
		
		if (green) {
			int strWidth = g.getFontMetrics(font).stringWidth(scoreTxt); 
																			
			strX = Gameplay.WIDTH / 2 - padding - strWidth;
		} else {
			strX = Gameplay.WIDTH / 2 + padding;
		}
		
		g.setFont(font);
		g.drawString(scoreTxt, strX, 50);

	}

	public void update(Ball b) {
		//makes sure in bounds & moves paddles
		y = Gameplay.inBounds(y + dir, height);
		System.out.print("Y:");
		System.out.println(y);
		int ballX = b.getX();
		int ballY = b.getY();
		
		if (green) {
			if (ballX <= width + x && ballY + Ball.SIZE >= y && ballY <= y + height)
				b.changeXDir();

		} else {

			if (ballX + Ball.SIZE >= x && ballY + Ball.SIZE >= y && ballY <= y + height)
				b.changeXDir();

		}
		
		
	}

	public void stop() {
		dir = 0;
		
	}

	public void flipDir(int dir2) {
		dir = speed * dir2;
		System.out.println(dir2);
		System.out.println(dir);
	}

}
