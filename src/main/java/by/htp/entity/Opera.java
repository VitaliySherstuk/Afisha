package by.htp.entity;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.Date;

public class Opera extends Event {

	private String singer;
	
	public Opera(){}
	public Opera(String name, String place, Date date, String time, BigDecimal cost, String singer)
	{
		super(name, place, date, time, cost);
		this.singer = singer;
	}
	
	public void setSingerOpera(String singer)
	{
		this.singer = singer;
	}
	public String getSinger()
	{
		return singer;
	}
	
//	@Override
//	public String toString() {
//		return "Opera [singer=" + singer + ",  getDate()=" + getDate() + ", getTime()="
//				+ getTime() + ", getName()=" + getName() + ", getPlace()=" + getPlace() + ", getCost()=" + getCost()
//				+ ", getClass()=" + getClass() + "]";
//	}
	
	public String toString() {
		DateFormat returnFormatDate = DateFormat.getDateInstance(DateFormat.LONG);
		return "Opera"+" " + this.getName()+" " + this.getPlace()+" " + returnFormatDate.format(this.getDate())+" "+this.getTime()+" "+this.getCost()+" "
		+this.singer+" "+"\n";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((singer == null) ? 0 : singer.hashCode());
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
		Opera other = (Opera) obj;
		if (singer == null) {
			if (other.singer != null)
				return false;
		} else if (!singer.equals(other.singer))
			return false;
		return true;
	}

}
