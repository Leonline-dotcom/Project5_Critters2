package assignment5;
/*The knight class (K)
 * They start with 200 Health
 * They have a Level System: Min=0  Max = 10
 * goblins are worth +1
 * Dragons are worth +3
 */
public class Critter2 extends Critter {
	
	public int newEnergyDiff;
    private int PowerLevel;
	

    @Override
    public void doTimeStep() {
    	if(this.getEnergy() >= Params.MIN_REPRODUCE_ENERGY && Critter.getRandomInt(11) < 4) {
    		Critter baby = new Critter2();
    		reproduce(baby, Critter.getRandomInt(8)); 
    	}
    	else if(Critter.getRandomInt(11) < 5){ walk(Critter.getRandomInt(8));}
    	else {newEnergyDiff = -Params.REST_ENERGY_COST;} // not needed?
    }

    @Override
    public boolean fight(String opponent) {
    	if(this.getEnergy() < 100 && opponent == "1") {
    		//run(Critter.getRandomInt(8));
    		return false;
    	}
    	else
        return true;
    }
    
    public boolean Encounter(Critter enemy) {
    	if(enemy.toString() == "1") {   //fighting a dragon, wins based on his level
    		int val = 100 - PowerLevel*10;
    		return(Critter.getRandomInt(101) < val);
    	}
    	else if(enemy.toString() == "2") {
    		return(this.PowerLevel >= ((Critter2)enemy).PowerLevel); //if fighting a knight, whoever is higher wins
    	}
		else{
			return true;
		}
    }
    
    public int getPower() {
    	return PowerLevel;
    }
    
    public void addPower(int num) {
    	
    	PowerLevel += num;
    	while(PowerLevel > 10) {
    		PowerLevel -= 1;
    	}
    }

    @Override
    public String toString() {
        return "2";
    }

	@Override
	public CritterShape viewShape() {
		// TODO Auto-generated method stub
		return CritterShape.SQUARE;
	}
}