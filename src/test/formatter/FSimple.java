package test.formatter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import org.apache.camel.builder.RouteBuilder;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.expression.ParseException;

import project.execute.core.StudyTest;
import project.study.Simple;
import project.study.core.Study;

public class FSimple {

	private ArrayList<Data> data = new ArrayList();
	public static final String WIDGET_LIST_KEY = "widgetList";
	protected static final short WIDGET_NAME_COLUMN = 0;
	protected static final short WIDGET_SIZE_COLUMN = 1;
	HSSFWorkbook workbook;

	public void formatter() {
		HSSFSheet sheet = workbook.createSheet("Widget List");
		sheet.setDefaultColumnWidth((short) 12);

		// GETCELL: getCell(SHEET, ROW, COLUMN);
		short currentRow = 0;

		// WRITE ROW FOR HEADER
		HSSFCell header0 = getCell(sheet, currentRow, WIDGET_NAME_COLUMN, false);
		// setText(header0, "NAME");

		HSSFCell header1 = getCell(sheet, currentRow, WIDGET_SIZE_COLUMN, false);
		// setText(header1, "SIZE");

		/*
		 * List widgetList = (List) model.get(WIDGET_LIST_KEY); Iterator
		 * widgetListIterator = widgetList.iterator();
		 * 
		 * while (widgetListIterator.hasNext()) { currentRow++; Widget widget =
		 * (Widget) widgetListIterator.next(); HSSFRow row =
		 * sheet.createRow(currentRow); row.createCell(WIDGET_NAME_COLUMN)
		 * .setCellValue(widget.getName()); row.createCell(WIDGET_SIZE_COLUMN)
		 * .setCellValue(widget.getSize()); } }
		 */
	}

	public void writeToFile() {

	}

	public void writeToFTP() {

	}

	protected HSSFCellStyle getCellStyle(short boldweight, boolean italic) {
		final HSSFCellStyle style = workbook.createCellStyle();
		final HSSFFont font = workbook.createFont();
		font.setBoldweight(boldweight);
		font.setItalic(italic);
		style.setFont(font);
		return style;
	}

	protected static HSSFCell getCell(HSSFSheet sheet, int row, int col,
			boolean create) {
		HSSFRow xlsRow = sheet.getRow(row);
		if (xlsRow == null) {
			if (!create)
				return null;
			xlsRow = sheet.createRow(row);
		}
		HSSFCell xlsCell = xlsRow.getCell((short) col);
		if (xlsCell == null) {
			if (!create)
				return null;
			xlsCell = xlsRow.createCell((short) col);
		}
		return xlsCell;
	}

	protected static void writeToCell(HSSFSheet sheet, int row, int col,
			String value) {
		writeToCell(sheet, row, col, value, null);
	}

	protected static void writeToCell(HSSFSheet sheet, int row, int col,
			String value, HSSFCellStyle style) {
		final HSSFCell xlsCell = getCell(sheet, row, col, true);
		xlsCell.setCellType(HSSFCell.CELL_TYPE_STRING);
		// xlsCell.setEncoding(HSSFCell.ENCODING_UTF_16);
		// xlsCell.setCellValue(new String(value.getBytes(CHARSET_UTF_16),
		// CHARSET_UTF_16));
		xlsCell.setCellValue(new HSSFRichTextString(value));
		if (style != null) {
			xlsCell.setCellStyle(style);
		}
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
