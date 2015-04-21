package Agents;
import Message;


public abstract class Agent {
	//every entity must have a unique identifying number
	private int m_ID;
		 
	//this is the next valid ID. Each time a BaseGameEntity is instantiated
	//this value is updated
	private static int  m_iNextValidID = 0;
		 
	public Agent(int id) {
		SetID(id);
	}
	
	//all entities must implement an update function
	public abstract void Update();
		 
	//all entities can communicate using messages. They are sent
	//using the MessageDispatcher singleton class
	public abstract boolean HandleMessage(Message msg);
	
	public int ID() {
		return m_ID;
	}
	
	//this must be called within the constructor to make sure the ID is set
	//correctly. It verifies that the value passed to the method is greater
	//or equal to the next valid ID, before setting the ID and incrementing
	//the next valid ID
	private void SetID(int val) {
		assert val >= m_iNextValidID : "<BaseGameEntity::SetID>: invalid ID";
		m_ID = val;
	    m_iNextValidID = m_ID + 1;
	}
}
