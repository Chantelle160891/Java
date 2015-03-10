import java.util.Comparator;



public class Journey{
	//Initializing instance variables for Journey class
	private String Dest;

	private int passengers;
	private String year;
	public double JourneyCost = 0;

	private String JID;
	private String RegNo;
	private Name N;
	
	Destination D;

	//Constructor for Journey
	public Journey(String ID, String DesName, int pass, String year)
	{
		if (ID.length() <4 || ID.length() >6)
		{
			throw new IllegalArgumentException("Registration Number" + " " + ID);
		}
		else if (pass < 1 || pass > 4)
		{
			throw new IllegalArgumentException("Passengers" + " " + pass);
		}
		RegNo = ID;
		Dest = DesName;
		passengers = pass;
		this.year = year;
	}

	//Getting and setting values

	public Journey(String jID, String rNo, String destinationName,
			Destination distance, int passengers2, float cost) 
	{
		JID = jID;
		RegNo = rNo;
		Dest = destinationName;
		D = distance;
		passengers = passengers2;
		JourneyCost = cost;
		
		
	}

	public int getPassengers()
	{
		return passengers;
	}
	public void setPassengers(int passengers)
	{
		this.passengers = passengers;
	}
	public String getYear()
	{
		return year;
	}
	public void setYear(String year)
	{
		this.year = year;
	}
	public String getDest() {
		return Dest;
	}

	public void setDest(String dest) {
		this.Dest = dest;
	}

	public String getRegNo() {
		return RegNo;
	}

	public void setRegNo(String regNo) {
		RegNo = regNo;
	}


	public double getJourneyCost(float Distance, int pass)
	{
		float dist = Distance;
		int P = pass;
		float InitialCost = 10;
		double InitFare = InitialCost + (dist * 1.6);
		if(P == 1)
		{ JourneyCost = InitFare; }
		if(P == 2)
		{ JourneyCost = InitFare + (0.05 * InitFare) ; }
		if(P == 3)
		{ JourneyCost = InitFare + (0.10 * InitFare); }
		if(P == 4)
		{ JourneyCost = InitFare + (0.15 * InitFare); }
		return JourneyCost;
	}
	//Calculating minimum cost of a journey
	public float CalcMinCost()  
	{
		int MinCost = 5;
		for (int cost = 0; cost < JourneyCost; cost++) 
		{
			if(cost < MinCost)
			{
				MinCost = cost;
			}
		}
		return MinCost;
	}


	//Get Details of Journey
	//Output - 1
	public String Details()
	{
		String Details = "\nJourney Details";
		Details += "\n:Driver License Number: " + this.getRegNo();
		Details += "\nDriver Name: " + N.GetInitials();
		Details += "\nDestination:" + this.getDest();
		Details += "\nPeople: " + getPassengers(); 
		Details += "\nCost: " + JourneyCost;
		return Details;
	}
};
	



