package agents;

import java.util.Timer;
import java.util.TimerTask;

import stateMachine.StateMachine;
import stateMachine.states.StandByState;

import messaging.Message;
import messaging.MessageDispatcher;

import bwapi.*;

public class UnitAgent extends Agent {
	//an instance of the state machine class
	private StateMachine<UnitAgent> m_pStateMachine;
	private Unit m_pUnit;
	
	public UnitAgent(int id, Unit unit) {
		super(id);
		m_pUnit = unit;
		//set up state machine
	    m_pStateMachine = new StateMachine<UnitAgent>(this);
	     
	    m_pStateMachine.SetCurrentState(new StandByState());
	}

	@Override
	public void Update() {	   
		m_pStateMachine.Update();
	}

	@Override
	public boolean HandleMessage(Message msg) {
		return m_pStateMachine.HandleMessage(msg);
	}

	public StateMachine<UnitAgent> GetFSM() {
		return m_pStateMachine;
	}

	@Override
	public void run() {
		super.run();
		// Update each 5 second
	    Timer timer = new Timer(true);
    	timer.schedule(new TimerTask() {
    	    public void run() {
    	        cycleMessage();
    	     }
    	  }, 0, 5000);
	}
	
	private void cycleMessage() {
		// Send message to friend each update
		for(UnitAgent ua : AgentControlleur.agents) {
			MessageDispatcher.Instance().DispatchMessage(MessageDispatcher.SEND_MSG_IMMEDIATELY, //time delay
				ID(),        								//ID of sender
                ua.ID(),            						//ID of recipient
                Message.message_type.Msg_MyPosition,   		//the message
                m_pUnit.getPosition());
		}
	}
	
	public Unit getUnit() {
		return m_pUnit;
	}
}