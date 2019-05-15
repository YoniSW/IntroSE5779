package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Plane extends FlatGeometry {
    protected Point3D P;
    protected Vector N;

    public Plane(Point3D p1, Point3D p2, Point3D p3) {
        Vector U = new Vector(p1, p2);
        Vector V = new Vector(p1, p3);
        Vector n = new Vector(U.crossProduct(V));

        n.normalize();
        n.scale(-1);

        setP(p1);
        setN(n);
    }

    public Plane(Vector n, Point3D p1) {
        super();
        n.normalize();
        n.scale(-1);

        setP(p1);
        setN(n);

    }

    public Plane(Color color, Point3D planePoint, Vector direction) {
        //later on use Color
        this(direction,planePoint);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Plane)) return false;
        if (o == null) return false;
        Plane plane = (Plane) o;
        return getP().equals(plane.getP()) &&
                getNormal(null).equals(plane.getNormal(null));
    }

    @Override
    public int hashCode() {
        return Objects.hash(getP(), getNormal(null));
    }

    public Plane(Point3D p1, Vector n) {
        this.setP(p1);
        this.setN(n);
    }

    @Override
    public Vector getNormal(Point3D point) {
        return new Vector(N);
    }

    public Point3D getP() {
        return new Point3D(P);
    }

    public void setP(Point3D p) {
        this.P = new Point3D(p);
    }

    public void setN(Vector n) {
        N = new Vector(n);
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        List<Point3D> intersectionPoints = new ArrayList<Point3D>();
        Point3D P0 = ray.getPOO();
        Point3D Q0 = this.getP();
        Vector N = this.getNormal(null);
        Vector V = ray.getDirection();

        if(N.dotProduct(V)==0)
        {
            // ray.getDirection() is parralel to the plane
            // no intersections points
            return intersectionPoints;
        }

        Vector u = new Vector(Q0, P0);
        double t = (N.dotProduct(u) * -1) / N.dotProduct(V);

        if (t >= 0) {
            V.scale(t);
            Point3D p = P0.addVector(V);
            intersectionPoints.add(p);
        }
        return intersectionPoints;
    }
}
