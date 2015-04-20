
public class StateMachine<T> {
	//a pointer to the agent that owns this instance
	private T          m_pOwner;	 
	private State<T>   m_pCurrentState;
	
	//a record of the last state the agent was in
	private State<T>   m_pPreviousState;
		 
	//this is called every time the FSM is updated
	private State<T>   m_pGlobalState;
		   
	public StateMachine(T owner)
	{
		m_pOwner = owner;
		m_pCurrentState = null;
		m_pPreviousState = null;
		m_pGlobalState = null;
	}
		 
	//use these methods to initialize the FSM
	public void SetCurrentState(State<T> s) {
		m_pCurrentState = s;
	}
	
	public void SetGlobalState(State<T> s) {
		m_pGlobalState = s;
	}
	
	public void SetPreviousState(State<T> s) {
		m_pPreviousState = s;
	}
		   
	//call this to update the FSM
	public void Update() {
	    //if a global state exists, call its execute method, else do nothing
	    if(m_pGlobalState != null)
	    	m_pGlobalState.Execute(m_pOwner);
	 
	    //same for the current state
	    if (m_pCurrentState != null)
    		m_pCurrentState.Execute(m_pOwner);
    }
		 
	public boolean HandleMessage(final Message msg) {
		//first see if the current state is valid and that it can handle
	    //the message
	    if (m_pCurrentState != null && m_pCurrentState.OnMessage(m_pOwner, msg))
	    {
	      return true;
	    }
	   
	    //if not, and if a global state has been implemented, send 
	    //the message to the global state
	    if (m_pGlobalState != null && m_pGlobalState.OnMessage(m_pOwner, msg))
	    {
	      return true;
	    }
	 
	    return false;
	}
		 
	//change to a new state
	public void  ChangeState(State<T> pNewState) {
		assert pNewState != null : "<StateMachine::ChangeState>:trying to assign null state to current";
		 
	    //keep a record of the previous state
	    m_pPreviousState = m_pCurrentState;
		 
	    //call the exit method of the existing state
	    m_pCurrentState.Exit(m_pOwner);
		 
	    //change state to the new state
	    m_pCurrentState = pNewState;
		 
	    //call the entry method of the new state
	    m_pCurrentState.Enter(m_pOwner);
    }
		 
	//change state back to the previous state
	public void  RevertToPreviousState() {
		ChangeState(m_pPreviousState);
    }
		 
	//returns true if the current state's type is equal to the type of the
	//class passed as a parameter. 
	public boolean isInState(final State<T> st) {
		if (m_pCurrentState.getClass().getSimpleName() == st.getClass().getSimpleName())
			return true;
    	return false;
	}
		 
	public State<T> CurrentState() {
		return m_pCurrentState;
	}
	
	public State<T> GlobalState() {
		return m_pGlobalState;
	}
	
	public State<T>	PreviousState() {
		return m_pPreviousState;
	} 
}
