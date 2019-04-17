package com.poison.es.elasticSearch;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Parent;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@Document(indexName="es",type="product")
public class ProductES implements Serializable {
    @Parent(type = "shopProduct")
    private String id;
    private String name;
    private String description;
}
