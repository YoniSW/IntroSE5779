package primitives;

import java.util.Objects;

import static java.lang.StrictMath.sqrt;

public class Point3D extends Point2D {

    public static final Point3D ZERO = new Point3D();

    private Coordinate z;

    public Point3D(double x, double y, double z) {
        setX(new Coordinate(x));
        setY(new Coordinate(y));
        setZ(new Coordinate(z));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Point3D)) return false;
        if (!super.equals(o)) return false;
        Point3D point3D = (Point3D) o;
        return getZ().equals(point3D.getZ());
    }

    public Vector substract(Point3D pt) {
        return new Vector(pt, this);
    }

    public double distance3D(Point3D other) {
        double zz = Util.uscale((other.getZ().get() - getZ().get()), (other.getZ().get() - getZ().get()));
        double xx_yy_zz = Util.uadd(this.xySquare(other), zz);

        return sqrt(xx_yy_zz);
    }


    public Point3D addVector(Vector v) {
        Point3D p_vec = v.getHead();

        Point3D result = new Point3D(
                this.getX().add(p_vec.getX()),
                this.getY().add(p_vec.getY()),
                this.getZ().add(p_vec.getZ()));
        return result;
    }

    public Point3D subtract(Vector v) {
        Point3D p_vec = v.getHead();

        Point3D result = new Point3D(
                this.getX().subtract(p_vec.getX()),
                this.getY().subtract(p_vec.getY()),
                this.getZ().subtract(p_vec.getZ()));

        return result;
    }

    @Override
    public String toString() {
        return "Point3D{" +
                "x=" + super.getX() +
                ", y=" + super.getY() +
                ", z=" + getZ() +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getZ());
    }

    public Point3D() {
        this.setZ(Coordinate.ZERO);
    }

    public Point3D(Point3D other) {
        super();
        this.setX(other.getX());
        this.setY(other.getY());
        this.setZ(other.getZ());
    }

    public Point3D(Coordinate x, Coordinate y, Coordinate z) {
        super(x, y);
        this.setZ(z);
    }

    public Coordinate getZ() {
        return new Coordinate(z.get());
    }

    public void setZ(Coordinate z) {
        this.z = new Coordinate(z);
    }
}
