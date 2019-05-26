package pl.parser.nbp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class DetailedInputParser implements InputParser {
    private ArrayList<String> currencyRegister = new ArrayList<String>(Arrays.asList("USD","EUR","CHF","GBP"));

    public Date formatDate(String dateString) throws ParseException, WrongDateException
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse(dateString);
        if(date.after(Calendar.getInstance().getTime()))
        {
            throw new WrongDateException();
        }
        return date;
    }
    public void checkIfCurrencyInRegister(String currency) throws NoCurrencyException
    {
        if(!currencyRegister.contains(currency))
        {
            throw new NoCurrencyException();
        }
    }
}
