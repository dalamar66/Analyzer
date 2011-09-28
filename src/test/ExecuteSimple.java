package test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.expression.ParseException;

import project.study.Simple;
import project.study.core.Study;

public class ExecuteSimple extends StudyTest { 
	
	public static void main(String[] args) {

		ExecuteSimple exec = new ExecuteSimple();
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
			for (;;) {}
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
		    	from("http://ichart.finance.yahoo.com/table.csv?s=%5EGDAXI&amp;d=8&amp;e=25&amp;f=2011&amp;g=d&amp;a=10&amp;b=26&amp;c=1990&amp;ignore=.csv").to("");
		    }
		};

	}
	
}
