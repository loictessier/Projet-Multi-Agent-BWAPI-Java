/**
 * Verifie si le joueur ennemi est Zerg
 */

package ia.regles;

import ia.Regle;
import agents.AgentControlleur;
import bwapi.*;

public class Regle3 extends Regle{

	public Regle3() {
		super(3);
	}
	
	@Override
	public boolean Activate(){
		activated =true;
//		Mirror mirror= new Mirror();
//		Game game = mirror.getGame();
		
		Game game = AgentControlleur.game; 
		
		if(game.enemy().getRace().c_str().equals(Race.Zerg.c_str()))
		{
			return true;
		}
		return false;
	}

}
