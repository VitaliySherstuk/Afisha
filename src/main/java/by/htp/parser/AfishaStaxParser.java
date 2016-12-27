package by.htp.parser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
//import java.io.StringReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import by.htp.entity.Event;
import by.htp.entity.Exibitions;
import by.htp.entity.Film;
import by.htp.entity.Genre;
import by.htp.entity.Opera;

public class AfishaStaxParser {

	List<Event> list; 
	public List<Event> parserStAX() throws XMLStreamException, FileNotFoundException, ParseException {

		list = new ArrayList<Event>();
		Event event = null;
		String marker=null;
		Film elementFilm = new Film();
		Opera elementOpera = new Opera();
		Exibitions elementExibition = new Exibitions();
		// StringReader input = new StringReader("Files/AfishaXMLNew.xml");
		InputStream input = new FileInputStream("AfishaXMLNew.xml");
		XMLInputFactory inputFactory = XMLInputFactory.newInstance();
		XMLStreamReader reader = inputFactory.createXMLStreamReader(input);
		
		while (reader.hasNext()) {
			int type = reader.next();

			switch (type) {
			case XMLStreamReader.START_ELEMENT:
				marker = reader.getLocalName().toString();
				switch (reader.getLocalName().toString()) {
				case "afisha":

					//System.out.println(reader.getAttributeValue(null, "city"));
					break;
				case "film":
					elementFilm = new Film();
					System.out.println("FILM");
					event = elementFilm;

					break;
				case "opera":
					elementOpera = new Opera();
					System.out.println("OPERA");
					event = elementOpera;
					break;
				case "exibition":
					elementExibition = new Exibitions();
					System.out.println("EXIBITION");
					event = elementExibition;
					break;
				}

				break;
			case XMLStreamReader.CHARACTERS:
				String text = reader.getText().trim();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
				
				if(text.isEmpty())
				{
					break;
				}
				if (event == elementFilm) {
					switch (marker) {
					
					case "name":
						
						elementFilm.setName(text);
						break;
					case "place":
						elementFilm.setPlace(text);
						break;
					case "date":
						elementFilm.setDate(dateFormat.parse(text));
						
						break;
					case "time":
						elementFilm.setTime(text);
						break;
					case "cost":
						elementFilm.setCost(new BigDecimal(Double.parseDouble(text)));
						break;
					case "genre":
						elementFilm.setGenreFilm(Genre.valueOf(text.toUpperCase()));
						break;
					case "raiting":
						elementFilm.setRatingFilm(Double.parseDouble(text));
						break;
					}
				}

				if (event == elementOpera) {
					switch (marker) {

					case "name":
						elementOpera.setName(text);
						break;
					case "place":
						elementOpera.setPlace(text);
						break;
					case "date":
						elementOpera.setDate(dateFormat.parse(text));
						//System.out.println(text);
						break;
					case "time":
						elementOpera.setTime(text);
						break;
					case "cost":
						elementOpera.setCost(new BigDecimal(Double.parseDouble(text)));
						break;
					case "singer":
						elementOpera.setSingerOpera(text);
						break;
					}
				}
				if (event == elementExibition) {
					switch (marker) {

					case "name":
						elementExibition.setName(text);
						break;
					case "place":
						elementExibition.setPlace(text);
						break;
					case "date":
						elementExibition.setDate(dateFormat.parse(text));
						break;
					case "time":
						elementExibition.setTime(text);
						break;
					case "cost":
						elementExibition.setCost(new BigDecimal(Double.parseDouble(text)));
						break;
					case "author":
						elementExibition.setAuthor(text);
						break;
					case "topic":
						elementExibition.setTopic(text);
						break;
					}

				}
				break;

			}

		}
		list.add(elementFilm);
		list.add(elementOpera);
		list.add(elementExibition);
		
		return list;
	}
}