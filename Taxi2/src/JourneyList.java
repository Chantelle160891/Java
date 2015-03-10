import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;



public class JourneyList {

	private ArrayList <Journey> journeyList;    	// ArrayList for storing Journeylist
	private File file,file2,file3;
	//TreeMap<String, Double> sfares = new TreeMap<String, Double>();
	Map<Integer, Double> sfares = new HashMap<Integer, Double>();
	String [][] j2 = new String[15][15]; // taking the exact file dimension
	int i=0;

	private ArrayList<Journey> jo = new ArrayList<Journey>();
	
	String temp = new String();	
	Journey j;
	DestinationList Dest;
	List<Destination> destination = new ArrayList<Destination>();
	Iterator<Destination> dest;
	TaxiList Taxi;

	public JourneyList(TaxiList t,DestinationList D) throws FileNotFoundException {				
		journeyList = new ArrayList<Journey>();
		Dest = D;
		Taxi = t;
		this.ReadJourneyFile();
	}  

	public void addJourney(Journey J){
		journeyList.add(J);
	}

	public ArrayList<Journey> getAllJourneys(){
		return journeyList;
	}
	public int getSize(){      //Size of the journey list
		return journeyList.size();
	}


	public void printJourney2015()
	{
		float distance = 0;
		double fare = 0;
		temp = String.format("%-10s", "Journey ID") + "\t" + 
				String.format("%-10s", "Reg No.") + "\t"
				+ String.format("%-20s", "Destination") + "\t"
				+ String.format("%-10s", "Distance") + "\t"
				+ String.format("%-10s", "Passengers") + "\t"
				+ String.format("%-10s", "Cost (in AED)") + "\n";

		temp += String.format("%-10s", "------") + "\t" + 
				String.format("%-10s", "-------") + "\t"
				+ String.format("%-20s", "------------") + "\t"
				+ String.format("%-10s", "--------") + "\t"
				+ String.format("%-10s", "----------") + "\t"
				+ String.format("%-10s", "-----") + "\n";
		int JID = 1001;
		for(Journey j : journeyList)
		{
			for(Destination d : Dest.GetAllDestinations())
			{
				if(j.getDest().equals(d.DestinationName))
					distance= d.Distance;
			}
			fare = j.getJourneyCost(distance, j.getPassengers());
			
			sfares.put(JID, fare);
			temp += String.format("%-10s", JID)+ "\t" + 
					String.format("%-10s", j.getRegNo()) + "\t"
					+ String.format("%-20s", j.getDest()) + "\t"
					+ String.format("%-10s", distance) + "\t" 
					+ String.format("%-10s", j.getPassengers()) + "\t" +  
					String.format("%.2f",fare) + "\t" + "\n";
			JID += 1;
			
		}
		//System.out.println(temp);
		WriteToFile("Journey Report.txt", temp);
	}

	public void printJourney2014()
	{
		String report = String.format("%-30s", "Destination Name") + 
				String.format("%-10s", "Year") + "\n";
		report += String.format("%-30s", "----------------") + 
				String.format("%-10s", "----") + "\n\n";
		for(int i=0;i<j2.length;i++)
		{
			report += String.format("%-30s", j2[i][0]) + 
					String.format("%-10s", j2[i][1]) + "\n"; 
		}
		//System.out.println(report);
		//WriteToFile("Journey Report2.txt", report);
	}

	public void ReadJourneyFile() throws FileNotFoundException{
		try {
			file = new File("Destinations 2015.csv");
			Scanner read = new Scanner(file);
			while (read.hasNextLine()) 
			{
				//read first line and process it
				String inputLine = read.nextLine(); 
				if (inputLine.length() != 0) {//ignored if blank line
					JProcess(inputLine);
				}
			}
			file2 = new File("Destinations 2014.csv");
			read = new Scanner(file2);
			while (read.hasNextLine()) 
			{
				//read first line and process it
				String inputLine = read.nextLine(); 
				if (inputLine.length() != 0) {//ignored if blank line
					J2Process(inputLine);
				}
			}
			/*file3 = new File("Journey Report.txt");
			read = new Scanner(file3);
			while (read.hasNextLine()) 
			{
				//read first line and process it
				String inputLine = read.nextLine(); 
				if (inputLine.length() != 0) {//ignored if blank line
					J3Process(inputLine);
				}
			}*/


		}
		//if the file is not found, stop with system exit
		catch (FileNotFoundException fnf){
			throw fnf;
		}
	}

