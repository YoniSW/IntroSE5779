package primitives;

/**
 * Coordinate for Graphics
 */
public final class Coordinate {
	//private static final double EPSILON = 0.0000001;
	protected double _coord;

	public static Coordinate ZERO = new Coordinate(0.0);
	
	/********** Constructors ***********/
    /**
     *
     * @param coord dooble value for coordinate
     */
	public Coordinate(double coord) {
		// if it too close to zero make it zero
		_coord = Util.alignZero(coord);
	}

    /**
     * Deep Clone Constructor
     * @param other
     */
	public Coordinate(Coordinate other) {
		_coord = other._coord;
	}

	/************** Getters/Setters *******/
	public double get() {
		return _coord;
	}

	/*************** Admin *****************/
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (!(obj instanceof Coordinate)) return false;
		return Util.usubtract(_coord, ((Coordinate)obj)._coord) == 0.0;
	}

	@Override
	public String toString() {
		return "" + _coord;
	}

	/************** Operations ***************/
	public Coordinate subtract(Coordinate other) {
		return new Coordinate(Util.usubtract(_coord, other._coord));
	}

	public Coordinate add(Coordinate other) {
		return new Coordinate(Util.uadd(_coord, other._coord));
	}

    /**
     *
     * @param num
     * @return new updated Coordinate
     */
	public Coordinate scale(double num) {
		return new Coordinate(Util.uscale(_coord, num));
	}
	
	public Coordinate multiply(Coordinate other) {
		return new Coordinate(Util.uscale(_coord, other._coord));
	}
	
}
