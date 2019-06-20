package com.poison.es.controller;

import com.poison.es.common.json.Response;
import com.poison.es.common.json.ResponseHelper;
import com.poison.es.service.elasticSearch.ElasticsearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rest/elasticsearch")
public class ElasticsearchController {

    @Autowired
    private ElasticsearchService elasticsearchService;

    //更新
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Response<?> check() {
        elasticsearchService.check();
        return ResponseHelper.createSuccessResponse();
    }
}
