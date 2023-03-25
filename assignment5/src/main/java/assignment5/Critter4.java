package assignment4;

import assignment4.Critter.TestCritter;

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
    
}