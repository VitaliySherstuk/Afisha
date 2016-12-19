package by.htp.entity;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.Date;

public class Exibitions extends Event{

	private String author;
	private String topic;
	
	public Exibitions(){}
	public Exibitions(String name, String place, Date date, String time, BigDecimal cost, String author, String topic)
	{
		super(name, place, date, time, cost);
		this.author = author;
		this.topic = topic;
	}
	
	public void setAuthor(String author)
	{
		this.author=author;
	}
	
	public void setTopic(String topic)
	{
		this.topic=topic;
	}
	public String getAuthor()
	{
		return author;
	}
	public String getTopic()
	{
		return topic;
	}
	
//	@Override
//	public String toString() {
//		return "Exibitions [author=" + author + ", topic=" + topic + ", getDate()="
//				+ getDate() + ", getTime()=" + getTime() + ", getName()=" + getName() + ", getPlace()=" + getPlace()
//				+ ", getCost()=" + getCost() + ",  getClass()=" + getClass() + "]";
//	}
	
	public String toString() {
		DateFormat returnFormatDate = DateFormat.getDateInstance(DateFormat.LONG);
		return "Exibition"+" " + this.getName()+" " + this.getPlace()+" " + returnFormatDate.format(this.getDate())+" "+this.getTime()+" "+this.getCost()+" "
		+this.author+" "+this.topic+"\n";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((topic == null) ? 0 : topic.hashCode());
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
		Exibitions other = (Exibitions) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (topic == null) {
			if (other.topic != null)
				return false;
		} else if (!topic.equals(other.topic))
			return false;
		return true;
	}
}
