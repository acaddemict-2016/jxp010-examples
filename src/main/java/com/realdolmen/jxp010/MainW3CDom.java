package com.realdolmen.jxp010;

import com.realdolmen.jxp010.domain.Movie;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Demonstration using the W3C DOM API.
 */
public class MainW3CDom {
    public static void main(String[] args) throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(new File("./movies.xml"));

        trimMovies(document);

        addMovie(new Movie("Kill Bill", "Action", "DVD"), document);

        List<Movie> movies = findMovies(document);
        for (Movie movie : movies) {
            System.out.println(movie);
        }

        saveMovies(document);
    }

    private static void trimMovies(Document document) {
        trimOrDie(document.getDocumentElement());
    }

    /**
     * Example of generic recursive tree-walking.
     * @param element The current element to walk over.
     */
    private static void trimOrDie(Element element) {
        NodeList children = element.getChildNodes();
        if(children.getLength() == 1) {
            Node singleton = children.item(0);
            if(singleton.getNodeType() == Node.TEXT_NODE) {
                singleton.setTextContent(singleton.getTextContent().trim());
            }
        }
        for (int i = 0; i < children.getLength(); i++) {
            Node node = children.item(i);
            if(node.getNodeType() == Node.ELEMENT_NODE) {
                trimOrDie((Element)node);
            }
        }
    }

    private static void saveMovies(Document document) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.transform(new DOMSource(document), new StreamResult(System.out));
    }

    private static List<Movie> findMovies(Document document) {
        ArrayList<Movie> movies = new ArrayList<>();
        NodeList movieElements = document.getElementsByTagName("movie");
        for (int i = 0; i < movieElements.getLength(); i++) {
            Element movieElement = (Element) movieElements.item(i);
            String title = movieElement.getElementsByTagName("title").item(0).getTextContent();
            String format = movieElement.getElementsByTagName("format").item(0).getTextContent();
            String type = movieElement.getElementsByTagName("type").item(0).getTextContent();
            movies.add(new Movie(title, format, type));
        }
        return movies;
    }

    private static void addMovie(Movie movie, Document doc) {
        Element movieElement = doc.createElement("movie");
        movieElement.appendChild(createSimpleElement("title", movie.getTitle(), doc));
        movieElement.appendChild(createSimpleElement("type", movie.getType(), doc));
        movieElement.appendChild(createSimpleElement("format", movie.getFormat(), doc));
        doc.getElementsByTagName("movies").item(0).appendChild(movieElement);
    }

    private static Element createSimpleElement(String elementName, String value, Document doc) {
        Element e = doc.createElement(elementName);
        e.setTextContent(value);
        return e;
    }
}
