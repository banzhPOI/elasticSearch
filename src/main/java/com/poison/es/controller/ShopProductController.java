package com.poison.es.controller;

import com.poison.es.common.json.Response;
import com.poison.es.common.json.ResponseHelper;
import com.poison.es.domain.ProductGroup;
import com.poison.es.service.productGroup.ProductGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("rest/shopProducts")
public class ShopProductController {

    @Autowired
    private ProductGroupService productGroupService;

}
