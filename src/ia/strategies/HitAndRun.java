package ia.strategies;

import messaging.Message;
import messaging.MessageDispatcher;
import bwapi.*;
import agents.AgentControlleur;
import agents.UnitAgent;
import ia.Strategie;

public class HitAndRun extends Strategie{

	private Position nextPos = null;
	
	public HitAndRun(){
		nom = "Hit and Run";
	}
	
	@Override
	public void run() {
		Game game = AgentControlleur.game;
		
		Unit ref = null;
		Unit target = null;
		Unit enemy = null;
		
		for(Unit u : game.enemy().getUnits()) {
			if(u.isVisible() && u.getType().c_str().equals(UnitType.Protoss_Zealot.c_str())) {
				if(u.getOrderTarget() != null) {
					if(u.getOrderTarget().getType().c_str().equals(UnitType.Terran_Marine.c_str())) {
						target = u.getOrderTarget();
						enemy = u;
					}
				}
					
			}
		}
		
		for(Unit u : AgentControlleur.self.getUnits()) {
			if(u.getType().c_str().equals(UnitType.Terran_Marine.c_str())) {
				if(u.getID() != target.getID()) {
					ref = u;
				}
			}
		}
		
		if(ref != null && enemy != null) {
			// While the unit hasn't arrived yet
			if(nextPos == null || (target.getPosition().getX() != nextPos.getX() && target.getPosition().getY() != nextPos.getY())) {
				int tx = target.getPosition().getX();
				int ty = target.getPosition().getY();
				
				int rx = ref.getPosition().getX();
				int ry = ref.getPosition().getY();
				
				int side = enemy.getAngle() < (Math.PI/2) || enemy.getAngle() > (3*Math.PI/2) ? 1 : -1;
				double angle = side * (45) * (Math.PI/180);
				int nx = ( (int) (((tx - rx) * Math.cos(angle)) - ((ty - ry) * Math.sin(angle)) + rx) );
				int ny = ( (int) (((tx - rx) * Math.sin(angle)) + ((ty - ry) * Math.cos(angle)) + ry) );
				
				nextPos = new Position(nx, ny);
				
				MessageDispatcher.Instance().DispatchMessage(MessageDispatcher.SEND_MSG_IMMEDIATELY, //time delay
						AgentControlleur.ID(),        								//ID of sender
		                target.getID(),            						//ID of recipient
		                Message.message_type.Msg_GoToPosition,   		//the message
		                nextPos);
				
				//if(!target.isMoving())
				//	target.move(nextPos);
			}
		} else if (enemy != null){
			for(Unit u : AgentControlleur.self.getUnits()) {
				if(u.getType().c_str().equals(UnitType.Terran_Marine.c_str())) {
					if(!u.isAttacking())
						u.attack(enemy);
				}
			}
		}
	}

}
