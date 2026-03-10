package com.photo.dto;

import com.photo.entity.enums.PaperType;
import com.photo.entity.enums.PrintSize;
import lombok.Data;

@Data
public class CreateOrderRequest {

    private String phone;

    private Long photoId;

    private PrintSize printSize;

    private PaperType paperType;

    private int quantity;

}