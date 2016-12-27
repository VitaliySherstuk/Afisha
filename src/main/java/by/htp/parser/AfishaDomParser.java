package by.htp.parser;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NameList;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import by.htp.entity.Event;
import by.htp.entity.Exibitions;
import by.htp.entity.Film;
import by.htp.entity.Genre;
import by.htp.entity.Opera;

public class AfishaDomParser {

	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	DocumentBuilder builder;
	
	Document document;
	Element afishaRoot;
	
	NodeList eventsNode =null;
	
	public void domParser(String pathXML) throws ParseException
	{
		
		
		try
		{
			builder = factory.newDocumentBuilder();
			document = builder.parse(new InputSource(pathXML));
			
			afishaRoot = document.getDocumentElement();
			//String att = afishaRoot.getAttribute("city");
			//System.out.println(afishaRoot.getFirstChild().getNodeName());
			
			//получим список узлов
			eventsNode = afishaRoot.getElementsByTagName("events");
			
			for(int i=0; i<eventsNode.getLength(); i++)
			{
				
				Element events = (Element) eventsNode.item(i);
				
				NodeList allEvents = events.getChildNodes();
			
			
				for(int j=0; j<allEvents.getLength(); j++)
				{
					Node eventNode = allEvents.item(j);
					
					Element eventElement;
					
					//getNodeType() отсекает все кроме opera film exibition (получаем текст между тегами)
					if(eventNode.getNodeType()==1)
					{
						eventElement = (Element) eventNode;
						Event eventEntity = buildEvent(eventElement, eventNode.getNodeName());
						//System.out.println(eventEntity);
					}
				}

			}
		}
			catch(IOException | SAXException | ParserConfigurationException e)
			{
				e.getMessage();
			}
	}
	
	public void domToXML(String pathXML)
	{
		try
		{
		for(int i=0; i<eventsNode.getLength(); i++)
		{
			Node nodeEvents = eventsNode.item(i);
			//System.out.println(nodeEvents.getNodeName());
			if(nodeEvents.getNodeName().equals("events"))
			{
				Element newFilm = document.createElement("film");
				Element newName = document.createElement("name");
				Element newPlace = document.createElement("place");
				Element newDate = document.createElement("date");
				Element newTime = document.createElement("time");
				Element newCost = document.createElement("cost");
				Element newGenre = document.createElement("genre");
				Element newRaiting = document.createElement("raiting");
				
				newPlace.appendChild(document.createTextNode("Place3"));
				newName.appendChild(document.createTextNode("Film3"));
				newDate.appendChild(document.createTextNode("2016.11.30"));
				newTime.appendChild(document.createTextNode("18:00"));
				newCost.appendChild(document.createTextNode("18"));
				newGenre.appendChild(document.createTextNode("drama"));
				newRaiting.appendChild(document.createTextNode("7.5"));
				
				newFilm.appendChild(newName);
				newFilm.appendChild(newPlace);
				newFilm.appendChild(newDate);
				newFilm.appendChild(newTime);
				newFilm.appendChild(newCost);
				newFilm.appendChild(newGenre);
				newFilm.appendChild(newRaiting);
				
				nodeEvents.appendChild(newFilm);
				
				TransformerFactory fac = TransformerFactory.newInstance();
				Transformer trans;
				
				trans = fac.newTransformer();
				trans.setOutputProperty(javax.xml.transform.OutputKeys.INDENT, "yes");
				DOMSource source = new DOMSource(document);
				StreamResult result = new StreamResult(new FileWriter("NewFile.xml"));
				trans.transform(source, result);
			}
		}
		}
		catch(IOException | TransformerException e)
		{
			e.printStackTrace();
		}
	}

	
	public  Event buildEvent(Element element, String nodeName) throws ParseException
	{
		Event event = null;
		System.out.println("nodeName: " + nodeName);
		
		DateFormat df = new SimpleDateFormat("yyyy.MM.dd");
		
		switch(nodeName)
		{
		case "film":
			Film film = new Film();
			film.setName(getSingleElementContent(element, "name"));
			film.setPlace(getSingleElementContent(element, "place"));
			film.setDate(df.parse(getSingleElementContent(element, "date")));
			film.setTime(getSingleElementContent(element, "time"));
			film.setCost(new BigDecimal(getSingleElementContent(element, "cost")));
			film.setGenreFilm(Genre.valueOf(getSingleElementContent(element, "genre").toUpperCase()));
			film.setRatingFilm(Double.parseDouble(getSingleElementContent(element, "rating")));
			event=film;
			break;
		case "opera":
			Opera opera = new Opera();
			opera.setName(getSingleElementContent(element, "name"));
			opera.setPlace(getSingleElementContent(element, "place"));
			opera.setCost(new BigDecimal(getSingleElementContent(element,"cost")));
			opera.setDate(df.parse(getSingleElementContent(element, "date")));
			opera.setTime(getSingleElementContent(element, "time"));
			opera.setSingerOpera(getSingleElementContent(element, "singer"));
			event = opera;
			break;
		case "exibition":
			Exibitions exibition = new Exibitions();
			exibition.setName(getSingleElementContent(element, "name"));
			exibition.setPlace(getSingleElementContent(element, "place"));
			exibition.setCost(new BigDecimal(getSingleElementContent(element,"cost")));
			exibition.setDate(df.parse(getSingleElementContent(element, "date")));
			exibition.setTime(getSingleElementContent(element, "time"));
			exibition.setAuthor(getSingleElementContent(element, "author"));
			exibition.setTopic(getSingleElementContent(element, "topic"));
			
			event = exibition;
			break;
		}
		return event;
	}

	private String getSingleElementContent(Element element, String tagName) {
		
		NodeList list = element.getElementsByTagName(tagName);
		Element el = (Element) list.item(0);
		
		String content = el.getTextContent().trim();
		return content;
		
	}
}
