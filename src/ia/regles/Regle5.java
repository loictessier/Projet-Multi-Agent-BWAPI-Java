/**
 * Verifie si le joueur dispose d'unites de type Zelote
 */

package ia.regles;

import ia.Condition;
import ia.Regle;
import bwapi.*;

public class Regle5 extends Regle{

	public Regle5() {
		super(5);
		requis.add(new Condition(1, true));
	}
	
	@Override
	public boolean Activate(){
		Mirror mirror= new Mirror();
		Game game = mirror.getGame();
		
		if(game.self().getUnits().get(0).getType().c_str().equals(UnitType.Protoss_Zealot.c_str())){
			return true;
		}
		return false;
		
	}

}
