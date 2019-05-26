package pl.parser.nbp;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

public class ValueExtractor {
    public ArrayList<Double> extractValues(ArrayList<Document> XMLList,String currency,String attribute)
    {
        ArrayList<Double> values = new ArrayList<>();
        for(Document doc: XMLList)
        {
            doc.getDocumentElement().normalize();
            NodeList listOfNodes = doc.getElementsByTagName("pozycja");
            for(int i = 0; i < listOfNodes.getLength();i++)
            {
                Node node = listOfNodes.item(i);
                if(node.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element element = (Element) node;
                    if(element.getElementsByTagName("kod_waluty").item(0).getTextContent().compareTo(currency) == 0)
                    {
                        String stringValue = element.getElementsByTagName(attribute).item(0).getTextContent();
                        stringValue = stringValue.replace(',','.');
                        Double value = Double.parseDouble(stringValue);
                        values.add(value);
                    }
                }
            }
        }
        return values;
    }

}
