package by.htp.dao.xml;


import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import by.htp.entity.Event;
import by.htp.entity.Exibitions;
import by.htp.entity.Film;
import by.htp.entity.Genre;
import by.htp.entity.Opera;

public class DateBase {
	
	private Statement st = null;
	private Connection connection = null;
	private final static String path ="jdbc:mysql://localhost:3306/afishadb?useSSL=false";
	private final static String login ="root";
	private final static String password ="root";
	SimpleDateFormat checkDate = new SimpleDateFormat("yyyy.MM.dd");
	
	private void connectDB(String db, String login, String password)
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			 connection =DriverManager.getConnection(db, login, password);
			
			st = connection.createStatement();
						
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		
		}
	
	public List<Event> createEvent()
	{
		Film film;
		Opera opera;
		Exibitions exibition;
		List<Event> listDB= new ArrayList<Event>();
		
		connectDB(path, login, password);
		
		try {
			ResultSet rs = st.executeQuery("SELECT * FROM events");
			while(rs.next())
			{
				if(rs.getString("event").equals("film"))
				{
					film = new Film();
					film.setName(rs.getString("name"));
					film.setPlace(rs.getString("place"));
					try {
						film.setDate(checkDate.parse(rs.getString("date")));
						
					} catch (ParseException e) {
						
						e.printStackTrace();
					}
					film.setTime(rs.getString("time"));
					film.setCost(BigDecimal.valueOf(Double.parseDouble(rs.getString("cost"))));
					film.setGenreFilm(Genre.valueOf(rs.getString("genre").toUpperCase()));
					film.setRatingFilm(Double.parseDouble(rs.getString("raiting")));
					listDB.add(film);
				}
				else if(rs.getString("event").equals("opera"))
				{
					opera = new Opera();
					opera.setName(rs.getString("name"));
					opera.setPlace(rs.getString("place"));
					try {
						opera.setDate(checkDate.parse(rs.getString("date")));
					} catch (ParseException e) {
						
						e.printStackTrace();
					}
					opera.setTime(rs.getString("time"));
					opera.setCost(BigDecimal.valueOf(Double.parseDouble(rs.getString("cost"))));
					opera.setSingerOpera(rs.getString("singer"));
					listDB.add(opera);
				}
				else if(rs.getString("event").equals("exibition"))
				{
					exibition = new Exibitions();
					exibition.setName(rs.getString("name"));
					exibition.setPlace(rs.getString("place"));
					try {
						exibition.setDate(checkDate.parse(rs.getString("date")));
					} catch (ParseException e) {
						
						e.printStackTrace();
					}
					exibition.setTime(rs.getString("time"));
					exibition.setCost(BigDecimal.valueOf(Double.parseDouble(rs.getString("cost"))));
					exibition.setAuthor(rs.getString("author"));
					exibition.setTopic(rs.getString("topic"));
					listDB.add(exibition);
				}
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listDB;
	}
	
	public void allToDisplay()
	{
		connectDB(path, login, password);
		if(st!=null)
		{
			try {
				ResultSet rs = st.executeQuery("SELECT * FROM events; ");
				while(rs.next())
				{
					System.out.println("Event: "+rs.getString(2));
					String name = rs.getString(3);
					System.out.print(name);
					String place = rs.getString(4);
					System.out.print("\t"+place);
					String date = rs.getString(5);
					System.out.print("\t"+date);
					String time = rs.getString(6);
					System.out.print("\t"+time);
					String cost = rs.getString(7);
					System.out.print("\t"+cost);
					System.out.println();
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void dateToDB(Film film)
	{
		connectDB(path, login, password);
		String name = film.getName();
		String place = film.getPlace();
		String date = checkDate.format(film.getDate());
		String time = film.getTime();
		String cost = film.getCost().toString();
		String genreFilm = film.getGenre().toString().toLowerCase();
		String rating= Double.toString(film.getRating());
		
		
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO events(event, name, place, date, time, cost, genre, raiting) VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, "film");
			ps.setString(2, name);
			ps.setString(3, place);
			ps.setString(4, date);
			ps.setString(5, time);
			ps.setString(6, cost);
			ps.setString(7, genreFilm);
			ps.setString(8, rating);
			ps.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	try {
		connection.close();
	} catch (SQLException e) {

		e.printStackTrace();
	}		
	}
	public void dateToDB(Opera opera)
	{
		connectDB(path, login, password);
		String name = opera.getName();
		String place = opera.getPlace();
		String date = checkDate.format(opera.getDate());
		String time = opera.getTime();
		String cost = opera.getCost().toString();
		String singer = opera.getSinger();
		
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO events(event, name, place, date, time, cost, singer) VALUE(?, ?, ?, ?, ?, ?, ?)");
		ps.setString(1, "opera");
		ps.setString(2, name);
		ps.setString(3, place);
		ps.setString(4, date);
		ps.setString(5, time);
		ps.setString(6, cost);
		ps.setString(7, singer);
		ps.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	public void dateToDB(Exibitions exibition)
	{
		connectDB(path, login, password);
		String name = exibition.getName();
		String place = exibition.getPlace();
		String date = checkDate.format(exibition.getDate());
		String time = exibition.getTime();
		String cost = exibition.getCost().toString();
		String author = exibition.getAuthor();
		String topic = exibition.getTopic();
		
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO events(event, name, place, date, time, cost, author, topic) VALUE(?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, "exibition");
			ps.setString(2, name);
			ps.setString(3, place);
			ps.setString(4, date);
			ps.setString(5, time);
			ps.setString(6, cost);
			ps.setString(7, author);
			ps.setString(8, topic);
			ps.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	public void deleteDateDB(String name, String place, String date, String time)
	{
		connectDB(path, login, password);
		try {
			PreparedStatement ps= connection.prepareStatement("DELETE FROM events WHERE name=? AND place=? AND date=? AND time=?");
			ps.setString(1, name);
			ps.setString(2, place);
			ps.setString(3, date);
			ps.setString(4, time);
			int count = ps.executeUpdate();
			if(count==1)
			{
				System.out.println("Данные удалены");
			}
			else
			{
				System.out.println("Данные не удалены");
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		try {
			connection.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
	
//	public void updateDateDBFilm(String name, String place, String date, String time, String fild, String value)
//	{
//		connectDB(path, login, password);
//		try {
//			PreparedStatement ps= connection.prepareStatement("UPDATE events SET  WHERE name=? AND place=? AND date=? AND time=?");
//			ps.setString(1, name);
//			ps.setString(2, place);
//			ps.setString(3, date);
//			ps.setString(4, time);
//			int count = ps.executeUpdate();
//		} catch (SQLException e) {
//			
//			e.printStackTrace();
//		}
//		try {
//			connection.close();
//		} catch (SQLException e) {
//
//			e.printStackTrace();
//		}
//		
//	}

}