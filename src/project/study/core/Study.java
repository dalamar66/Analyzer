package project.study.core;

import java.util.Calendar;
import java.util.Date;

import project.model.Quote;

public abstract class Study extends DataTicker {

	private Calendar startDate;
	private Calendar endDate;
	
	private boolean completed;
	private int numProcessed;
	
	abstract protected void process(Quote quote);
	abstract protected void clean();
	abstract protected String getDescription();
	
	public void reset() {
		this.setCompleted(false);
		this.setNumProcessed(0);
		this.clean();
	}
	
	public Calendar getStartDate() {
		return startDate;
	}
	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}
	public Calendar getEndDate() {
		return endDate;
	}
	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}
	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	public void setNumProcessed(int numProcessed) {
		this.numProcessed = numProcessed;
	}

	public int getNumProcessed() {
		return numProcessed;
	}
	
	public int getNumberQuotes() {
		int counter = 0;
		if ((startDate != null) && (endDate != null)) {
			for (Quote quote:this.getQuotes()) {
				if ((quote.getDate().getTimeInMillis() <= (endDate.getTimeInMillis())) 
						&& (quote.getDate().getTimeInMillis() >= (startDate.getTimeInMillis()))) {
					counter++;
				}
			}
			return counter;
		} else {
				return getNumProcessed();
		}
	}
	
	private boolean quoteInRange(Quote quote) {
		return ((quote.getDate().getTimeInMillis() <= (this.getEndDate().getTimeInMillis())) 
				&& ((quote.getDate().getTimeInMillis() >= (this.getStartDate().getTimeInMillis()))));
	}
	
	public void processQuotes() {
		completed = false;
		for (Quote quote: this.getQuotes()) {
			if (this.quoteInRange(quote)) {
				this.process(quote);
				numProcessed++;
				//System.out.println("NumProcessed: "+numProcessed+"  ");
				if (getNumProcessed() == (getNumberQuotes()-1)) {
					//System.out.println("Completed: "+numProcessed+"  ");
					completed = true;
				}
			}
		}
	}

}
