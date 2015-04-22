package agents;

import java.util.Hashtable;
import java.util.Map.Entry;

public class AgentManager {
	private static AgentManager instance = new AgentManager();
	private Hashtable<Integer, Agent> m_EntityMap = new Hashtable<>();
 
	private AgentManager(){}
 
	public static AgentManager Instance() { 
		return instance;
	}
 
	// Enregistre un agent dans le registre
	public void RegisterEntity(Agent NewEntity) {
		m_EntityMap.put(NewEntity.ID(), NewEntity);
	}
 
	// Recupere un agent selon son ID
	public Agent GetEntityFromID(int id) {
		//find the entity
		Agent agent = m_EntityMap.get(id);
		return agent;
	}
 
	// Supprime un agent du registre
	public void RemoveEntity(Agent pEntity) {
		for(Entry<Integer, Agent> entry: m_EntityMap.entrySet()){
            if(pEntity.ID() == entry.getValue().ID()) {
                m_EntityMap.remove(entry.getKey());
                break;
            }
        }
	}
}
