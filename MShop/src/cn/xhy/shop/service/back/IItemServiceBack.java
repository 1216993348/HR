package cn.xhy.shop.service.back;

import cn.xhy.shop.vo.Item;

import java.util.List;
import java.util.Set;

public interface IItemServiceBack {
    boolean insert(Item vo) throws Exception;
    boolean update(Item vo) throws Exception;
    boolean delete(Set<Integer> ids) throws Exception;
    List<Item> list() throws Exception;
}
