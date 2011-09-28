package test;

import java.text.DateFormat;
import java.text.DecimalFormat;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

import project.study.Simple;
import project.study.core.Study;

public abstract class StudyTest {
	
	abstract public void printTestResults(Study study);

	private static int loaded = 0;
	
	static CamelContext context = new DefaultCamelContext();

	DecimalFormat decFormat = new DecimalFormat("##.####");

	DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT);

	public void printResults(Simple study) {
		
		System.out.println("-------------------------");		
		System.out.println(study.getDescription());
		System.out.println("Ticker: "+study.getTicker());
		System.out.println("StartDate: "+dateFormat.format(study.getStartDate().getTime()));
		System.out.println("EndDate: "+dateFormat.format(study.getEndDate().getTime()));
		System.out.println("-------------------------");
		printTestResults(study);
	}

	public static void setLoaded(int loaded) {
		StudyTest.loaded = loaded;
	}

	public static int getLoaded() {
		return loaded;
	}

}
