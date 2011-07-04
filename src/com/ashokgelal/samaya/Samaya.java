package com.ashokgelal.samaya;

public class Samaya extends DateTime{
    public Samaya(String aDateTime) {
        super(aDateTime);
    }

    public Samaya(int aYear, int aMonth, int aDay, int aHour, int aMinute, int aSecond, int aNanoseconds) {
        super(aYear, aMonth, aDay, aHour, aMinute, aSecond, aNanoseconds);
    }

    public Samaya(int aYear, int aMonth, int aDay, int aHour, int aMinute, int aSecond) {
        super(aYear, aMonth, aDay, aHour, aMinute, aSecond, 0);
    }

    public DateTime minus(int year, int month, int day, int hour, int minute, int second)
    {
        return minus(year, month, day, hour, minute, second, DayOverflow.Abort);
    }

    public DateTime minusSeconds(int seconds)
    {
        return minus(0, 0, 0, 0, 0, seconds);
    }

}
