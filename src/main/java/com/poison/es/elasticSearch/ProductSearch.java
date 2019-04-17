package com.poison.es.elasticSearch;

import org.elasticsearch.index.query.MatchQueryBuilder;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProductSearch extends ElasticsearchRepository<ProductES, String> {

     Iterable<ProductES> findByNameLikeOrDescriptionLike(String name,String description);
}
