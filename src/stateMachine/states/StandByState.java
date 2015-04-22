package stateMachine.states;

import bwapi.Position;
import stateMachine.State;
import agents.UnitAgent;
import messaging.Message;

/**
 * Etat standBy, l'agent ne fait rien
 *
 */
public class StandByState extends State<UnitAgent> {

	@Override
	public void Enter(UnitAgent agent) {
		System.out.println("Enter state StandBy");
	}

	@Override
	public void Execute(UnitAgent agent) {
		System.out.println("Standing");
	}

	@Override
	public void Exit(UnitAgent agent) {
		System.out.println("Exit standby state");
	}

	/**
	 * Gestion des messages recu
	 */
	@Override
	public boolean OnMessage(UnitAgent agent, Message message) {
		switch(message.Msg) {
		// Message informant de la position d'un autre agent unité
		case Msg_MyPosition:
			Position pos = (Position) message.ExtraInfo;
			System.out.println("Message reçu : Unit "+ message.Sender + " is at (" + pos.getX() + ", " + pos.getY() + ")");
			return true;
		case Msg_GoToPosition:
			// Changement de position
			// Changement d'etat pour l'etat MovingState
			agent.GetFSM().ChangeState(new MovingState((Position) message.ExtraInfo));
			return true;
		default :
			return false;
		}
	}
}
