package com.poison.es.elasticSearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

@Component
public interface ProductSearch extends ElasticsearchRepository<ProductES, String> {

}
