package com.realdolmen.jxp010;

import com.realdolmen.jxp010.domain.Movie;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Demonstration using the W3C push type SAX API.
 */
public class MainSax {
    public static void main(String[] args) throws Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        MovieTitleHandler handler = new MovieTitleHandler();
        parser.parse(new File("./movies.xml"), handler);
        List<Movie> movies = handler.getMovies();
        for (Movie movie : movies) {
            System.out.println(movie);
        }
    }

    /**
     * Note you can do a better job avoiding all the ifs if you create some infrastructure that makes use of polymorphism.
     * This serves as an example however to show the 'awkwardness' of using the W3C SAX API.
     */
    private static class MovieTitleHandler extends DefaultHandler {
        private Movie movie = null;

        private List<Movie> movies = new ArrayList<>();
        private boolean insideTitle = false;
        private boolean insideFormat = false;
        private boolean insideType = false;

        public List<Movie> getMovies() {
            return movies;
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            switch (qName) {
                case "movie":
                    movie = new Movie();
                    break;
                case "title":
                    insideTitle = true;
                    break;
                case "format":
                    insideFormat = true;
                    break;
                case "type":
                    insideType = true;
                    break;
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            switch (qName) {
                case "movie":
                    movies.add(movie);
                    break;
                case "title":
                    insideTitle = false;
                    break;
                case "format":
                    insideFormat = false;
                    break;
                case "type":
                    insideType = false;
                    break;
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            String value = new String(ch, start, length).trim();
            if(insideTitle) movie.setTitle(value);
            else if(insideFormat) movie.setFormat(value);
            else if(insideType) movie.setType(value);
        }
    }
}
