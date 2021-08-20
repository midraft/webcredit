package com.example.application.data.generator;

import com.example.application.data.entity.Person;
import com.example.application.data.service.PersonRepository;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.vaadin.artur.exampledata.ChanceStringType;
import org.vaadin.artur.exampledata.DataType;
import org.vaadin.artur.exampledata.ExampleDataGenerator;

import java.time.LocalDateTime;

@SpringComponent
public class DataGenerator {

    @Bean
    public CommandLineRunner loadData(PersonRepository personRepository) {
        return args -> {
            Logger logger = LoggerFactory.getLogger(getClass());
            if (personRepository.count() != 0L) {
                logger.info("Using existing database");
                return;
            }
            int seed = 123;

            logger.info("Generating demo data");

            logger.info("... generating 100 Person entities...");
            ExampleDataGenerator<Person> personRepositoryGenerator = new ExampleDataGenerator<>(Person.class,
                    LocalDateTime.of(2021, 5, 28, 0, 0, 0));
            personRepositoryGenerator.setData(Person::setId, DataType.ID);
            personRepositoryGenerator.setData(Person::setSurname, DataType.LAST_NAME);
            personRepositoryGenerator.setData(Person::setName, DataType.FIRST_NAME);
            personRepositoryGenerator.setData(Person::setPatronymic, new ChanceStringType("patronymic") );
            personRepositoryGenerator.setData(Person::setPatronymic, DataType.FULL_NAME);
            personRepositoryGenerator.setData(Person::setPhone, DataType.PHONE_NUMBER);
            personRepositoryGenerator.setData(Person::setEmail, DataType.EMAIL);
            //personRepositoryGenerator.setData(Person::setPassportSeries, DataType.NUMBER_UP_TO_10000);
            //personRepositoryGenerator.setData(Person::setPassportID, DataType.NUMBER_UP_TO_10000);
            personRepository.saveAll(personRepositoryGenerator.create(100, seed));

            logger.info("Generated demo data");
        };
    }

}