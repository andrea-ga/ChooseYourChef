package com.andreaga.utility.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.*;

public class XMLConverter {

    public static List<Map<String,String>> leggiXML(String percorso, String element){
        List<Map<String,String>> ris = new ArrayList<>();

        try {
            File f = new File(percorso);

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            Document doc = dBuilder.parse(f);
            NodeList lista = doc.getElementsByTagName(element);
            for(int i = 0; i < lista.getLength(); i++) {
                Element e = (Element)lista.item(i);
                Map<String, String> mappa = _getAttributes(e);

                if(!mappa.isEmpty())
                    ris.add(mappa);
                else
                    ris.add(_getElements(e));
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        return ris;
    }

    //L'underscore nel nome si mette perchè il metodo è private
    private static Map<String, String> _getElements(Element e) {
        Map<String,String> res = new LinkedHashMap<>();
        NodeList listanodi = e.getChildNodes();

        for(int j = 0; j < listanodi.getLength(); j++) {
            String chiave = listanodi.item(j).getNodeName();
            String valore = listanodi.item(j).getTextContent();

            if(!chiave.equals("#text"))
                res.put(chiave, valore);
        }

        return res;
    }

    private static Map<String, String> _getAttributes(Element e) {
        Map<String,String> res = new HashMap<>();

        NamedNodeMap listaattributi = e.getAttributes();
        for(int j = 0; j < listaattributi.getLength(); j++) {
            String chiave = listaattributi.item(j).getNodeName();
            String valore = listaattributi.item(j).getNodeValue();

            res.put(chiave, valore);
        }

        return res;
    }
}
