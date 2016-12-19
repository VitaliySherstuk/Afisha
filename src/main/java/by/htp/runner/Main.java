package by.htp.runner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

import by.htp.entity.*;
import by.htp.logic.AfishaVisitor;
import by.htp.logic.impl.ConsoleAfishaVisitor;
import by.htp.logic.impl.FileAfishaVisitor;

public class Main {

	//private final static Logger log = LogManager.getLogger();
	
	public static void main(String[] args) {
		
		
		
		
		
		
		
		//Properties property = new Properties();
//		try {
//			property.load(new FileInputStream("resources/config.properties"));
//			System.out.println(property.getProperty("name"));
//		} catch (FileNotFoundException e) {
//			
//			e.printStackTrace();
//		} catch (IOException e) {
//			
//			e.printStackTrace();
//		}
		
//	try{
//			ResourceBundle boundleRU = ResourceBundle.getBundle("config", new Locale("ru_RU"));
//			ResourceBundle boundleEN = ResourceBundle.getBundle("config", Locale.ENGLISH);
//			
//			System.out.println(boundleRU.getString("file"));
//			System.out.println(boundleEN.getString("name"));
//		}
//	catch(Exception e)
//	{
//		//e.printStackTrace();
//		log.error("Some error");
//		log.info("new event was");
//		log.debug("new ");
//	}
		
		
		
		
		SimpleDateFormat checkDate = new SimpleDateFormat("yyyy.MM.dd");
		Date day=null;
		Date dayStart=null;
		Date dayEnd=null;
		Date dayFilm=null;
		Date dayOpera=null;
		Date dayExibition=null;
		try {
			day= checkDate.parse("2016.11.17");
			dayStart = checkDate.parse("2016.11.17");
			dayEnd = checkDate.parse("2016.11.18");
			dayFilm = checkDate.parse("2016.11.16");
			dayOpera = checkDate.parse("2016.11.17");
			dayExibition = checkDate.parse("2016.11.18");
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		
		Event oneFilm = new Film("Белая акула", "Октябрь", dayFilm, "20:20", BigDecimal.valueOf(5.20), Genre.HORROR, 5.5);		
		Event oneOpera = new Opera("АААА", "Епаара", dayOpera, "18:00", BigDecimal.valueOf(3.15), "Вася");
		Event oneExibition = new Exibitions("Шеев", "ART", dayExibition, "10:20", BigDecimal.valueOf(2.00), "Шагал", "Картины Шагала");
		
		Afisha oneAfisha = new Afisha("Минск");
		oneAfisha.addEvent(oneFilm);
		oneAfisha.addEvent(oneOpera);
		oneAfisha.addEvent(oneExibition);		
		//System.out.println(oneAfisha);
		
		AfishaVisitor visitor = new ConsoleAfishaVisitor();
		visitor.loadAfisha(oneAfisha);
		
		System.out.println("На дату 17.11.2016");
		
		visitor.loadAfisha(oneAfisha, day);
		//visitor.loadAfisha(oneAfisha, day);
		
		System.out.println();
		
		System.out.println("На даты 17.11.2016 - 18.11.016");
		visitor.loadAfisha(oneAfisha, dayStart, dayEnd);
		//visitor.loadAfisha(oneAfisha, dayStart, dayEnd);
		
		System.out.println("Afisha - 2. Смотреть AfishaTextFile.txt, AfishaTextFileDate.txt и AfishaDateDate.txt");
		Afisha twoAfisha = new Afisha("Минск");
		StreamAfisha streamFile = new StreamAfisha(twoAfisha);
		FileAfishaVisitor fav = new FileAfishaVisitor();
		fav.loadAfisha(streamFile.getAfishaFile());
		fav.loadAfisha(streamFile.getAfishaFile(), day);
		fav.loadAfisha(streamFile.getAfishaFile(), dayStart, dayEnd);		
	}	
}
