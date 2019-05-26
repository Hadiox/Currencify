import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import pl.parser.nbp.DetailedInputParser;
import pl.parser.nbp.NoCurrencyException;
import pl.parser.nbp.WrongDateException;

import java.text.ParseException;

public class TestClass {

    @Test
    public void parserTest()
    {
        DetailedInputParser detailedInputParser = new DetailedInputParser();
        Assertions.assertThrows(NoCurrencyException.class,()->detailedInputParser.checkIfCurrencyInRegister("PLN"));
        Assertions.assertThrows(WrongDateException.class,()->detailedInputParser.formatDate("2020-02-26"));
        Assertions.assertThrows(WrongDateException.class,()->detailedInputParser.formatDate("2017-52-12"));
        Assertions.assertThrows(ParseException.class,()->detailedInputParser.formatDate("20202020"));
    }


}
