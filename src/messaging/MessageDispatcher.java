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
	
	// Liste ne contenant pas de doublon et trié par ordre d'arrivé
	private LinkedHashSet<Message> PriorityQ = new LinkedHashSet<Message>();
	 
	// singleton
	public static MessageDispatcher Instance() {
		return INSTANCE;
	}
	
	// Distribue le message au receveur
	private void Discharge(Agent pReceiver, final Message msg) {
		if (!pReceiver.HandleMessage(msg))
		{
			//telegram could not be handled
			System.out.println("Message not handled");
		}
	}
	
	private MessageDispatcher() {}
	
	/**
	 * Envoie un message
	 * @param delay
	 * @param sender
	 * @param receiver
	 * @param msg
	 * @param ExtraInfo
	 */
	public void DispatchMessage(double delay, int sender, int receiver, message_type msg, Object ExtraInfo) {
		// Recuperation des agents
		Agent pReceiver = AgentManager.Instance().GetEntityFromID(receiver);
		 
		// Verifie le receveur
		if (pReceiver == null) {
			System.out.println("Warning! No Receiver with ID of " + receiver + " found");
			return;
		}

		// Creation du message
		Message message = new Message(0, sender, receiver, msg, ExtraInfo);
		   
		// Message sans délai                   
		if (delay <= 0.0f) {
			//Envoie du message
			Discharge(pReceiver, message);
		} else { 			// Sinon calcul du moment ou le message sera envoyé
			double CurrentTime = System.nanoTime();
			message.DispatchTime = CurrentTime + delay;
			 
			// Insertion dans la liste
			PriorityQ.add(message);        
		}
	}
	 
	/**
	 * Envoi des messages avec délai
	 */
	public void DispatchDelayedMessages() {
		// Recuperation du temps atuel
		double CurrentTime = System.nanoTime();
		 
		//Suppression des messages avec un délai dépassé
		Iterator<Message> iter = PriorityQ.iterator();
		while( !PriorityQ.isEmpty() && (((Message[])PriorityQ.toArray())[0].DispatchTime < CurrentTime) 
				&& (((Message[])PriorityQ.toArray())[0].DispatchTime > 0) ) {
			// Recuperation du premier message
			Message message = iter.next();
			
			// Receveur
			Agent pReceiver = AgentManager.Instance().GetEntityFromID(message.Receiver);
		
			// Envoie du message
			Discharge(pReceiver, message);
			 
			// Suppression de la liste
			iter.remove();
		}
	}
}
