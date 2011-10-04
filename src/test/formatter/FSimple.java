package test.formatter;

import java.io.FileNotFoundException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import project.study.core.Constants;
import test.formatter.core.Formatter;

public class FSimple extends Formatter{
	
	public FSimple() {
		headersCol.add("Ticker");
		headersCol.add("Start Date");
		headersCol.add("End Date");
		headersCol.add("Avg. Profit");
		headersCol.add("Perc. Profit");
	}

	ArrayList<String> headersCol = new ArrayList<String>();
	ArrayList<String> headersRow = new ArrayList<String>();

	private ArrayList<Data> data = new ArrayList();

	public void format() {
		
		wb = new XSSFWorkbook();
		
		Map styles = createStyles(wb);
        Sheet sheet = wb.createSheet("Loan Calculator");
        sheet.setPrintGridlines(false);
        sheet.setDisplayGridlines(false);
        PrintSetup printSetup = sheet.getPrintSetup();
        printSetup.setLandscape(true);
        sheet.setFitToPage(true);
        sheet.setHorizontallyCenter(true);
        sheet.setColumnWidth(0, 768);
        sheet.setColumnWidth(1, 768);
        sheet.setColumnWidth(2, 2816);
        sheet.setColumnWidth(3, 3584);
        sheet.setColumnWidth(4, 3584);
        sheet.setColumnWidth(5, 3584);
        sheet.setColumnWidth(6, 3584);
        
        Row titleRow = sheet.createRow(0);
        titleRow.setHeightInPoints(35F);
        for(int i = 1; i <= 7; i++)
            titleRow.createCell(i).setCellStyle((CellStyle)styles.get("title"));

        Cell titleCell = titleRow.getCell(2);
        titleCell.setCellValue("Simple");

	}
	
