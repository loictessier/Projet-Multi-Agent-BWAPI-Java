package ia;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public abstract class Regle {
	protected boolean activated;
	protected List<Condition> requis;
	private int no;
	
	public Regle(int no){
		activated=false;
		requis=new ArrayList<Condition>();
		this.no=no;
	}
	
	abstract public boolean Activate();
	
	/**
	 * D�sactive la r�gle et r�initialise les conditions
	 */
	public void Desactive(){
		activated=false;
		Iterator<Condition> it = requis.iterator();
	    while (it.hasNext()) {
	    	((Condition)it.next()).reset();
	    }
	}
	
	/**
	 * Valide si la r�gle est activable
	 * @return
	 */
	public boolean canBeActivated(){
		Iterator<Condition> it = requis.iterator();
	    while (it.hasNext()) {
	    	if (((Condition)it.next()).isRespecte()==false)
	    		return false;
	    }
	    
	    if (activated==false)
	    	return true;
	    
	    return false;	
	}
	
	/**
	 * Apr�s activation d'une r�gle on peut v�rifier si les pr�requis d'autre r�gles sont remplis
	 * @param no Numero de la regle activee
	 * @param val Valeur booleene
	 */
	public void updateRequis(int no, boolean val){
		Iterator<Condition> it = requis.iterator();
		Condition tmp;
	    while (it.hasNext()) {
	    	tmp=(Condition) it.next();
	    	tmp.update(no, val);
	    }
	}
	
	
	
	public int getNo(){
		return no;
	}
}
