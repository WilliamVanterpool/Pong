package main;

import javax.swing.JFrame;

public class Window {
	
	public Window(String name, Gameplay gameplay) {
		JFrame frame = new JFrame(name);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close when x clicked
		frame.setResizable(false); // cant change window size
		frame.add(gameplay); //shows game
		frame.pack(); //ensures correct window size
		frame.setLocationRelativeTo(null); //Middle of screen
		frame.setVisible(true); //...self explanatory
		
		gameplay.start();
	
	}

}
