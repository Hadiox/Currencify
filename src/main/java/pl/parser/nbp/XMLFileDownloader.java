package pl.parser.nbp;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.InputStream;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class XMLFileDownloader {
    private final ArrayList<Document> documentList = new ArrayList<>();

    public ArrayList<Document> downloadXMLFiles(ArrayList<String> fileNames) throws ParserConfigurationException, MalformedURLException,
            IOException, SAXException
    {
        DocumentBuilderFactory fac = DocumentBuilderFactory.newInstance();
        fac.setNamespaceAware(false);
        fac.setValidating(false);
        fac.setFeature("http://xml.org/sax/features/namespaces", false);
        fac.setFeature("http://xml.org/sax/features/validation", false);
        fac.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
        fac.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        DocumentBuilder documentBuilder = fac.newDocumentBuilder();
        for(String fileName: fileNames) {
            String URLString = createURLFromFileName(fileName);
            URL url = new URL(URLString);
            InputStream stream = url.openStream();
            Document doc = documentBuilder.parse(stream);
            documentList.add(doc);
        }
        return documentList;
    }
    private String createURLFromFileName(String fileName)
    {
        return "http://www.nbp.pl/kursy/xml/"+fileName+".xml";
    }
}
