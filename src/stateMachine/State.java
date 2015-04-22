package stateMachine;

import messaging.Message;

/**
 * Etat selon le design pattern State
 *
 * @param <T>
 */
public abstract class State<T> {
	// A l'entr� de l'�tat
	public abstract void Enter(T agent);
		 
	// Excution de l'�tat a chaque tour
	public abstract void Execute(T agent);
		 
	// QUand on sort de l'�tat
	public abstract void Exit(T agent);
		 
	// Quand l'agent recoit un message
	public abstract boolean OnMessage(T agent, final Message message);
}
