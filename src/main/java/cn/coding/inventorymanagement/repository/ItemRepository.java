package cn.coding.inventorymanagement.repository;

import cn.coding.inventorymanagement.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

    Item findFirstByCameraIp(float cameraIp);

    Item findFirstByComputerIp(float computerIp);

    Long countById(Long id);
}
