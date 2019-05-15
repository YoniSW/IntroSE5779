package primitives;

import java.util.Objects;

import static java.lang.StrictMath.sqrt;

public class Vector {
    private Point3D head;

    public Vector(Point3D head) {
        if (head.equals(Point3D.ZERO)) {
            throw new IllegalArgumentException("not explicit Point(0,0,0) allowed");
        }
        this.setHead(head);
    }

    public Vector() {
        this.setHead(new Point3D());
    }

    public Vector(Point3D pt1, Point3D pt2) {

        if (pt1.equals(pt2)) {
            throw new IllegalArgumentException("pt1 == pt2 !!! nu nu nu");
        }
        setHead(new Point3D(
                pt2.getX().subtract(pt1.getX()),
                pt2.getY().subtract(pt1.getY()),
                pt2.getZ().subtract(pt1.getZ())));
    }

    public Vector(Vector direction) {
        setHead(direction.getHead());
    }

    public Vector(double x, double y, double z) {
        this(new Point3D(x, y, z));
    }

    //public void scale(double scalingFactor) {
    public Vector scale(double scalingFactor) {
        this.head.setX(head.getX().scale(scalingFactor));
        this.head.setY(head.getY().scale(scalingFactor));
        this.head.setZ(head.getZ().scale(scalingFactor));

        return this;
    }

    public void add(Vector vector) {
        this.head = this.head.addVector(vector);
    }

    //public void subtract(Vector vector) {
    public Vector subtract(Vector vector) {
        this.head = this.head.subtract(vector);
        return this;
    }

    public Vector crossProduct(Vector vector) {

        double x1 = this.getHead().getX().get();
        double y1 = this.getHead().getY().get();
        double z1 = this.getHead().getZ().get();

        double x2 = vector.getHead().getX().get();
        double y2 = vector.getHead().getY().get();
        double z2 = vector.getHead().getZ().get();

        Point3D pt = new Point3D(
                new Coordinate(Util.usubtract(Util.uscale(y1, z2), Util.uscale(z1, y2))),
                new Coordinate(Util.usubtract(Util.uscale(z1, x2), Util.uscale(x1, z2))),
                new Coordinate(Util.usubtract(Util.uscale(x1, y2), Util.uscale(y1, x2))));
        if (pt.equals(Point3D.ZERO)) {
            return new Vector();
        }
        return new Vector(pt);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Vector)) return false;
        Vector vector = (Vector) o;
        return getHead().equals(vector.getHead());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getHead());
    }

    @Override
    public String toString() {
        return "Vector{" +
                "head=" + getHead().toString() +
                '}';
    }

    public Point3D getHead() {
        return new Point3D(head);
    }

    public void setHead(Point3D head) {
        this.head = new Point3D(head);
    }

    public void normalize() {
        double x = this.getHead().getX().get();
        double y = this.getHead().getY().get();
        double z = this.getHead().getZ().get();

        double length = this.length();

        if (length == 0)
            throw new IllegalArgumentException("length = 0 -- cannot normalize");

        this.setHead(new Point3D(
                new Coordinate(x / length),
                new Coordinate(y / length),
                new Coordinate(z / length)));
    }

    public double dotProduct(Vector vector) {

        double x1 = this.getHead().getX().get();
        double y1 = this.getHead().getY().get();
        double z1 = this.getHead().getZ().get();

        double x2 = vector.getHead().getX().get();
        double y2 = vector.getHead().getY().get();
        double z2 = vector.getHead().getZ().get();

        return Util.uadd(
                Util.uadd(
                        Util.uscale(x1, x2),
                        Util.uscale(y1, y2)),
                Util.uscale(z1, z2));

    }

    public double length() {
        double x = this.getHead().getX().get();
        double y = this.getHead().getY().get();
        double z = this.getHead().getZ().get();

        return sqrt(
                Util.uadd(
                        Util.uadd(
                                Util.uscale(x, x),
                                Util.uscale(y, y)),
                        Util.uscale(z, z)));

    }
}
