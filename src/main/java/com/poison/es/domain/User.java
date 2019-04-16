package com.poison.es.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@Document(indexName="esuser",type="user")
public class User implements Serializable {
    private Long id;
    private String username;
    private String password;

    //VO
    private List<String> roleStrList;
    private List<String> permissionStrList;
}
