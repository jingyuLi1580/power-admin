package com.example.powerAdmin.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.powerAdmin.entity.Stock;
import com.example.powerAdmin.entity.StockChild;
import com.example.powerAdmin.utils.PinYinUtil;
import com.example.powerAdmin.utils.TinyPinyinUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.dictionary.py.Pinyin;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Configuration
public class IndustryMapConfig {
    @Autowired
    private ResourceLoader resourceLoader;

    private Map<String, String> industryMap = new HashMap<>();
    private static Logger logger = LoggerFactory.getLogger(IndustryMapConfig.class);

    @Bean
    public Map<String, String> industryMap() throws IOException {

        return null;
    }

        public static void main(String[] args) throws IOException {
        for (File confileFile : FileUtils.listFiles(new File("doc"),
                FileFilterUtils.suffixFileFilter("json"), null)) {
            loadConfigFromJSONFile(confileFile);

        }
    }
//    public static void main(String[] args) {
//        String text = "重载不是重任";
//        List<Pinyin> pinyinList = HanLP.convertToPinyinList(text);
//        System.out.print("原文,");
//        for (char c : text.toCharArray()) {
//            System.out.printf("%c,", c);
//        }
//        System.out.println();
//
//        System.out.print("拼音（数字音调）,");
//        for (Pinyin pinyin : pinyinList) {
//            System.out.printf("%s,", pinyin);
//        }
//        System.out.println();
//
//        System.out.print("拼音（符号音调）,");
//        for (Pinyin pinyin : pinyinList) {
//            System.out.printf("%s,", pinyin.getPinyinWithToneMark());
//        }
//        System.out.println();
//
//        System.out.print("拼音（无音调）,");
//        for (Pinyin pinyin : pinyinList) {
//            System.out.printf("%s,", pinyin.getPinyinWithoutTone());
//        }
//        System.out.println();
//
//        System.out.print("声调,");
//        for (Pinyin pinyin : pinyinList) {
//            System.out.printf("%s,", pinyin.getTone());
//        }
//        System.out.println();
//
//        System.out.print("声母,");
//        for (Pinyin pinyin : pinyinList) {
//            System.out.printf("%s,", pinyin.getShengmu());
//        }
//        System.out.println();
//
//        System.out.print("韵母,");
//        for (Pinyin pinyin : pinyinList) {
//            System.out.printf("%s,", pinyin.getYunmu());
//        }
//        System.out.println();
//
//        System.out.print("输入法头,");
//        for (Pinyin pinyin : pinyinList) {
//            System.out.printf("%s,", pinyin.getHead());
//        }
//        System.out.println();
//    }


    public static Stock loadConfigFromJSONFile(File configFile) throws IOException {
        String jsonConfigContent = FileUtils.readFileToString(configFile, "UTF-8");
        JSONObject jobj = JSON.parseObject(jsonConfigContent);

        Stock suggTypeConfig = new Stock();

        JSONArray suggestions = jobj.getJSONArray("items");
        List<StockChild> suggestionConfigList = new ArrayList<>();
        for (int i = 0; i < suggestions.size()-1; i++) {
            JSONObject suggJobj = suggestions.getJSONObject(i);
            ObjectMapper objectMapper = new ObjectMapper();
            StockChild stockChild = objectMapper.convertValue(suggJobj, StockChild.class);
            if (stockChild.getCname().contains("长")) {
                String s = TinyPinyinUtil.toPingyin(stockChild.getCname());
                System.out.println("stockChild包含多音字： " + stockChild.getCname()+"/--读音："+s);
            }
//            char[] chars = stockChild.getCname().toCharArray();
//            for (char aChar : chars) {
//                if (PinYinUtil.checkPinYin(aChar)) {
//                    System.out.println(aChar + "是多音字:");
//                }
//            }
//                    SuggestionConfig suggestionConfig = SuggestionConfig.parseFromJSONObject(suggJobj);
            suggestionConfigList.add(stockChild);
        }
        suggTypeConfig.setItem(suggestionConfigList);
        return suggTypeConfig;
    }
}