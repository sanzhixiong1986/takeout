package com.sky.service;

import com.sky.vo.TurnoverReportVO;

import java.time.LocalDate;

/**
 * 统计方法
 */
public interface ReportService {

    /**
     * 统计指定时间的
     * @param begin
     * @param end
     * @return
     */
    TurnoverReportVO getTurnover(LocalDate begin,LocalDate end);
}
