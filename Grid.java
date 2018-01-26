package assasin1;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class Grid  extends JComponent implements Runnable{
	int iDirs[];
	int jDirs[];
	ArrayList<Player> playerList;
	Player grid[][];
	BufferedImage image;
	int[][] pixels;
	Random r;
	
	public Grid(int x,int y, int num, int assisinCount){
		playerList = new ArrayList<Player>();
		  r = new Random();
		 iDirs = new int[8];
			iDirs[0] = -1;
			iDirs[1] = -1;
			iDirs[2] = 0;
			iDirs[3] = 1;
			iDirs[4] = 1;
			iDirs[5] = 1;
			iDirs[6] = 0;
			iDirs[7] = -1;
		    jDirs = new int[8];
			jDirs[0] =0;
			jDirs[1] = 1;
			jDirs[2] = 1;
			jDirs[3] = 1;
			jDirs[4] =0;
			jDirs[5] = -1;
			jDirs[6] = -1;
			jDirs[7] = -1;
			grid = new Player[x][y];
			for(int i=0; i<num;i++){
				//playerList.add(new Player);
				int xpos =  r.nextInt(x);
				int ypos = r.nextInt(y);
				while(grid[xpos][ypos] != null){
				 xpos = r.nextInt(x);
				 ypos = r.nextInt(y);
				}
			     playerList.add(new Player(xpos,ypos,false,'a'));
			     grid[xpos][ypos] = playerList.get(playerList.size()-1);
			     
				
				
			}
			/*int assasin = r.nextInt(playerList.size());
			playerList.get(assasin).isAssisin = true;
			
			int assasin2 = r.nextInt(playerList.size());
			playerList.get(assasin2).isAssisin = true;
			
			int assasin3 = r.nextInt(playerList.size());
			playerList.get(assasin3).isAssisin = true;*/
			for(int i =0; i<assisinCount;i++){
				int assasin = r.nextInt(playerList.size());
				playerList.get(assasin).isAssisin = true;
				
			}
		
	}
	void printGrid(){
		for(int i=0; i<grid.length;i++){
			for(int j=0; j<grid[i].length;j++){
				if(grid[i][j] == null)System.out.print("0");
				
				
			   else System.out.print((grid[i][j]).id);

			
			}
			System.out.println("");
		
	}
	}
	public void initialize(){
      int w = getSize().width,h = getSize().height;
	w = grid.length;
	h= grid[0].length;
		
		
		
		//image = new BufferedImage(cm,wr,false,null);
		image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		
		pixels = new int[image.getWidth()][image.getHeight()];
		for(int i =0; i<pixels.length; i++){
			for(int j =0; j<pixels[i].length;j++){
				//if(grid[i][j] != null){
					//	pixels[i][j] = 255255255;
				//}else{
					//pixels[i][j] = 0;
					
				//}
				pixels[i][j] = 0;
		}
	}
		for(int i =0; i< playerList.size();i++){
			pixels[playerList.get(i).xpos][playerList.get(i).ypos] = rgbValue(255, 255, 0);
			if(playerList.get(i).isAssisin == true) pixels[playerList.get(i).xpos][playerList.get(i).ypos] = rgbValue(255, 0, 0);
		}
		image = setRGBPixels(image,pixels);
			
	
	}
	public void paint(Graphics g){
		if(image == null)initialize();
		g.drawImage(image, 0, 0, this);
		
		
	}
	public void run(){
		
		while(playerList.size() >1){
			runGame();
			initialize();
			repaint();
			System.out.println(playerList.size() + "is the size");
			
		}
		
	}
	void movePlayer(Player p, int i){
		System.out.println(playerList.size() + "is the size");
		int prevx = p.xpos;
		int prevy = p.ypos;
		int newx = p.xpos + iDirs[i];
		int newy = p.ypos + jDirs[i];
		if(grid[newx][newy] == null){
			grid[prevx][prevy] = null;
			grid[newx][newy] = p;
			p.xpos = newx;
			p.ypos = newy;
			
			
		}
		else{
			grid[prevx][prevy] = grid[newx][newy];
			(grid[prevx][prevy]).xpos = prevx;
			(grid[prevx][prevy]).ypos = prevy;
			
			
			grid[newx][newy] = p;
			p.xpos = newx;
			p.ypos = newy;
			if((grid[prevx][prevy]).isAssisin && grid[newx][newy].turnsLeft ==-1){
				grid[newx][newy].turnsLeft = r.nextInt(100);
				
				
				
			}
			if((grid[newx][newy]).isAssisin && grid[prevx][prevy].turnsLeft ==-1){
				grid[prevx][prevy].turnsLeft = r.nextInt(100);
				
				
				
			}
			
		}
		
	}
	int getDirection(Player a, Player b){
		//a to b
		//701
		//6x2
		//543
		int x = b.xpos-a.xpos;
		int y = b.ypos-a.ypos;
		if(x < 0 && y==0 )return 0;
		if(x< 0 && y > 0 ) return 1;
		if(x ==0  && y > 0)return 2;
		if(x>0 && y>0) return 3;
		if(x>0 && y==0)return 4;
		if(x>0 && y<0) return 5;
		if(x==0 && y < 0) return 6;
		if(x<0 && y<0) return 7;
		/*if(x ==0 && y<0 )return 0;
		if(x>0 && y <0 ) return 1;
		if(x>0 && y ==0)return 2;
		if(x>0 && y>0) return 3;
		if(x==0 && y>0)return 4;
		if(x<0 && y>0) return 5;
		if(x<0 && y == 0) return 6;
		if(x<0 && y<0) return 7;*/
		return 0;
		
		
	}
	void runGame(){
		for(int i =0; i< playerList.size();i++){
			Player a = playerList.get(i);
		    // r = new Random();
			Player b = playerList.get((r.nextInt(50))%playerList.size());
			while(b.xpos == a.xpos && b.ypos == a.ypos){
				 b = playerList.get((r.nextInt(playerList.size()))%playerList.size());
				
			}
			movePlayer(a,getDirection(a,b));
			if(a.turnsLeft == 0){
				grid[a.xpos][a.ypos] = null;
				playerList.remove(a);
				
			}
			if(a.turnsLeft != -1){
				a.turnsLeft--;
			}
			
		}
		
		
	}
	public static BufferedImage setRGBPixels(BufferedImage img, int[][] pixels) {
		// assumes width >0
		if (pixels == null) {
			//failure("Your pixel array is null!");
			return img;
		}
		int width = pixels.length, height = pixels[0].length;
		BufferedImage target = (img != null && 
				img.getWidth() == pixels.length && 
				img.getHeight() == pixels[0].length) ? img : new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++)
				target.setRGB(i, j, pixels[i][j]);
		return target;
	}

	/**
	 * Gets the BufferedImage as a 2D array of RGB pixel values.
	 */
	public static int[][] getRGBPixels(BufferedImage img) {
		if (img == null)
			return null;
		int[][] result = null;
		try {
			PixelGrabber g = new PixelGrabber(img, 0, 0, -1, -1, true);
			g.grabPixels();
			int[] pixels = (int[]) g.getPixels();

			int w = g.getWidth(), h = g.getHeight();
			result = new int[w][h];

			for (int j = 0, count = 0; j < h; j++)
				for (int i = 0; i < w; i++)
					result[i][j] = pixels[count++];

			return result;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	public static void saveImage(BufferedImage img, File file)
			throws IOException {
		
		if (img == null)
			System.out.println("Failed");
		else {
			String format = file.getPath().toLowerCase();
			int lastIndex = format.lastIndexOf('.');
			if (lastIndex >= 0)
				format = format.substring(lastIndex + 1);
			if (format.equals("jpg"))
				format = "jpeg";
			//loading("Writing " + format + " at " + file.getPath());
			ImageIO.write(img, format, file);
			//success("Write completed.");
		}
	}
	public static int redValue(int rgb) {
		return (rgb>>16) & 0xff;
	}

	public static int greenValue(int rgb) {
		return (rgb>>8) & 0xff;
	}

	public static int blueValue(int rgb) {
		return rgb & 0xff;
	}

	public static int rgbValue(int r, int g, int b) {
		return (r<<16 | g<<8 | b);
	}

	public static int getWidth(int[][] image) {
		return image.length;
	}

	public static int getHeight(int[][] image) {
		return image[0].length;
	}


	

}
