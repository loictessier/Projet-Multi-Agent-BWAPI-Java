package Agents;
import Message;


public class UnitAgent extends Agent {
	//an instance of the state machine class
	private StateMachine<UnitAgent>  m_pStateMachine;
	
	public UnitAgent(int id) {
		super(id);
		
		//set up state machine
	    m_pStateMachine = new StateMachine<UnitAgent>(this);
	     
	    //m_pStateMachine.setCurrentState(GoHomeAndSleepTilRested::Instance());
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
}