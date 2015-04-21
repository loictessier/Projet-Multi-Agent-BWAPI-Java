package stateMachine.states;

import stateMachine.State;
import agents.UnitAgent;
import messaging.Message;
import bwapi.Position;
import bwapi.Unit;

public class MovingState extends State<UnitAgent> {
	private Position pos;
	
	public MovingState(Position pos) {
		this.pos = pos;
	}
	
	@Override
	public void Enter(UnitAgent agent) {
		System.out.println("Enter state Moving");
	}

	@Override
	public void Execute(UnitAgent agent) {
		System.out.println("Moving");
		Unit unit = agent.getUnit();
		if(!unit.isMoving())
			unit.move(pos);
	}

	@Override
	public void Exit(UnitAgent agent) {
		System.out.println("Exit moving state");
	}

	@Override
	public boolean OnMessage(UnitAgent agent, Message message) {
		switch(message.Msg) {
		case Msg_MyPosition:
			//Position pos = (Position) message.ExtraInfo;
//			System.out.println("Unit "+ message.Sender + " is at (" + pos.getX() + ", " + pos.getY() + ")");
			return true;
		case Msg_GoToPosition:
			this.pos = (Position) message.ExtraInfo;
			return true;
		default :
			return false;
		}
	}
}
