package pl.parser.nbp;

import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ListFileDownloader {
    private Date dateBegin;
    private Date dateEnd;

    public ListFileDownloader(Date dateBegin, Date dateEnd) {
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
    }
    public ArrayList<File> downloadList() throws IOException
    {
        ArrayList<File> list = new ArrayList<>();
        Calendar calendarDateBegin = new GregorianCalendar();
        Calendar calendarDateEnd = new GregorianCalendar();
        calendarDateBegin.setTime(dateBegin);
        calendarDateEnd.setTime(dateEnd);
        Path resourcesDirectory = Paths.get("src\\main\\resources");
        Path absolutePath = resourcesDirectory.toAbsolutePath();
        String absolutePathString = absolutePath.toString();
        for(int yearIterator = calendarDateBegin.get(Calendar.YEAR);yearIterator<=calendarDateEnd.get(Calendar.YEAR);yearIterator++)
        {
            String fileNameString;
            if(yearIterator != Calendar.getInstance().get(Calendar.YEAR))
            {
                fileNameString = "dir"+ Integer.toString(yearIterator) + ".txt";
            }
            else
            {
                fileNameString = "dir.txt";
            }
            File file = new File(absolutePathString +"\\"+ fileNameString);
            URL source = new URL("http://www.nbp.pl/kursy/xml/"+fileNameString);
            FileUtils.copyURLToFile(source, file);
            list.add(file);
        }
        return list;
    }
}
