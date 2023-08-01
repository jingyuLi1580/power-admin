/**
 * 通联数据机密
 * --------------------------------------------------------------------
 * 通联数据股份公司版权所有 © 2013-2021
 *
 * 注意：本文所载所有信息均属于通联数据股份公司资产。本文所包含的知识和技术概念均属于
 * 通联数据产权，并可能由中国、美国和其他国家专利或申请中的专利所覆盖，并受商业秘密或
 * 版权法保护。
 * 除非事先获得通联数据股份公司书面许可，严禁传播文中信息或复制本材料。
 *
 * DataYes CONFIDENTIAL
 * --------------------------------------------------------------------
 * Copyright © 2013-2021 DataYes, All Rights Reserved.
 *
 * NOTICE: All information contained herein is the property of DataYes
 * Incorporated. The intellectual and technical concepts contained herein are
 * proprietary to DataYes Incorporated, and may be covered by China, U.S. and
 * Other Countries Patents, patents in process, and are protected by trade
 * secret or copyright law.
 * Dissemination of this information or reproduction of this material is
 * strictly forbidden unless prior written permission is obtained from DataYes.
 */
package com.example.powerAdmin.utils;

import com.github.promeg.pinyinhelper.Pinyin;
import com.github.promeg.pinyinhelper.PinyinMapDict;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;


public class TinyPinyinUtil {

    private static Logger logger = LoggerFactory.getLogger(TinyPinyinUtil.class);

    private static String dictFilePath = "data/tinypinyin.dict";

    private static class TinyPinyinUtilHolder {
        private static TinyPinyinUtil singleton = new TinyPinyinUtil();
    }

    private Map<String, String[]> userDefinedPinyinMap;

    private TinyPinyinUtil() {
        userDefinedPinyinMap = Maps.newHashMap();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(dictFilePath));
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (StringUtils.isBlank(line) || line.startsWith("#")) {
                    continue;
                }
                String[] attrs = line.split("\t");
                if (attrs.length == 2) {
                    String[] pinyins = attrs[0].split("'");
                    String name = attrs[1];
                    userDefinedPinyinMap.put(name, pinyins);
                }
            }
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        Pinyin.init(Pinyin.newConfig().with(new PinyinMapDict() {
            @Override
            public Map<String, String[]> mapping() {
                return userDefinedPinyinMap;
            }
        }));
    }

    public static TinyPinyinUtil getInstance() {
        return TinyPinyinUtilHolder.singleton;
    }

    public static String toPingyin(String text) {
        if (StringUtils.isBlank(text)) {
            return "";
        }
        TinyPinyinUtil instance = TinyPinyinUtil.getInstance();
        String res = Pinyin.toPinyin(text,"");
        if (StringUtils.isNotBlank(res)) {
            return res.toUpperCase();
        }
        return res;
    }

    public static String toFirstPySpell(String text){
        if (StringUtils.isBlank(text)) {
            return "";
        }
        TinyPinyinUtil instance = TinyPinyinUtil.getInstance();
        String pinyin = Pinyin.toPinyin(text,",");
        StringBuilder firstSpell = new StringBuilder(128);
        if (StringUtils.isNotBlank(pinyin)) {
            for (String py: pinyin.split(",")) {
                if (StringUtils.isNotBlank(py)) {
                    firstSpell.append(py.charAt(0));
                }
            }
        }
        return firstSpell.toString().toUpperCase();
    }

    public static void main(String[] args) {
        String str = toPingyin("国网英大");
        System.out.println("str = " + str);
    }
}
