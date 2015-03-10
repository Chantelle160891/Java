

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

public class DestinationList {

	private TreeSet <Destination> DestinationList;
	Iterator<Destination> itemsIterator;
	private File file;
	TaxiList taxis;

	String temp = new String();
	Destination d;


	public DestinationList(TaxiList t) throws FileNotFoundException
	{
		DestinationList = new TreeSet<Destination>();
		taxis = t;
		this.ReadDestinationFile();
	}

	public void addDestination(Destination D)
	{
		DestinationList.add(D);
	}

	public TreeSet<Destination> GetAllDestinations()
	{
		return DestinationList;
	}

	public void printDestination()
	{
		temp = String.format("%-10s", "Start Point")
				+ "\t"
				+ String.format("%-20s", "Destination")
				+ "\t"
				+ String.format("%-10s", "Distance")
				+ "\n";
		temp += String.format("%-10s", "---------")
				+ "\t"
				+ String.format("%-20s", "-----------")
				+ "\t"
				+ String.format("%-10s", "-----------")
				+ "\n\n";

		for(Destination d : DestinationList)
		{
			temp += String.format("%-10s", d.getStartPoint())
					+ "\t"
					+ String.format("%-20s", d.getDestinationName())
					+ "\t"
					+ String.format("%-10s", d.getDistance())
					+ "\n";
		}
		//System.out.println(temp);
		WriteToFile("DestinationReport.txt", temp);
	}

	public void ReadDestinationFile() throws FileNotFoundException{
		try {
			file = new File("Destinations.csv");
			Scanner read = new Scanner(file);
			while (read.hasNextLine()) {
				//read first line and process it
				String inputLine = read.nextLine(); 
				if (inputLine.length() != 0) {//ignored if blank line
					DProcess(inputLine);
				}
			}
			read.close();
		}
		//if the file is not found, stop with system exit
		catch (FileNotFoundException fnf){
			throw fnf;
		}
	}
	private void DProcess(String line) {
		try {
			String parts [] = line.split(",");
			String StartPoint = parts[0];
			String DestinationName = parts[1];
			String Dis = parts[2];
			float Distance = Float.parseFloat(Dis);	

			Destination dest = new Destination(StartPoint, DestinationName, Distance);
			DestinationList.add(dest);

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

	//Writing to a text file
	public  void WriteToFile(String filename, String temp) 
	{

		FileWriter fw;
		try 
		{
			fw = new FileWriter("Destination Report.txt");
			fw.write(temp);
			fw.close();
		}

		/** To show message and stop if file not found
		 */
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
	
}

