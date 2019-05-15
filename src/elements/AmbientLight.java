package elements;

import java.awt.Color;
import java.util.Map;

public class AmbientLight extends Light {

    private double _Ka = 0.3;

    // ***************** Constructors ********************** //

    public AmbientLight() {
        super(Color.WHITE);
    }

    public AmbientLight(AmbientLight aLight) {
        super(aLight._color);
    }

    public AmbientLight(int r, int g, int b) {
        super(new Color(r, g, b));
    }

    public AmbientLight(Map<String, String> attributes) {
        String[] ambientLightColors = attributes
                .get("color").split("\\s+");
        _color = new Color((int) (255 * Double.valueOf(ambientLightColors[0])),
                (int) (255 * Double.valueOf(ambientLightColors[1])),
                (int) (255 * Double.valueOf(ambientLightColors[2])));
    }

    public AmbientLight(int red, int green, int blue, double k) {
        this(red, green, blue);
        _Ka = k;
    }

    // ***************** Getters/Setters ********************** //

    public Color getColor() {
        return _color;
    }

    public void setColor(Color color) {
        _color = color;
    }

    public double getKa() {
        return _Ka;
    }

    public Color getIntensity() {
//        return new Color((int) (_color.getRed() * _Ka),
//                (int) (_color.getGreen() * _Ka),
//                (int) (_color.getBlue() * _Ka));
        float kaVal = (float)_Ka;
        return new Color(kaVal,kaVal,kaVal);
    }

}
