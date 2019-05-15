package geometries;

import primitives.*;

import java.util.List;

public abstract class Geometry implements IGeometry {
    private Material _material = new Material();
    private double _nShininess = 1;
    private Color _emmission = new Color(java.awt.Color.BLACK);

    public void set_emmission_Color(Color _emmission) {
        this._emmission = new Color(_emmission);
    }

    public void set_emmission_awtColor(java.awt.Color _emmission) {
        this._emmission = new Color(_emmission);
    }

    public void setShininess(double n) {
        _nShininess = n;
    }

    public void setMaterial(Material _material) {
        this._material = new Material(_material);
    }

    public void setEmmission(Color emmission) {
        this._emmission = new Color(_emmission);
    }

    public Color getEmmission() {
        return new Color(_emmission);
    }

    public Material getMaterial() {
        return new Material(_material);
    }

    public double getShininess() {
        return _nShininess;
    }


    public void setKs(double ks) {
        _material.setKs(ks);
    }

    public void setKd(double kd) {
        _material.setKd(kd);
    }

    public void setKr(double Kr) {
        _material.setKr(Kr);
    }

    public void setKt(double Kt) {
        _material.setKt(Kt);
    }

    @Override
    public abstract List<Point3D> findIntersections(Ray ray);

    @Override
    public abstract Vector getNormal(Point3D point);


}
