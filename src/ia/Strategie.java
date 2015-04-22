package ia;

/**
 * Strat�gie appliqu� par les unit�s
 *
 */

public abstract class Strategie {
	// Nom de la strat�gie
	protected String nom;

	// Fonction d'�xecution de la strat�gie
	public abstract void run();

	@Override
	public String toString(){ 
		return this.nom;	 
	}


}
