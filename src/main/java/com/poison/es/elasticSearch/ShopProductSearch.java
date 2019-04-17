package com.poison.es.elasticSearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

@Component
public interface ShopProductSearch extends ElasticsearchRepository<ShopProductES, String> {

}
