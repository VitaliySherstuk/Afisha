package by.htp.dao.xml;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import by.htp.entity.Afisha;
//import by.htp.entity.Event;
import by.htp.entity.Exibitions;
import by.htp.entity.Film;
import by.htp.entity.Genre;
import by.htp.entity.Opera;

public class AfishaXMLHandler extends DefaultHandler {

	private final static Logger log = LogManager.getLogger();
	enum eventEnum{NAME, PLACE, DATE, TIME, COST, GENRE, RATING, SINGER, AUTHOR, TOPIC};	
	String type;
	Afisha afishaXML;
	Film xmlFilm;
	Opera xmlOpera;
	Exibitions xmlExibition;
	String marker;
	SimpleDateFormat checkDate = new SimpleDateFormat("yyyy.MM.dd");
	private StringBuilder text;
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		
		text.append(ch, start, length);
	}

	
	@Override
	public void startDocument() throws SAXException {
		System.out.println("startDocument");
		//super.startDocument();
	}

	@Override
	public void endDocument() throws SAXException {
		System.out.println("endDocument");
		//super.endDocument();
	}

	public Afisha getAfishaXML()
	{
		return afishaXML;
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		
		
		if(qName!=null && qName.equals("afisha"))
		{
			afishaXML = new Afisha(attributes.getValue(0));
			log.info("New afisha was created");
			
		}
		
		text = new StringBuilder();
		//System.out.println(attributes.getValue(0));
		//if(qName.equals("event") && qName!=null)
		if(qName!=null && qName!="events")
		{
			 //type= attributes.getValue(0);
			
			switch(qName)
			{
			case "film":
				//event = new Film();
				marker = "film";
				xmlFilm = new Film();
				afishaXML.addEvent(xmlFilm);
				break;
			case "opera":
				//event = new Opera();
				marker = "opera";
				xmlOpera = new Opera();
				afishaXML.addEvent(xmlOpera);
				break;
			case "exibition":
				//event= new Exibitions();
				marker = "exibition";
				xmlExibition = new Exibitions();
				afishaXML.addEvent(xmlExibition);
			break;
			}	
		}
	}

	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		
		if(qName!=null && qName!="film" &&qName!="events" && qName!="opera" && qName!="exibition" && qName!="afisha")
		{
		eventEnum s = eventEnum.valueOf(qName.toUpperCase());
		//System.out.println(s);
		if(marker.equals("film"))
		{
			//System.out.println(marker);
			switch(s)
			{
			case NAME:
				xmlFilm.setName(text.toString());
				break;
			case PLACE:
				xmlFilm.setPlace(text.toString());
				break;
			case DATE:
				try {
					xmlFilm.setDate(checkDate.parse(text.toString()));
				} catch (ParseException e) {
					
					e.printStackTrace();
				}
			break;
			case TIME:
				xmlFilm.setTime(text.toString());
				break;
			case COST:
				xmlFilm.setCost(BigDecimal.valueOf(Double.parseDouble(text.toString())));
				break;
			case GENRE:
				xmlFilm.setGenreFilm(Genre.valueOf(text.toString().toUpperCase()));
			break;
			case RATING:
				xmlFilm.setRatingFilm(Double.parseDouble(text.toString()));
				break;
			}
		}
		else if(marker.equals("exibition"))
		{
			//System.out.println(marker);
			switch(s)
			{
			case NAME:
				xmlExibition.setName(text.toString());
				break;
			case PLACE:
				xmlExibition.setPlace(text.toString());
				break;
			case DATE:
				try {
					xmlExibition.setDate(checkDate.parse(text.toString()));
				} catch (ParseException e) {
					
					e.printStackTrace();
				}
			break;
			case TIME:
				xmlExibition.setTime(text.toString());
				break;
			case COST:
				xmlExibition.setCost(BigDecimal.valueOf(Double.parseDouble(text.toString())));
				break;
			case AUTHOR:
				xmlExibition.setAuthor(text.toString());
			break;
			case TOPIC:
				xmlExibition.setTopic(text.toString());
			break;
			}	
		}
		else if(marker.equals("opera"))
		{
			switch(s)
			{
			case NAME:
				xmlOpera.setName(text.toString());
				break;
			case PLACE:
				xmlOpera.setPlace(text.toString());
				break;
			case DATE:
				try {
					xmlOpera.setDate(checkDate.parse(text.toString()));
				} catch (ParseException e) {
					
					e.printStackTrace();
				}
			break;
			case TIME:
				xmlOpera.setTime(text.toString());
				break;
			case COST:
				xmlOpera.setCost(BigDecimal.valueOf(Double.parseDouble(text.toString())));
				break;
			case SINGER:
				xmlOpera.setSingerOpera(text.toString());
				break;
			}
		}
		}
	}
}
