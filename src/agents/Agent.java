package agents;

import messaging.Message;

/**
 * Classe abstraite de l'agent
 *
 */
public abstract class Agent extends Thread {
	// Un agent a un ID unique
	private int m_ID;
		 
	// Prochain ID valide
	private static int  m_iNextValidID = 0;
		 
	public Agent(int id) {
		SetID(id);
	}
	
	// Foinction de mis a jour d'un agent
	public abstract void Update();
		 
	// Gestion de messages par l'agent
	public abstract boolean HandleMessage(Message msg);
	
	public int ID() {
		return m_ID;
	}
	
	// Mis a jour de l'ID
	private void SetID(int val) {
		assert val >= m_iNextValidID : "<BaseGameEntity::SetID>: invalid ID";
		m_ID = val;
	    m_iNextValidID = m_ID + 1;
	}
}
