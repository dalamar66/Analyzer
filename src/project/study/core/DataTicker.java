package project.study.core;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import project.model.Quote;

public class DataTicker {

	private String ticker;
	protected ArrayList<Quote> quotes = new ArrayList<Quote>();
	protected boolean sorted = false;
	
	public String getTicker() {
		return ticker;
	}
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	protected ArrayList<Quote> getQuotes() {
		return quotes;
	}
	protected void setQuotes(ArrayList<Quote> quotes) {
		this.quotes = quotes;
	}

	public int numberQuotes() {
		return quotes.size();
	}
	
	public Quote getQuoteBefore(Quote quote) {
		if (this.sorted == false) {
			Collections.sort(quotes);
			this.sorted = true;
		}
		
		if (quotes.indexOf(quote) != 0) {
			return quotes.get(quotes.indexOf(quote)-1);
		} else {
			return quote;
		}
	}
	
	public Quote getQuoteAfter(Quote quote) {
		if (this.sorted == false) {
			Collections.sort(quotes);
			this.sorted = true;
		}

		if (quotes.indexOf(quote) != (quotes.size()-1)) {
			return quotes.get(quotes.indexOf(quote)+1);
		} else {
			return quote;
		}
	}

	public Calendar getFirstDate() {
		if (this.sorted == false) {
			Collections.sort(quotes);
			this.sorted = true;
		}
		
		return quotes.get(quotes.size()-1).getDate();
	}
	
	public Calendar getLastDate() {
		if (this.sorted == false) {
			Collections.sort(quotes);
			this.sorted = true;
		}
			
		return quotes.get(0).getDate();		
	}
	
	public void load(List<String> data) {
		try {
			Quote quote = Quote.load(data);
			this.quotes.add(quote);
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
