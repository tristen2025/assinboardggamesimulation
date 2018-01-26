package assasin1;

public class Player {
public char id;	
public	int turnsLeft;
public	int xpos;
public	int ypos;
public boolean isAssisin;
public Player(int x,int y, boolean i,char yi){
	this.turnsLeft = -1;
	
	this.xpos=x;
	this.ypos=y;
	this.isAssisin=i;
	this.id = yi;
}
	
	

}
