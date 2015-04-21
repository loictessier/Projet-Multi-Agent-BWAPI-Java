/**
 * Verifie si le joueur dispose d'unites de type Marines
 */

package strategie;

import bwapi.*;

public class Regle6 extends Regle{

	public Regle6() {
		super(6);
		requis.add(new Condition(2, true));
	}
	
	@Override
	public boolean Activate(){
		Mirror mirror= new Mirror();
		Game game = mirror.getGame();
		
		if(game.self().getUnits().get(0).getType().c_str().equals(UnitType.Terran_Marine.c_str())){
			return true;
		}
		return false;
		
	}	
}
