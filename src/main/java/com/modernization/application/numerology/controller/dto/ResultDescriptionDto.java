package com.modernization.application.numerology.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ResultDescriptionDto {

    @JsonIgnore
    private Long id;
    private String category;
    private String description;
    private String language;
}
