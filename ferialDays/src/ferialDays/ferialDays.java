package ferialDays;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ferialDays {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		try {
			BufferedReader br = new BufferedReader(new FileReader("/home/omega/Desktop/realFerialDays.txt"));
			String date;
			while((date = br.readLine()) != null) {
				String[] cDate = date.split(" ");
				String realDate = "";
				//System.out.println(cDate[0] + " " + cDate[1]);//day month
				realDate += "2019-";
				
				if(cDate[1].trim().length() == 1) {
					realDate += "0"+cDate[1].trim()+"-";
				}else {
					realDate += cDate[1].trim()+"-";
				}
				
				if(cDate[0].trim().length() == 1) {
					realDate += "0"+cDate[0].trim();
				}else {
					realDate += cDate[0].trim();
				}
				
				System.out.println("new Date(\""+realDate+"T12:00:00Z\"),");
				/*String[] cDate = date.split("-");
				String realDate = "";
				realDate += cDate[2].trim()+"-";
				if(cDate[0].trim().length() == 1) {
					realDate += "0"+cDate[0].trim()+"-";
				}else {
					realDate += cDate[0].trim()+"-";
				}
				if(cDate[1].trim().length() == 1) {
					realDate += "0"+cDate[1].trim();
				}else {
					realDate += cDate[1].trim();
				}
				System.out.println(realDate+"T12:00:00Z");//month*/
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
