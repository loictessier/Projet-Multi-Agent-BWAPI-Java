/**
 * Verifie si le joueur ennemi est Protoss
 */

package ia.regles;

import ia.Regle;
import bwapi.Game;
import bwapi.Mirror;
import bwapi.Race;

public class Regle4 extends Regle{

	public Regle4() {
		super(4);
	}
	
	@Override
	public boolean Activate(){
		Mirror mirror= new Mirror();
		Game game = mirror.getGame();
		
		if(game.enemy().getRace().c_str().equals(Race.Protoss.c_str()))
		{
			return true;
		}
		return false;
	}

}
