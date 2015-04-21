package messaging;
import java.util.Iterator;
import java.util.LinkedHashSet;

import messaging.Message.message_type;

import agents.Agent;
import agents.AgentManager;




public class MessageDispatcher {	
	public static final double SEND_MSG_IMMEDIATELY = 0.0f;
	public static final int NO_ADDITIONAL_INFO   = 0;
	public static final int SENDER_ID_IRRELEVANT = -1;
	private static MessageDispatcher INSTANCE = new MessageDispatcher();
	
	//a LinkedHashSet is used as the container for the delayed messages
	//because of the benefit of automatic sorting and avoidance
	//of duplicates. Messages are sorted by their dispatch time.
	private LinkedHashSet<Message> PriorityQ = new LinkedHashSet<Message>();
	 
	//this method is utilized by DispatchMessage or DispatchDelayedMessages.
	//This method calls the message handling member function of the receiving
	//entity, pReceiver, with the newly created telegram
	private void Discharge(Agent pReceiver, final Message msg) {
		if (!pReceiver.HandleMessage(msg))
		{
			//telegram could not be handled
			System.out.println("Message not handled");
		}
	}
	 
	private MessageDispatcher() {}
	 
	//this class is a singleton
	public static MessageDispatcher Instance() {
		return INSTANCE;
	}
	
	//send a message to another agent. Receiving agent is referenced by ID.
	public void DispatchMessage(double delay, int sender, int receiver, message_type msg, Object ExtraInfo) {
		//get pointers to the sender and receiver
		Agent pSender   = AgentManager.Instance().GetEntityFromID(sender);
		Agent pReceiver = AgentManager.Instance().GetEntityFromID(receiver);
		 
		//make sure the receiver is valid
		if (pReceiver == null) {
			System.out.println("Warning! No Receiver with ID of " + receiver + " found");
			return;
		}

		//create the telegram
		Message message = new Message(0, sender, receiver, msg, ExtraInfo);
		   
		//if there is no delay, route telegram immediately                       
		if (delay <= 0.0f) {
			System.out.println("Instant telegram dispatched by " + pSender.ID() + " for " + pReceiver.ID() + ". Msg is "+ msg);
			 
			//send the telegram to the recipient
			Discharge(pReceiver, message);
		} else { 			//else calculate the time when the telegram should be dispatched
			double CurrentTime = System.nanoTime();
			 
			message.DispatchTime = CurrentTime + delay;
			 
			//and put it in the queue
			PriorityQ.add(message);   
			 
			System.out.println("Delayed telegram from " + pSender.ID() + " for " + pReceiver.ID() + ". Msg is " + msg);             
		}
	}
	 
	//send out any delayed messages. This method is called each time through   
	//the main game loop.
	public void DispatchDelayedMessages() {
		//get current time
		double CurrentTime = System.nanoTime();
		 
		//now peek at the queue to see if any telegrams need dispatching.
		//remove all telegrams from the front of the queue that have gone
		//past their sell by date
		Iterator<Message> iter = PriorityQ.iterator();
		while( !PriorityQ.isEmpty() && (((Message[])PriorityQ.toArray())[0].DispatchTime < CurrentTime) 
				&& (((Message[])PriorityQ.toArray())[0].DispatchTime > 0) ) {
			//read the telegram from the front of the queue
			Message message = iter.next();
			
			//find the recipient
			Agent pReceiver = AgentManager.Instance().GetEntityFromID(message.Receiver);
			 
			System.out.println("Queued telegram ready for dispatch: Sent to " + pReceiver.ID() + ". Msg is " + message);
			 
			//send the telegram to the recipient
			Discharge(pReceiver, message);
			 
			//remove it from the queue
			iter.remove();
		}
	}
}
