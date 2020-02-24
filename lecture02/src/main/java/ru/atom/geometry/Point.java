package ru.atom.geometry;

/**
 * Template class for
 */
public class Point implements Collider {
    private int X;
    private int Y;

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    public Point(int x, int y) {
        X = x;
        Y = y;
    }
    // fields
    // and methods

    /**
     * @param o - other object to check equality with
     * @return true if two points are equal and not null.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        // cast from Object to Point
        Point point = (Point) o;

        // your code here
        return (point.X == this.X) && (point.Y == this.Y);
    }

    @Override
    public boolean isColliding(Collider other) {
        if (other.equals(this)) {
            return true;
        }
        return false;
    }
}
