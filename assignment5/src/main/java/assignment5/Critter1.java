package assignment5;
/*The Dragon class (D)
 
 * Starting Health (Energy) is 400
 * Reproduction needs 200 Health
 * Rest takes 10 Health
 * FIGHT 
 * they have a 10/10 chance in winning against goblins
 * IF they are fighting  another dragon its 50/50
 * IF they fight a Knight Lvl: 
 * 0-4 = 100,90,80,70,60
 * 5-10 = 50, 40, 30, 20, 10, 0%
 * 
 * 
 */

import javafx.scene.paint.Color;

public class Critter1 extends Critter {

    @Override
    public void doTimeStep() {
        int chance =Critter.getRandomInt(11);
        int direction = Critter.getRandomInt(8);
        String useless = this.look(direction, false);
        if(this.getEnergy() >= Params.MIN_REPRODUCE_ENERGY && (chance <= 4)) {
    		//Critter baby = new Critter1();
    	//	reproduce(baby, Critter.getRandomInt(8));
    	}
    	else if(chance > 4 ){ 
    		
                   // run(direction);
                    run(direction);
                    }
    	else {
    		walk(direction);
    	} 
    }

    @Override
    public boolean fight(String opponent) {
        return true;
    }

    public boolean Encounter(Critter enemy) {
    	if(enemy.toString() == "2") {
    		int val = 100 - ((Critter2)enemy).getPower()*10;
    		return(Critter.getRandomInt(101) > val);
    	}
    	else if(enemy.toString() == "1") {
    		int val = Critter.getRandomInt(2);
    		return(val > 0);
    	}
    	return true;
    }
    
    
    
    @Override
    public String toString() {
        return "1";
    }

	@Override
	public CritterShape viewShape() {
		// TODO Auto-generated method stub
		return CritterShape.DIAMOND;
	}
	
	@Override
	public Color viewColor() {
		return Color.RED;
	}
    
	@Override
	public Color viewOutlineColor() {
		return Color.BLACK;
	}
}