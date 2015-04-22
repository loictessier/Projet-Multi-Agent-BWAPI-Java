package stateMachine;

import messaging.Message;

/**
 * Etat selon le design pattern State
 *
 * @param <T>
 */
public abstract class State<T> {
	// A l'entré de l'état
	public abstract void Enter(T agent);
		 
	// Excution de l'état a chaque tour
	public abstract void Execute(T agent);
		 
	// QUand on sort de l'état
	public abstract void Exit(T agent);
		 
	// Quand l'agent recoit un message
	public abstract boolean OnMessage(T agent, final Message message);
}
