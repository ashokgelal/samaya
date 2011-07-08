package com.ashokgelal.samaya;

import java.util.TimeZone;

public class Samaya extends DateTime{
    public static final Samaya MinValue = new Samaya(1, 1, 1, 0, 0, 0, 0);
    public static final Samaya MaxValue = new Samaya(9999, 12, 31, 23, 59, 59, 9999999);

    public Samaya(String aDateTime) {
        super(aDateTime);
    }

    public Samaya(int aYear, int aMonth, int aDay, int aHour, int aMinute, int aSecond, int aNanoseconds) {
        super(aYear, aMonth, aDay, aHour, aMinute, aSecond, aNanoseconds);
    }

    public Samaya(int aYear, int aMonth, int aDay, int aHour, int aMinute, int aSecond) {
        super(aYear, aMonth, aDay, aHour, aMinute, aSecond, 0);
    }

   public Samaya(int year, int month, int day) {
       super(year, month, day, 0, 0, 0, 0);
   }

   public Samaya(int year, int month, int day, int hour, int minute) {
       super(year, month, day, hour, minute, 0, 0);
   }

    private Samaya Subtract(int year, int month, int day, int hour, int minute, int second)
    {
        DateTime time = minus(year, month, day, hour, minute, second, DayOverflow.LastDay);
        return new Samaya(time);
    }

    public TimeSpan Subtract(Samaya time)
    {
        // AG: feel backasswards so I'm reverting the way minus works
        return TimeSpan.FromSeconds(time.numSecondsFrom(this));
    }

    public Samaya Subtract(TimeSpan timeSpan) {
        return new Samaya(minus(0, 0, timeSpan.Days(), timeSpan.Hours(), timeSpan.Minutes(), timeSpan.Seconds(), DayOverflow.LastDay));
    }

    public Samaya Add(TimeSpan timeSpan){
        DateTime time = plus(0, 0, timeSpan.Days(), timeSpan.Hours(), timeSpan.Minutes(), timeSpan.Seconds(), DayOverflow.LastDay);
        return new Samaya(time);
    }

    public Samaya AddDays(double days)
    {
        return Add(TimeSpan.FromDays(days));
    }

    public Samaya AddHours(double hours)
    {
       return Add(TimeSpan.FromHours(hours));
    }

    public Samaya AddMilliseconds(double milliseconds)
    {
        return Add(TimeSpan.FromMilliseconds(milliseconds));
    }

    public Samaya AddMinutes(double minutes)
    {
        return Add(TimeSpan.FromMinutes(minutes));
    }

    public Samaya AddSeconds(double seconds)
    {
        return Add(TimeSpan.FromSeconds(seconds));
    }

    public Samaya AddTicks(long ticks )
    {
        return Add(TimeSpan.FromTicks(ticks));
    }

    public Samaya AddMonths(int months)
    {
        DateTime time = plus(0, months, 0, 0, 0, 0, DayOverflow.LastDay);
        return new Samaya(time);
    }

    public Samaya AddYears(int years)
    {
        DateTime time = plus(years, 0, 0, 0, 0, 0, DayOverflow.LastDay);
        return new Samaya(time);
    }
    public Samaya(DateTime time){
        super(time.getYear(), time.getMonth(), time.getDay(), time.getHour(), time.getMinute(), time.getSecond(), time.getNanoseconds());
    }

    public static Samaya now() {
        DateTime time = Samaya.now(TimeZone.getDefault());
        return new Samaya(time);
    }

    public long Milliseconds() {
        return getMilliseconds(TimeZone.getDefault());
    }

    public Integer Hour() {
        return getHour();
    }
}
