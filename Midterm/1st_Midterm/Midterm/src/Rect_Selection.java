
public class Rect_Selection {

    public final int x1, y1, x2, y2;


    public Rect_Selection(int x1, int y1, int x2, int y2) {
        this.x1 = Math.min(x1, x2);
        this.y1 = Math.min(y1, y2);
        this.x2 = Math.max(x1, x2);
        this.y2 = Math.max(y1, y2);
    }

    public int getWidth()  { return x2 - x1; }
    public int getHeight() { return y2 - y1; }


    /**
    * Returns the rectangle coordinates in a readable format:
    * (x1,y1) -> (x2,y2)
    * Useful for debugging.
    */
    @Override
    public String toString() {
        return String.format("(%d,%d) -> (%d,%d)", x1, y1, x2, y2); 
    }
}