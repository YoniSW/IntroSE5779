package geometries;

import java.util.List;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class Cylinder extends RadialGeometry {

    private Point3D _axisPoint;
    private Vector _axisDirection;

    // ***************** Constructors ********************** //

    public Cylinder(){
        super(0.0);
        _axisPoint = new Point3D();
        _axisDirection = new Vector();
    }

    public Cylinder(Cylinder cylinder){
        super(cylinder.radius);
        _axisPoint = cylinder.getAxisPoint();
        _axisDirection = cylinder.getAxisDirection();
    }

    public Cylinder(double radius, Point3D axisPoint, Vector axisDirection){
        super(radius);
        _axisPoint = new Point3D(axisPoint);
        _axisDirection = new Vector(axisDirection);
    }

    // ***************** Getters/Setters ********************** //

    public Point3D getAxisPoint()    { return new Point3D(_axisPoint);	  }
    public Vector getAxisDirection() { return new Vector(_axisDirection); }

    public void setAxisPoint(Point3D axisPoint)        { this._axisPoint = new Point3D(axisPoint); }
    public void setAxisDirection(Vector axisDirection) { this._axisDirection = new Vector(axisDirection); }


    // ***************** Operations ******************** //

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        // To be implemented
        return null;
    }


    /**
     *
     * @param point but be on the circumference of the cylinder
     * @return Normal to the cylinder
     */
    @Override
    public Vector getNormal(Point3D point) {
        Point3D P = new Point3D(point);
        Point3D P0 = new Point3D(_axisPoint);
        Vector v = this.getAxisDirection();
        v.normalize();

        double t= v.dotProduct(P.substract(P0));
        v.scale(t);
        Point3D P1 = P0.addVector(v);

        Vector N = P.substract(P1);
        N.normalize();
        return N;
    }

}