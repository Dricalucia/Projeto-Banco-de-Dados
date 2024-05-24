package com.cesar.portaltemaki.service;

import com.cesar.portaltemaki.model.Item;

import java.util.List;
import java.util.Map;

public interface ItemService {
    void saveItem(Item item);
    void deleteItem(int idItem);
    void updateItem(Item item);
    List<Item> findAllItems();
    Item findItemById(int idItem);
    List<Item> findItemsByCategoria(int idCategoria);
    List<Map<String, Object>> findQuantidadeItensVendidosEntreDatas(String dataInicial, String dataFinal);
}
