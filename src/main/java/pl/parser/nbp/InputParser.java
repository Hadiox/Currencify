package pl.parser.nbp;

import java.text.ParseException;
import java.util.Date;

interface InputParser {
    public Date formatDate(String dateString) throws ParseException, WrongDateException;
    public void checkIfCurrencyInRegister(String currency) throws NoCurrencyException;

}