	/*public int CalcMaxCost()  
	{
		int MaxCost = 0;
		for (int cost = 0; cost < JourneyCost; cost++) 
		{
			if(cost > MaxCost)
			{
				MaxCost = cost;
			}
		}
		return MaxCost;
	}*/
	
	private void JProcess(String line) {
		try {
			String parts [] = line.split(",");
			String ID = parts[0];
			String DestinationName = parts[1];
			String pass = parts[2];
			int Passengers = Integer.parseInt(pass); 
			String Year = parts[3];
			//Creating new Destination object to add to list
			Journey J = new Journey(ID, DestinationName, Passengers, Year);
			journeyList.add(J);


		}

		//this catches trying to convert a String to an integer
		catch (NumberFormatException nfe) {
			String error = "Number conversion error in '"
					+ line + "'  - " + nfe.getMessage();
			System.out.println(error);
		}
		//this catches missing items if only one or two items
		//other omissions will result in other errors
		catch (ArrayIndexOutOfBoundsException air) {
			String error = "Not enough items in  : '" + line
					+ "' index position : " + air.getMessage();
			System.out.println(error);
		}
	}

	private void J2Process(String line) {
		try {
			String parts [] = line.split(",");
			String DestinationName = parts[0]; 
			String Year = parts[1];
			//Creating new Destination object to add to list
			j2[i][0] = DestinationName;
			j2[i][1] = Year;
			i++;
		}

		//this catches trying to convert a String to an integer
		catch (NumberFormatException nfe) {
			String error = "Number conversion error in '"
					+ line + "'  - " + nfe.getMessage();
			System.out.println(error);
		}
		//this catches missing items if only one or two items
		//other omissions will result in other errors
		catch (ArrayIndexOutOfBoundsException air) {
			String error = "Not enough items in  : '" + line
					+ "' index position : " + air.getMessage();
			System.out.println(error);
		}
	}
	
	/*private void J3Process(String line) {
		try {
			String parts [] = line.split(",");
			String JID = parts[0];
			String RNo = parts[1];
			String DestinationName = parts[2];
			String dis = parts[3];
			float distance = Float.parseFloat(dis);
			String pass = parts[4];
			int Passengers = Integer.parseInt(pass); 
			String c = parts[5];
			float cost = Float.parseFloat(c);
			//Creating new Destination object to add to list
			Journey J = new Journey(JID, RNo, DestinationName, distance, Passengers, cost);
			journeyList.add(J);


		}/*

		//this catches trying to convert a String to an integer
		catch (NumberFormatException nfe) {
			String error = "Number conversion error in '"
					+ line + "'  - " + nfe.getMessage();
			System.out.println(error);
		}
		//this catches missing items if only one or two items
		//other omissions will result in other errors
		catch (ArrayIndexOutOfBoundsException air) {
			String error = "Not enough items in  : '" + line
					+ "' index position : " + air.getMessage();
			System.out.println(error);
		}
	}*/
	

	//Writing to a text file
	public  void WriteToFile(String filename, String tem) 
	{

		FileWriter fw;
		try 
		{
			fw = new FileWriter(filename);
			fw.write(tem);
			fw.close();
		}

		// To show message and stop if file not found
		 
		catch (FileNotFoundException fnf)
		{
			System.out.println(filename + " not found ");
			System.exit(0);
		}

		//stack and exit
		catch (IOException ioe)
		{
			ioe.printStackTrace();
			System.exit(1);
		}
	}

	 
	public void getTaxiDest()
	{
		String report = "";
		for(Taxi t : Taxi.GetAllTaxis())
		{
			TreeSet<String> dest = new TreeSet<String>();
			for(Journey j : journeyList)
			{
				if(t.GetRegNo().equals(j.getRegNo()))
					dest.add(j.getDest());
			}
			report += "Driver Name : " + t.getDriverName().getFullName() +  "\n\t";
			for(String s : dest)
				report+= s + "\n\t";
			report += "\n";
		}
		//System.out.println(report);
		WriteToFile("Taxi Driver Report.txt", report);
	}

