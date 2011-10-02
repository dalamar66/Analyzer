package project.execute;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http.HttpConstants;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.params.DefaultHttpParams;
import org.springframework.expression.ParseException;

import project.execute.core.GetUrl;
import project.execute.core.TodayDate;
import project.study.Simple;
import project.study.core.Study;

public class EDownload { 
	
	static CamelContext context = new DefaultCamelContext();

	public static void main(String[] args) {

		EDownload exec = new EDownload();
		exec.test();
				
	}
	
	public void test() {

		try {
			context.addRoutes(this.buildRoute());
			context.start();
			
	    	String urlFTSE = "http://ichart.finance.yahoo.com/table.csv?s=%5EFTSE&amp;d=8&amp;e=30&amp;f=2011&amp;g=d&amp;a=3&amp;b=2&amp;c=1984&amp;ignore=.csv";
	    	String urlIBEX = "http://ichart.finance.yahoo.com/table.csv?s=%5EIBEX&amp;d=9&amp;e=2&amp;f=2011&amp;g=d&amp;a=1&amp;b=15&amp;c=1993&amp;ignore=.csv";
	    	String urlHANGSENG = "http://ichart.finance.yahoo.com/table.csv?s=%5EHSI&amp;d=9&amp;e=2&amp;f=2011&amp;g=d&amp;a=11&amp;b=31&amp;c=1986&amp;ignore=.csv";
	    	String urlDAX = "http://ichart.finance.yahoo.com/table.csv?s=%5EGDAXI&amp;d=9&amp;e=2&amp;f=2011&amp;g=d&amp;a=10&amp;b=26&amp;c=1990&amp;ignore=.csv";
	    	String urlSP500 = "http://ichart.finance.yahoo.com/table.csv?s=%5EGSPC&amp;d=9&amp;e=2&amp;f=2011&amp;g=d&amp;a=0&amp;b=3&amp;c=1950&amp;ignore=.csv";
	    	String urlDowJones = "http://ichart.finance.yahoo.com/table.csv?s=%5EDJI&amp;d=9&amp;e=2&amp;f=2011&amp;g=d&amp;a=9&amp;b=1&amp;c=1928&amp;ignore=.csv";
	    	String urlNasdaq = "http://ichart.finance.yahoo.com/table.csv?s=NDAQ&amp;d=9&amp;e=2&amp;f=2011&amp;g=d&amp;a=6&amp;b=2&amp;c=2002&amp;ignore=.csv";
	    	
	    	GetUrl geturl = new GetUrl();
	    	
	    	geturl.retrieve(context, urlFTSE, "FTSE_Index");
	    	geturl.retrieve(context, urlIBEX, "Ibex35_Index");
	    	geturl.retrieve(context, urlHANGSENG, "HangSeng_Index");
	    	geturl.retrieve(context, urlDAX, "DAX_Index");
	    	geturl.retrieve(context, urlSP500, "SP500_Index");
	    	geturl.retrieve(context, urlDowJones, "DowJones_Index");
	    	geturl.retrieve(context, urlNasdaq, "Nasdaq_Index");
	    	
	    	TodayDate.writeUpdatedDate();
			
	    	for (;;) {
	    		Thread.sleep(1000000);
	    	}
			
		} catch (ParseException e) { 
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public RouteBuilder buildRoute() {
		return new RouteBuilder() {
		    public void configure() {
		    	from("direct:retrieve").to("file://C:/input/data?fileName=${in.header.fileName}.csv");
		    }
		};

	}
	
}
