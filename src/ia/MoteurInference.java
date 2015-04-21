package ia;

import ia.regles.Regle1;
import ia.regles.Regle10;
import ia.regles.Regle2;
import ia.regles.Regle3;
import ia.regles.Regle4;
import ia.regles.Regle5;
import ia.regles.Regle6;
import ia.regles.Regle7;
import ia.regles.Regle8;
import ia.regles.Regle9;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



public class MoteurInference {
	
	private static MoteurInference Instance = new MoteurInference();
	private List <Regle> regles;
	public Strategie maStrategie = null;
	
	private MoteurInference(){
		regles = new ArrayList<Regle>();
		regles.add(new Regle1());
		regles.add(new Regle1());
		regles.add(new Regle2());
		regles.add(new Regle3());
		regles.add(new Regle4());
		regles.add(new Regle5());
		regles.add(new Regle6());
		regles.add(new Regle7());
		regles.add(new Regle8());
		regles.add(new Regle9());
		regles.add(new Regle10());
	}
	
	public static MoteurInference GetInstance()
	{
		return Instance;
	}
	
	public void choixStrategie(){
		boolean regleActivable=true;
		
		while (regleActivable)
		{
			Iterator<Regle> i = regles.iterator();
			Regle tmp;
			boolean val;
			
			//Choix de la règle
			while(i.hasNext()){
				tmp=(Regle) i.next();
				if (tmp.canBeActivated()){
					val=tmp.Activate();
					regleActivable = updateActivationCondition(tmp.getNo(), val);
					break;
				}
			}
		}
		
		
		
		resetRegle();
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
