import java.util.ArrayList;
import strategie.MoteurInference;
import strategie.Strategie;

import messaging.MessageDispatcher;


import agents.AgentManager;
import agents.UnitAgent;
import bwapi.*;
import bwta.BWTA;

public class AgentControlleur extends DefaultBWListener {
	ArrayList<UnitAgent> agents = new ArrayList<UnitAgent>();
    private Mirror mirror = new Mirror();

    private Game game;

    private Player self;
    
    private MoteurInference moteurInference = MoteurInference.GetInstance();
    private Strategie maStrategie = null;
    
    public void run() {
        mirror.getModule().setEventListener(this);
        mirror.startGame();
        
        // Update
        while(!game.isPaused() && game.isInGame()) {
        	for(UnitAgent ua : agents)
        		ua.Update();
         
            //dispatch any delayed messages
            MessageDispatcher.Instance().DispatchDelayedMessages();
        }
	}

    @Override
    public void onUnitCreate(Unit unit) {
        System.out.println("New unit " + unit.getType());
    }

    @Override
    public void onStart() {
        game = mirror.getGame();
        self = game.self();

        //Use BWTA to analyze map
        //This may take a few minutes if the map is processed first time!
        System.out.println("Analyzing map...");
        BWTA.readMap();
        BWTA.analyze();
        System.out.println("Map data ready");
        while(maStrategie == null)
        {
        	moteurInference.choixStrategie();
        	maStrategie = moteurInference.maStrategie;
        }
        
        for(Unit un : self.getUnits()) {
        	UnitAgent ua = new UnitAgent(un.getID(), un);
        	agents.add(ua);
        	AgentManager.Instance().RegisterEntity(ua);
        	ua.start();
        }
    }

    @Override
    public void onFrame() {
        game.setTextSize(10);
        game.drawTextScreen(10, 10, "Playing as " + self.getName() + " - " + self.getRace());

        StringBuilder units = new StringBuilder("My units:\n");

        //iterate through my units
        for (Unit myUnit : self.getUnits()) {
            units.append(myUnit.getType()).append(" ").append(myUnit.getTilePosition()).append("\n");

           
        }

        //draw my units on screen
        game.drawTextScreen(10, 25, units.toString());
    }

    public static void main(String[] args) {
        new AgentControlleur().run();
    }
}