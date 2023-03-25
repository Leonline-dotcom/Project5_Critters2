package assignment4;

import assignment4.Critter.TestCritter;

/*The Legendary Sensu Bean
 * When eaten, the individual has their base health recovered + 150
 * IF Knight: LVL +2
 * 
 */
public class Critter3 extends TestCritter {
	
	private int newEnergyDiff;
	
    public static int REFRESH_SENSU__BEAN_COUNT = 1;

    public String toString() {
        return "4";
    }

    public boolean fight(String enemy) {
        if( enemy == "@"){
            return true;
        }
        
        return false;
    }

    public void doTimeStep() {
        setEnergy(getEnergy() + Params.PHOTOSYNTHESIS_ENERGY_AMOUNT*10);
    }
    
}
