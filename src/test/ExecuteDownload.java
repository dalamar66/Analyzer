package test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.expression.ParseException;

import project.study.Simple;
import project.study.core.Study;

public class ExecuteDownload { 
	
	static CamelContext context = new DefaultCamelContext();

	public static void main(String[] args) {

		ExecuteDownload exec = new ExecuteDownload();
		exec.test();
				
	}
	
	public void test() {

		try {
			context.addRoutes(buildRoute(tickerIbex, fileNameIbex, simpleStudyIbex));
			context.start();
			
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
		    	from("file://C:/input?fileName="+fileName+"&delete=false").unmarshal().csv().split().simple("body").bean(study, "load");
		    }
		};

	}
	
}
