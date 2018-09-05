package no.hvl.dat108.oppgave2;

/**
 * Defines a point in a 2D Euclidean space.
 *
 * @author Atle Geitung
 * @version 17.05.2018 C++ version
 * @version 30.08.2018 Java version
 */
public class Point {
    private final Double x;
    private final Double y;

    /**
     * Constructs a new point.
     */
    public Point() {
        x = 0.;
        y = 0.;
    }

    /**
     * Constructs a new point.
     * 
     * @param x
     * @param y
     */
    public Point(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return the x
     */
    public Double getX() {
        return x;
    }

    /**
     * @return the y
     */
    public Double getY() {
        return y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

}

