package com.ashokgelal.samaya.tests;

import java.util.Calendar;
import java.util.TimeZone;

import com.ashokgelal.samaya.Samaya;
import com.ashokgelal.samaya.TimeSpan;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class SamayaTest{
    @Test
    public void testAddTime(){
        Samaya time = new Samaya(2001, 1, 1);
        TimeSpan duration = new TimeSpan(36, 0, 0);
        Samaya samaya = time.Add(duration);
        assertEquals(2001, samaya.getYear().intValue());
        assertEquals(1, samaya.getMonth().intValue());
        assertEquals(2, samaya.getDay().intValue());
        assertEquals(12, samaya.getHour().intValue());
        assertEquals(0, samaya.getMinute().intValue());
        assertEquals(0, samaya.getSecond().intValue());
    }

    @Test
    public void testAddTime1(){
        Samaya time = new Samaya(2001, 1, 1);
        TimeSpan duration = new TimeSpan(36, 36, 5, 0);
        Samaya samaya = time.Add(duration);
        assertEquals(2001, samaya.getYear().intValue());
        assertEquals(2, samaya.getMonth().intValue());
        assertEquals(7, samaya.getDay().intValue());
        assertEquals(12, samaya.getHour().intValue());
        assertEquals(5, samaya.getMinute().intValue());
        assertEquals(0, samaya.getSecond().intValue());
    }

    @Test
    public void testAddDays(){
        Samaya time = new Samaya(2001, 1, 1);
        Samaya samaya = time.AddDays(36);
        assertEquals(2001, samaya.getYear().intValue());
        assertEquals(2, samaya.getMonth().intValue());
        assertEquals(6, samaya.getDay().intValue());
        assertEquals(0, samaya.getHour().intValue());
        assertEquals(0, samaya.getMinute().intValue());
        assertEquals(0, samaya.getSecond().intValue());
    }

    @Test
    public void testAddHours(){
        Samaya time = new Samaya(2001, 1, 1);
        Samaya samaya = time.AddHours(36);
        assertEquals(2001, samaya.getYear().intValue());
        assertEquals(1, samaya.getMonth().intValue());
        assertEquals(2, samaya.getDay().intValue());
        assertEquals(12, samaya.getHour().intValue());
        assertEquals(0, samaya.getMinute().intValue());
        assertEquals(0, samaya.getSecond().intValue());
    }

    @Test
    public void testAddHours1(){
        Samaya time = new Samaya(2001, 1, 31);
        Samaya samaya = time.AddHours(36);
        assertEquals(2001, samaya.getYear().intValue());
        assertEquals(2, samaya.getMonth().intValue());
        assertEquals(1, samaya.getDay().intValue());
        assertEquals(12, samaya.getHour().intValue());
        assertEquals(0, samaya.getMinute().intValue());
        assertEquals(0, samaya.getSecond().intValue());
    }

    @Test
    public void testAddMonths(){
        Samaya time = new Samaya(2001, 1, 31);
        Samaya samaya = time.AddMonths(36);
        assertEquals(2004, samaya.getYear().intValue());
        assertEquals(1, samaya.getMonth().intValue());
        assertEquals(31, samaya.getDay().intValue());
        assertEquals(0, samaya.getHour().intValue());
        assertEquals(0, samaya.getMinute().intValue());
        assertEquals(0, samaya.getSecond().intValue());
    }

    @Test
    public void testAddMinutes(){
        Samaya time = new Samaya(2001, 12, 31, 23, 59);
        Samaya samaya = time.AddMinutes(6);
        assertEquals(2002, samaya.getYear().intValue());
        assertEquals(1, samaya.getMonth().intValue());
        assertEquals(1, samaya.getDay().intValue());
        assertEquals(0, samaya.getHour().intValue());
        assertEquals(5, samaya.getMinute().intValue());
        assertEquals(0, samaya.getSecond().intValue());
    }

    @Test
    public void testAddSeconds(){
        Samaya time = new Samaya(2001, 12, 31, 23, 59, 59);
        Samaya samaya = time.AddSeconds(6);
        assertEquals(2002, samaya.getYear().intValue());
        assertEquals(1, samaya.getMonth().intValue());
        assertEquals(1, samaya.getDay().intValue());
        assertEquals(0, samaya.getHour().intValue());
        assertEquals(0, samaya.getMinute().intValue());
        assertEquals(5, samaya.getSecond().intValue());
    }

    @Test
    public void testAddMilliseconds(){
        Samaya time = new Samaya(2001, 12, 31, 23, 59, 59);
        Samaya samaya = time.AddMilliseconds(2000);
        assertEquals(2002, samaya.getYear().intValue());
        assertEquals(1, samaya.getMonth().intValue());
        assertEquals(1, samaya.getDay().intValue());
        assertEquals(0, samaya.getHour().intValue());
        assertEquals(0, samaya.getMinute().intValue());
        assertEquals(1, samaya.getSecond().intValue());
    }

    @Test
    public void testAddTicks(){
        Samaya time = new Samaya(2001, 12, 31, 23, 59, 59);
        Samaya samaya = time.AddTicks(60000000L);
        assertEquals(2002, samaya.getYear().intValue());
        assertEquals(1, samaya.getMonth().intValue());
        assertEquals(1, samaya.getDay().intValue());
        assertEquals(0, samaya.getHour().intValue());
        assertEquals(0, samaya.getMinute().intValue());
        assertEquals(5, samaya.getSecond().intValue());
    }

    @Test
    public void testSubtractTime(){
        Samaya date1 = new Samaya(1996, 6, 3, 22, 15, 0);
        Samaya date2 = new Samaya(1996, 12, 6, 13, 2, 0);

        TimeSpan span = date2.Subtract(date1);
        assertEquals(185, span.Days());
        assertEquals(14, span.Hours());
        assertEquals(47, span.Minutes());
        assertEquals(0, span.Seconds());
    }

    @Test
    public void testSubtractTime1(){
        Samaya date1 = new Samaya(1996, 6, 3, 22, 15, 0);
        Samaya date2 = new Samaya(1996, 12, 6, 13, 2, 0);

        TimeSpan span = date1.Subtract(date2);
        assertEquals(-185, span.Days());
        assertEquals(-14, span.Hours());
        assertEquals(-47, span.Minutes());
        assertEquals(0, span.Seconds());
    }

    @Test
    public void testSubtractTime2(){
        Samaya date1 = new Samaya(1996, 6, 3, 22, 15, 0);
        Samaya date2 = new Samaya(1996, 12, 6, 13, 2, 0);
        Samaya date3 = new Samaya(1996, 10, 12, 8, 42, 0);
        TimeSpan diff = date2.Subtract(date1);
        Samaya date4 = date3.Subtract(diff);
        assertEquals(1996, date4.getYear().intValue());
        assertEquals(4, date4.getMonth().intValue());
        assertEquals(9, date4.getDay().intValue());
        assertEquals(17, date4.getHour().intValue());
        assertEquals(55, date4.getMinute().intValue());
    }

    @Test
    public void testSubtractTime3(){
        Samaya date1 = new Samaya(1996, 12, 6, 13, 2, 0);
        Samaya date2 = new Samaya(1996, 10, 12, 8, 42, 0);
        TimeSpan diff = date1.Subtract(date2);
        assertEquals(55, diff.Days());
        assertEquals(4, diff.Hours());
        assertEquals(20, diff.Minutes());
    }

    @Test
    public void testSubtractTime4(){
        Samaya date1 = new Samaya(1996, 6, 3, 22, 15, 0);
        Samaya date2 = new Samaya(1996, 12, 6, 13, 2, 0);
        Samaya date3 = new Samaya(1996, 10, 12, 8, 42, 0);
        TimeSpan diff = date2.Subtract(date3);
        Samaya date4 = date1.Subtract(diff);
        assertEquals(1996, date4.getYear().intValue());
        assertEquals(4, date4.getMonth().intValue());
        assertEquals(9, date4.getDay().intValue());
        assertEquals(17, date4.getHour().intValue());
        assertEquals(55, date4.getMinute().intValue());
    }
    
    @Test
    public void testSinceEpoch(){
    	Samaya date = new Samaya(2011, 7, 16, 20, 51, 25);
    	long seconds = date.Timestamp();
    	assertEquals(1310849485L, seconds);
    }
    
    @Test
    public void testTimestampToEpoch(){
    	Samaya date = Samaya.FromTimestamp(1310867739 * 1000L);
    	assertEquals(2011, date.getYear().intValue());
    	assertEquals(7, date.getMonth().intValue());
    	assertEquals(16, date.getDay().intValue());
    	assertEquals(19, date.getHour().intValue());
    	assertEquals(55, date.getMinute().intValue());
    	assertEquals(39, date.getSecond().intValue());
    	assertEquals(1310867739000L, date.Milliseconds());
    }
}


