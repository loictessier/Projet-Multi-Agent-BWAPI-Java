package ia.strategies;

import messaging.Message;
import messaging.MessageDispatcher;
import bwapi.*;
import agents.AgentControlleur;
import ia.Strategie;

/**
 * Stratégie de Hit And Run
 *
 */

public class HitAndRun extends Strategie{
	// Position sauvegardé de l'appat
	private Position nextPos = null;
	
	public HitAndRun(){
		nom = "Hit and Run";
	}
	
	/**
	 * Execution de la stratégie
	 */
	@Override
	public void run() {
		Game game = AgentControlleur.game;
		
		Unit ref = null;		// Marine qui va tenir la position et tiré
		Unit target = null;		// Marine qui va servir d'appat
		Unit enemy = null;		// Enemy zealot
		
		// On recupere la cible du zealot et le zealot
		for(Unit u : game.enemy().getUnits()) {
			// Si on a un zealot et qu'il est visible
			if(u.isVisible() && u.getType().c_str().equals(UnitType.Protoss_Zealot.c_str())) {
				//Si il a une cible
				if(u.getOrderTarget() != null) {
					// SI sa cible est un marine
					if(u.getOrderTarget().getType().c_str().equals(UnitType.Terran_Marine.c_str())) {
						// On sauve
						target = u.getOrderTarget();
						enemy = u;
					}
				}
					
			}
		}
		
		// ON recupere le marine qui tient la postion
		for(Unit u : AgentControlleur.self.getUnits()) {
			// Si c'est un marine
			if(u.getType().c_str().equals(UnitType.Terran_Marine.c_str())) {
				// Si l'unité est différent de la cible
				if(u.getID() != target.getID()) {
					ref = u;
				}
			}
		}
		
		// Si on a un appat et un tireur
		if(ref != null && enemy != null) {
			// Tant que l'appat n'a pas bougé ou atteint la position demandé
			if(nextPos == null || (target.getPosition().getX() != nextPos.getX() && target.getPosition().getY() != nextPos.getY())) {
				// On calcule une rotation vectorielle de 10° de l'appat autour du marine tireur
				int tx = target.getPosition().getX();
				int ty = target.getPosition().getY();
				
				int rx = ref.getPosition().getX();
				int ry = ref.getPosition().getY();
				
				// Rotation selon d'ou vient le zealot
				int side = enemy.getAngle() < (Math.PI/2) || enemy.getAngle() > (3*Math.PI/2) ? 1 : -1;
				// Rotation vectorielle
				double angle = side * (45) * (Math.PI/180);
				int nx = ( (int) (((tx - rx) * Math.cos(angle)) - ((ty - ry) * Math.sin(angle)) + rx) );
				int ny = ( (int) (((tx - rx) * Math.sin(angle)) + ((ty - ry) * Math.cos(angle)) + ry) );
				
				nextPos = new Position(nx, ny);
				
				// Envoie de la nouvelle position calculée à l'appat
				// Lappat recoit la postion et va bouger à la nouvelle position
				MessageDispatcher.Instance().DispatchMessage(MessageDispatcher.SEND_MSG_IMMEDIATELY, //time delay
						AgentControlleur.ID(),        								//ID of sender
		                target.getID(),            						//ID of recipient
		                Message.message_type.Msg_GoToPosition,   		//the message
		                nextPos);
				
				// Si c'était la stratégie qui bougeait l'appat
				//if(!target.isMoving())
				//	target.move(nextPos);
			}
		// SI on a qu'un marine
		} else if (enemy != null){
			// Toute les unités présentes tirent sur l'ennemi
			for(Unit u : AgentControlleur.self.getUnits()) {
				if(u.getType().c_str().equals(UnitType.Terran_Marine.c_str())) {
					if(!u.isAttacking())
						u.attack(enemy);
				}
			}
		}
	}

}
