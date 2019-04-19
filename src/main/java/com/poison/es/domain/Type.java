package com.poison.es.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class Type {
    private Long id;
    private String name;
    private String description;
    private Date creationTime;
    private Integer deleted;
}
