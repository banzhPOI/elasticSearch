package com.poison.es.elasticSearch;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Parent;

import java.io.Serializable;

@Getter
@Setter
@ToString
@Document(indexName="es",type="shopProduct")
public class ShopProductES implements Serializable {
    @Id
    private String shopId;
    @Parent(type = "product")
    private String productId;
}
