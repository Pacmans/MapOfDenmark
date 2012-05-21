package dataStructure;

import controller.Controller;

/**
 * @(#)AddressParser.java
 *
 * KF04-F2012
 * Att. Filip Sieczkowski
 *.
 * @author Pacmans
 * @version 14. maj. 2012
 */
public class AddressParser {

    private String[] address = {null, null, null, null, null};

    /**
     * The Primary method that takes a String and uses
     * the other methods to "cut" the string into components.
     * @param String		the address to be parsed.
     * @return String[]		an array of string that contains the given info
     * 						String[0] = road
     * 						String[1] = house number
     * 						String[2] = house letter (if the house got one)
     * 						String[3] = postal code
     * 						String[4] = County
     * @throws Exception
     */
    public String[] parseAddress(String s) throws Exception {
    	for(int i = 0; i < 5; i++){
    		 address[i] = null;
    	}
    	parseStreet(s);
        return address;

    }

    /**
     * Sets address[0] (road name)
     * @param String s		the string to find the road part from.
     */
    private void parseStreet(String s) throws Exception {
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
    
    /**
     * sets address[1] (House Number)
     * @param s			the String to find the house number part from.
     */

    private void parseNumber(String s) {
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
    
    /**
     * sets address[2] (House Letter)
     * 					only in the scenario that the string contains
     * a letter right after the house number, if there is not a letter
     * right after this the method just passes the string on.
     * 
     * @param s			the String to find the house number part from.
     */

    private void parseHouse(String s) {
        String x = s, house = "";

        String[] split = x.split(",");
            if (split[0].trim().length() > 0) {
                house = split[0];
        }
        
        x = s.substring(house.length());

        address[2] = house.trim();
        if(x.length() > 0) parseZip(x);
        else{
        	address[4] = "";
        }
    }
    /**
     * sets address[3] (zip/postal code)
     * 					in the case that there is more left on the String
     * 					we look for 4 numbers in a sequel which then must be 
     * 					the zip code.
     * @param s			the String to find the zip code from.
     */

    private void parseZip(String s) {
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
            zip = x.substring(start, slut+1);
            address[3] = zip.trim();
            x = x.substring(slut).trim();
        }
         // at the end we set address[4] to either Sweden (in the case that zip = 0) else we find it using the postal.
        
        address[4] = Controller.getInstance().getPostal().get(address[3]);
         
         if(address[4] == null) address[4] = "Sweden";
    }
}
