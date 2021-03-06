package com.geekbrains.webapp.core.aop.services;

import com.geekbrains.webapp.core.aop.utils.StatisticServiceMap;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class StatisticService {
    private final StatisticServiceMap statisticServiceMap;

    public Map<String, Long> getStatisticService () {
        return statisticServiceMap.getMap();
    }
}
