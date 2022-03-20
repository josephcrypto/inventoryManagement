package cn.coding.inventorymanagement.model.paging;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageItem {

    private PageItemType pageItemType;

    private int index;

    private boolean active;
}
