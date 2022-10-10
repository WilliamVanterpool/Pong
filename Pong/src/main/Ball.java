package main;

import java.awt.Color;
import java.awt.Graphics;

public class Ball {
	
	public static final int SIZE = 16;
	
	private int x, y;
	private int xDir, yDir;
	private int speed = 5;
	
	public Ball() {
		reset();
		
	}

	public void reset() {
		//starting position
		x = Gameplay.WIDTH / 2 - SIZE / 2;
		y = Gameplay.HEIGHT / 2 - SIZE / 2;
		
		//starting direction
		xDir = Gameplay.sign(Math.random() * 2.0 - 1);
		yDir = Gameplay.sign(Math.random() * 2.0 - 1);
	}
	
	public void changeXDir() {
		xDir *= -1;
	}


	public void changeYDir() {
		yDir *= -1;
	}

	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, SIZE, SIZE);
		
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void update(Paddles player, Paddles player2) {
		//movement
		x += xDir * speed;
		y += yDir * speed;
		
		//collisions
		if (y + SIZE >= Gameplay.HEIGHT || y <= 0)
			changeYDir();
		
		//goals
		if (x + SIZE >= Gameplay.WIDTH) {
			player.addPoint();
			reset();
		}
		
		if (x <= 0) {
			player2.addPoint();
			reset();
		}
	}
}
