package modernjavainaction.chap12;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class DateTimeTest {

    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
        test5();
        test6();
        test7();
        test8();
        test81();
        test9();
        test10();
        test101();
        test102();
        instantOfLocalDateTime();
        toInstantLocalDateTime();
    }

    /**
     * 输出：
     * LocalDateTime最小值：-999999999-01-01T00:00
     * LocalDateTime最大值：+999999999-12-31T23:59:59.999999999
     * -999999999-1-1
     * 999999999-12-31
     */
    private static void test1() {
        LocalDateTime min = LocalDateTime.MIN;
        LocalDateTime max = LocalDateTime.MAX;

        System.out.println("LocalDateTime最小值：" + min);
        System.out.println("LocalDateTime最大值：" + max);
        System.out.println(min.getYear() + "-" + min.getMonthValue() + "-" + min.getDayOfMonth());
        System.out.println(max.getYear() + "-" + max.getMonthValue() + "-" + max.getDayOfMonth());
    }

    /**
     * 输出：
     * 当前时区的本地时间：2021-01-17T17:00:41.446
     * 当前时区的本地时间：2021-01-17T17:00:41.447
     * 纽约时区的本地时间：2021-01-17T04:00:41.450
     */
    private static void test2() {
        System.out.println("当前时区的本地时间：" + LocalDateTime.now());
        System.out.println("当前时区的本地时间：" + LocalDateTime.of(LocalDate.now(), LocalTime.now()));
        System.out.println("纽约时区的本地时间：" + LocalDateTime.now(ZoneId.of("America/New_York"))); // 落后13小时
    }

    /**
     * 输出：
     * 计算前：2021-01-17T17:10:15.381
     * 计算后：2021-01-20T14:10:15.381
     * 相差天数：3
     * 相差小时数：-3
     */
    private static void test3() {
        LocalDateTime now = LocalDateTime.now(ZoneId.systemDefault());
        System.out.println("计算前：" + now);


        LocalDateTime after = now.plusDays(3); // 加3天
        after = after.plusHours(-3); // 减3个小时, 效果同now.minusDays(3);
        System.out.println("计算后：" + after);

        // 计算时间差
        Period period = Period.between(now.toLocalDate(), after.toLocalDate());
        System.out.println("相差天数：" + period.getDays());
        Duration duration = Duration.between(now.toLocalTime(), after.toLocalTime());
        System.out.println("相差小时数：" + duration.toHours());
    }

    /**
     * 输出：
     * 格式化输出：2021-01-17T17:15:32.48197
     * 格式化输出（本地化输出，中文环境）：21-1-17 下午5:15
     * 解析后输出：2021-01-17T18:00
     */
    private static void test4() {
        LocalDateTime now = LocalDateTime.now(ZoneId.systemDefault());
        System.out.println("格式化输出：" + DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(now));
        System.out.println("格式化输出（本地化输出，中文环境）：" + DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT, FormatStyle.SHORT).format(now));

        String dateTimeStrParam = "2021-01-17 18:00:00";
        System.out.println("解析后输出：" + LocalDateTime.parse(dateTimeStrParam, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.US)));
    }

    /**
     * 输出：
     * OffsetDateTime最小值：-999999999-01-01T00:00+18:00
     * OffsetDateTime最大值：+999999999-12-31T23:59:59.999999999-18:00
     * +18:00:-999999999-1-1
     * -18:00:999999999-12-31
     */
    private static void test5() {
        OffsetDateTime min = OffsetDateTime.MIN;
        OffsetDateTime max = OffsetDateTime.MAX;

        System.out.println("OffsetDateTime最小值：" + min);
        System.out.println("OffsetDateTime最大值：" + max);
        System.out.println(min.getOffset() + ":" + min.getYear() + "-" + min.getMonthValue() + "-" + min.getDayOfMonth());
        System.out.println(max.getOffset() + ":" + max.getYear() + "-" + max.getMonthValue() + "-" + max.getDayOfMonth());
    }

    /**
     * 输出：
     * 当前位置偏移量的本地时间：2021-01-17T19:02:06.328+08:00
     * 偏移量-4（纽约）的本地时间：：2021-01-17T19:02:06.329-04:00
     * 纽约时区的本地时间：2021-01-17T06:02:06.330-05:00
     */
    private static void test6() {
        System.out.println("当前位置偏移量的本地时间：" + OffsetDateTime.now());
        System.out.println("偏移量-4（纽约）的本地时间：：" + OffsetDateTime.of(LocalDateTime.now(), ZoneOffset.of("-4"))); // 直接拼接
        System.out.println("纽约时区的本地时间：" + OffsetDateTime.now(ZoneId.of("America/New_York"))); // 时间转换
    }

    /**
     * 输出：
     * 格式化输出（本地化输出，中文环境）：21-1-17 下午7:06
     * 解析后输出：2021-01-17T18:00+07:00
     */
    private static void test7() {
        OffsetDateTime now = OffsetDateTime.now(ZoneId.systemDefault());
        System.out.println("格式化输出（本地化输出，中文环境）：" + DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT, FormatStyle.SHORT).format(now));

        String dateTimeStrParam = "2021-01-17T18:00:00+07:00";
        System.out.println("解析后输出：" + OffsetDateTime.parse(dateTimeStrParam));
    }

    /**
     * 输出：
     * 当前时区（北京）时间为：2021-01-17T18:00
     * -4偏移量地方的晚上18点：2021-01-17T18:00-04:00
     * -4偏移量地方的晚上18点（方式二）：2021-01-17T18:00-04:00
     * 当前地区对应的-4地方的时间：2021-01-17T06:00-04:00
     */
    private static void test8() {
        LocalDateTime localDateTime = LocalDateTime.of(2021, 1, 17, 18, 0, 0);
        System.out.println("当前时区（北京）时间为：" + localDateTime);

        // 转换为偏移量为 -4的OffsetDateTime时间
        // 1、-4地方的晚上18点
        System.out.println("-4偏移量地方的晚上18点：" + OffsetDateTime.of(localDateTime, ZoneOffset.ofHours(-4)));
        System.out.println("-4偏移量地方的晚上18点（方式二）：" + localDateTime.atOffset(ZoneOffset.ofHours(-4)));
        // 2、北京时间晚上18:00 对应的-4地方的时间点
        Instant instant = localDateTime.toInstant(ZoneOffset.ofHours(8));
        System.out.println("当前地区对应的-4地方的时间：" + OffsetDateTime.ofInstant(instant, ZoneOffset.ofHours(-4)));
    }

    /**
     * 输出：
     * -4偏移量时间为：2021-01-17T19:33:28.139-04:00
     * LocalDateTime的表示形式：2021-01-17T19:33:28.139
     */
    private static void test81() {
        LocalDateTime localDateTime = LocalDateTime.parse("2021-01-17T19:33:28.139");
        OffsetDateTime offsetDateTime = OffsetDateTime.of(localDateTime, ZoneOffset.ofHours(-4));
        System.out.println("-4偏移量时间为：" + offsetDateTime);

        // 转为LocalDateTime 注意：时间还是未变的哦
        System.out.println("LocalDateTime的表示形式：" + offsetDateTime.toLocalDateTime());
    }

    /**
     * 输出：
     * 当前位置偏移量的本地时间：2021-01-17T19:25:10.520+08:00[Asia/Shanghai]
     * 纽约时区的本地时间：2021-01-17T19:25:10.521-05:00[America/New_York]
     * 北京实现对应的纽约时区的本地时间：2021-01-17T06:25:10.528-05:00[America/New_York]
     */
    private static void test9() {
        System.out.println("当前位置偏移量的本地时间：" + ZonedDateTime.now());
        System.out.println("纽约时区的本地时间：" + ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("America/New_York")));
        System.out.println("北京实现对应的纽约时区的本地时间：" + ZonedDateTime.now(ZoneId.of("America/New_York")));
    }

    /**
     * 输出：
     * 当前时区（北京）时间为：2021-01-17T18:00
     * 纽约时区晚上18点：2021-01-17T18:00-05:00[America/New_York]
     * 纽约时区晚上18点（方式二）：2021-01-17T18:00-05:00[America/New_York]
     * 北京地区此时间对应的纽约的时间：2021-01-17T06:00-04:00
     * 北京地区此时间对应的纽约的时间：2021-01-17T06:00-04:00
     */
    private static void test10() {
        LocalDateTime localDateTime = LocalDateTime.of(2021, 1, 17, 18, 0, 0);
        System.out.println("当前时区（北京）时间为：" + localDateTime);

        // 转换为偏移量为 -4的OffsetDateTime时间
        // 1、-4地方的晚上18点
        System.out.println("纽约时区晚上18点：" + ZonedDateTime.of(localDateTime, ZoneId.of("America/New_York")));
        System.out.println("纽约时区晚上18点（方式二）：" + localDateTime.atZone(ZoneId.of("America/New_York")));
        // 2、北京时间晚上18:00 对应的-4地方的时间点
        Instant instant = localDateTime.toInstant(ZoneOffset.ofHours(8));
        System.out.println("北京地区此时间对应的纽约的时间：" + ZonedDateTime.ofInstant(instant, ZoneOffset.ofHours(-4)));
        System.out.println("北京地区此时间对应的纽约的时间：" + ZonedDateTime.ofInstant(localDateTime, ZoneOffset.ofHours(8), ZoneOffset.ofHours(-4)));
    }

    /**
     * 输出：
     * -4偏移量时间为：2021-01-17T19:43:28.320-04:00
     * ZonedDateTime的表示形式：2021-01-17T19:43:28.320-04:00
     * ZonedDateTime的表示形式：2021-01-17T18:43:28.320-05:00[America/New_York]
     * ZonedDateTime的表示形式：2021-01-17T19:43:28.320-05:00[America/New_York]
     */
    private static void test101() {
        LocalDateTime localDateTime = LocalDateTime.of(2021, 1, 17, 19, 43, 28, 320_000_000);
        OffsetDateTime offsetDateTime = OffsetDateTime.of(localDateTime, ZoneOffset.ofHours(-4));
        System.out.println("-4偏移量时间为：" + offsetDateTime);

        // 转换为ZonedDateTime的表示形式, 2021.03.14 - 2021.11.07期间，纽约的偏移量是-4，其余时候是-5。
        System.out.println("ZonedDateTime的表示形式：" + offsetDateTime.toZonedDateTime());
        System.out.println("ZonedDateTime的表示形式：" + offsetDateTime.atZoneSameInstant(ZoneId.of("America/New_York")));
        System.out.println("ZonedDateTime的表示形式：" + offsetDateTime.atZoneSimilarLocal(ZoneId.of("America/New_York")));
    }

    /**
     * 输出：
     * -4偏移量时间为：2021-05-05T18:00-04:00
     * ZonedDateTime的表示形式：2021-05-05T18:00-04:00
     * ZonedDateTime的表示形式：2021-05-05T18:00-04:00[America/New_York]
     * ZonedDateTime的表示形式：2021-05-05T18:00-04:00[America/New_York]
     */
    private static void test102() {
        LocalDateTime localDateTime = LocalDateTime.of(2021, 5, 5, 18, 0, 0);
        OffsetDateTime offsetDateTime = OffsetDateTime.of(localDateTime, ZoneOffset.ofHours(-4));
        System.out.println("-4偏移量时间为：" + offsetDateTime);

        // 转换为ZonedDateTime的表示形式
        System.out.println("ZonedDateTime的表示形式：" + offsetDateTime.toZonedDateTime());
        System.out.println("ZonedDateTime的表示形式：" + offsetDateTime.atZoneSameInstant(ZoneId.of("America/New_York")));
        System.out.println("ZonedDateTime的表示形式：" + offsetDateTime.atZoneSimilarLocal(ZoneId.of("America/New_York")));
    }

    private static void instantOfLocalDateTime() {

        // https://stackoverflow.com/questions/16151383/what-does-the-000z-of-yyyy-mm-ddt000000-000z-mean
        ZoneId zoneId = ZoneId.of("Asia/Shanghai");
        Instant instant = Instant.parse("2006-04-03T05:10:15.00Z");

        // Here, this method creates an instance of LocalDateTime by using the given Instant and ZoneId
        System.out.println("LocalDateTime.ofInstant(instant,zoneId): " + LocalDateTime.ofInstant(instant, zoneId)); // 2006-04-03T13:10:15
    }

    private static void toInstantLocalDateTime() {
        LocalDateTime localDateTime = LocalDateTime.parse("2006-04-03T13:10:15");

        ZoneId zoneId = ZoneId.of("Asia/Shanghai");
        ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId);
        System.out.println("zonedDateTime = " + zonedDateTime);

        Instant instant = zonedDateTime.toInstant();
        System.out.println("instant from ZonedDateTime.toInstant() = " + instant); // 2006-04-03T05:10:15Z

        ZoneOffset zoneOffset = ZoneOffset.of("+8");
        System.out.println("zoneOffset = " + zoneOffset);

        Instant instantOfZoneOffset = localDateTime.toInstant(zoneOffset);
        System.out.println("instant from LocalDateTime.toInstant() = " + instantOfZoneOffset); // 2006-04-03T05:10:15Z
    }
}
