package test;

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
			context.addRoutes(this.buildRoute());
			context.start();
			
			Thread.sleep(1000000);
			
		} catch (ParseException e) { 
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public RouteBuilder buildRoute() {
		return new RouteBuilder() {
		    public void configure() {
		    	
		    	String url = "http://ichart.finance.yahoo.com/table.csv?s=%5EFTSE&amp;d=8&amp;e=30&amp;f=2011&amp;g=d&amp;a=3&amp;b=2&amp;c=1984&amp;ignore=.csv";
		    	
		    	GetUrl geturl = new GetUrl();
		    	
		    	DefaultHttpParams.getDefaultParams().setParameter("http.protocol.cookie-policy",
		    			CookiePolicy.BROWSER_COMPATIBILITY);
		    	
		    	ProducerTemplate template;
		    	
		    	from("direct:a")
		    	.to("file://C:/input/data?fileName=target.csv");
		    }
		};

	}
	
}
