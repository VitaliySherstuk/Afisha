package by.htp.entity;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.Date;

public class Film extends Event {

	private Genre genreFilm;
	private double ratingFilm;
	
	
	public Film(){}
	public Film(String name, String place, Date date, String time, BigDecimal cost, Genre genreFilm, double ratingFilm)
	{
		super(name, place, date, time, cost);
		this.genreFilm = genreFilm;
		this.ratingFilm = ratingFilm;
	}

	public void setRatingFilm(double ratingFilm)
	{
		this.ratingFilm=ratingFilm;
	}
	public void setGenreFilm(Genre genreFilm)
	{
		this.genreFilm = genreFilm;
	}
	public Genre getGenre()
	{
		return genreFilm;
	}
	public double getRating()
	{
		return ratingFilm;
	}
	
//	@Override
//	public String toString() {
//		return "Film [genreFilm=" + genreFilm + ", ratingFilm=" + ratingFilm + ", getDate()=" + getDate() + ", getTime()=" + getTime() + ", getName()=" + getName() + ", getPlace()="
//				+ getPlace() + ", getCost()=" + getCost() + ", getClass()="
//				+ getClass() + "]";
//	}
	@Override
	public String toString() {
		DateFormat returnFormatDate = DateFormat.getDateInstance(DateFormat.LONG);
		//this.getDate().toString()
		return "Film"+" " + this.getName()+" " + this.getPlace()+" " +returnFormatDate.format(this.getDate()) +" "+this.getTime()+" "+this.getCost()+" "
		+this.genreFilm.toString()+" "+ String.valueOf(this.ratingFilm)+" "+"\n";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((genreFilm == null) ? 0 : genreFilm.hashCode());
		long temp;
		temp = Double.doubleToLongBits(ratingFilm);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		if (genreFilm != other.genreFilm)
			return false;
		if (Double.doubleToLongBits(ratingFilm) != Double.doubleToLongBits(other.ratingFilm))
			return false;
		return true;
	}
	
	
}
