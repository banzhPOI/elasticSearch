package com.poison.es.service.shopProduct;

import com.poison.es.domain.ShopProduct;
import com.poison.es.elasticSearch.ShopProductES;
import com.poison.es.elasticSearch.ShopProductSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional

public class ShopProductServiceImpl implements ShopProductService {

    @Autowired
    private ShopProductMapper shopProductMapper;
    @Autowired
    private ShopProductSearch shopProductSearch;

    @Override
    public void addShopProduct(ShopProduct shopProduct) {
        shopProductMapper.addShopProduct(shopProduct);
        ShopProductES shopProductES=makeEsInfo(shopProduct);
        shopProductSearch.save(shopProductES);
    }

    private ShopProductES makeEsInfo(ShopProduct shopProduct) {
        ShopProductES shopProductES=new ShopProductES();
        shopProductES.setProductId(shopProduct.getProductId());
        shopProductES.setShopId(shopProduct.getShopId());
        return shopProductES;
    }
}
