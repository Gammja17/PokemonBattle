package gameplay;

import pokemon.*;
import skill.*;
import window.*;

import java.util.function.Consumer;
import java.awt.EventQueue;
import java.util.*;

public class Main {
	private static GameManager gm = new GameManager();
	private static Player p = new Player();
	public static Consumer<String> logCallback = System.out::println;
	public static Scanner sc = new Scanner(System.in);
	public static Random rand = new Random();

	

         
    public static void main(String[] args) {
        
    	EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartingWindow frame = new StartingWindow(gm, p);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
        
    }

    
    

}
