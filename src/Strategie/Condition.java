package Strategie;
/**
 * Représente une condition d'activation
 * @author UQAC
 */
public class Condition {
	private int no;
	private boolean valeur;
	private boolean respecte;
	
	public Condition(int no, boolean val){
		this.no=no;
		this.valeur=val;
		this.respecte=false;
	}
	
	public boolean isRespecte(){
		return respecte;
	}
	
	public void setRespecte(){
		respecte=true;
	}
	
	public void update(int no, boolean val){
		if (no==this.no){
			if (val==this.valeur){
				respecte=true;
			}
		}
	}
	
	public void reset(){
		respecte=false;
	}
}
