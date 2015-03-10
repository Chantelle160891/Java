import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

	public class TaxiList {
		
		public TreeSet<Taxi> TaxiList;
		private File file;
		
		Taxi t;
		String temp = new String();
		
		public TaxiList() throws FileNotFoundException
		{
			TaxiList = new TreeSet<Taxi>();
			this.ReadTaxiFile();
		}
		
		/*public static void addTaxi(Taxi T)
		{
			TaxiList.add(T);
		}
		*/
		public TreeSet<Taxi> GetAllTaxis()
		{
			return TaxiList;
		}
		
		public void printTaxi()
		{
			temp = String.format("%-10s", "Reg. No")
					+ "\t"
					+ String.format("%5s", "Driver Name")
					+ "\n";
			temp += String.format("%-10s", "-------")
					+ "\t"
					+ String.format("%5s", "-----------")
					+ "\n\n";
			for(Taxi t : TaxiList)
			{
				temp += String.format("%-10s", t.GetRegNo())
				+ "\t"
				+ String.format("%5s", t.getDriverName().getFullName())
				+ "\n";
			}
			//System.out.println(temp);
			WriteToFile("TaxiReport.txt", temp); 
		}
		
		
		public void ReadTaxiFile() throws FileNotFoundException{
			String temp = new String();
			try {
				file = new File("Taxi Details.csv");
				Scanner read = new Scanner(file);
				while (read.hasNextLine()) {
					//read first line and process it
					String inputLine = read.nextLine(); 
					if (inputLine.length() != 0) {//ignored if blank line
							TProcess(inputLine);
					}
				}
			}
			//if the file is not found, stop with system exit
			catch (FileNotFoundException fnf){
				throw fnf ;
			}
			
		}
		
		/**
		 * Method used to process each line from TaxiDetails file
		 * @param line
		 */
		private void TProcess(String line)  {
			try {
				String parts [] = line.split(",");
				String ID= parts[0];

				Name DriverName = new Name(parts [1], parts[2]);
				
				Taxi taxi = new Taxi(ID, DriverName);
				TaxiList.add(taxi);
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
			    fw = new FileWriter("Taxi Report.txt");
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



