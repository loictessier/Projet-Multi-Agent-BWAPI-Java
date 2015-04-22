package messaging;


public class Message {
	// Types de message
	public static enum message_type {
		Msg_MyPosition("MyPosition"),
		Msg_Attack("Attack"),
		Msg_GoToPosition("Go to Position"),
		Msg_NotRecognized("Not recognized!");
		  
		private String s;
		  
		message_type(String s) {
			this.s = s;
		}
	};
	
	// Delai minimum
	public final double SmallestDelay = 0.25;
	
	// Envoyeur
	public int Sender;
		 
	//Receveur
	public int Receiver;
		 
	// Message typé
	public message_type Msg;
		 
	// Délai de dispatch du message
	public double DispatchTime;
	 
	// Information addtionnel comme une position par exemple
	public Object ExtraInfo = null;
		 
	public Message() {
		DispatchTime = -1;
        Sender = -1;
        Receiver = -1;
        Msg = message_type.Msg_NotRecognized;
	}
		 
		 
	public Message(double time, int sender, int receiver, message_type msg, Object info) {
		DispatchTime = time;
        Sender = sender;
        Receiver = receiver;
        Msg = msg;
        ExtraInfo = info;
    }
	
	@Override
	public String toString() {
		return this.Msg.s;
	}
}
