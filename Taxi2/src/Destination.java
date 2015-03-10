

public class Destination implements Comparable<Destination>{
	private String StartPoint;
	public String DestinationName;
	public float Distance;

	public Destination(String string) 
	{
		// TODO Auto-generated constructor stub
	}


	public Destination(String startPoint, String destinationName, Float distance) 
	{
		if (distance <1 || distance >100)
		{
			throw new IllegalArgumentException("Distance" + " " + distance);
		}
		StartPoint = startPoint;
		DestinationName = destinationName;
		Distance = distance;
	}


	public void DestinationList(String dname, float ddist)
	{
		DestinationName = dname;
		Distance = ddist;
	}
	
	public String getStartPoint()
	{
		return StartPoint;
	}
	
	public void setStartPoint(String startPoint)
	{
		StartPoint = startPoint;
	}

	public String getDestinationName() 
	{
		return DestinationName;
	}

	public void setDestinationName(String destinationName) 
	{
		DestinationName = destinationName;
	}

	public float getDistance() 
	{
		return Distance;
	}

	public void setDistance(float dDistance) 
	{
		Distance = dDistance;
	}


	public int compareTo(Destination d) {
		return DestinationName.compareTo(d.DestinationName);
	}
}
