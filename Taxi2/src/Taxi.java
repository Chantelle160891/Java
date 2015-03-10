

public class Taxi implements Comparable<Taxi> {
	String RegNumber;
	Name DriverName;
	
	public Taxi(String RN, Name DN)
	{
		if (RN.length() <4 || RN.length() >6)
		{
			throw new IllegalArgumentException("Registration Number" + " " + RN);
		}
		RegNumber = RN;
		DriverName = DN;
	}
	
	public String GetRegNo()
	{
		return RegNumber;
	}
	
	public void SetRegNo(String RN)
	{
		RegNumber = RN;
	}
	
	public Name getDriverName()
	{
		return DriverName;
	}

	public int compareTo(Taxi t) {
			return RegNumber.compareTo(t.RegNumber);
	}
}
