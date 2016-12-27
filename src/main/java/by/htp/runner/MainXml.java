package by.htp.runner;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.stream.XMLStreamException;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import by.htp.dao.xml.AfishaXMLHandler;
import by.htp.dao.xml.DateBase;
import by.htp.entity.Afisha;
import by.htp.entity.Event;
import by.htp.entity.Exibitions;
import by.htp.entity.Film;
import by.htp.entity.Genre;
import by.htp.entity.Opera;
import by.htp.logic.AfishaVisitor;
import by.htp.logic.impl.ConsoleAfishaVisitor;
import by.htp.parser.AfishaDomParser;
import by.htp.parser.AfishaStaxParser;


public class MainXml {

	public static void main(String[] args) throws SAXException, IOException {
		

		
		DateBase db = new DateBase();
		List<Event> listDB = new ArrayList<Event>();
		listDB = db.createEventList();
		Film filmFromDB = new Film();
		Opera operaFromDB = new Opera();
		Exibitions exibitionDB = new Exibitions();
		
		for(Event v : listDB)
		{
			System.out.println(v);
		}
		
		System.out.println("Вывод базы на экран");
		db.allToDisplay();
		System.out.println();
		
		System.out.println("Чтение из XML через SAX парсер");
		
		XMLReader reader;
		reader = XMLReaderFactory.createXMLReader();
		InputSource source = new InputSource("AfishaXMLNew.xml");
		AfishaXMLHandler handler = new AfishaXMLHandler();
		reader.setContentHandler(handler);
		reader.parse(source);
		Afisha afishaXML;
		afishaXML = handler.getAfishaXML();
		ConsoleAfishaVisitor consoleDispley = new ConsoleAfishaVisitor();
		consoleDispley.loadAfisha(afishaXML);
		System.out.println();
		
		System.out.println("Добавление элементов в DB из XML через StAX парсер");
		
		AfishaStaxParser asp = new AfishaStaxParser();
		List<Event> listStAX = new ArrayList<Event>();
		try {
			listStAX = asp.parserStAX();
			for(Event e : listStAX)
			{
			//System.out.println(e.getClass().getName());	
				if(e.getClass().getName().equals("by.htp.entity.Film"))
				{
					
					db.dateToDB((Film)e);
				}
				else if(e.getClass().getName().equals("by.htp.entity.Opera"))
				{
					db.dateToDB((Opera)e);
				}
				else if(e.getClass().getName().equals("by.htp.entity.Exibitions"))
				{
					db.dateToDB((Exibitions)e);
				}
			}
		} catch (XMLStreamException | ParseException e) {
			
			e.printStackTrace();
		}
	
		//Чтение и запись через DOM parser
		AfishaDomParser domParserOb = new AfishaDomParser();
		try {
			domParserOb.domParser("AfishaXMLNew.xml");
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		
		domParserOb.domToXML("NewFile.xml");
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
		Film oneFilm = new Film("Белая акула", "Октябрь", dayFilm, "20:20", BigDecimal.valueOf(5.20), Genre.HORROR, 5.5);
		Opera oneOpera = new Opera("АААА", "Епаара", dayOpera, "18:00", BigDecimal.valueOf(3.15), "Вася");
		Exibitions oneExibition = new Exibitions("Шеев", "ART", dayExibition, "10:20", BigDecimal.valueOf(2.00), "Шагал", "Картины Шагала");
		db.dateToDB(oneFilm);
		db.dateToDB(oneOpera);
		db.dateToDB(oneExibition);
		
		db.deleteDateDB("Белая акула", "Октябрь", "2016.11.16", "20:20");
		
		
		System.out.println("Выбранные 3 события из БД и выведенные через ConsoleAfishaVisitor");
		filmFromDB=(Film)db.createEventFromDB("film1", "place10", "2016.11.30", "21:00");
		operaFromDB = (Opera)db.createEventFromDB("opera1", "plce20", "2016.12.10", "10:00");
		exibitionDB = (Exibitions)db.createEventFromDB("exibition1", "place30", "2016.12.6", "12:00");
		Afisha oneAfisha = new Afisha("Минск");
		oneAfisha.addEvent(filmFromDB);
		oneAfisha.addEvent(operaFromDB);
		oneAfisha.addEvent(exibitionDB);		
		
		AfishaVisitor visitor = new ConsoleAfishaVisitor();
		visitor.loadAfisha(oneAfisha);
		
		System.out.println("UPDATE");
		db.updateDateDBFilm("film4", "place11", "2016.12.17", "21:00", "cost", "8");
		db.updateDateDBFilm("opera3", "place22", "2016.12.12", "17:00", "cost", "6");
		db.updateDateDBFilm("exibition3", "place30", "2016.12.14", "13:00", "cost", "9");
		
	}

}
