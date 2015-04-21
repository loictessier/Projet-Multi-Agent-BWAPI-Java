/**
 * Verifie sir le joueur controle par Bwapi est Terran
 */

package ia.regles;

import ia.Regle;
import agents.AgentControlleur;
import bwapi.*;

public class Regle2 extends Regle{

	public Regle2() {
		super(2);
	}

	@Override
	public boolean Activate(){
		activated =true;

		Player self = AgentControlleur.self; 

		if(self.getRace().c_str().equals(Race.Terran.c_str()))
		{
			return true;
		}
		return false;
	}

}
