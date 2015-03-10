import java.io.FileNotFoundException;


public class Manager {
	
	public void run() throws FileNotFoundException
	{
		TaxiList t = new TaxiList();
		DestinationList d = new DestinationList(t);
		JourneyList j = new JourneyList(t,d);
		//t.printTaxi();
		//d.printDestination();
		j.printJourney2015();
		//j.printJourney2014();
		//j.getTaxiDest();
		//j.getOnly2014();
		//j.getNew2015();
		//j.getBoth20142015();
		//j.getJourneyByCost();
		j.Print();

	
	}

}
