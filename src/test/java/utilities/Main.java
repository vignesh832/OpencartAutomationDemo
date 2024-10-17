package utilities;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Main {
	 
	public static void main(String[] args) {
		SimpleDateFormat sd= new SimpleDateFormat("yyyy-MM-dd,HH:mm:SS");
		String date =sd.format(new Date());
		System.out.println(date);
	}

}
