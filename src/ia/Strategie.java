package ia;

/**
 * Stratégie appliqué par les unités
 *
 */

public abstract class Strategie {
	// Nom de la stratégie
	protected String nom;

	// Fonction d'éxecution de la stratégie
	public abstract void run();

	@Override
	public String toString(){ 
		return this.nom;	 
	}


}
