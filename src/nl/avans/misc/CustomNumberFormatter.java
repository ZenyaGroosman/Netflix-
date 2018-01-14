package nl.avans.misc;

import javax.swing.text.NumberFormatter;
import java.text.NumberFormat;
import java.text.ParseException;

/*This class gets used as a restriction for text fields, by only alowing them to use numbers,
the overide makes it so that the field can also be empty
 */
public class CustomNumberFormatter extends NumberFormatter {
    public CustomNumberFormatter(NumberFormat format){
        super(format);
    }

    @Override
    public Object stringToValue(String text) throws ParseException {
        if (text.isEmpty())
            return null;
        return super.stringToValue(text);
    }
}
