package main;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.image.BufferStrategy;

public class Gameplay extends Canvas implements Runnable {
	
	private static final long serialVersionUID = -9068089579414413327L;
	
	//Window Height & Width
	public static final int WIDTH = 1200;
	public static final int HEIGHT = WIDTH * 9/16;
	
	public boolean inPlay = false; //defaults game to be not running
	public static boolean vsCpu = true; //defaults to vs cpu
	private Thread gameThread;
	
	private Ball ball;
	private Paddles player;
	private Paddles player2;
	public static int newY;


	public Gameplay() {
		
		canvasSetup();
		
		new Window("Pong", this);
		
		initialize();
		
		this.addKeyListener(new Input(player, player2));
		this.setFocusable(true);
	}
		
	private void initialize() {
		//Initialize balls
		ball = new Ball();
		
		//Initialize paddles
		player = new Paddles(Color.green, true);
		player2 = new Paddles(Color.red, false);
		

	}

	private	void canvasSetup() {
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		this.setMinimumSize(new Dimension(WIDTH, HEIGHT));
	}

	@Override
	public void run() {
		this.requestFocus();

		// game timer
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (inPlay) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				update();
				delta--;
				draw();
				frames++;
			}

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}

		stop();
	}

	private void draw() {
		
		BufferStrategy buffer = this.getBufferStrategy();
		
		if(buffer == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = buffer.getDrawGraphics();
		//background
		background(g);
		
		//ball
		ball.draw(g);
		
		//paddles + Score
		player.draw(g);
		player2.draw(g);

		g.dispose();
		buffer.show();
	}

	private void background(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
			
		// Dotted line in the middle
		g.setColor(Color.white);
		Graphics2D g2d = (Graphics2D) g; 
											
		// How to make a dotted line:
		Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] { 10 }, 0);
		g2d.setStroke(dashed);
		g.drawLine(WIDTH / 2, 0, WIDTH / 2, HEIGHT);
		
	}

	private void update() {
		
		//update ball
		ball.update(player, player2);
		
		//update paddles
		player.update(ball);
		player2.update(ball);
		
	}

	public void start() {
		gameThread = new Thread(this);
		gameThread.start();
		inPlay = true;
		
	}
	
	public void stop() {
		try {
			gameThread.join();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public static int sign(double d) {
		if (d <= 0) {return -1;} else {return 1;}
	}
	
	

	public static int inBounds(int y, int h) {
 		if(y >= 0 && y<= 590) {
			newY = y ;
			
		} else if (y <= 0) {
			newY = 0;
			
		}else if (y >= 590) {
			newY = 590;
		}
		return newY;
	}
	
	public static void main(String[] args) {
		new Gameplay();
	}



}
