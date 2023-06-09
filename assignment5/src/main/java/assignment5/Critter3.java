package assignment5;

import assignment5.Critter.TestCritter;
import javafx.scene.paint.Color;

/*The Legendary Sensu Bean
 * When eaten, the individual has their base health recovered + 150
 * IF Knight: LVL +2
 * 
 */
public class Critter3 extends TestCritter {
	
	private int newEnergyDiff;
	
    public static int REFRESH_SENSU__BEAN_COUNT = 1;

    public String toString() {
        return "3";
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

	@Override
	public CritterShape viewShape() {
		// TODO Auto-generated method stub
		return CritterShape.DIAMOND;
	}
	
	@Override
	public Color viewColor() {
		return Color.BLUEVIOLET;
	}
    
}
