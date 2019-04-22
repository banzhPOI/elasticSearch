package com.poison.es.elasticSearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import java.lang.ref.Reference;

@Component
public interface ProductGroupSearch extends ElasticsearchRepository<ProductGroupES, String> {

    Reference<Object> findAllByGroupId(String groupId);
}
