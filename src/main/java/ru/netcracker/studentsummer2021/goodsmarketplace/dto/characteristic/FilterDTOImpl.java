package ru.netcracker.studentsummer2021.goodsmarketplace.dto.characteristic;


import lombok.Data;

@Data
public class FilterDTOImpl implements FilterDTO {

    private Long productId;
    private String charact;
    private String value;
}
