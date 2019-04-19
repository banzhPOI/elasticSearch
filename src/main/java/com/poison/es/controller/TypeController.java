package com.poison.es.controller;

import com.poison.es.common.json.Response;
import com.poison.es.common.json.ResponseHelper;
import com.poison.es.domain.Type;
import com.poison.es.service.type.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("rest/types")
public class TypeController {

    @Autowired
    private TypeService typeService;

    //新增
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Response<?> addType(@RequestBody Type type) {
        typeService.addType(type);
        return ResponseHelper.createSuccessResponse();
    }

    //列表(不分页)
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Response<?> findAllTypes() {
        List<Type> types = typeService.findAllTypes();
        return ResponseHelper.createSuccessResponse(types);
    }
}
