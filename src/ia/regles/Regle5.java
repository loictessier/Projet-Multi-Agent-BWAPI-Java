/**
 * Verifie si le joueur dispose d'unites de type Zelote
 */

package ia.regles;

import ia.Condition;
import ia.Regle;
import agents.AgentControlleur;
import bwapi.*;

public class Regle5 extends Regle{

	public Regle5() {
		super(5);
		requis.add(new Condition(1, true));
	}
	
	@Override
	public boolean Activate(){
		activated =true;

		Player self = AgentControlleur.self; 

		if(self.getUnits().get(0).getType().c_str().equals(UnitType.Protoss_Zealot.c_str())){
			return true;
		}
		return false;
		
	}

}
