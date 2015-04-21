package Agents;

import java.util.Hashtable;
import java.util.Map.Entry;

public class AgentManager {
	private static AgentManager instance = new AgentManager();
	//to facilitate quick lookup the entities are stored in a std::map, in which
	//pointers to entities are cross referenced by their identifying number
	private Hashtable<Integer, Agent> m_EntityMap = new Hashtable<>();
 
	private AgentManager(){}
 
	public static AgentManager Instance() { 
		return instance;
	}
 
	//this method stores a pointer to the entity in the std::vector
	//m_Entities at the index position indicated by the entity's ID
	//(makes for faster access)
	public void RegisterEntity(Agent NewEntity) {
		m_EntityMap.put(NewEntity.ID(), NewEntity);
	}
 
	//returns a pointer to the entity with the ID given as a parameter
	public Agent GetEntityFromID(int id) {
		//find the entity
		Agent agent = m_EntityMap.get(id);
		return agent;
	}
 
	//this method removes the entity from the list
	public void RemoveEntity(Agent pEntity) {
		for(Entry<Integer, Agent> entry: m_EntityMap.entrySet()){
            if(pEntity.ID() == entry.getValue().ID()) {
                m_EntityMap.remove(entry.getKey());
                break;
            }
        }
	}
}
