package Strategie;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class MoteurInference {
	
	private List <Regle> regles;
	
	public MoteurInference(){
		regles = new ArrayList<Regle>();
		regles.add(new Regle1());
	}
	
	public Strategie choixStrategie(){
		boolean regleActivable=true;
		
		while (regleActivable){
			Iterator<Regle> i = regles.iterator();
			Regle tmp;
			boolean val;
			
			//Choix de la r�gle
			while(i.hasNext()){
				tmp=(Regle) i.next();
				if (tmp.canBeActivated()){
					break;
				}
			}
		}
	}
	
	/**
	 * R�initialise les r�gles pour la prochaine inf�rence
	 */
	private void resetRegle() {
		Iterator<Regle> i = regles.iterator();
		while(i.hasNext()){
			((Regle)i.next()).Desactive();
		}
	}
	
	/**
	 * Met � jour les r�gles du moteur en indiquant la nouvelle r�gle ayant �t� activ�e.
	 */
	private boolean updateActivationCondition(int regle, boolean valide){
		Iterator<Regle> i = regles.iterator();
		boolean activable = false;
		Regle tmp;
		
		while (i.hasNext()){
			tmp=((Regle)i.next());
			tmp.updateRequis(regle, valide);
			
			if (tmp.canBeActivated()){
				activable=true;
			}
		}
		return activable;
	}
}
