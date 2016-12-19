package by.htp.entity;

import java.util.HashSet;
import java.util.Set;

public class Afisha {

	private Set<Event> events;
	private String city;
	
	public Afisha(String city){ 
		
		events = new HashSet<Event>();
		this.city = city;
	}

	public void setEvents(Set<Event> events) {
		this.events = events;
	}

	public Set<Event> viewEvents()
	{
		return events;
	}

	@Override
	public String toString() {
		return "Afisha [events=" + events + ", city=" + city + "]";
	}


	public boolean addEvent(Event event)
	{
		if(event!=null)
		{
			events.add(event);
			return true;
		}
		else
		{
			return false;
		}
	}	
}
