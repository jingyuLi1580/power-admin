package com.example.powerAdmin.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.example.powerAdmin.request.ChannelUserRequest;
import com.example.powerAdmin.service.IUserService;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 自定义监听器 用于处理导入数据的校验
 */
@Slf4j
public class UserListener extends AnalysisEventListener<ChannelUserRequest>  {

    List<ChannelUserRequest> datas = new ArrayList<>();

    IUserService userService;

    //记录耗时
    private Long elapsedTime;

    //记录成功条数
    private Integer successNo = 0;

    //记录失败条数
    private Integer failNo = 0;

    //返回提示信息
    public String result = "";

    public UserListener(IUserService userService) {
        this.userService = userService;
    }

    /**
     * 每解析一行，回调该方法
     *
     * @param data
     * @param analysisContext
     */
    @Override
    public void invoke(ChannelUserRequest userRequest, AnalysisContext analysisContext) {
        //行号
        Integer lineNumber = analysisContext.readRowHolder().getRowIndex()-1;
        if (!Objects.isNull(userRequest) && lineNumber != 0) {
            log.info("导入第{}行数据为，{}",lineNumber, JSON.toJSON(userRequest));
            //对入参必填字段做判空处理
            judgeReqeust(userRequest,lineNumber);
            //数据存储到list,供批量处理，或后续自己业务逻辑处理
            datas.add(userRequest);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        elapsedTime = System.currentTimeMillis();

        if (datas.size() < 0) {
            log.debug("表格中数据为空，请修改后再上传");
            return;
        }
        Map<String,Integer> map = userService.insChannelUser(datas);
        log.info("导入成功条数为，{}，失败条数为，{}",map.get("successNo"),map.get("failNo"));
        result = "导入成功条数为，"+map.get("successNo")+"，失败条数为，{}"+map.get("failNo");
        // 存储完成清理 list
        datas.clear();
    }

    //对入参必填字段做判空处理
    private void judgeReqeust(ChannelUserRequest userRequest, Integer lineNumber) {
        if (StringUtils.isEmpty(userRequest.getEmail())) {
            throw new RuntimeException("第"+lineNumber+"行，该属性值为空，请重新填写");
        }
    }
}
