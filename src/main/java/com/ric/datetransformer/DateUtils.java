/**
 * 
 */
package com.ric.datetransformer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Date Utility 
 *
 */
public class DateUtils {


	/**Converts the date to required output format
	 * Output format- Date- 21st-Jan-Twenty Twenty One
	 * Six Hours Seven Minutes Ten Seconds - Morning
	 * Day of week : Thursday
	 * @param dateStr
	 * @return
	 * @throws Exception
	 */
	public static void convertDate(String dateStr) throws Exception {

		
		String[] splitDate = dateStr.split(" ");

		String date = splitDate[0];
		String time = splitDate[1];
		String morningEve = splitDate[2];

		String[] d = date.split("-");

		String dd = ordinalValue(Integer.valueOf(d[0]));
		String monStr = convertMonth(d[1]);
		String year = convertNumToStr(Integer.valueOf(d[2]));

		System.out.println("Output is - \nDate is - " + dd + " " + monStr + " " + year);

		String[] t = time.split(":");

		String hh = null;
		String mm = null;
		String ss = null;

		if (isValidHour(Integer.valueOf(t[0]))) {
			hh = convertNumToStr(Integer.valueOf(t[0]));
		}
		if (isValidMin(Integer.valueOf(t[1]))) {
			mm = convertNumToStr(Integer.valueOf(t[1]));
		}

		if (isValidSec(Integer.valueOf(t[2]))) {
			ss = convertNumToStr(Integer.valueOf(t[2]));
		}

		String mornEveTime = convertAMPM(morningEve);

		System.out.println("Time is - " + hh + " hours " + mm + " minutes " + ss + " seconds - " + mornEveTime);

		System.out.println(
				"Day of week - " + getDayOfWeek(Integer.valueOf(d[0]), Integer.valueOf(d[1]), Integer.valueOf(d[2])));

		

	}

	
	/**Checks if the entered hour(s) is valid or not
	 * 
	 * @param num
	 * @return
	 * @throws Exception
	 */
	private static boolean isValidHour(int num) throws Exception {
		if (num > 0 && num <= 12)
			return true;
		else
			throw new Exception("Invalid Hour");
	}

	
	/**
	 * Checks if the entered minute(s) is valid or not
	 * @param num
	 * @return
	 * @throws Exception
	 */
	private static boolean isValidMin(int num) throws Exception {
		if (num >= 0 && num <= 60)
			return true;
		else
			throw new Exception("Invalid Minutes");
	}

	/**Checks if the entered second(s) is valid or not
	 * 
	 * @param num
	 * @return
	 * @throws Exception
	 */
	private static boolean isValidSec(int num) throws Exception {
		if (num >= 0 && num <= 60)
			return true;
		else
			throw new Exception("Invalid Seconds");
	}

	/**Returns Day of the week for the entered date
	 * 
	 * @param dd
	 * @param month
	 * @param year
	 * @return
	 * @throws ParseException
	 */
	private static String getDayOfWeek(int dd, int month, int year) throws ParseException {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		Date utilDate = formatter.parse(year + "/" + month + "/" + dd);
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE");
		return simpleDateformat.format(utilDate);

	}

	/**Infers Morning or Evening as per entered value -AM or PM
	 * 
	 * @param morningEve
	 * @return
	 * @throws Exception
	 */
	private static String convertAMPM(String morningEve) throws Exception {
		switch (morningEve) {
		case "AM":
			return "Morning";
		case "PM":
			return "Evening";
		default:
			throw new Exception("Invalid AM-PM");
		}
	}

	/**Converts a number to words
	 * 
	 * @param number
	 * @return
	 */
	private static String convertNumToStr(int number) {

		String str = "";

		String unitsArr[] = { "Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
				"Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen" };
		String tensArr[] = { "Zero", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty",
				"Ninety" };

		if (number == 0) {
			return "Zero";
		}
		if ((number / 100) > 0) {
			str += convertNumToStr(number / 100) + " ";
			number %= 100;
		}

		if (number > 0) {
			// if number in unit array then get it from unitArr otherwise from tensArray
			if (number < 20) {
				str += unitsArr[number];
			} else {
				// get value from tens array
				str += tensArr[number / 10];
				if ((number % 10) > 0) {
					str += " " + unitsArr[number % 10];
				}
			}
		}
		return str;
	}

	/**Converts the date to ordinal number
	 * 
	 * @param i
	 * @return
	 * @throws Exception
	 */
	private static String ordinalValue(int i) throws Exception {
		if (i > 0 && i < 31) {
			int mod100 = i % 100;
			int mod10 = i % 10;
			// Handle 11, 12,13 th
			if (mod10 == 1 && mod100 != 11) {
				return i + "st";
			} else if (mod10 == 2 && mod100 != 12) {
				return i + "nd";
			} else if (mod10 == 3 && mod100 != 13) {
				return i + "rd";
			} else {
				return i + "th";
			}
		} else {
			throw new Exception("Invalid Date for ordinal");
		}

	}

	/**Convert number to Month in Mon format
	 * 
	 * @param mon
	 * @return
	 * @throws Exception
	 */
	private static String convertMonth(String mon) throws Exception {
		String monStr = null;

		switch (mon) {
		case "01":
			monStr = "Jan";
			break;
		case "02":
			monStr = "Feb";
			break;
		case "03":
			monStr = "Mar";
			break;
		case "04":
			monStr = "Apr";
			break;
		case "05":
			monStr = "May";
			break;
		case "06":
			monStr = "Jun";
			break;
		case "07":
			monStr = "Jul";
			break;
		case "08":
			monStr = "Aug";
			break;
		case "09":
			monStr = "Sep";
			break;
		case "10":
			monStr = "Oct";
			break;
		case "11":
			monStr = "Nov";
			break;
		case "12":
			monStr = "Dec";
			break;

		default:
			throw new Exception("Invalid Month");

		}
		return monStr;

	}
}
