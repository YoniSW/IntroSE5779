package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Point2DTest {

    @Test
    void toStringTest() {
        Point2D p = new Point2D(2.0,3.0);
        assertEquals("Point2D{x=2.0, y=3.0}",p.toString());
    }
}