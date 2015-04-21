
public class Message {
	//these telegrams will be stored in a priority queue. Therefore the >
	//operator needs to be overloaded so that the PQ can sort the telegrams
	//by time priority. Note how the times must be smaller than
	//SmallestDelay apart before two Telegrams are considered unique.
	public final double SmallestDelay = 0.25;
	
	//the entity that sent this telegram
	public int Sender;
		 
	//the entity that is to receive this telegram
	public int Receiver;
		 
	//the message itself. These are all enumerated in the file
	//"MessageTypes.h"
	public int Msg;
		 
	//messages can be dispatched immediately or delayed for a specified amount
	//of time. If a delay is necessary this field is stamped with the time 
	//the message should be dispatched.
	public double DispatchTime;
	 
	//any additional information that may accompany the message
	public String ExtraInfo;
		 
	public Message() {
		DispatchTime = -1;
        Sender = -1;
        Receiver = -1;
        Msg = -1;
	}
		 
		 
	public Message(double time, int sender, int receiver, int msg, String info) {
		DispatchTime = time;
        Sender = sender;
        Receiver = receiver;
        Msg = msg;
        ExtraInfo = info;
    }	 
	
	/* Comparateur
	public boolean operator==(const Telegram& t1, const Telegram& t2) {
	  return ( fabs(t1.DispatchTime-t2.DispatchTime) < SmallestDelay) &&
	          (t1.Sender == t2.Sender)        &&
	          (t1.Receiver == t2.Receiver)    &&
	          (t1.Msg == t2.Msg);
	}
	 
	inline bool operator<(const Telegram& t1, const Telegram& t2) {
		if (t1 == t2) {
			return false;
		} else {
			return  (t1.DispatchTime < t2.DispatchTime);
		}
	}
	 
	inline std::ostream& operator<<(std::ostream& os, const Telegram& t) {
		os << "time: " << t.DispatchTime << "  Sender: " << t.Sender << "   Receiver: " << t.Receiver << "   Msg: " << t.Msg;
	 
	  return os;
	}
	}*/
}
