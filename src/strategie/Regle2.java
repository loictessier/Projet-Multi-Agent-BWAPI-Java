/**
 * Verifie sir le joueur controle par Bwapi est Terran
 */

package strategie;

import bwapi.*;

public class Regle2 extends Regle{

	public Regle2() {
		super(2);
	}
	
	@Override
	public boolean Activate(){
		Mirror mirror= new Mirror();
		Game game = mirror.getGame();
		
		if(game.self().getRace().c_str().equals(Race.Terran.c_str()))
		{
			return true;
		}
		return false;
	}

}
