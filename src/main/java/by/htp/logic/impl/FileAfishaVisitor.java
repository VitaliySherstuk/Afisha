package by.htp.logic.impl;

//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
//import java.io.PrintWriter;
//import java.math.BigDecimal;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
import java.util.Date;
import by.htp.entity.Afisha;
import by.htp.entity.Event;
//import by.htp.entity.Exibitions;
//import by.htp.entity.Film;
//import by.htp.entity.Genre;
//import by.htp.entity.Opera;
//import by.htp.logic.AfishaVisitor;

public class FileAfishaVisitor 
{	
	public void  loadAfisha(Afisha afisha)
	{
		
		FileWriter fw=null;
		try {
		fw = new FileWriter("AfishaTextFile.txt");
		for(Event eventElement : afisha.viewEvents())
		{
			fw.write(eventElement.toString());
			
		}
		
		fw.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	public void loadAfisha(Afisha afisha, Date date)
	{
		FileWriter fw1=null;
		try {
		fw1 = new FileWriter("AfishaTextFileDate.txt");
		
		for(Event event : afisha.viewEvents())
		{	
			if(event.getDate().equals(date))
			{
				
				fw1.write(event.toString());
			}
		}
		fw1.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	public void loadAfisha(Afisha afisha, Date dateStart, Date dateEnd)
	 {
		FileWriter fw2=null;
		try {
		fw2 = new FileWriter("AfishaTextFileDateDate.txt");
		
		for(Event event : afisha.viewEvents())
		{
			if(event.getDate().after(dateStart) & event.getDate().before(dateEnd)
					||event.getDate().equals(dateStart)||event.getDate().equals(dateEnd))
			{
				fw2.write(event.toString());
			}
		}
		fw2.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
	 }
	
}
