package AddressParser;

/**
 * @(#)AddressParser.java
 *
 * KF04-F2012
 * Att. Filip Sieczkowski
 *
 * @author Pacmans
 * @version 19. Feb. 2012
 */
public class AddressParser {

    private String[] address = {"", "", "", "", "", ""};

    public static void main(String[] args) {
        AddressParser addressParser = new AddressParser();
    }

    public void valid(String s) throws Exception {
        if (s.contains("#")) {
            throw new Exception("Not a valid address");
        }
        if (s.length() < 5) {
            throw new Exception("Address too short");
        }
    }

    /*
     * Method in question
     * @param String
     * @return String[]
     * @throws Exception
     */
    public String[] parseAddress(String s) throws Exception {
        try {
            valid(s);

            parseCity(parseZip(parseFloor(parseHouse(parseNumber(parseStreet(s))))));
        } catch (Exception e) {
            System.out.println(e);
            String[] str = {""};
            return str;
        }
        return address;
    }

    /*
     * Sets address[0]
     * @param String s
     * @return rest of param string
     */
    public String parseStreet(String s) throws Exception {
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
            throw new Exception("Address must contain numbers");
        }
        address[0] = street.trim() + "#";
        return x.trim();
    }

    public String parseNumber(String s) {
        String x = s, number = "";
        int slut = 0;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                if (i + 1 == s.length() || !Character.isDigit(s.charAt(i + 1))) { //next char is not a digit
                    slut = i + 1;
                    break;
                }
            }
        }
        number = s.substring(0, slut).trim();
        x = s.substring(slut, s.length());
        if (number.length() >= 4) { //if number larger than 4 (could be zip)
            number = "";
            x = s;
        }
        address[1] = number.trim() + "#";
        return x.trim();
    }

    public String parseHouse(String s) {
        String x = s, house = "";

        //If "Rued Langgaards Vej 7 A"
        int i;
        for (i = 0; i < s.length(); i++) {
            if (Character.isAlphabetic(s.charAt(i))) {
                house += s.charAt(i);
            } else {
                break;
            }
        }
        house = s.substring(0, i);
        x = s.substring(i, s.length());
        //We don't consider house letter to be larger than two characters

        if (i > 2) {
            house = "";
            x = s;
        }
        address[2] = house.trim() + "#";
        return x.trim();
    }

    public String parseFloor(String s) {
        String x = s, floor = "";
        int start = -1;
        int slut = 0;

        //runs from house number till end
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) { //if current char is digit
                if (start == -1) {
                    start = i;
                }
                slut = i;
                if (i + 1 != s.length() && !Character.isDigit(s.charAt(i + 1))) { //if next char is not a digit
                    slut = i + 1;
                    break;
                }
            }
        }
        if (start == -1) {
            address[3] = "#";
            return s;
        }
        if ((slut - start) < 3) { //if not zip
            x = s.substring(slut, s.length());
            floor = s.substring(start, slut);
        }

        address[3] = floor.trim() + "#";
        return x.trim();
    }

    public String parseZip(String s) {
        String x = s, zip = "";
        int start = -1;
        int slut = 0;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) { //if current char is digit
                if (start == -1) {
                    start = i;
                }
                slut = i;
                if (i < s.length() && !Character.isDigit(s.charAt(i + 1))) { //if next char is not a digit
                    slut = i + 1;
                    break;
                }
            }
        }
        if (start == -1) {
            address[4] = "#";
            return x;
        }
        if ((slut - start) == 4) { //if zip
            zip = s.substring(start, slut);
            x = s.substring(slut, s.length());
        }
        address[4] = zip.trim() + "#";
        return x.trim();
    }

    public void parseCity(String s) {
        String city = "";
        //Char in string pointers
        int start = -1;

        for (int i = 0; i < s.length(); i++) {
            if (Character.isAlphabetic(s.charAt(i))) {
                start = i;
                break;
            }
        }
        if (start != -1) {
            city = s.substring(start, s.length());
            //insert code to remove unnecesary , . ! ? # "

            //if string ends with zip
            city.trim();
            if (city.length() > 3 && Character.isDigit(city.charAt(city.length() - 1))) {
                city = city.substring(0, city.length() - 4).trim();
            }
            address[5] = city.trim();
        }
    }
}