	public void getOnly2014()
	{
		String report;
		TreeSet<String> d2014 = new TreeSet<String>() ;
		int count = 0;
		for(int i=0;i<j2.length;i++)
		{
			int flag = 0;
			for(Journey j: journeyList)
			{				
				if(j2[i][0].equals(j.getDest()))
					flag = 1;
			}
			if(flag == 0)
			{
				d2014.add(j2[i][0]);
				count++;
			}
		}
		report = count + " destinations only in 2014 \n";
		for(String s : d2014)
			report += s + "\n";
		System.out.println(report);
		temp += report;
	}
	
	public void getNew2015()
	{
		String report;
		TreeSet<String> d2015 = new TreeSet<String>() ;
		int count = 0;
		for(Journey j : journeyList)
		{
			int flag = 0;
			for(int i=0; i<j2.length; i++)
			{
				if(j.getDest().equals(j2[i][0]))
					flag = 1;
			}
			if(flag == 0)
			{
				d2015.add(j.getDest());
				count++;
			}
		}
		report = count + " new destinations in 2015\n";
		for(String s : d2015)
			report += s + "\n";
		System.out.println(report);
		temp+= "\n" + report;
	}
	
	public void getBoth20142015()
	{
		String report;
		TreeSet<String> d20142015 = new TreeSet<String>() ;
		for(Journey j : journeyList)
		{
			for(int i=0; i<j2.length; i++)
			{
				if(j.getDest().equals(j2[i][0]))
					d20142015.add(j.getDest());
			}
		}
		report = d20142015.size() + " destinations in 2014 and 2015\n";
		for(String s : d20142015)
			report += s + "\n";
		temp += " \n" + report;
		System.out.println(report);
		//WriteToFile("Destinations Report.txt", temp);
		
	}
	
	/*public void getJourneyByCost()
	{
		String report = "Sorting Journeys by Cost" + "\n";
		for(Map.Entry<String, Double> entry : sfares.entrySet()) 
		{
			  String key = entry.getKey();
			  Double value = entry.getValue();

			  System.out.println(key + " => " + value);
		}*/
	
	
	
	/*public void getJourneyByCost()
	{
		String report = "Sorting Journeys by Cost" + "\n";
		for(Map.Entry<String, Double> entry : sfares.entrySet()) 
		{
			  System.out.println(entry.getKey() + " => " + entry.getValue());
		}
	}*/
	
	public static Map<Integer, Double> sortByValue(Map<Integer, Double> map){
		List list = new LinkedList(map.entrySet());
		Collections.sort(list, new Comparator() {
			
			public int compare(Object o1, Object o2) {
				return ((Comparable)((Map.Entry)(o2)).getValue()).compareTo(((Map.Entry) (o1)).getValue());
			}
			
		});
		
		Map result = new LinkedHashMap();
		for (Iterator it = list.iterator(); it.hasNext();){
			Map.Entry entry = (Map.Entry)it.next();
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}
	
	public void Print(){
		
		Map<Integer, Double> sortedMap = sortByValue(sfares);
		for(Map.Entry<Integer, Double> entry: sortedMap.entrySet()){
			System.out.println(entry.getKey() + "     " + entry.getValue());
		}
		
	}
		/*for(Journey J : journeyList)
		{
			for(Destination d : Dest.GetAllDestinations())
			{
				if(J.getDest().equals(d.DestinationName))
					distance= d.Distance;
			}
			fare = J.getJourneyCost(distance, J.getPassengers());
			report += String.format("%-10s", J.getRegNo()) + "\t";
			report += String.format("%-10s", J.getDest()) + "\t";
			report += String.format("%-10s", distance) + "\t";
			report += String.format("%.2f", fare) + "\t";
			report += "\n";
		}
		
		WriteToFile("JourneyByCost.txt", report);
	}*/
	
}