package com.cesar.portaltemaki.service;

import com.cesar.portaltemaki.model.Item;
import com.cesar.portaltemaki.repository.ItemRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService{
    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public void saveItem(Item item) {
        itemRepository.addItem(item);
    }

    @Override
    public void deleteItem(int idItem) {
        itemRepository.deleteItem(idItem);
    }

    @Override
    public void updateItem(Item item) {
        itemRepository.updateItem(item);
    }

    @Override
    public List<Item> findAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public Item findItemById(int idItem) {
        try {
            return itemRepository.findItemById(idItem);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Item> findItemsByCategoria(int idCategoria) {
        return itemRepository.findItemsByCategoria(idCategoria);
    }
}
