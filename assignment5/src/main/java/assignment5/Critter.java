/*
 * CRITTERS Critter.java
 * EE422C Project 5 submission by
 * Replace <...> with your actual data.
 * <Leon Shaji>
 * <LS44376>
 * <17150>
 * <Andrew Rentz>
 * <atr983>
 * <17150>
 * Slip days used: <0>
 * Spring 2023
 */

package assignment5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Arrays;

import java.lang.Math;

/*
 * See the PDF for descriptions of the methods and fields in this
 * class.
 * You may add fields, methods or inner classes to Critter ONLY
 * if you make your additions private; no new public, protected or
 * default-package code or data can be added to Critter.
 */

public abstract class Critter {

    /* START --- NEW FOR PROJECT 5 */
    public enum CritterShape {
        CIRCLE,
        SQUARE,
        TRIANGLE,
        DIAMOND,
        STAR
    }

    /* the default color is white, which I hope makes critters invisible by default
     * If you change the background color of your View component, then update the default
     * color to be the same as you background
     *
     * critters must override at least one of the following three methods, it is not
     * proper for critters to remain invisible in the view
     *
     * If a critter only overrides the outline color, then it will look like a non-filled
     * shape, at least, that's the intent. You can edit these default methods however you
     * need to, but please preserve that intent as you implement them.
     */
    public javafx.scene.paint.Color viewColor() {
        return javafx.scene.paint.Color.WHITE;
    }

    public javafx.scene.paint.Color viewOutlineColor() {
        return viewColor();
    }

    public javafx.scene.paint.Color viewFillColor() {
        return viewColor();
    }

    public abstract CritterShape viewShape();

    protected final String look(int direction, boolean steps) {
        return "";
    }

    public static String runStats(List<Critter> critters) {
        // TODO Implement this method
        return null;
    }


    public static void displayWorld(Object pane) {
        // TODO Implement this method
    }

	/* END --- NEW FOR PROJECT 5
			rest is unchanged from Project 4 */

            private int energy;
            private int x_coord;
            private int y_coord;
            private Point position;
            private boolean ateSensuBean = false;
        
            private static String[][] grid = new String[Params.WORLD_WIDTH][Params.WORLD_HEIGHT]; 
        
            private static List<Critter> population = new ArrayList<Critter>();
            private static List<Critter> babies = new ArrayList<Critter>();
            private static ArrayList<Critter> deadList = new ArrayList<>();
            
            private static Map<Point, HashSet<Critter>> critterLocation = new HashMap<>(); //name is not exactly correct, a point with values could only contain 1 critter, thus no conflict
            private static HashSet<Point> ConflictCoords = new HashSet<>(); //this ONLY contains coords with 2 or more critters
        
    /* Gets the package name.  This assumes that Critter and its
     * subclasses are all in the same package. */
    private static String myPackage;

    static {
        myPackage = Critter.class.getPackage().toString().split(" ")[1];
    }

    private static Random rand = new Random();

    public static int getRandomInt(int max) {
        return rand.nextInt(max);
    }

    public static void setSeed(long new_seed) {
        rand = new Random(new_seed);
    }

    /**
     * create and initialize a Critter subclass.
     * critter_class_name must be the qualified name of a concrete
     * subclass of Critter, if not, an InvalidCritterException must be
     * thrown.
     *
     * @param critter_class_name
     * @throws InvalidCritterException
     */
    public static void createCritter(String critter_class_name)
            throws InvalidCritterException {
        // TODO: Modded Point class so test it out
        try{
    		Class CritterClass = Class.forName(myPackage+"."+critter_class_name);
			Critter NewCritter = (Critter) CritterClass.newInstance(); 
			NewCritter.x_coord = Critter.getRandomInt(Params.WORLD_WIDTH);
			NewCritter.y_coord = Critter.getRandomInt(Params.WORLD_HEIGHT);
            NewCritter.position = new Point(NewCritter.x_coord, NewCritter.y_coord);
			NewCritter.energy = Params.START_ENERGY;
			population.add(NewCritter);
			if(!critterLocation.containsKey(NewCritter.position)) {
			critterLocation.put(NewCritter.position, new HashSet<Critter>());
			}
			critterLocation.get(NewCritter.position).add(NewCritter);
			// TODO Auto-generated catch block
		} 
    	catch (IllegalAccessException | InstantiationException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new InvalidCritterException(critter_class_name);
		}
    	
    }

    /**
     * Gets a list of critters of a specific type.
     *
     * @param critter_class_name What kind of Critter is to be listed.
     *                           Unqualified class name.
     * @return List of Critters.
     * @throws InvalidCritterException
     */
    public static List<Critter> getInstances(String critter_class_name)
            throws InvalidCritterException {
        // TODO: Complete this method
        return null;
    }

    /**
     * Clear the world of all critters, dead and alive
     */
    public static void clearWorld() {
        clearGrid();
        population.clear();
        babies.clear();
        critterLocation.clear();
    }

    public static void worldTimeStep() {
        // TODO: Complete this method
    }

    public abstract void doTimeStep();

    public abstract boolean fight(String oponent);

    /* a one-character long string that visually depicts your critter
     * in the ASCII interface */
    public String toString() {
        return "";
    }

    protected int getEnergy() {
        return energy;
    }

    protected final void walk(int direction) {
        this.position.translate(direction, 1);
    }

    protected final void run(int direction) {
        this.position.translate(direction, 2);
    }

    protected final void reproduce(Critter offspring, int direction) {
        if(this.energy >= Params.MIN_REPRODUCE_ENERGY){
            offspring.position = new Point(this.position);
            offspring.energy = (int) Math.floorDiv(this.energy, 2) + Params.WALK_ENERGY_COST;
            offspring.walk(direction);
            babies.add(offspring);
            this.energy = (int)Math.ceil((double)this.energy/(double)2); //modified this line, ceilDiv not in Math or something like that
        }
    }

    /**
     * The TestCritter class allows some critters to "cheat". If you
     * want to create tests of your Critter model, you can create
     * subclasses of this class and then use the setter functions
     * contained here.
     * <p>
     * NOTE: you must make sure that the setter functions work with
     * your implementation of Critter. That means, if you're recording
     * the positions of your critters using some sort of external grid
     * or some other data structure in addition to the x_coord and
     * y_coord functions, then you MUST update these setter functions
     * so that they correctly update your grid/data structure.
     */
    static abstract class TestCritter extends Critter {

        protected void setEnergy(int new_energy_value) {
            super.energy = new_energy_value;
        }

        protected void setX_coord(int new_x_coord) {
            super.x_coord = new_x_coord;
        }

        protected void setY_coord(int new_y_coord) {
            super.y_coord = new_y_coord;
        }

        protected int getX_coord() {
            return super.x_coord;
        }

        protected int getY_coord() {
            return super.y_coord;
        }

        /**
         * This method getPopulation has to be modified by you if you
         * are not using the population ArrayList that has been
         * provided in the starter code.  In any case, it has to be
         * implemented for grading tests to work.
         */
        protected static List<Critter> getPopulation() {
            return population;
        }

        /**
         * This method getBabies has to be modified by you if you are
         * not using the babies ArrayList that has been provided in
         * the starter code.  In any case, it has to be implemented
         * for grading tests to work.  Babies should be added to the
         * general population at either the beginning OR the end of
         * every timestep.
         */
        protected static List<Critter> getBabies() {
            return babies;
        }
    }

    private static void clearGrid(){
        for( String[] row : grid){
            Arrays.fill(row, " ");
        }

    }
}
