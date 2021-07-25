package ru.netcracker.studentsummer2021.goodsmarketplace.service.salesUnit;


import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.product.ProductPublicDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.salesUnit.SalesUnitDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.salesUnit.SalesUnitPublicDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.SalesUnit;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.Users;

import java.util.GregorianCalendar;

@Component
public class SalesUnitConverter {

    public SalesUnit fromDTOToSalesUnit(Users user, SalesUnitDTO salesUnitDTO){
        SalesUnit salesUnit = new SalesUnit();
        salesUnit.setId(salesUnit.getId());
        if(user.getAccountType().getAuthorities().contains(new SimpleGrantedAuthority("admin"))){
            salesUnit.setShop(salesUnitDTO.getShopId());
        }else {
            salesUnit.setShop(user.getShopId());
        }
        salesUnit.setProduct(salesUnitDTO.getProductId());
        salesUnit.setDesc(salesUnitDTO.getDesc());
        salesUnit.setDateCreated(new GregorianCalendar());
        salesUnit.setModifiedDate(new GregorianCalendar());
        salesUnit.setPrice(salesUnitDTO.getPrice());
        salesUnit.setQuantity(salesUnitDTO.getQuantity());
        salesUnit.setShipCost(salesUnitDTO.getShipCost());
        return salesUnit;
    }

    public SalesUnitPublicDTO fromSalesUnitToDTO(SalesUnit salesUnit, ProductPublicDTO productPublicDTO){
        return SalesUnitPublicDTO.builder()
                .id(salesUnit.getId())
                .dateCreated(salesUnit.getDateCreated())
                .desc(salesUnit.getDesc())
                .modifiedDate(salesUnit.getModifiedDate())
                .price(salesUnit.getPrice())
                .product(productPublicDTO)
                .quantity(salesUnit.getQuantity())
                .shipCost(salesUnit.getShipCost())
                .shopId(salesUnit.getShop())
                .build();
    }
}
