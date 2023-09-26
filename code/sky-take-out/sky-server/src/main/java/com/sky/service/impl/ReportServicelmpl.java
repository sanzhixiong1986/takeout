package com.sky.service.impl;

import com.sky.mapper.OrderMapper;
import com.sky.service.ReportService;
import com.sky.vo.TurnoverReportVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportServicelmpl implements ReportService {

    //订单表
    @Autowired
    private OrderMapper orderMapper;

    /**
     * @param begin
     * @param end
     * @return
     */
    public TurnoverReportVO getTurnover(LocalDate begin, LocalDate end) {
        //时间的计算出来字符串
        List<LocalDate> dateList = new ArrayList<>();
        dateList.add(begin);
        //日期的字符串计算
        while (!begin.equals(end)) {
            begin = begin.plusDays(1);
            dateList.add(begin);
        }

        List<Double> sumList = new ArrayList<>();
        for (LocalDate date : dateList) {
            //营业额的状态为已经完成的
            LocalDateTime BeginTime = LocalDateTime.of(date, LocalTime.MIN);//date 00:00:00
            LocalDateTime EndTime = LocalDateTime.of(date, LocalTime.MAX);  //date 23:59:59
            //数据库操作
            Map map = new HashMap();
            map.put("begin", BeginTime);
            map.put("end", EndTime);
            map.put("sataus", 5);
            Double turnOver = orderMapper.sumByMap(map);
            turnOver = turnOver == null ? 0.0 : turnOver;
            sumList.add(turnOver);
        }

        return TurnoverReportVO.builder()
                .dateList(StringUtils.join(dateList, ","))
                .turnoverList(StringUtils.join(sumList, ","))
                .build();
    }
}
