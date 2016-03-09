package com.realdolmen.jxp010;

import com.realdolmen.jxp010.domain.Address;
import com.realdolmen.jxp010.domain.Person;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 * Demonstration using JAXB with custom objects with @Xml* annotations.
 */
public class MainJaxbAnnotationsCodeFirst {
    public static void main(String[] args) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Person.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        Address address = new Address(
            "Huizingen",
            "1587",
            "42/B",
            "A. Vaucampslaan"
        );
        Person person = new Person("Jimi", "Hendrix", address, "jimih@hotmail.com", "hendrix@gmail.com");

//        marshaller.marshal(address, new File("./address.xml"));
        marshaller.marshal(person, System.out);
    }
}
