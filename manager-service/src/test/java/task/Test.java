package task;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.jenschen.entity.InfoEntity;
import com.jenschen.enumeration.RepeatCollectTypeEnum;
import com.jenschen.request.info.InfoReq;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Test {

    public static void main(String[] args) {


    }

    public static List<InfoEntity> splitActivity(InfoReq infoReq){
        List<InfoEntity> infoEntityList = new ArrayList<>();
        if(RepeatCollectTypeEnum.ONCE.equals(infoReq.getRepeatCollectType())){
            //如果只有一次
            InfoEntity infoEntity = new InfoEntity();
            infoEntity.setStartDateTime(infoReq.getStartDateTime());
            infoEntity.setEndDateTime(infoReq.getEndDateTime());
            infoEntityList.add(infoEntity);
        }else if(RepeatCollectTypeEnum.EVERY_DAY.equals(infoReq.getRepeatCollectType())){
            LocalDateTime cur = infoReq.getSendDateTime();
            InfoEntity infoEntity = new InfoEntity();
            infoEntity.setStartDateTime(cur);
            infoEntity.setEndDateTime(LocalDateTimeUtil.endOfDay(cur));

            cur = LocalDateTimeUtil.beginOfDay(cur);
            cur = cur.plusDays(1);
            while(cur.isBefore(infoReq.getEndDateTime())){
                InfoEntity info = new InfoEntity();
                infoEntity.setStartDateTime(cur);
                LocalDateTime end = LocalDateTimeUtil.endOfDay(cur);
                end = end.isAfter(infoReq.getEndDateTime()) ? info.getEndDateTime() : end;
                infoEntity.setEndDateTime(end);
                cur = cur.plusDays(1);
            }
        }else if(RepeatCollectTypeEnum.EVERY_WEEK.equals(infoReq.getRepeatCollectType())){
            LocalDateTime cur = infoReq.getSendDateTime();
            InfoEntity infoEntity = new InfoEntity();
            infoEntity.setStartDateTime(cur);
            infoEntity.setEndDateTime(LocalDateTimeUtil.endOfDay(cur));

            cur = LocalDateTimeUtil.beginOfDay(cur);
            cur = cur.plusDays(1);
            while(cur.isBefore(infoReq.getEndDateTime())){
                int[] weeks = Arrays.stream(infoReq.getRepeatValue().split(","))
                        .flatMapToInt(e -> IntStream.of(Integer.parseInt(e)))
                        .toArray();
                if(Arrays.binarySearch(weeks, cur.getDayOfWeek().getValue()) >= 0){
                    InfoEntity info = new InfoEntity();
                    infoEntity.setStartDateTime(cur);
                    LocalDateTime end = LocalDateTimeUtil.endOfDay(cur);
                    end = end.isAfter(infoReq.getEndDateTime()) ? info.getEndDateTime() : end;
                    infoEntity.setEndDateTime(end);
                }
                cur = cur.plusDays(1);
            }
        }

        return infoEntityList;

    }
}
