package project.study;

import java.util.Calendar;
import project.model.Quote;
import project.study.core.Study;

public class DayOfTheWeek  extends Study {
	
	private Day[] days = new Day[5];
	private String description = "Day of the week";
	
	public DayOfTheWeek() {
		days[0] = new Day();
		days[1] = new Day();
		days[2] = new Day();
		days[3] = new Day();
		days[4] = new Day();
	}
	
	public class Day {
		private double prob;
		private double percent;
		private int counter = 0;
		
		public double getProb() {
			return prob;
		}
		public void addProb(double prob) {
			this.prob = this.prob + (prob/(getNumProcessed()+1));
		}
		public double getPercent() {
			return percent;
		}
		public void addPercent(double percent) {
			this.percent = this.percent + (percent/(getNumProcessed()+1));
		}
		public void reset() {
			this.counter = 0;
			this.percent = 0;
			this.prob = 0;
		}
		public int getCounter() {
			return counter;
		}	

	}
		
	public Day getDay(int num) {
		return days[num];
	}
	
	@Override
	public void process(Quote quote) {

		int dayOfWeek = quote.getDate().get(Calendar.DAY_OF_WEEK)-2;    // 6=Friday
		double percent = quote.getIncrement();
		int up = 0;
		
		if (quote.isGain()) up = 1;
		
		days[dayOfWeek].addPercent(percent);
		days[dayOfWeek].addProb(up);
		
	}
	
	protected void clean(){
		days[0].reset();
		days[1].reset();
		days[2].reset();
		days[3].reset();
		days[4].reset();
	}

	@Override
	protected String getDescription() {
		return description;
	}

}
