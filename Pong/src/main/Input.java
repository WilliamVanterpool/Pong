package main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Input extends KeyAdapter {

	private Paddles player; // player
	private boolean playerUp = false; // playerUp = left up (up1 in video)
	private boolean playerDown = false;

	private Paddles player2; // right paddle
	private boolean player2Up = false;
	private boolean player2Down = false;


	
	public Input(Paddles p1, Paddles p2) {
		player = p1;
		player2 = p2;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_B) {
			System.out.println("Bot Toggled");
			Gameplay.vsCpu = !(Gameplay.vsCpu);
		}

		if (key == KeyEvent.VK_UP && !Gameplay.vsCpu) {
			player2.flipDir(-1);
			player2Up = true;
		}
		if (key == KeyEvent.VK_DOWN && !Gameplay.vsCpu) {
			player2.flipDir(1);
			player2Down = true;
		}
		
			
		if (key == KeyEvent.VK_W) {
			player.flipDir(-1);
			playerUp = true;
		}
		if (key == KeyEvent.VK_S) {
			player.flipDir(1);
			playerDown = true;
		}
		
		
		// exit
		if (key == KeyEvent.VK_ESCAPE) {
			System.exit(1);
		}
	}

	

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_UP) {
			player2Up = false;
		}
		if (key == KeyEvent.VK_DOWN) {
			player2Down = false;
		}
		if (key == KeyEvent.VK_W) {
			playerUp = false;
		}
		if (key == KeyEvent.VK_S) {
			playerDown = false;
		}


		if (!playerUp && !playerDown)
			player.stop();
		if (!player2Up && !player2Down)
			player2.stop();
	}

}