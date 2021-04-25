package com.ric.datetransformer;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DatetransformerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatetransformerApplication.class, args);
//		String dateStr = "27-04-2021 12:00:10 AM";

		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter a date");

		String dateStr = scanner.nextLine();

		System.out.println("Entered date is - " + dateStr);
		try {
			DateUtils.convertDate(dateStr);
		} catch (Exception e) {
			System.out.println("Invalid input date format : " + dateStr);
			scanner.close();
			e.printStackTrace();
		}
	}

	
}
