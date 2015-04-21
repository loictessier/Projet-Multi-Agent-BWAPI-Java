/**
 * Verifie si le joueur controle par Bwapi est Protoss
 */
package ia.regles;

import ia.Regle;
import agents.AgentControlleur;
import bwapi.*;

public class Regle1 extends Regle{
	
	public Regle1() {
		super(1);
	}
	
	@Override
	public boolean Activate(){
		activated =true;
		
		Player self = AgentControlleur.self; 
		
		if(self.getRace().c_str().equals(Race.Protoss.c_str()))
		{
			return true;
		}
		return false;
		
	}
	
}
