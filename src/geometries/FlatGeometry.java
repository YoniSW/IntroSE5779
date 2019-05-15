package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

public abstract class FlatGeometry extends Geometry {
    @Override
    public abstract List<Point3D> findIntersections(Ray ray);

    @Override
    public abstract Vector getNormal(Point3D point);

}
