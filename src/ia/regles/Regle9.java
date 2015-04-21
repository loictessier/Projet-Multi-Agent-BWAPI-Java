/**
 * Definit Strategie RetreatAndStand
 */
package ia.regles;

import ia.Condition;
import ia.MoteurInference;
import ia.Regle;
import ia.Strategie;
import ia.strategies.RetreatAndStand;

public class Regle9 extends Regle{

	public Regle9() {
		super(9);
		requis.add(new Condition(1, true));
		requis.add(new Condition(3, true));
	}
	
	@Override
	public boolean Activate()
	{
		activated = true;
		
		
		Strategie RetreatAndStand = new RetreatAndStand();
		MoteurInference moteur = MoteurInference.GetInstance();
		moteur.maStrategie = RetreatAndStand;
		return true;
	}

}
