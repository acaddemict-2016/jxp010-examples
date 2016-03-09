package com.realdolmen.jxp010;

import com.realdolmen.jxp010.domain.Movie;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Demonstration using Java 6's StAX pull-type API.
 */
public class MainStax {
    public static void main(String[] args) throws Exception {
        List<Movie> movies = parseDocument();
        for (Movie movie : movies) {
            System.out.println(movie);
        }
    }

    private static List<Movie> parseDocument() throws FileNotFoundException, XMLStreamException {
        XMLInputFactory factory = XMLInputFactory.newFactory();

        BufferedReader reader = new BufferedReader(
            new FileReader("./movies.xml")
        );

        XMLEventReader er = factory.createXMLEventReader(reader);

        List<Movie> movies = new ArrayList<>();
        while(er.hasNext()) {
            XMLEvent e = er.nextEvent();
            if(e.isStartElement() && e.asStartElement().getName().getLocalPart().equals("movie")) {
                movies.add(extractMovie(er));
            }
        }
        return movies;
    }

    private static Movie extractMovie(XMLEventReader er) throws XMLStreamException {
        String title = nextCharacters(er);
        String type = nextCharacters(er);
        String format = nextCharacters(er);
        return new Movie(title, type, format);
    }

    private static String nextCharacters(XMLEventReader er) throws XMLStreamException {
        nextStartElement(er);
        return er.nextEvent().asCharacters().getData().trim();
    }

    private static StartElement nextStartElement(XMLEventReader er) throws XMLStreamException {
        XMLEvent current = er.nextEvent();
        while(!current.isStartElement()) {
            current = er.nextEvent();
        }
        return current.asStartElement();
    }
}
