Chapter 12. New Date and Time API
===============

### 历史情况

* java.util.Date
    * 年份的起始选择是1900年，月份的起始从0开始
    * 输出显示不直观（Thu Sep 21 00:00:00 CET 2017），也不在任何方面支持时区
    * DateFormat方法只在Date类里有，且非线程安全
    * java.util.Date类是可变的
* java.util.Calendar
    * 年份的起始选择被修正，但月份的起始依然是从0开始
    * java.util.Calendar是可变的

###          

* java.time.LocalDate/LocalTime/LocalDateTime
    * get(TemporalField field)
        * ChronoField枚举实现了TemporalField接口
            * ChronoField.YEAR
            * ChronoField.MONTH_OF_YEAR
            * ChronoField.DAY_OF_MONTH
    * 也可以使用更具可读性的方法
        * getYear()
        * getMonthValue()
        * ...
    * 合并LocalDate和LocalTime
        * of(LocalDate date, LocalTime time)
        * date.atTime(time)
        * time.atDate(date)
    * LocalDateTime拆分
        * toLocalDate()
        * toLocalTime()
    * LocalDateTime、LocalDate和LocalTime默认严格按照ISO 8601规定的日期和时间格式进行打印

* java.time.Instant
    * Instant与ZonedDateTime是等价的
    * 机器的日期和时间格式, 以Unix元年时间（传统的设定为UTC时区1970年1月1日午夜时分）开始所经历的秒数进行计算。

* java.time.temporal.Temporal
    * 目前为止，你看到的所有类都实现了Temporal接口，Temporal接口定义了如何读取和操纵为时间建模的对象的值。

* java.time.Period和java.time.Duration
    * The Period class uses the units year, month and day to represent a period of time.
    * The Duration class represents an interval of time in seconds or nanoseconds and is most suited for handling
      shorter amounts of time, in cases that require more precision.
    * Duration工厂方法
        * Duration.of(long amount, TemporalUnit unit)

* ChronoUnit枚举实现了TemporalUnit接口
    * ChronoUnit.MINUTES
    * ChronoUnit.WEEKS
    * ChronoUnit.NANOS

### 操纵、解析和格式化日期

* java.time.LocalDate/LocalTime/LocalDateTime
    * with(TemporalField field, long newValue)

* TemporalAdjuster
    * TemporalAdjusters类的静态工厂方法提供了大量的TemporalAdjuster
    * 可以通过实现TemporalAdjuster接口实现一个定制的TemporalAdjuster

* DateTimeFormatter
    * 创建DateTimeFormatter
        * 最简单的方法是通过它的静态工厂方法以及常量, 像BASIC_ISO_DATE和ISO_LOCAL_DATE
        * DateTimeFormatter.ofPattern("yyyy-MM-dd")
        * DateTimeFormatterBuilder

### 处理不同的时区和历法

* java.time.ZoneId类是java.util.TimeZone类的替代品
    * 静态方法 fromInstant()
        * LocalDateTime.ofInstant(Instant instant, ZoneId zone)
    * toInstant()
        * localDateTime.toInstant(ZoneOffset offset)

