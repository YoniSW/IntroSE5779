package primitives;

import java.util.Objects;

import static java.lang.StrictMath.sqrt;

public class Point2D {
    protected Coordinate x;
    protected Coordinate y;

    public Point2D() {
        setX(Coordinate.ZERO);
        setY(Coordinate.ZERO);
    }

    public Point2D(Coordinate x, Coordinate y) {
        this.setX(x);
        this.setY(y);
    }

    public Point2D(double db_x, double db_y) {
        this(new Coordinate(db_x),new Coordinate(db_y));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o== null) return false;
        if (!(o instanceof Point2D)) return false;
        Point2D point2D = (Point2D) o;
        return getX().equals(point2D.getX()) &&
                getY().equals(point2D.getY());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }

    public double xySquare(Point2D other) {
        double xx = Util.uscale(
                Util.usubtract(other.getX().get(), getX().get()),
                Util.usubtract(other.getX().get(), getX().get()));
        double yy = Util.uscale(
                Util.usubtract(other.getY().get(), getY().get()),
                Util.usubtract(other.getY().get(), getY().get()));

        return xx + yy;
    }

    public double distance2D(Point2D other)
    {
        return sqrt(xySquare(other));
    }

    @Override
    public String toString() {
        //return "(" + _x.getX() + " , " + _y.getY() + ")" ;
        return "Point2D{" +
                "x=" + getX() +
                ", y=" + getY() +
                '}';
    }
    public Point2D(Point2D other) {
        this.setX(other.getX());
        this.setY(other.getY());
    }


    public Coordinate getX() {
        return new Coordinate(x.get());
    }
    public Coordinate getY() {
        return new Coordinate(y.get());
    }

    public void setX(Coordinate x) {
        this.x = new Coordinate(x);
    }

    public void setY(Coordinate y) {
        this.y = new Coordinate(y);
    }
}
