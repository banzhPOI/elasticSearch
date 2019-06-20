package com.poison.es.service.elasticSearch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional

public class ElasticsearchServiceImpl implements ElasticsearchService {

    private static final Logger logger = LoggerFactory.getLogger(ElasticsearchServiceImpl.class);


    @Override
    public void check() {

    }

    @Override
    public void test() {

    }
}
