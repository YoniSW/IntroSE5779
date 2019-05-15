package elements;

import primitives.*;

import java.util.Map;

// al taatik stam kacha -- tachshov kodem !!
public class Camera {

    // Eye point of the camera
    private Point3D _P0;
    private Vector _vUp;
    private Vector _vTo;

    // Should be calculated as the cross product if vUp and vTo
    private Vector _vRight;

    // ***************** Constructors ********************** //

    public Camera(){

        _P0 = new Point3D(0, 0, 10);
        _vUp = new Vector(1.0, 0.0, 0.0);
        _vTo = new Vector(0.0, 0.0, -1.0);

        _vRight = _vTo.crossProduct(_vUp);
    }

    public Camera (Camera camera){
        _P0     = camera.getP0();
        _vUp    = camera.get_vUp();
        _vTo    = camera.get_vTo();
        _vRight = camera.get_vRight();
    }

    public Camera (Point3D P0, Vector vUp, Vector vTo){

        _P0 = new Point3D(P0);
        _vUp = new Vector(vUp);
        _vTo = new Vector(vTo);

        _vRight = new Vector(_vTo.crossProduct(_vUp));
        //for extra check we add:
        _vUp = _vTo.crossProduct(_vRight);

        _vRight.normalize();
        _vUp.normalize();
        _vTo.normalize();

    }

    public Camera (Map<String, String> attributes){

        String[] P0params = attributes
                .get("P0").split("\\s+");

        _P0 = new Point3D(Double.valueOf(P0params[0]),
                Double.valueOf(P0params[1]),
                Double.valueOf(P0params[2]));

        String[] vToParam = attributes
                .get("vTo").split("\\s+");
        _vTo = new Vector(Double.valueOf(vToParam[0]),
                Double.valueOf(vToParam[1]),
                Double.valueOf(vToParam[2]));

        String[] vUpParam =  attributes
                .get("vUp").split("\\s+");
        _vUp = new Vector(Double.valueOf(vUpParam[0]),
                Double.valueOf(vUpParam[1]),
                Double.valueOf(vUpParam[2]));

        _vRight = _vUp.crossProduct(_vTo);
        _vUp = _vTo.crossProduct(_vRight);

        _vUp.normalize();
        _vTo.normalize();
        _vRight.normalize();
    }

    // ***************** Getters/Setters ********************** //

    public Vector get_vUp()         { return new Vector(_vUp);     }
    public Vector get_vTo()         { return new Vector(_vTo); 	   }
    public Point3D getP0()          { return new Point3D(_P0);     }
    public Vector get_vRight()      { return new Vector(_vRight);  }

    public void set_vUp(Vector vUp) { this._vUp = new Vector(vUp); }
    public void set_vTo(Vector vTo) { this._vTo = new Vector(vTo); }
    public void setP0(Point3D P0)   { this._P0  = new Point3D(P0); }


    // ***************** Administration ********************** //

    public String toString(){
        return "Vto: "   + _vTo + "\n" +
                "Vup: "   + _vUp + "\n" +
                "Vright:" + _vRight + ".";
    }

    // ***************** Operations ******************** //

    public Ray constructRayThroughPixel(int Nx, int Ny, double i, double j,
                                        double screenDist, double screenWidth,
                                        double screenHeight){

        // Calculating the image center
        Vector vToward = new Vector(_vTo);
        Vector vRight = new Vector(_vRight);
        Vector vUP = new Vector(_vUp);

        vToward.normalize();
        vRight.normalize();
        vUP.normalize();

        Point3D Pc = getP0().addVector(vToward.scale(screenDist));

        // Calculating x-y ratios
        double Rx = screenWidth  / Nx;
        double Ry = screenHeight / Ny;

        // Calculating P - the intersection point
        double y_j = (j - (Ny / 2.0)) * Ry + (Ry / 2.0);
        double x_i = (i - (Nx / 2.0)) * Rx + (Rx / 2.0);

        vRight.scale(x_i);
        vUP.scale(y_j);

        Vector deltaV = vRight.subtract(vUP);

        Point3D p_ij = Pc.addVector(deltaV);

        Vector V_ij = new Vector(getP0(), p_ij);
        V_ij.normalize();
        // returning the constructed ray between P0 and the intersection point
        return new Ray(getP0(), V_ij);
    }
}
