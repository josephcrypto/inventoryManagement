package cn.coding.inventorymanagement.service;

import cn.coding.inventorymanagement.exception.ItemNotFoundException;
import cn.coding.inventorymanagement.model.Item;
import cn.coding.inventorymanagement.model.paging.Paged;
import cn.coding.inventorymanagement.model.paging.Paging;
import cn.coding.inventorymanagement.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public void save(Item item) {
        itemRepository.save(item);
    }

    public Item getById(Long id) throws ItemNotFoundException {
        Optional<Item> result = itemRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new ItemNotFoundException("Item not found with ID " + id);
    }

    public void deleteById(Long id) throws ItemNotFoundException {
        Long count = itemRepository.countById(id);
        if (count == null || count == 0) {
            throw new ItemNotFoundException("Item not found with ID " + id);
        }
        itemRepository.deleteById(id);
    }

    public List<Item> listAllItem() {
        return itemRepository.findAll();
    }

    public Paged<Item> getItemWithPaginated(int pageNumber, int size) {
        PageRequest request = PageRequest.of(pageNumber -1 ,size, Sort.Direction.ASC, "id");
        Page<Item> itemPage = itemRepository.findAll(request);
        return new Paged<>(itemPage, Paging.of(itemPage.getTotalPages(), pageNumber, size));
    }
}
