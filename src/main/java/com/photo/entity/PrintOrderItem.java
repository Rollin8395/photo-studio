package com.photo.entity;

import com.photo.entity.enums.PaperType;
import com.photo.entity.enums.PrintSize;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class PrintOrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private PrintOrder order;

    @ManyToOne
    private PhotoAsset photo;

    @Enumerated(EnumType.STRING)
    private PrintSize printSize;

    @Enumerated(EnumType.STRING)
    private PaperType paperType;

    private int quantity;

    private Double price;

}