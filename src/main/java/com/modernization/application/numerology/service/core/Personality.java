package com.modernization.application.numerology.service.core;

import com.modernization.application.numerology.service.model.Person;
import org.springframework.stereotype.Service;

@Service("Personality")
public class Personality implements NumerologyComputer {

    @Override
    public int computeNumberFor(Person... person) {
        return person[0].getDay();
    }
}
