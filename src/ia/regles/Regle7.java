/**
 * Verifie si le joueur ennemi a des unite de type Zergling
 */

package ia.regles;

import ia.Condition;
import ia.Regle;
import agents.AgentControlleur;
import bwapi.*;

public class Regle7 extends Regle{

	public Regle7() {
		super(7);
		requis.add(new Condition(3, true));
	}
	
	@Override
	public boolean Activate(){
		activated = true;
		Game game = AgentControlleur.game; 
		
		for(Unit un : game.enemy().getUnits()){
			if(un.isVisible())
			{
				if(un.getType().c_str().equals(UnitType.Zerg_Zergling.c_str()))
				{
					return true;
				}
				return false;
			}
		}
		return false;
	}	

}
