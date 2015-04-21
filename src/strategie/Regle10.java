/**
 * Definit strategie HitAndRun
 */

package strategie;

public class Regle10 extends Regle{

	public Regle10() {
		super(10);
		requis.add(new Condition(2, true));
		requis.add(new Condition(4, true));
	}
	
	@Override
	public boolean Activate()
	{
		Strategie HitAndRun = new HitAndRun();
		MoteurInference moteur = MoteurInference.GetInstance();
		moteur.maStrategie = HitAndRun;
		return true;
	}
	

}
