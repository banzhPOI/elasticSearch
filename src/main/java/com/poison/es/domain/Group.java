package com.poison.es.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class Group {
    private Long id;
    private String name;
    private String description;
    private Date creationTime;
    private Integer deleted;
}
