package assignment5;

//import assignment4.Critter.TestCritter;

/*The Legendary Sensu Bean
 * When eaten, the individual has their base health recovered + 150
 * IF Knight: LVL +2
 * 
 */
public class Critter3 extends Critter {
	

	
    public static int REFRESH_SENSU__BEAN_COUNT = 1;

    
    /** 
     * @return String
     */
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
        //setEnergy(getEnergy() + Params.PHOTOSYNTHESIS_ENERGY_AMOUNT*10);
    }

    @Override
    public CritterShape viewShape() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'viewShape'");
    }
    
}
