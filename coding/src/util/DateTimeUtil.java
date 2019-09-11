package util;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @description:
 * @author: bowen.wei@hand-china.com
 * @version: 1.0
 * @date: 2019-09-11 9:48
 */
public class DateTimeUtil {
    public static void main(String[] args) {
        //获取当前年月日
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate.toString());
        //根据指定的年月日构造
        LocalDate localDate1 = LocalDate.of(2019, 8 , 8);
        System.out.println(localDate1.toString());
        //获取当前时分秒
        LocalTime localTime = LocalTime.now();
        System.out.println(localTime.toString());
        //根据指定的时分秒构造
        LocalTime localTime1 = LocalTime.of(8, 18, 28);
    }
}
