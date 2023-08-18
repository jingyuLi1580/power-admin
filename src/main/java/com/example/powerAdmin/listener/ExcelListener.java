package com.example.powerAdmin.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * excel表格读取监视器
 */
public class ExcelListener extends AnalysisEventListener {
    //可以通过实例获取该值
    private List<Object> datas = new ArrayList<Object>();
    /**
     * excel标题字段类型
     */
    private List<String> excelType;
    /**
     * 统计对比错误次数
     */
    private AtomicInteger rowCountError = new AtomicInteger();
    /**
     * 执行总条数
     */
    private AtomicLong rowCount = new AtomicLong();
    /**
     * 返回错误信息的结果集
     */
    List<String> results = new ArrayList<>();

    public List<Object> getDatas() {
        return datas;
    }

    public void setDatas(List<Object> datas) {
        this.datas = datas;
    }

    @Override
    public void invoke(Object o, AnalysisContext analysisContext) {

        doSomething(o);
        datas.add(o);
        //统计总行数
        rowCount.incrementAndGet();
    }

    /**
     * 对入参进行一些校验，判空处理
     * @param object
     */
    private void doSomething(Object object) {

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        saveData();
        System.out.println("rowCount = " + rowCount.get());
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
    }
}
