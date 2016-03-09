package com.realdolmen.jxp010;

import com.realdolmen.movies.*;
import com.realdolmen.movies.Movie;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 * Demonstration using JAXB XJC (generate code from XSD) with custom objects with @Xml* annotations.
 * (run 'mvn clean generate-sources' first)
 */
public class MainJaxbXjcContractFirst {
    public static void main(String[] args) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance("com.realdolmen.movies");


        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        Movies movies = new Movies();
        Movie movie = new Movie();
        movie.setTitle("Jackie Brown");
        movie.setType("Pop");
        movie.setFormat("VHS");
        movies.getMovie().add(movie);
        marshaller.marshal(movies, System.out);
    }
}
