package agents;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import stateMachine.StateMachine;
import stateMachine.states.StandByState;

import messaging.Message;
import messaging.MessageDispatcher;

import bwapi.*;

/**
 * Agent unité qui est un thread
 *
 */
public class UnitAgent extends Agent {
	// Une instance de la machine a etats
	// abdou est une putain de machine !!
	private StateMachine<UnitAgent> m_pStateMachine;
	private Unit m_pUnit;
	
	/**
	 * Constructeur
	 * @param id
	 * @param unit
	 */
	public UnitAgent(int id, Unit unit) {
		super(id);
		m_pUnit = unit;
	    m_pStateMachine = new StateMachine<UnitAgent>(this);
	    m_pStateMachine.SetCurrentState(new StandByState());
	}

	/**
	 * Mis à jour de l'agent par la machine a etat
	 */
	@Override
	public void Update() {	   
		m_pStateMachine.Update();
	}

	/**
	 * Gestion des messages par la mahine a etats
	 */
	@Override
	public boolean HandleMessage(Message msg) {
		return m_pStateMachine.HandleMessage(msg);
	}

	/**
	 * Lancement d'un timer qui envoie un message toutes les 10 secondes
	 */
	@Override
	public void run() {
		super.run();
		// Update each 5 second
	    Timer timer = new Timer(true);
    	timer.schedule(new TimerTask() {
    	    public void run() {
    	        cycleMessage();
    	     }
    	  }, 0, 10000);
	}
	
	/**
	 * Message contenant la position de l'agent envoyé aléatoirement  toutes les 10 secondes
	 */
	private void cycleMessage() {
		// Send message to friend each update
		Random random = new Random();
		int pif = random.nextInt(4);
		for(UnitAgent ua : AgentControlleur.agents) {
			if(ua.ID()==pif) {
				//System.out.println(ua.m_pUnit.);
				MessageDispatcher.Instance().DispatchMessage(MessageDispatcher.SEND_MSG_IMMEDIATELY, //time delay
					ID(),        								//ID of sender
					ua.ID(),            						//ID of recipient
                	Message.message_type.Msg_MyPosition,   		//the message
                	m_pUnit.getPosition());
			}
		}
	}
	
	public StateMachine<UnitAgent> GetFSM() {
		return m_pStateMachine;
	}
	
	public Unit getUnit() {
		return m_pUnit;
	}
}
