/**
 * Verifie si le joueur ennemi a des unites de type Zelotes
 */

package ia.regles;

import ia.Condition;
import ia.Regle;
import agents.AgentControlleur;
import bwapi.*;

public class Regle8 extends Regle{

	public Regle8() {
		super(8);
		requis.add(new Condition(4, true));
	}
	
	@Override
	public boolean Activate(){
		activated = true;
		Game game = AgentControlleur.game; 
			
		for(Unit un : game.enemy().getUnits()){
			if(un.isVisible())
			{
				if(un.getType().c_str().equals(UnitType.Protoss_Zealot.c_str()))
				{
					return true;
				}
				return false;
			}
		}
		return false;
	}	

}
