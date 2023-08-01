package com.example.powerAdmin.entity;

import lombok.Data;

import java.util.List;

@Data
public class Stock {
    private List<StockChild> item;
}
