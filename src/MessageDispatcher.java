import java.util.LinkedHashSet;

import Agents.Agent;


public class MessageDispatcher {
	//this class is a singleton
	public static MessageDispatcher Instance();
	
	//a LinkedHashSet is used as the container for the delayed messages
	//because of the benefit of automatic sorting and avoidance
	//of duplicates. Messages are sorted by their dispatch time.
	LinkedHashSet<Message> PriorityQ = new LinkedHashSet<Message>();
	 
	//this method is utilized by DispatchMessage or DispatchDelayedMessages.
	//This method calls the message handling member function of the receiving
	//entity, pReceiver, with the newly created telegram
	private void Discharge(Agent* pReceiver, final Telegram msg);
	 
	private MessageDispatcher() {}
	 
	//send a message to another agent. Receiving agent is referenced by ID.
	void DispatchMessage(double  delay,
	                       int    sender,
	                       int    receiver,
	                       int    msg,
	                       void*  ExtraInfo);
	 
	//send out any delayed messages. This method is called each time through   
	//the main game loop.
	void DispatchDelayedMessages();
}
