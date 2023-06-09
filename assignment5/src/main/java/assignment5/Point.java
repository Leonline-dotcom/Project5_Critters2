package assignment5;


public class Point {
    private int x,y;
    public static Point[][] pointGrid = new Point[Params.WORLD_WIDTH][Params.WORLD_HEIGHT];
    
    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Point(Point p){
        this.x = p.getX();
        this.y = p.gety();
    }

    public int getX(){
        return x;
    }

    public int gety(){
        return y;
    }
    
    @Override
    public boolean equals(Object o) {
    	System.out.println("tried equals");
    	if(o == this) {return true;}
    	if(!(o instanceof Point)) {return false;}
    	
    	Point point = (Point) o;
    	
    	if((point.x == this.x) && (point.y == this.y)){return true;}
    	return false;
    	
    }

    public void translate(int direction, int stepAmount){
    	
        switch (direction) {
            case 0:
                x += stepAmount;
                break;
            case 1:
                x += stepAmount;
                y += stepAmount;
                break;
            case 2:
                y += stepAmount;
                break;
            case 3:
                x -= stepAmount;
                y += stepAmount;
                break;
            case 4:
                x -= stepAmount;
                break;
            case 5:
                x -= stepAmount;
                y -= stepAmount;
                break;
            case 6:
                y -= stepAmount;
                break;
            case 7:
                x += stepAmount;
                y -= stepAmount;
                break;

            default:
                break;
        }
        if(x < 0) {
        	x = Params.WORLD_WIDTH + x;
        }
        else {
        x = Math.abs(x % (Params.WORLD_WIDTH));
        }
        if(y < 0) {
        	y = Params.WORLD_HEIGHT + y;
        }
        else {
        y = Math.abs(y % (Params.WORLD_HEIGHT));
        }

    }

}
