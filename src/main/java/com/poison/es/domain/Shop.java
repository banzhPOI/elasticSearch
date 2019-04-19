package com.poison.es.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class Shop {

    private Long id;
    private String name;
    private String description;
    private String logo;
    private Long groupId;
    private Date creationTime;
    private Integer deleted;
}
