package stateMachine;

import messaging.Message;

/**
 * Machine a etats
 *
 * @param <T>
 */
public class StateMachine<T> {
	// Proprietaire de la machine a etats
	private T          m_pOwner;	 
	private State<T>   m_pCurrentState;
	
	// Etat precedent
	private State<T>   m_pPreviousState;
		 
	// Etat global
	private State<T>   m_pGlobalState;
		   
	public StateMachine(T owner)
	{
		m_pOwner = owner;
		m_pCurrentState = null;
		m_pPreviousState = null;
		m_pGlobalState = null;
	}
		 
	// Initialise l'etat
	public void SetCurrentState(State<T> s) {
		m_pCurrentState = s;
	}
	
	public void SetGlobalState(State<T> s) {
		m_pGlobalState = s;
	}
	
	public void SetPreviousState(State<T> s) {
		m_pPreviousState = s;
	}
		   
	// Mis a jour de la machine a etat
	public void Update() {
	    if(m_pGlobalState != null)
	    	m_pGlobalState.Execute(m_pOwner);
	 
	    // Execution de l'etat courant
	    if (m_pCurrentState != null)
    		m_pCurrentState.Execute(m_pOwner);
    }
	
	/**
	 * Gestion des messages de l'agent
	 * @param msg
	 * @return
	 */
	public boolean HandleMessage(final Message msg) {
		// Gestion des messages effective ?
	    if (m_pCurrentState != null && m_pCurrentState.OnMessage(m_pOwner, msg)) {
	      return true;
	    }
	   
	    // Gestion des messages par un etat global effective ?
	    if (m_pGlobalState != null && m_pGlobalState.OnMessage(m_pOwner, msg))
	    {
	      return true;
	    }
	 
	    return false;
	}
		 
	// Changement d'etat
	public void  ChangeState(State<T> pNewState) {
		assert pNewState != null : "<StateMachine::ChangeState>:trying to assign null state to current";
		 
	    // Ancien etat sauvé
	    m_pPreviousState = m_pCurrentState;
		 
	    // On sort de l'état courant
	    m_pCurrentState.Exit(m_pOwner);
		 
	    // On met a jour l'etat courant
	    m_pCurrentState = pNewState;
		 
	    // ON entre dans le nouvel etat
	    m_pCurrentState.Enter(m_pOwner);
    }
		 
	// Retour a l'etat precedent
	public void  RevertToPreviousState() {
		ChangeState(m_pPreviousState);
    }
		 
	// Test si on dans l'etat demandé
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
