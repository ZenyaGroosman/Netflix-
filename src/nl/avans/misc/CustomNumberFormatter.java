package nl.avans.misc;

import javax.swing.text.NumberFormatter;
import java.text.NumberFormat;
import java.text.ParseException;

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
