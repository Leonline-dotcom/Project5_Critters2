package assignment5;

import assignment5.Critter.TestCritter;
import javafx.scene.paint.Color;

/*The Reaper (R)
 * Will not move from its original position
 * if it encounters anything, the opponent dies
 */
public class Critter4 extends TestCritter {

    @Override
    public void doTimeStep() {
       
    }

    @Override
    public boolean fight(String opponent) {
        return true;
    }

    @Override
    public String toString() {
        return "4";
    }

	@Override
	public CritterShape viewShape() {
		// TODO Auto-generated method stub
		return CritterShape.STAR;
	}
    
	@Override
	public Color viewColor() {
		return Color.BLACK;
	}
	
	@Override
	public Color viewOutlineColor() {
		return Color.RED;
	}
}