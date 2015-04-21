package ia;

public abstract class Strategie {

	protected String nom;

	public abstract void run();

	@Override
	public String toString(){ 
		return this.nom;	 
	}


}
