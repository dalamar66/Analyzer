package project.execute.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import project.study.core.Constants;

public class TodayDate {

	Date today = new Date();
	Date lastUpdate = new Date();

	public boolean isUpdated() {
		return true;
	}

	public static void writeUpdatedDate() {
		File file = new File(Constants.PATHDATA + "/.update");
		boolean exist = false;
		try {
			exist = file.createNewFile();
			FileWriter fstream = new FileWriter(Constants.PATHDATA + "/.update");
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(DateFormat.getInstance().format(new Date()));
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!exist) {
			System.out.println("File created successfully.");
		} else {
			System.out.println("File updated successfully.");
		}
	}

	public static Date readUpdatedDate() {
		File file = new File(Constants.PATHDATA + "/.update");
		try {
			FileReader fstream = new FileReader(Constants.PATHDATA + "/.update");
			BufferedReader in = new BufferedReader(fstream);
			String date = in.readLine();
			DateFormat format = DateFormat.getDateTimeInstance(
					DateFormat.MEDIUM, DateFormat.SHORT);

			return format.parse(date);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
