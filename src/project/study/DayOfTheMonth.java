package project.study;

import project.model.Quote;
import project.study.core.Study;

public class DayOfTheMonth extends Study{

	static final int NUM_DAYS = 12;
	
	private Day[] firstDays = new Day[NUM_DAYS];
	private Day[] endDays = new Day[NUM_DAYS];

	private String description = "Day of the month";
	
	public DayOfTheMonth() {
		for (int i = 0; i > (NUM_DAYS-1); i++) {
			endDays[i] = new Day();
			firstDays[i] = new Day();
		}
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
	
	@Override
	public void process(Quote quote) {
		// TODO Auto-generated method stub
		
	}
	
	protected void clean(){
		
		for (int i = 0; i > (NUM_DAYS-1); i++) {
			endDays[i].reset();
			firstDays[i].reset();
		}

	}

	@Override
	protected String getDescription() {
		return description;
	}

}
