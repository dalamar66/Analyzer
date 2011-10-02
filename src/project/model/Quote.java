package project.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;


import project.execute.EDayOfTheYear;
import project.execute.ESimple;
import project.execute.core.StudyTest;
import project.util.HashCodeUtil;

public class Quote implements Comparable<Quote> {

	private Calendar date;
	private double open;
	private double high;
	private double low;
	private double close;
	private int volume;
	
	public Calendar getDate() {
		return date;
	}
	public void setDate(Calendar date) {
		this.date = date;
	}
	public double getOpen() {
		return open;
	}
	public void setOpen(double open) {
		this.open = open;
	}
	public double getHigh() {
		return high;
	}
	public void setHigh(double high) {
		this.high = high;
	}
	public double getLow() {
		return low;
	}
	public void setLow(double low) {
		this.low = low;
	}
	public double getClose() {
		return close;
	}
	public void setClose(double close) {
		this.close = close;
	}
	public int getVolume() {
		return volume;
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}
	
	public double getIncrement() {
		return ((close-open)/open)*100;
	}
	
	public double getIncrementFromYesterday(Quote quoteYesterday) {
		if (quoteYesterday == this) return 0; // First quote
		return ((quoteYesterday.getClose()-close)/quoteYesterday.getClose())*100;
	}
	
	public double getIncrementGap(Quote quoteYesterday) {
		if (quoteYesterday == this) return 0; // First quote
		return ((quoteYesterday.getClose()-open)/quoteYesterday.getClose())*100;
	}
	
	public boolean isGain() {
		return getIncrement()>0;
	}

	public boolean isGainFromYesterday(Quote quoteYesterday) {
		return getIncrementFromYesterday(quoteYesterday)>0;
	}

	public boolean isGainGap(Quote quoteYesterday) {
		return getIncrementGap(quoteYesterday)>0;
	}

    public static Quote load(List<String> data) throws ParseException {
    	
    	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    	GregorianCalendar calendar = new GregorianCalendar();
    	calendar.setTime(df.parse(data.get(0))); 
    	
        Quote quote = new Quote();
        quote.setDate(calendar);
        quote.setOpen(Double.parseDouble(data.get(1)));
        quote.setHigh(Double.parseDouble(data.get(2)));
        quote.setLow(Double.parseDouble(data.get(3)));
        quote.setClose(Double.parseDouble(data.get(4)));
        quote.setVolume(Integer.parseInt(data.get(5)));
        StudyTest.setLoaded(EDayOfTheYear.getLoaded() + 1);
        //System.out.println("Loaded Quote "+ExecuteDoWIbex.loaded+": "+quote.getDate().getTime());
        return quote;
    }
    
	@Override
	public int compareTo(Quote quote) {
	    return (quote.getDate().compareTo(this.getDate()));
	}
	
	@Override
	public int hashCode() {
	     int result = HashCodeUtil.SEED;
	     result = HashCodeUtil.hash( result, this.close );
	     result = HashCodeUtil.hash( result, this.open );
	     result = HashCodeUtil.hash( result, this.low );
	     result = HashCodeUtil.hash( result, this.high );
	     result = HashCodeUtil.hash( result, this.volume );
	     result = HashCodeUtil.hash( result, this.date );
	     return result;
	}
	
	public boolean equals(Object object){
		if ( this == object ) return true;
	     if ( !(object instanceof Quote) ) return false;

	     Quote quote = (Quote)object;
	     return
	       ( this.close == quote.close) &&
	       ( this.open == quote.open ) &&
	       ( this.high == quote.high ) &&
	       ( this.low == quote.low ) &&
	       ( this.volume == quote.volume ) &&
	       ( this.date.equals(quote.date) );
	}
}