    private static Map createStyles(Workbook wb)
    {
        Map styles = new HashMap();
        Font titleFont = wb.createFont();
        titleFont.setFontHeightInPoints((short)14);
        titleFont.setFontName("Trebuchet MS");
        CellStyle style = wb.createCellStyle();
        style.setFont(titleFont);
        style.setBorderBottom((short)7);
        style.setBottomBorderColor(IndexedColors.GREY_40_PERCENT.getIndex());
        styles.put("title", style);
        Font itemFont = wb.createFont();
        itemFont.setFontHeightInPoints((short)9);
        itemFont.setFontName("Trebuchet MS");
        style = wb.createCellStyle();
        style.setAlignment((short)1);
        style.setFont(itemFont);
        styles.put("item_left", style);
        style = wb.createCellStyle();
        style.setAlignment((short)3);
        style.setFont(itemFont);
        styles.put("item_right", style);
        style = wb.createCellStyle();
        style.setAlignment((short)3);
        style.setFont(itemFont);
        style.setBorderRight((short)7);
        style.setRightBorderColor(IndexedColors.GREY_40_PERCENT.getIndex());
        style.setBorderBottom((short)7);
        style.setBottomBorderColor(IndexedColors.GREY_40_PERCENT.getIndex());
        style.setBorderLeft((short)7);
        style.setLeftBorderColor(IndexedColors.GREY_40_PERCENT.getIndex());
        style.setBorderTop((short)7);
        style.setTopBorderColor(IndexedColors.GREY_40_PERCENT.getIndex());
        style.setDataFormat(wb.createDataFormat().getFormat("_($* #,##0.00_);_($* (#,##0.00);_($* \"-\"??_);_(@_)"));
        styles.put("input_$", style);
        style = wb.createCellStyle();
        style.setAlignment((short)3);
        style.setFont(itemFont);
        style.setBorderRight((short)7);
        style.setRightBorderColor(IndexedColors.GREY_40_PERCENT.getIndex());
        style.setBorderBottom((short)7);
        style.setBottomBorderColor(IndexedColors.GREY_40_PERCENT.getIndex());
        style.setBorderLeft((short)7);
        style.setLeftBorderColor(IndexedColors.GREY_40_PERCENT.getIndex());
        style.setBorderTop((short)7);
        style.setTopBorderColor(IndexedColors.GREY_40_PERCENT.getIndex());
        style.setDataFormat(wb.createDataFormat().getFormat("0.000%"));
        styles.put("input_%", style);
        style = wb.createCellStyle();
        style.setAlignment((short)3);
        style.setFont(itemFont);
        style.setBorderRight((short)7);
        style.setRightBorderColor(IndexedColors.GREY_40_PERCENT.getIndex());
        style.setBorderBottom((short)7);
        style.setBottomBorderColor(IndexedColors.GREY_40_PERCENT.getIndex());
        style.setBorderLeft((short)7);
        style.setLeftBorderColor(IndexedColors.GREY_40_PERCENT.getIndex());
        style.setBorderTop((short)7);
        style.setTopBorderColor(IndexedColors.GREY_40_PERCENT.getIndex());
        style.setDataFormat(wb.createDataFormat().getFormat("0"));
        styles.put("input_i", style);
        style = wb.createCellStyle();
        style.setAlignment((short)2);
        style.setFont(itemFont);
        style.setDataFormat(wb.createDataFormat().getFormat("m/d/yy"));
        styles.put("input_d", style);
        style = wb.createCellStyle();
        style.setAlignment((short)3);
        style.setFont(itemFont);
        style.setBorderRight((short)7);
        style.setRightBorderColor(IndexedColors.GREY_40_PERCENT.getIndex());
        style.setBorderBottom((short)7);
        style.setBottomBorderColor(IndexedColors.GREY_40_PERCENT.getIndex());
        style.setBorderLeft((short)7);
        style.setLeftBorderColor(IndexedColors.GREY_40_PERCENT.getIndex());
        style.setBorderTop((short)7);
        style.setTopBorderColor(IndexedColors.GREY_40_PERCENT.getIndex());
        style.setDataFormat(wb.createDataFormat().getFormat("$##,##0.00"));
        style.setBorderBottom((short)7);
        style.setBottomBorderColor(IndexedColors.GREY_40_PERCENT.getIndex());
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern((short)1);
        styles.put("formula_$", style);
        style = wb.createCellStyle();
        style.setAlignment((short)3);
        style.setFont(itemFont);
        style.setBorderRight((short)7);
        style.setRightBorderColor(IndexedColors.GREY_40_PERCENT.getIndex());
        style.setBorderBottom((short)7);
        style.setBottomBorderColor(IndexedColors.GREY_40_PERCENT.getIndex());
        style.setBorderLeft((short)7);
        style.setLeftBorderColor(IndexedColors.GREY_40_PERCENT.getIndex());
        style.setBorderTop((short)7);
        style.setTopBorderColor(IndexedColors.GREY_40_PERCENT.getIndex());
        style.setDataFormat(wb.createDataFormat().getFormat("0"));
        style.setBorderBottom((short)7);
        style.setBottomBorderColor(IndexedColors.GREY_40_PERCENT.getIndex());
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern((short)1);
        styles.put("formula_i", style);
        return styles;
    }


	public void writeToFile() throws IOException {

		FileOutputStream fOut = new FileOutputStream(Constants.PATH
				+ "/Results/test1.xsl");
		wb.write(fOut);
		fOut.flush();
		fOut.close();

	}

	public void writeToFTP() {

	}

	public class Data {
		private Date initDate;
		private Date endDate;
		private String ticker;

		public String getTicker() {
			return ticker;
		}

		public void setTicker(String ticker) {
			this.ticker = ticker;
		}

		public Double getGain() {
			return gain;
		}

		public void setGain(Double gain) {
			this.gain = gain;
		}

		public Double getProb() {
			return prob;
		}

		public void setProb(Double prob) {
			this.prob = prob;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		private Double gain;
		private Double prob;
		private String type;

		public void setInitDate(Date initDate) {
			this.initDate = initDate;
		}

		public Date getInitDate() {
			return initDate;
		}

		public void setEndDate(Date endDate) {
			this.endDate = endDate;
		}

		public Date getEndDate() {
			return endDate;
		}
	}
}
