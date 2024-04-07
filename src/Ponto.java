public class Ponto 
{
    private int x, y;
    
    /**
     * set new values to the point (not yet used but can be usefull as in the poligono classs).
     * 
     * @author (Diogo Silva a79764)
     * @version (1.0.0 - 19/02/24)
     * @param int (X and Y)
     */
    public Ponto(int x, int y) 
    {
        this.x = x;
        this.y = y;
        if(!this.isFirstQuadrant()) Cliente.printError("Ponto:vi");
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public void setX(int x)
    {
        if(x < 0)
        {
            Cliente.printError("Ponto:vi");
        }
        this.x = x;
    }

    public void setY(int y)
    {
        if(y < 0)
        {
            Cliente.printError("Ponto:vi");
        }
        this.y = y;
    }
    /**
     * distance beetwin the point and other 
     * 
     * @author (Diogo Silva a79764)
     * @version (1.0.0 - 19/02/24)
     * @param Ponto
     * @return distance beetwin them two
     */
    int dist(Ponto p) 
    {
        int dx = x - p.x;
        int dy = y - p.y;
        return (int) Math.sqrt(dx * dx + dy * dy);
    }

    int distSquared(Ponto p) 
    {
        int dx = x - p.x;
        int dy = y - p.y;
        return dx * dx + dy * dy;
    }
    /**
     * Check X and Y to see if they are in the first square (positives)
     * 
     * @author (Diogo Silva a79764)
     * @version (1.0.0 - 19/02/24)
     * @param args
     * @return If it's in first squere = true or not.
     */
    boolean isFirstQuadrant() {
        return x >= 0 && y >= 0;
    }

    /**
     * Check if they are colinear (same equasion (y = ax + b))
     * 
     * @author (Diogo Silva a79764)
     * @version (1.0.0 - 19/02/24)
     * @param args
     * @return if they are in same equasion or not
     */
    public static boolean isColinear(Ponto p1, Ponto p2, Ponto p3) {
        return Math.abs((p2.y - p1.y) * (p3.x - p1.x) - (p3.y - p1.y) * (p2.x - p1.x)) < 1e-9;
    }
    
    /**
     * Determines the orientation (direction) of the segment formed by three points.
     * 
     * @author Diogo Silva (a79764)
     * @version 1.0.0 - 2024/02/19
     * @param p The first point of the segment.
     * @param q The second point of the segment.
     * @param r The third point of the segment.
     * @return It returns 0 if the points are colinear, 1 if they are in a clockwise
     *         orientation, and 2 if they are in a counterclockwise orientation.
     */
    public static int orient(Ponto p, Ponto q, Ponto r) {
        int result = (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y);
        if (Math.abs(result) < 1e-9) // Comparar pq pode ser colinear
            return 0;
        return (result > 0) ? -1 : 1; // Retornar -1 para orientação horária, 1 para anti-horária
    }

    /**
     * Checks if point q is in the line segment formed by points p and r.
     * 
     * @author Diogo Silva (a79764)
     * @version 1.0.0 - 2024/02/19
     * @param p The first endpoint of the line segment.
     * @param q The point to be checked.
     * @param r The second endpoint of the line segment.
     * @return true if point are in the same line
     */
    public static boolean sameSegment(Ponto p, Ponto q, Ponto r) 
    {
        if (q.x < Math.max(p.x, r.x) && q.x > Math.min(p.x, r.x) &&
                q.y < Math.max(p.y, r.y) && q.y > Math.min(p.y, r.y))
            return true;
        return false;
    }

    @Override
    public String toString() 
    {
        return "(" + x + "," + y + ")";
    }
}
