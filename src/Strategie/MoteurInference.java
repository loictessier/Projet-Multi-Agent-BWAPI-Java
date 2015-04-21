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
			
			//Choix de la règle
			while(i.hasNext()){
				tmp=(Regle) i.next();
				if (tmp.canBeActivated()){
					break;
				}
			}
		}
	}
	
	/**
	 * Réinitialise les règles pour la prochaine inférence
	 */
	private void resetRegle() {
		Iterator<Regle> i = regles.iterator();
		while(i.hasNext()){
			((Regle)i.next()).Desactive();
		}
	}
	
	/**
	 * Met à jour les règles du moteur en indiquant la nouvelle règle ayant été activée.
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
