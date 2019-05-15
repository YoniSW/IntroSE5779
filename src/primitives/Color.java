package primitives;

public class Color {
    java.awt.Color _color;

    public Color() {
        //new java.awt.Color(255, 255, 255)
        _color = java.awt.Color.WHITE;
    }

    public Color(Color color) {
        setColor(color.getColor());
    }

    public Color(java.awt.Color color) {
        setColor(color);
    }


    public Color(double r, double g, double b) {
        setColor(r, g, b);
    }

    public Color(int r, int g, int b) {
        setColor(r, g, b);
    }

    public Color(int r, int g, int b, int a) {
        setColor(r, g, b, a);
    }

    private void setColor(int r, int g, int b, int a) {
        this._color = new java.awt.Color(r, g, b, a);
    }


    /**
     * @param r percenteage of RED   in RGB  0.0..1.0
     * @param g percenteage of GREEN in RGB  0.0..1.0
     * @param b percenteage of BLUE  in RGB  0.0..1.0
     */
    public void setColor(double r, double g, double b) {
        this._color = new java.awt.Color((float) r, (float) g, (float) b);
    }

    /**
     * @param r value for RED   between 0..255
     * @param g value for GREEN between 0..255
     * @param b value for BLUE  between 0..255
     */
    public void setColor(int r, int g, int b) {
        this._color = new java.awt.Color(r, g, b);
    }

    public Color scale(double k) {
        if (k < 0)
            throw new IllegalArgumentException(
                    "Can't scale a color by a negative number");
        double r = _color.getRed() / 255.0 * k;
        double g = _color.getGreen() / 255.0 * k;
        double b = _color.getBlue() / 255.0 * k;

        return new Color(r, g, b);
    }

    public java.awt.Color getColor() {
        return new java.awt.Color(_color.getRGB());
    }

    public void setColor(Color color) {
        this._color = color.getColor();
    }

    private void setColor(java.awt.Color color) {
        this._color = new java.awt.Color(color.getRGB());
    }

    /*   public static java.awt.Color addColors(java.awt.Color... colors) {
           int R = 0, G = 0, B = 0;

           for (java.awt.Color c : colors) {
               R += c.getRed();
               G += c.getGreen();
               B += c.getBlue();
           }
           if (R > 255) R = 255;
           if (G > 255) G = 255;
           if (B > 255) B = 255;

           java.awt.Color I = new java.awt.Color(R, G, B);

           return I;
       }
   */
    public static Color blend(java.awt.Color c0, java.awt.Color c1) {
        double totalAlpha = c0.getAlpha() + c1.getAlpha();

        double weight0 = c0.getAlpha() / totalAlpha;
        double weight1 = c1.getAlpha() / totalAlpha;

        double r = weight0 * c0.getRed() + weight1 * c1.getRed();
        double g = weight0 * c0.getGreen() + weight1 * c1.getGreen();
        double b = weight0 * c0.getBlue() + weight1 * c1.getBlue();

        double a = Math.max(c0.getAlpha(), c1.getAlpha());

        return new Color((int) r, (int) g, (int) b, (int) a);
    }
}

