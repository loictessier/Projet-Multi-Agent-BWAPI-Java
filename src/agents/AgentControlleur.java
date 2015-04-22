package agents;
import ia.MoteurInference;
import ia.Strategie;

import java.util.ArrayList;

import messaging.MessageDispatcher;


import bwapi.*;
import bwta.BWTA;

/**
 * Classe controlleur
 *
 */
public class AgentControlleur extends DefaultBWListener {
	// Liste des agents
	public static ArrayList<UnitAgent> agents = new ArrayList<UnitAgent>();
    
	public static Mirror mirror = new Mirror();
	public static Game game;
	public static Player self;
	
    private static int m_ID = -1;
    
    private MoteurInference moteurInference = MoteurInference.GetInstance();
    private Strategie maStrategie = null;
    
    /**
     *  Lancement de la partie
     */
    public void run() {
        mirror.getModule().setEventListener(this);
        mirror.startGame();
	}

    @Override
    public void onUnitCreate(Unit unit) { }

    @Override
    public void onStart() {
    	this.maStrategie = null;
        game = mirror.getGame();
        self = game.self();

        //Use BWTA to analyze map
        //This may take a few minutes if the map is processed first time!
        System.out.println("Analyzing map...");
        BWTA.readMap();
        BWTA.analyze();
        System.out.println("Map data ready");
        
        // Choix de la stratégie
        while(maStrategie == null)
        {
        	moteurInference.choixStrategie();
        	this.maStrategie = moteurInference.maStrategie;
        }
        
        // Instanciation des agents unités
        for(Unit un : self.getUnits()) {
        	UnitAgent ua = new UnitAgent(un.getID(), un);
        	agents.add(ua);
        	AgentManager.Instance().RegisterEntity(ua);
        	ua.start();
        }
    }

    @Override
    public void onFrame() {
        // Affichage
    	game.setTextSize(10);
        game.drawTextScreen(10, 10, "Playing as " + self.getName() + " - " + self.getRace());
        if(maStrategie != null){
        	 game.drawTextScreen(10,100,"La strategie est "+maStrategie);
        }
        StringBuilder units = new StringBuilder("My units:\n");
        //iterate through my units
        for (Unit myUnit : self.getUnits())
            units.append(myUnit.getType()).append(" ").append(myUnit.getTilePosition()).append("\n");
        //draw my units on screen
        game.drawTextScreen(10, 25, units.toString());
        
        // Lancement de la stratégie
        maStrategie.run();
        
        // Mis à jour des agents
        for(UnitAgent ua : agents)
    		ua.Update();
     
        //Dispatch des messages
        MessageDispatcher.Instance().DispatchDelayedMessages();
    }

    public static void main(String[] args) {
        new AgentControlleur().run();
    }
    
    public static int ID() {
    	return m_ID;
    }
}
