package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Point3DTest {

    @Test
    void addVector() {
        Point3D p1 = new Point3D(2, 3, 4);
        Vector v = new Vector(new Point3D(0, 1, -10), new Point3D(0, 1, 2));

        Point3D result = p1.addVector(v);
        assertEquals(new Point3D(2,3,16),result);

    }

    @Test
    void subtract() {
        Point3D p1 = new Point3D(2, 3, 4);
        Vector v = new Vector(new Point3D(0, 1, 10), new Point3D(0, 1, 2));

        Point3D result = p1.subtract(v);
        assertEquals(new Point3D(2,3,12),result);

    }
}