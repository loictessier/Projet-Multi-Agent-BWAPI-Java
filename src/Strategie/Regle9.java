package Strategie;

public class Regle9 extends Regle{

	public Regle9() {
		super(9);
		requis.add(new Condition(1, true));
		requis.add(new Condition(3, true));
	}
	
	@Override
	public boolean Activate()
	{
		Strategie RetreatAndStand = new RetreatAndStand();
		return true;
	}

}
