package project.execute;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.expression.ParseException;

import project.execute.core.StudyTest;
import project.study.Simple;
import project.study.core.Study;

public class ESimple extends StudyTest { 
	
	public static void main(String[] args) {

		ESimple exec = new ESimple();
		exec.test();
				
	}
	
	public void test() {

		Simple simpleStudyIbex = new Simple(Simple.OPENCLOSE);
		String tickerIbex = "Ibex 35";
		String fileNameIbex = "tableIbex35.csv";

		try {
			context.addRoutes(buildRoute(tickerIbex, fileNameIbex, simpleStudyIbex));
			context.start();
			
			boolean done = false;
			this.wait();
		} catch (ParseException e) { 
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public RouteBuilder buildRoute(final String ticker, final String fileName, final Simple study) {
		return new RouteBuilder() {
		    public void configure() {
		    	study.setTicker(ticker);
		    	from("file://C:/input?fileName="+fileName+"&noop=true").unmarshal().csv().split().simple("body").bean(study, "load");
		    }
		};

	}

	public void run(Simple study, int type) {
		study.setType(type);
		study.processQuotes();					
		printResults(study);
		study.reset();		
	}
	
	public void printTestResults(Study study) {
		
		Simple simpleStudy = (Simple)study;
		
		System.out.println("Number Quotes: "+simpleStudy.getNumberQuotes());
		System.out.println("Number Processed: "+simpleStudy.getNumProcessed());
		System.out.println("Gain: "+decFormat.format(simpleStudy.getTotalIncrement()));
		System.out.println("Prob: "+decFormat.format(simpleStudy.getProb()*100));
	}

	
}
