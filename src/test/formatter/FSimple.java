package test.formatter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import project.study.core.Constants;

public class FSimple {

	private ArrayList<Data> data = new ArrayList();

	HSSFWorkbook workbook;

	public void formatter() {
		workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Java Class Info");
		sheet.setColumnWidth((short) 0, (short) 10000);

		HSSFRow row = sheet.createRow((short) 0);

		HSSFFont font = workbook.createFont();
		font.setColor(HSSFFont.COLOR_RED);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// Create the style
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setFont(font);

		HSSFCell cell = row.createCell((short) 0);
		cell.setCellStyle(cellStyle);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue("Class Name ");

	}

	public void writeToFile() throws IOException {

		FileOutputStream fOut = new FileOutputStream(Constants.PATH
				+ "/Results/test1.xsl");
		workbook.write(fOut);
		fOut.flush();
		fOut.close();

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
