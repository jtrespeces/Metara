package Downloader;

import java.util.Scanner;

public class ICAO {
	String code;
	public ICAO() {
	}
	public String getCode() {
		System.out.println("Enter the ICAO code");
		String input;
		Scanner rl = new Scanner(System.in);
		input = rl.nextLine();
		if (input.length()==4) {
			code = input.toUpperCase();
		} 
                else {
			System.out.println("No METAR available for this entered place");
		}
		rl.close();
		return code;
	}
}
