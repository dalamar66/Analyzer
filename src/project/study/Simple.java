package project.study;

import project.model.Quote;
import project.study.core.Study;

public class Simple extends Study {
	
	private String description1 = "Simple test";
	private String[] description2 = new String[] {
			"Between Close and Close", 
			"Between Open and Close", 
			"Between Close and Open"}; 
	
	public static final int CLOSECLOSE = 0;
	public static final int OPENCLOSE = 1;
	public static final int CLOSEOPEN = 2;
	
	private float totalIncrement = 0;
	private float up = 0;
	private int type = 0;

	public Simple() {
		this.type = Simple.CLOSECLOSE;
	}
	
	public Simple(int style) {
		this.type = style;
	}
	
	@Override
	public void process(Quote quote) {

		if (type == Simple.CLOSEOPEN) {
			totalIncrement += quote.getIncrementGap(this.getQuoteBefore(quote));
			if (quote.isGainGap(this.getQuoteBefore(quote))) {
				up++;
			}
		} else if (type == Simple.OPENCLOSE) {
			totalIncrement += quote.getIncrement();
			if (quote.isGain()) {
				up++;
			}
		} else if (type == Simple.CLOSECLOSE) {
			totalIncrement += quote.getIncrementFromYesterday(this.getQuoteBefore(quote));
			if (quote.isGainFromYesterday(this.getQuoteBefore(quote))) {
				up++;
			}
		}

		if (this.isCompleted()) {
//			System.out.println("Completed : " + isCompleted() + " totalIncrement " + totalIncrement + " up " + up);
			totalIncrement /= (float)getNumProcessed();
			up /= (float)getNumProcessed();
		}
	//	System.out.println("IsCompleted : "+this.isCompleted()+" NumQuotes: "+this.getNumberQuotes() + "  ");
		
	}
	
	public float getTotalIncrement() {
		return totalIncrement;
	}

	public float getProb() {
		return up;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	public String getDescription() {
		return description1 + " - " + description2[type];
	}

	protected void clean(){
		this.up = 0;
		this.totalIncrement = 0;
	}
}
