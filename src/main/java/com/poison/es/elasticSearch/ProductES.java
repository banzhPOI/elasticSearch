package com.poison.es.elasticSearch;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@Document(indexName="product",type="product")
public class ProductES implements Serializable {
    private Long id;
    private String name;
    private String description;
}
