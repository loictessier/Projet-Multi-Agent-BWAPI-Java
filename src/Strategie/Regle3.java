/**
 * Verifie si le joueur ennemi est Zerg
 */

package Strategie;

import bwapi.*;

public class Regle3 extends Regle{

	public Regle3() {
		super(3);
	}
	
	@Override
	public boolean Activate(){
		Mirror mirror= new Mirror();
		Game game = mirror.getGame();
		
		if(game.enemy().getRace().c_str().equals(Race.Zerg.c_str()))
		{
			return true;
		}
		return false;
	}

}
