package com.realdolmen.jxp010;

import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.IOException;

/**
 * Demonstration using the awesome JDOM framework!
 */
public class MainJDom {
    private static final Namespace NAMESPACE = Namespace.getNamespace("http://realdolmen.com/movies");

    public static void main(String[] args) throws IOException {
        Element movies = new Element("movies", NAMESPACE);
        movies.addContent(new MovieElement("Kill Bill", "Action", "DVD"));
        movies.addContent(new MovieElement("Pulp Fiction", "Pop", "Bluray"));
        new XMLOutputter(Format.getPrettyFormat()).output(movies, System.out);
    }

    private static class MovieElement extends Element {
        public MovieElement(String title, String type, String format) {
            setName("movie");
            setNamespace(NAMESPACE);
            addContent(new Element("title", getNamespace()).setText(title));
            addContent(new Element("format", getNamespace()).setText(format));
            addContent(new Element("type", getNamespace()).setText(type));
        }
    }
}
