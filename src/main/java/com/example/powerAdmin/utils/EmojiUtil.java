//package com.example.powerAdmin.utils;
//
//import com.alibaba.fastjson.JSONObject;
//import io.netty.util.internal.StringUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//
//import java.lang.reflect.Field;
//
///**
// * @description: 表情包工具类
// * @author: luoping
// * @date: 2022/10/21
// **/
//@Slf4j
//public class EmojiUtil {
//
//    /**
//     * 将文本中的emoji表情包转码
//     * @param emojiStr
//     * @return
//     */
//    public static String encode(String emojiStr) {
//        if(StringUtil.isBlank(emojiStr)) {
//            return null;
//        }
//        if(!EmojiManager.containsEmoji(emojiStr)) {
//            return emojiStr;
//        }
//        return EmojiParser.parseToAliases(emojiStr);
//    }
//
//    /**
//     * 将文本中的emoji表情（转码后）恢复成表情包
//     * @param deEmojiStr
//     * @return
//     */
//    public static String decode(String deEmojiStr) {
//        if(StringUtil.isBlank(deEmojiStr)) {
//            return null;
//        }
////        if(!EmojiManager.containsEmoji(deEmojiStr)) {
////            return deEmojiStr;
////        }
//        return EmojiParser.parseToUnicode(deEmojiStr);
//    }
//
//    /**
//     * 将对象中@EmojiField修饰的字符串进行emoji表情包转码
//     * @param t
//     * @param <T>
//     */
//    public static <T> void encode(T t) {
//        Field[] fields = t.getClass().getDeclaredFields();
//        for (Field field : fields) {
//            // 判断字段注解是否存在
//            boolean annotationPresent = field.isAnnotationPresent(EmojiField.class);
//            if (annotationPresent) {
//                if(!StringUtils.equals(field.getType().getName(), "java.lang.String")) {
//                    // 只处理String类型
//                    continue;
//                }
//                // 将字段中包含emoji表情包的内容进行转码赋值
//                field.setAccessible(true);
//                try {
//                    String srcStr = (String)field.get(t);
//                    field.set(t, encode(srcStr));
//                }catch (Exception e) {
//                    log.error("EmojiUtil encode error: {}", JSONObject.toJSONString(t), e);
//                }
//                field.setAccessible(false);
//            }
//        }
//    }
//
//    /**
//     * 将对象中@EmojiField修饰的字符串进行emoji表情包解码
//     * @param t
//     * @param <T>
//     */
//    public static <T> void decode(T t) {
//        Field[] fields = t.getClass().getDeclaredFields();
//        for (Field field : fields) {
//            // 判断字段注解是否存在
//            boolean annotationPresent = field.isAnnotationPresent(EmojiField.class);
//            if (annotationPresent) {
//                if(!StringUtils.equals(field.getType().getName(), "java.lang.String")) {
//                    // 只处理String类型
//                    continue;
//                }
//                // 将字段中包含emoji表情包的内容进行转码赋值
//                field.setAccessible(true);
//                try {
//                    String srcStr = (String)field.get(t);
//                    field.set(t, decode(srcStr));
//                }catch (Exception e) {
//                    log.error("EmojiUtil decode error: {}", JSONObject.toJSONString(t), e);
//                }
//                field.setAccessible(false);
//            }
//        }
//    }
//
//    /**
//     * 是否包含emoji表情包
//     * @param str
//     * @return
//     */
//    public static boolean containEmoji(String str) {
//        return EmojiManager.containsEmoji(str);
//    }
//
//}
