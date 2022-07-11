package com.jenschen.service.impl.taskConverter;

import com.jenschen.enumeration.ErrorEnum;
import com.jenschen.enumeration.RepeatCollectTypeEnum;
import com.jenschen.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskConverterFactory {

    @Autowired
    private  OnceTaskConverter onceTaskConverter;

    @Autowired
    private  EveryDayConverter everyDayConverter;

    @Autowired
    private  EveryWeekConverter everyWeekConverter;

    @Autowired
    private  EveryMonthConverter everyMonthConverter;


    public TaskConverter getTaskConverter(RepeatCollectTypeEnum repeatCollectTypeEnum){
        if(RepeatCollectTypeEnum.ONCE.equals(repeatCollectTypeEnum)){
            return onceTaskConverter;
        }else if(RepeatCollectTypeEnum.EVERY_DAY.equals(repeatCollectTypeEnum)){
            return everyDayConverter;
        }else if(RepeatCollectTypeEnum.EVERY_WEEK.equals(repeatCollectTypeEnum)){
            return everyWeekConverter;
        }else if(RepeatCollectTypeEnum.EVERY_MONTH.equals(repeatCollectTypeEnum)){
            return everyMonthConverter;
        }

        throw new BizException(ErrorEnum.INFO_ERROR_REPEATCOLLECTTYPE);
    }
}
