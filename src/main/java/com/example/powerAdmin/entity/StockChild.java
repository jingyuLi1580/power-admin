package com.example.powerAdmin.entity;

import lombok.Data;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

@Data
public class StockChild {
    private String stockCode;
    private String ticker;
    private String cname;
    private String investLevelDesc;
    private String investDirectionDesc;
    private String pxLast;
    private String targetPrice;
    private String valueDate;
    private String tradeCurrency;
    private String tradeCurrencyCName;
    private String reportInvestLevel;
    private String reportInvestDirection;
    private Boolean topPicked;
    private List<String> tags;
    private String fullNameCn;
    private String tickerAlias;
    private String stockCodeAlias;

}
