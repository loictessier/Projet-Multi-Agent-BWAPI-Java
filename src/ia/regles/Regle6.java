/**
 * Verifie si le joueur dispose d'unites de type Marines
 */

package ia.regles;

import ia.Condition;
import ia.Regle;
import agents.AgentControlleur;
import bwapi.*;

public class Regle6 extends Regle{

	public Regle6() {
		super(6);
		requis.add(new Condition(2, true));
	}
	
	@Override
	public boolean Activate(){
		activated =true;

		Player self = AgentControlleur.self; 

		if(self.getUnits().get(0).getType().c_str().equals(UnitType.Terran_Marine.c_str())){
			return true;
		}
		return false;
		
	}	
}
