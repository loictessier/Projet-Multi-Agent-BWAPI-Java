/**
 * Verifie si le joueur controle par Bwapi est Protoss
 */
package Strategie;

import bwapi.*;

public class Regle1 extends Regle{
	
	public Regle1() {
		super(1);
	}
	
	@Override
	public boolean Activate(){
		
		Mirror mirror= new Mirror();
		Game game = mirror.getGame();
		
		if(game.self().getRace().c_str().equals(Race.Protoss.c_str()))
		{
			return true;
		}
		return false;
		
	}
	
}
