package assasin1;

import javax.swing.JFrame;

public class Main {
	public static void main(String args[]){
		System.out.println("hi");
		Grid g= new Grid(1000,700,10000,500);
		JFrame frame = new JFrame("Static Generator");
		frame.add(g);
		frame.setSize(2000,1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		new Thread(g).start();
		/*g.printGrid();
		g.runGame();
		System.out.println("");
		g.printGrid();
		g.runGame();
		System.out.println("");
		g.printGrid();
		g.runGame();
		System.out.println("");
		g.printGrid();
		while(g.playerList.size() >1){
			g.runGame();
			System.out.println("");
			g.printGrid();
			
			
		}
		*/
		
	}

}
