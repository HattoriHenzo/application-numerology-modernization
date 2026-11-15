package com.modernization.application.numerology.service.core;

import com.modernization.application.numerology.configuration.TestApplicationConfiguration;
import com.modernization.application.numerology.data.TestData;
import com.modernization.application.numerology.service.model.Person;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

@SpringJUnitWebConfig(TestApplicationConfiguration.class)
abstract class BaseNumerologyComputerTest implements WithAssertions {

    protected Person person;

    @BeforeEach
    void setUp() {
        person = TestData.createPerson();
    }
}