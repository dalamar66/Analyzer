package project.study;

import project.model.Quote;
import project.study.core.Study;

public class DayOfTheYear extends Study{

	private int day;
	private int month;
	private float prob;
	private float percent;
	
	private String description = "Day of the year";

	
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public float getProb() {
		return prob;
	}
	public void setProb(float prob) {
		this.prob = prob;
	}
	public float getPercent() {
		return percent;
	}
	public void setPercent(float percent) {
		this.percent = percent;
	}
	
	@Override
	public void process(Quote quote) {
		// TODO Auto-generated method stub
		
	}
	
	protected void clean(){
		this.percent = 0;
		this.prob = 0;
	}
	@Override
	protected String getDescription() {
		return description;
	}


}
