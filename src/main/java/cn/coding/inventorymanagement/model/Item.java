package cn.coding.inventorymanagement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private float cameraIp;

    private float computerIp;

    private String position;

    private String department;

    private String chinaName;

    private float routerAp;

    private String systemUnit;

    private String monitor;

    private String h3cMiniRouter;

    private String tpLinkRouter;

    private String tpLinkSwitch16p;

    private String tpLinkSwitch8p;

    private String nvr32Channel;

    private String h3cManagementSwitch;

    private String rj45NetworkCableBox;

    private String condition;

    private String password;

    private String used;

    private String notUsed;

    private int totalItem;

    private String remark;


}
