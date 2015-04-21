package Strategie;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


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
	 * Désactive la règle et réinitialise les conditions
	 */
	public void Desactive(){
		activated=false;
		Iterator it = requis.iterator();
	    while (it.hasNext()) {
	    	((Condition)it.next()).reset();
	    }
	}
	
	/**
	 * Valide si la règle est activable
	 * @return
	 */
	public boolean canBeActivated(){
		Iterator it = requis.iterator();
	    while (it.hasNext()) {
	    	if (((Condition)it.next()).isRespecte()==false)
	    		return false;
	    }
	    
	    if (activated==false)
	    	return true;
	    
	    return false;	
	}
	
	/**
	 * Après activation d'une règle on peut vérifier si les prérequis d'autre règles sont remplis
	 * @param no Numero de la regle activee
	 * @param val Valeur booleene
	 */
	public void updateRequis(int no, boolean val){
		Iterator it = requis.iterator();
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
