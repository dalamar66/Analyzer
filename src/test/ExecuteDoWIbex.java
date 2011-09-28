package test;

import org.apache.camel.builder.RouteBuilder;

import project.study.DayOfTheWeek;	
import project.study.Simple;
import project.study.core.DataTicker;
import project.study.core.Study;

public class ExecuteDoWIbex extends StudyTest{ 
	
	static DayOfTheWeek study = new DayOfTheWeek();
	
	public static void main(String[] args) {
		
		String tickerIbex = "Ibex 35";
		String fileNameIbex = "tableIbex35.csv";
		DayOfTheWeek dowStudyIbex = new DayOfTheWeek();
		
		try {
			context.addRoutes(buildRoute(tickerIbex, fileNameIbex, dowStudyIbex));
			context.start();
			
			boolean done = false;
			for (;;) {
				if ((getLoaded()==4643) && !done) {
					Thread.sleep(2000);
					System.out.println("Quotes loaded");
					
					done = true;
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static RouteBuilder buildRoute(final String ticker, final String fileName, final DataTicker study) {
		return new RouteBuilder() {
		    public void configure() {
		    	study.setTicker(ticker);
		    	from("file://C:/input?fileName="+fileName+"&delete=false").unmarshal().csv().split().simple("body").bean(study, "load");
		    }
		};

	}

	public void run(Simple study) {
		study.processQuotes();					
		printResults(study);
		study.reset();		
	}
	
	public void printTestResults(Study study) {
		
		DayOfTheWeek dowStudy = (DayOfTheWeek)study;
		
		System.out.println("Monday: "+decFormat.format(dowStudy.getDay(0).getPercent()));
		System.out.println("Monday: "+decFormat.format(dowStudy.getDay(0).getProb()*100));
		System.out.println("Tuesday: "+decFormat.format(dowStudy.getDay(1).getPercent()));
		System.out.println("Tuesday: "+decFormat.format(dowStudy.getDay(1).getProb()*100));
		System.out.println("Wednesday: "+decFormat.format(dowStudy.getDay(2).getPercent()));
		System.out.println("Wednesday: "+decFormat.format(dowStudy.getDay(2).getProb()*100));
		System.out.println("Thursday: "+decFormat.format(dowStudy.getDay(3).getPercent()));
		System.out.println("Thursday: "+decFormat.format(dowStudy.getDay(3).getProb()*100));
		System.out.println("Friday: "+decFormat.format(dowStudy.getDay(4).getPercent()));
		System.out.println("Friday: "+decFormat.format(dowStudy.getDay(4).getProb()*100));
		System.out.println("-------------------------");
	}

}
