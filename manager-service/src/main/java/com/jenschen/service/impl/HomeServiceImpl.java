package com.jenschen.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.jenschen.base.Response;
import com.jenschen.dao.HomeDao;
import com.jenschen.dao.InfoDao;
import com.jenschen.elastic.dao.AnswerDao;
import com.jenschen.elastic.entity.AnswerEntity;
import com.jenschen.entity.HomeEntity;
import com.jenschen.entity.InfoEntity;
import com.jenschen.helper.SpringHelper;
import com.jenschen.response.home.ChartData;
import com.jenschen.response.home.HomeResp;
import com.jenschen.service.HomeService;
import com.jenschen.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;


@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    private AnswerDao answerDao;

    @Autowired
    private InfoDao infoDao;

    @Autowired
    private HomeDao homeDao;

    @Override
    public Response<Object> getHomeInfo() {
        String username = SpringHelper.getUserName();
        HomeEntity home = homeDao.getBasicHomeInfo(username);
        HomeResp homeResp = BeanUtil.copyProperties(home, HomeResp.class);
        List<ChartData> data = createChartData();
        homeResp.setData(data);

        return ResultUtil.success(homeResp);
    }

    private List<ChartData> createChartData(){
        final int num = 3;
        List<ChartData> data = new ArrayList<>(num);

        List<InfoEntity> infoEntityList = infoDao.getLastNInfo(num);

        final int days = 7;
        LocalDate now = LocalDate.now();
        LocalDateTime from = LocalDateTime.of(now, LocalTime.MAX);
        LocalDateTime to = LocalDateTime.of(now, LocalTime.MIN).minusDays(days);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        List<String> dates = new ArrayList<>(days);
        for(int i = 0; i < days; i++){
            dates.add(to.plusDays(i).format(formatter));
        }

        for(var info : infoEntityList){
            List<AnswerEntity> list = answerDao.findByInfoIdAndCreatedAtBetweenOrderByCreatedAtDesc(info.getId(), from, to);
            TreeMap<String, Integer> map = new TreeMap<>();
            for(var entity : list){
                if(entity.getCreatedAt() == null){
                    continue;
                }
                String date = entity.getCreatedAt().format(formatter);
                Integer count = map.getOrDefault(date, 0);
                map.put(date, count + 1);
            }

            List<Integer> values = new ArrayList<>(days);
            for(int i = 0; i < days; i++){
                Integer count = map.getOrDefault(dates.get(i), 0);
                values.add(count);
            }

            ChartData chartData = ChartData.builder().name(info.getName())
                    .dates(dates).values(values).build();
            data.add(chartData);
        }

        return data;
    }
}
