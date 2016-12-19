package by.htp.logic.impl;

import java.util.Date;
import by.htp.entity.Afisha;
import by.htp.entity.Event;
import by.htp.logic.AfishaVisitor;

public class ConsoleAfishaVisitor implements AfishaVisitor{

	
	public void  loadAfisha(Afisha afisha)
	{
		for(Event event : afisha.viewEvents())
		{
			System.out.println(event);
		}
	}
	
	public void loadAfisha(Afisha afisha, Date date)
	{
		for(Event event : afisha.viewEvents())
		{
			if(event.getDate().equals(date))
			{
				System.out.println(event);
			}
		}
	}

	public void loadAfisha(Afisha afisha, Date dateStart, Date dateEnd)
	{
		for(Event event : afisha.viewEvents())
		{
			if(event.getDate().after(dateStart) & event.getDate().before(dateEnd)
					||event.getDate().equals(dateStart)||event.getDate().equals(dateEnd))
			{
				System.out.println(event);
			}
		}
	}
	
}
