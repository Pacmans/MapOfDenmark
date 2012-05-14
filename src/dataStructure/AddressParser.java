package dataStructure;

<<<<<<< HEAD
=======
import controller.Controller;

>>>>>>> a6faaf556b0bad2cedaea3a934eccbe826b35068
/**
 * @(#)AddressParser.java
 *
 * KF04-F2012
 * Att. Filip Sieczkowski
 *.
 * @author Pacmans
 * @version 19. Feb. 2012
 */
public class AddressParser {

    private String[] address = {null, null, null, null, null};

    /*
     * Method in question
     * @param String
     * @return String[]
     * @throws Exception
     */
    public String[] parseAddress(String s) throws Exception {
    	for(int i = 0; i < 5; i++){
    		 address[i] = null;
    	}
    	parseStreet(s);
<<<<<<< HEAD
        return address;
=======
    	return address;
>>>>>>> a6faaf556b0bad2cedaea3a934eccbe826b35068
    }

    /*
     * Sets address[0]
     * @param String s
     * @return rest of param string
     */
    public void parseStreet(String s) throws Exception {
        String x = s, street = "";
        int i;
        for (i = 0; i < s.length(); i++) {
            //if next char is digit, street name must be complete
            if (Character.isDigit(s.charAt(i))) {
                street = s.substring(0, i);
                x = s.substring(i, s.length());
                break;
            }
        }
        if (i == s.length()) {
            street = s;
            address[1] = "0";
            address[2] = "";
            address[4] = "";
        }else{
        	parseNumber(x);
        }
        address[0] = street.trim();
    }

    public void parseNumber(String s) {
        String x = s, number = "";
        int end = 0;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                if (i + 1 == s.length() || !Character.isDigit(s.charAt(i + 1))) { //next char is not a digit
                    end = i + 1;
                    break;
                }
            }
        }
        number = s.substring(0, end);
        x = s.substring(end, s.length()).trim();
        address[1] = number.trim();
        if(x.length() > 1) parseHouse(x.trim());
        else{
        	address[2] = "";
        	address[4] = "";
        }
    }

    public void parseHouse(String s) {
        String x = s, house = "";

        //If "Rued Langgaards Vej 7 A"
        String[] split = x.split(",");
            if (split[0].trim().length() > 0) {
                house = split[0];
        }
        
<<<<<<< HEAD
        x = s.substring(house.length()+1);
=======
        x = s.substring(house.length());
>>>>>>> a6faaf556b0bad2cedaea3a934eccbe826b35068
        //We don't consider house letter to be larger than two characters

        address[2] = house.trim();
        if(x.length() > 0) parseZip(x);
        else{
        	address[4] = "";
        }
    }

    public void parseZip(String s) {
        String x = s.trim(), zip = "";
        int start = -2;
        int slut = 0;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) { //if current char is digit
                if (start == -2) {
                    start = i;
                }
                slut = i;
                if (i+1 < s.length() && !Character.isDigit(s.charAt(i + 1))) { //if next char is not a digit
                    slut = i + 1;
                    break;
                }
            }
        }
         if ((slut + start) >= 0) { //if zip
<<<<<<< HEAD
            zip = x.substring(start-1, slut);
            x = x.substring(slut).trim();
        }
        address[3] = zip.trim();
        if(x.length() > 1) address[4] = x.trim();
        else address[4] = "";
=======
            zip = x.substring(start, slut+1);
            address[3] = zip.trim();
            x = x.substring(slut).trim();
        }
        if(zip == "0") address[4] = "Sverige";
        else address[4] = Controller.getInstance().getPostal().get(address[3]);
>>>>>>> a6faaf556b0bad2cedaea3a934eccbe826b35068
    }
}
