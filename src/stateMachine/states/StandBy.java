package stateMachine.states;

import bwapi.Position;
import stateMachine.State;
import agents.UnitAgent;
import messaging.Message;

public class StandBy extends State<UnitAgent> {

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

	@Override
	public boolean OnMessage(UnitAgent agent, Message message) {
		switch(message.Msg) {
			case Msg_MyPosition:
				Position pos = (Position) message.ExtraInfo;
				System.out.println("Unit "+ message.Sender + " is at (" + pos.getX() + ", " + pos.getY() + ")");
				return true;
			default :
				return false;
		}
	}

}
