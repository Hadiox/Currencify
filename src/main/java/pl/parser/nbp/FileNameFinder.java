package pl.parser.nbp;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FileNameFinder {
    private Date dateBegin;
    private Date dateEnd;
    private ArrayList<File> listOfFiles;

    public FileNameFinder(Date dateBegin, Date dateEnd)throws WrongDateException {
        if(dateBegin.after(dateEnd))
        {
            throw new WrongDateException();
        }
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        ListFileDownloader listFileDownloader = new ListFileDownloader(dateBegin,dateEnd);
        try {
            listOfFiles = listFileDownloader.downloadList();
        }
        catch(IOException e)
        {
            System.out.println("URL and downloading problem");
        }
    }

    public ArrayList<String> findFileNames()
    {
        ArrayList<String> fileNames = new ArrayList<>();
        for(File xmlList:listOfFiles)
        {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(xmlList));
                String line = reader.readLine();
                while (line!= null) {
                    Date fileDate = convertFileNameToDate(line);
                    if (isBetweenBeginAndEnd(fileDate) && line.startsWith("c")) {
                        fileNames.add(line);
                    }
                    line = reader.readLine();
                }
                reader.close();
            }
            catch(FileNotFoundException e)
            {
                System.out.println("File not found");
                return null;
            }
            catch(IOException e)
            {
                System.out.println("IOException");
                e.printStackTrace();
                return null;
            }
            catch(ParseException e)
            {
                System.out.println("Parse exception in findFileNames method");
                e.printStackTrace();
            }
        }
        return fileNames;
    }
    private boolean isBetweenBeginAndEnd(Date testedDate)
    {
        return (testedDate.after(this.dateBegin)&&testedDate.before(this.dateEnd)) || (testedDate.compareTo(dateBegin)==0)
                || (testedDate.compareTo(dateEnd)==0);
    }
    private Date convertFileNameToDate(String fileName) throws ParseException
    {
        String year = fileName.substring(5,7);
        String month = fileName.substring(7,9);
        String day = fileName.substring(9,11);
        String date = day+month+"20"+year;
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
        return formatter.parse(date);
    }
}
