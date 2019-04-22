package com.poison.es.service.shop;

import com.poison.es.common.error.CodeException;
import com.poison.es.domain.Group;
import com.poison.es.domain.Shop;
import com.poison.es.service.group.GroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional

public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopMapper shopMapper;
    @Autowired
    private GroupMapper groupMapper;

    @Override
    public void addShop(Shop shop) {
        //查询分组是否存在
        hasGroup(shop);
        shopMapper.addShop(shop);
    }

    private void hasGroup(Shop shop) {
        Long groupId = shop.getGroupId();
        Group group = groupMapper.findGroupById(groupId);
        if (group == null) {
            throw new CodeException(-1, "分组不存在或已被删除");
        }
    }

    @Override
    public Shop findShopById(Long id) {
        Shop shop = shopMapper.findShopById(id);
        return shop;
    }

    @Override
    public List<Shop> findShopsByFilter(String filter){
        List<Shop> shops=shopMapper.findShopsByFilter(filter);
        return shops;
    }
}
