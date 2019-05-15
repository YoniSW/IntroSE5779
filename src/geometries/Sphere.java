package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Sphere  extends RadialGeometry{

    private Point3D center;

    public Sphere(){
        super(0.0);
        setCenter(new Point3D());
    }

    public Sphere (Sphere sphere){
        super(sphere.getRadius());
        setCenter(sphere.getCenter());
    }

    public Sphere(double radius, Point3D center){
        super(radius);
        setCenter(center);
    }

    public Sphere(Point3D center, double radius) {
        this(radius, center);
    }

    public Sphere(Color color, double r, Point3D centerPoint) {
        this(r, centerPoint);
        super.set_emmission_awtColor(color);
    }

    public Sphere(Map<String, String> sphereAttributes) {
        //        radius = Double.valueOf(sphereAttributes.get("radius"));
        super(Double.valueOf(sphereAttributes.get("radius")));

        String[] centerPoints = sphereAttributes
                .get("center").split("\\s+");

        center = new Point3D(Double.valueOf(centerPoints[0]),
                Double.valueOf(centerPoints[1]),
                Double.valueOf(centerPoints[2]));

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Sphere)) return false;
        Sphere sphere = (Sphere) o;
        return getCenter().equals(sphere.getCenter());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCenter());
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "center=" + getCenter() +
                '}';
    }
  @Override
    public Vector getNormal(Point3D point) {
        Vector N = new Vector(center, point);
        N.normalize();
        return N;

    }

    public Point3D getCenter() {
        return new Point3D(center);
    }

    public void setCenter(Point3D center) {
        this.center = new Point3D(center);
    }

    // TO DO
    //replace pow and other arithmetic fubction with Util methods

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        List<Point3D> intersectionPoints= new ArrayList<Point3D>(2);

        Vector u = new Vector(ray.getPOO(), this.getCenter());
        double tm = u.dotProduct(ray.getDirection());
        double d = Math.sqrt((u.length()*u.length()) - (tm*tm));

        if (d > this.getRadius())
            return intersectionPoints; // return null;

        double th = Math.sqrt((this.getRadius()*this.getRadius()) - (d*d));

        double t1 = tm - th;
        double t2 = tm + th;

        if (t1 >= 0){
            Vector V = ray.getDirection();
            V.scale(t1);
            Point3D p = ray.getPOO();
            Point3D P1 =p.addVector(V);
            intersectionPoints.add(P1);
        }

        if (t2 >= 0){
            Vector V = ray.getDirection();
            V.scale(t2);
            Point3D p = ray.getPOO();
            Point3D P2 =p.addVector(V);
            intersectionPoints.add(P2);
        }

        return intersectionPoints;
    }
}
