package ru.atom.geometry;

public class Bar implements Collider {

    private Point firstCorner;
    private Point secondCorner;

    private Point topLeft;
    private Point topRight;
    private Point bottomLeft;
    private Point bottomRight;

    public Bar(int firstCornerX, int firstCornerY, int secondCornerX, int secondCornerY) {
        firstCorner = new Point(firstCornerX, firstCornerY);
        secondCorner = new Point(secondCornerX, secondCornerY);

        setCorners();
    }

    private void setCorners() {
        if (firstCorner.getY() >= secondCorner.getY()) { // top
            if (firstCorner.getX() >= secondCorner.getX()) { // topRight
                topRight = firstCorner;
                bottomLeft = secondCorner;
                topLeft = new Point(bottomLeft.getX(), topRight.getY());
                bottomRight = new Point(topRight.getX(), bottomLeft.getY());
            } else { // topLeft
                topLeft = firstCorner;
                bottomRight = secondCorner;
                topRight = new Point(bottomRight.getX(), topLeft.getY());
                bottomLeft = new Point(topLeft.getX(), bottomRight.getY());
            }
        } else { // bottom
            if (firstCorner.getX() >= secondCorner.getX()) { // bottomRight
                bottomRight = firstCorner;
                topLeft = secondCorner;
                bottomLeft = new Point(topLeft.getX(), bottomRight.getY());
                topRight = new Point(bottomRight.getX(), topLeft.getY());
            } else { // bottomLeft
                bottomLeft = firstCorner;
                topRight = secondCorner;
                bottomRight = new Point(topRight.getX(), bottomLeft.getY());
                topLeft = new Point(bottomLeft.getX(), topRight.getY());
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bar bar = (Bar) o;

        // return (firstCorner.equals(bar.firstCorner)) && (secondCorner.equals(bar.secondCorner));
        return (topLeft.equals(bar.topLeft) && topRight.equals(bar.topRight) &&
                bottomLeft.equals(bar.bottomLeft) && bottomRight.equals(bar.bottomRight));
    }

    @Override
    public boolean isColliding(Collider other) {
        if (other.getClass() == Point.class) {
            Point p = (Point) other;
            return (p.getX() >= topLeft.getX() && p.getX() <= bottomRight.getX()) &&
                    (p.getY() >= bottomLeft.getY() && p.getY() <= topLeft.getY());
        }

        if (other.getClass() == Bar.class) {
            Bar b = (Bar) other;

            // thanks geeksforgeeks.org!
            // if one bar is on the left side of other
            if (b.topLeft.getX() > bottomRight.getX() || topLeft.getX() > b.bottomRight.getX()) {
                return false;
            }

            // if one bar is above other
            if (b.topLeft.getY() < bottomRight.getY() || topLeft.getY() < b.bottomRight.getY()) {
                return false;
            }
            return true;

        } // if bar.class
        return false;
    }
}
