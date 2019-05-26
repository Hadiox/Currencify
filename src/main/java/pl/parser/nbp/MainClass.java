package pl.parser.nbp;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class MainClass {
    private InputParser inputParser;

    public MainClass(InputParser inputParser) {
        this.inputParser = inputParser;
    }

    public static void main(String[]args)
    {
        MainClass application = new MainClass(new DetailedInputParser());
        String currency = args[0];
        try{
            application.inputParser.checkIfCurrencyInRegister(currency);
        }
        catch(NoCurrencyException e)
        {
            System.out.println("No such currency in register");
            return;
        }
        String dateBeginString = args[1];
        String dateEndString = args[2];
        Date dateBegin;
        Date dateEnd;
        try {
            dateBegin = application.inputParser.formatDate(dateBeginString);
            dateEnd = application.inputParser.formatDate(dateEndString);
        }
        catch(ParseException e)
        {
            System.out.println("Date input is wrong");
            return;
        }
        catch(WrongDateException e)
        {
            System.out.println("Date is larger than actual");
            return;
        }
        FileNameFinder fileNameFinder;
        try {
            fileNameFinder = new FileNameFinder(dateBegin, dateEnd);
        }
        catch(WrongDateException e)
        {
            System.out.println("Begin date is larger than end date");
            return;
        }
        ArrayList<String> listOfFiles = fileNameFinder.findFileNames();
        ArrayList<Document> XMLList;
        try{
            XMLFileDownloader xmlFileDownloader = new XMLFileDownloader();
            XMLList = xmlFileDownloader.downloadXMLFiles(listOfFiles);
        }
        catch(ParserConfigurationException e)
        {
            System.out.println("Document builder exception");
            e.printStackTrace();
            return;
        }
        catch(MalformedURLException e)
        {
            System.out.println("Wrong URL");
            return;
        }
        catch(IOException e)
        {
            System.out.println("Problem with downloading file");
            return;
        }
        catch(SAXException e)
        {
            System.out.println("Parsing XML exception");
            return;
        }
        ValueExtractor valueExtractor = new ValueExtractor();
        ArrayList<Double> currencyBuyValue = valueExtractor.extractValues(XMLList,currency,"kurs_kupna");
        ArrayList<Double> currencySellValue = valueExtractor.extractValues(XMLList,currency,"kurs_sprzedazy");
        String strCurrencyBuyAvg = String.format("%.4f",new Calculator().calculateAvg(currencyBuyValue));
        String strCurrencySellStdDev = String.format("%.4f",new Calculator().calculateStdDev(currencySellValue));
        System.out.println(strCurrencyBuyAvg);
        System.out.println(strCurrencySellStdDev);

    }
}
