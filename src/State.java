
public abstract class State<T> {
	//this will execute when the state is entered
	public abstract void Enter(T agent);
		 
	//this is the states normal update function
	public abstract void Execute(T agent);
		 
	//this will execute when the state is exited. 
	public abstract void Exit(T agent);
		 
	//this executes if the agent receives a message from the 
	//message dispatcher
	public abstract boolean OnMessage(T agent, final Message message);
}
