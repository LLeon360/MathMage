/**
 * UnitVector.java
 * 
 * Allows movement to be saved
 * as a 2 dimensional unit vector
 * and does angle calculations as well
 * 
 *	 *INSPIRATION Inspired by Vectors concept from Unity Game Engine
 * 
 * @author Leon Liu and Unity
 * @since 4/8/20
 */
public class UnitVector 
{
	
	/**
	 * x and y components of the vector
	 * Uses doubles for accuracy when
	 * creating unit vectors
	 */
	private double x,y, angle;
	
	/**
	 * Sets the x and y components to
	 * given parameters
	 * Constructor for 2 dimensional vector
	 * in component notation
	 * Then converts the vector into a unit
	 * vector and calculates the angle in standard
	 * position
	 */
	public UnitVector(double x, double y)
	{
		this.x = x;
		this.y = y;
		convert();
		calcAngle();
	}
	
	/**
	 * Sets the angle of the vector
	 * given a direction and then
	 * sets the x and y components according
	 * to tangent ratios of the angle
	 */
	public UnitVector(double angle)
	{
		this.angle = angle;
		setAngle(angle);
	}

	/**
	 * Converts the vector into a unit vector. Makes sure
	 * magnitude is not zero so that division works. Unit vectors
	 * make sure that objects move at the same speed regardless of
	 * direction.
	 */
	public void convert()
	{
		double magnitude;
		magnitude = Math.sqrt(Math.pow(x,2)+Math.pow(y, 2));
		if(magnitude != 0)
		{
			x = ( x / magnitude);
			y = ( y / magnitude);
		}
	}
	
	/**
	 * Uses tangent to calculate the angle of the
	 * vector in standard position adds angles
	 * depending on quadrant because arc tangent
	 * returns acute angles
	 * Also has two hardcoded quadrantal angles
	 * because you cannot divide by zero so tangent
	 * is undefined at 90 and 270
	 */
	public void calcAngle()
	{
		if(x == 0)
		{
			if(y == 1)
			{
				angle = Math.toRadians(90);
			}
			else
			{
				angle = Math.toRadians(270);
			}
		}
		else if((x > 0 && y >= 0))
		{
			angle = Math.atan(y/x);
		}
		else if((x > 0 && y < 0))
		{
			angle = Math.toRadians(Math.toDegrees(Math.atan(y/x)) + 360);
		}
		else
		{
			angle = Math.toRadians(Math.toDegrees(Math.atan(y/x)) + 180);
		}
	}
	
	/**
	 * Getters and setters
	 */
	
	public double getAngle()
	{
		return angle;
	}
	
	/**
	 * Sets the angle and updates
	 * the x and y components to fit the angle 
	 */
	public void setAngle(double angle)
	{
		this.angle = angle;
		x = Math.cos(angle);
		y = Math.sin(angle);
	}
	
	public double getX()
	{
		return x;
	}

	public void setX(double x)
	{
		this.x = x;
	}

	public double getY()
	{
		return y;
	}

	public void setY(double y)
	{
		this.y = y;
	}
	
}
