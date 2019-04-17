package com.poison.es.elasticSearch;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

@Getter
@Setter
@ToString
@Document(indexName="shopproduct",type="shopproduct")
public class ShopProductES implements Serializable {
    private Long id;
    private Long shopId;
    private Long productId;
}
