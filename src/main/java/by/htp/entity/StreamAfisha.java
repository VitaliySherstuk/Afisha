package by.htp.entity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class StreamAfisha {

	public ArrayList<String> eventData = new ArrayList<String>();
	private Afisha afishaFile;
	public StreamAfisha(){}
	public StreamAfisha(Afisha afishaFile)
	{
		this.afishaFile = afishaFile;
		streamEventsRead();
	}

	
	final private void streamEventsRead()
	{
		try
		{
			FileReader fr = new FileReader("AfishaDoc.txt");
			BufferedReader br = new BufferedReader(fr);
			
			String stringLine;
			
			while((stringLine = br.readLine())!=null)
			{
				
				eventData.add(stringLine);
			}
			fr.close();
			br.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		afishaNew();
		
	}

	private void afishaNew()
	{
		for(String element : eventData)
		{
			String[] arrayString=null;
			arrayString = element.split(";");
			SimpleDateFormat checkDate = new SimpleDateFormat("yyyy.MM.dd");
			switch(arrayString[0])
			{
			case "Film":
				Genre checkGenere=null;
				switch(arrayString[6])
				{
				case "COMEDY":
					checkGenere=Genre.COMEDY;
					break;
				case "HORROR":
					checkGenere = Genre.HORROR;
					break;
				case "DRAMA":
					checkGenere = Genre.DRAMA;
					break;
				}
				Date arrayDateFilm=null;
				try {
					arrayDateFilm = checkDate.parse(arrayString[3]);
				} catch (ParseException e) {
					
					e.printStackTrace();
				}
				//System.out.println(arrayString[3]);
				afishaFile.addEvent(new Film( arrayString[1], arrayString[2], arrayDateFilm, arrayString[4], 
						new BigDecimal(arrayString[5]) , checkGenere, Double.parseDouble(arrayString[7])));
				
				break;
			case "Opera":
				Date arrayDateOpera=null;
				try {
					arrayDateOpera = checkDate.parse(arrayString[3]);
				} catch (ParseException e) {
					
					e.printStackTrace();
				}
				afishaFile.addEvent(new Opera(arrayString[1], arrayString[2], arrayDateOpera, arrayString[4], 
						new BigDecimal(arrayString[5]), arrayString[6]));
				break;
			case "Exibition":
				Date arrayDateExibition=null;
				try {
					arrayDateExibition = checkDate.parse(arrayString[3]);
				} catch (ParseException e) {
					
					e.printStackTrace();
				}
				afishaFile.addEvent(new Exibitions( arrayString[1], arrayString[2], arrayDateExibition, arrayString[4], 
						new BigDecimal(arrayString[5]) , arrayString[6], arrayString[7]));
				break;
			}
		}
	}
	
	public Afisha getAfishaFile()
	{
		return afishaFile;
	}
}
