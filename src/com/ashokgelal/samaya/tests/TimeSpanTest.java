package com.ashokgelal.samaya.tests;

import com.ashokgelal.samaya.TimeSpan;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class TimeSpanTest {

    @Test
    public void TicksPerMillisecond_Has_To_Be_10000(){
        assertEquals(10000l, TimeSpan.TicksPerMillisecond);
    }
    @Test
    public void TicksPerSecond_Has_To_Be_10000000(){
        assertEquals(10000000l, TimeSpan.TicksPerSecond);
    }
    @Test
    public void TicksPerMinute_Has_To_Be_600000000(){
        assertEquals(600000000l, TimeSpan.TicksPerMinute);
    }
    @Test
    public void TicksPerHour_Has_To_Be_36000000000(){
        assertEquals(36000000000l, TimeSpan.TicksPerHour);
    }

    @Test
    public void TicksPerDay_Has_To_Be_864000000000(){
        assertEquals(864000000000l, TimeSpan.TicksPerDay);
    }

    @Test
    public void ConstructorTest_Passing_Ticks(){
        TimeSpan span = new TimeSpan(864000000000l);
        assertEquals(864000000000l, span.Ticks());
    }

    @Test
    public void ConstructorTest_Passing_Hours_Minutes_Seconds(){
        TimeSpan span = new TimeSpan(23, 13, 46);
        assertEquals(23, span.Hours());
        assertEquals(13, span.Minutes());
        assertEquals(46, span.Seconds());
    }
    @Test
    public void ConstructorTest_Passing_Days_Hours_Minutes_Seconds(){
        TimeSpan span = new TimeSpan(12, 3, 19, 21);
        assertEquals(12, span.Days());
        assertEquals(3, span.Hours());
        assertEquals(19, span.Minutes());
        assertEquals(21, span.Seconds());
    }
    @Test
    public void ConstructorTest_Passing_Days_Hours_Minutes_Seconds_Milliseconds(){
        TimeSpan span = new TimeSpan(1, 24, 9, 59, 500);
        assertEquals(1, span.Days());
        assertEquals(24, span.Hours());
        assertEquals(9, span.Minutes());
        assertEquals(59, span.Seconds());
        assertEquals(500, span.Milliseconds());
    }

    @Test
    public void Test_For_Correct_Calculation_Of_Time_From_Ticks(){
        TimeSpan span = new TimeSpan(9006100100001L);
        assertEquals(10, span.Days());
        assertEquals(10, span.Hours());
        assertEquals(10, span.Minutes());
        assertEquals(10, span.Seconds());
        assertEquals(10, span.Milliseconds());
    }

    @Test
    public void Test_For_Correct_Calculation_Of_TotalTime_From_Ticks(){
        TimeSpan span = new TimeSpan(9006100100001l);
        assertEquals(10.423726081848145, span.TotalDays());
        assertEquals(250.16944885253906, span.TotalHours());
        assertEquals(15010.166015625, span.TotalMinutes());
        assertEquals(900610.0, span.TotalSeconds());
        assertEquals(9.00609984E8, span.TotalMilliseconds());
    }

    @Test
    public void Test_For_Zero_TimeSpan(){
        TimeSpan zero = TimeSpan.Zero;
        assertEquals(0.0, zero.TotalDays());
        assertEquals(0.0, zero.TotalHours());
        assertEquals(0.0, zero.TotalMinutes());
        assertEquals(0.0, zero.TotalSeconds());
        assertEquals(0.0, zero.TotalMilliseconds());
        assertEquals(0, zero.Days());
        assertEquals(0, zero.Hours());
        assertEquals(0, zero.Minutes());
        assertEquals(0, zero.Seconds());
        assertEquals(0, zero.Milliseconds());
        assertEquals(0, zero.Ticks());
    }

    @Test
    public void Test_For_Max_TimeSpan(){
        TimeSpan max = TimeSpan.MaxValue;
        assertEquals(9223372036854775807L, max.Ticks());
        assertEquals(1.0675199E7, max.TotalDays());
        assertEquals(2.562048E8, max.TotalHours());
        assertEquals(1.5372286976E10, max.TotalMinutes());
        assertEquals(9.22337214464E11, max.TotalSeconds());
        assertEquals(9.2233718038528E14, max.TotalMilliseconds());

        assertEquals(10675199, max.Days());
        assertEquals(2, max.Hours());
        assertEquals(48, max.Minutes());
        assertEquals(5, max.Seconds());
        assertEquals(477, max.Milliseconds());
    }

    @Test
    public void Test_For_Min_TimeSpan(){
        TimeSpan max = TimeSpan.MinValue;
        assertEquals(-9223372036854775808L, max.Ticks());
        assertEquals(-1.0675199E7, max.TotalDays());
        assertEquals(-2.562048E8, max.TotalHours());
        assertEquals(-1.5372286976E10, max.TotalMinutes());
        assertEquals(-9.22337214464E11, max.TotalSeconds());
        assertEquals(-9.2233718038528E14, max.TotalMilliseconds());

        assertEquals(-10675199, max.Days());
        assertEquals(-2, max.Hours());
        assertEquals(-48, max.Minutes());
        assertEquals(-5, max.Seconds());
        assertEquals(-477, max.Milliseconds());
    }

    @Test
    public void Test_For_Static_TimeSpan_Addition(){
        TimeSpan time1 = new TimeSpan(1, 0, 56, 0);
        TimeSpan time2 = new TimeSpan(12, 0, 0);
        TimeSpan time3 = TimeSpan.Add(time1, time2);

        assertEquals(1, time3.Days());
        assertEquals(12, time3.Hours());
        assertEquals(56, time3.Minutes());
        assertEquals(0, time3.Seconds());
        assertEquals(0, time3.Milliseconds());
    }

    @Test
    public void Test_For_Static_TimeSpan_Addition1(){
        TimeSpan time1 = new TimeSpan(1, 20, 0);
        TimeSpan time2 = new TimeSpan(0, 45, 10);
        TimeSpan time3 = TimeSpan.Add(time1, time2);

        assertEquals(0, time3.Days());
        assertEquals(2, time3.Hours());
        assertEquals(5, time3.Minutes());
        assertEquals(10, time3.Seconds());
        assertEquals(0, time3.Milliseconds());
    }

    @Test
    public void Test_For_Static_TimeSpan_Addition2(){
        TimeSpan time1 = new TimeSpan(1, 10, 20, 30, 40);
        TimeSpan time2 = new TimeSpan(-1, 2, 3, 4, 5);
        TimeSpan time3 = TimeSpan.Add(time1, time2);

        assertEquals(0, time3.Days());
        assertEquals(12, time3.Hours());
        assertEquals(23, time3.Minutes());
        assertEquals(34, time3.Seconds());
        assertEquals(45, time3.Milliseconds());
    }

    @Test
    public void Test_For_Static_TimeSpan_Addition3(){
        TimeSpan time1 = new TimeSpan(182, 12, 30, 30, 505);
        TimeSpan time2 = new TimeSpan(182, 11, 29, 29, 495);
        TimeSpan time3 = TimeSpan.Add(time1, time2);

        assertEquals(365, time3.Days());
        assertEquals(0, time3.Hours());
        assertEquals(0, time3.Minutes());
        assertEquals(0, time3.Seconds());
        assertEquals(0, time3.Milliseconds());
    }

    @Test
    public void Test_For_Static_TimeSpan_Addition4(){
        TimeSpan time1 = new TimeSpan(888888888888888L);
        TimeSpan time2 = new TimeSpan(999999999999999L);
        TimeSpan time3 = TimeSpan.Add(time1, time2);

        assertEquals(2186, time3.Days());
        assertEquals(5, time3.Hours());
        assertEquals(8, time3.Minutes());
        assertEquals(8, time3.Seconds());
        assertEquals(888, time3.Milliseconds());
    }

    @Test
    public void Test_For_TimeSpan_Addition(){
        TimeSpan time1 = new TimeSpan(1, 0, 56, 0);
        TimeSpan time2 = new TimeSpan(12, 0, 0);
        TimeSpan time3 = time1.Add(time2);

        assertEquals(1, time3.Days());
        assertEquals(12, time3.Hours());
        assertEquals(56, time3.Minutes());
        assertEquals(0, time3.Seconds());
        assertEquals(0, time3.Milliseconds());
    }

    @Test
    public void Test_For_Static_TimeSpan_Subtraction(){
        TimeSpan time1 = new TimeSpan(1, 0, 56, 0);
        TimeSpan time2 = new TimeSpan(12, 0, 0);
        TimeSpan time3 = TimeSpan.Subtract(time1, time2);

        assertEquals(0, time3.Days());
        assertEquals(12, time3.Hours());
        assertEquals(56, time3.Minutes());
        assertEquals(0, time3.Seconds());
        assertEquals(0, time3.Milliseconds());
    }

    @Test
    public void Test_For_Static_TimeSpan_Subtraction1(){
        TimeSpan time1 = new TimeSpan(1, 20, 0);
        TimeSpan time2 = new TimeSpan(0, 45, 10);
        TimeSpan time3 = TimeSpan.Subtract(time1, time2);

        assertEquals(0, time3.Days());
        assertEquals(0, time3.Hours());
        assertEquals(34, time3.Minutes());
        assertEquals(50, time3.Seconds());
        assertEquals(0, time3.Milliseconds());
    }

    @Test
    public void Test_For_Static_TimeSpan_Subtraction2(){
        TimeSpan time1 = new TimeSpan(1, 10, 20, 30, 40);
        TimeSpan time2 = new TimeSpan(-1, 2, 3, 4, 5);
        TimeSpan time3 = TimeSpan.Subtract(time1, time2);

        assertEquals(2, time3.Days());
        assertEquals(8, time3.Hours());
        assertEquals(17, time3.Minutes());
        assertEquals(26, time3.Seconds());
        assertEquals(35, time3.Milliseconds());
    }

    @Test
    public void Test_For_Static_TimeSpan_Subtraction3(){
        TimeSpan time1 = new TimeSpan(182, 12, 30, 30, 505);
        TimeSpan time2 = new TimeSpan(182, 11, 29, 29, 495);
        TimeSpan time3 = TimeSpan.Subtract(time1, time2);

        assertEquals(0, time3.Days());
        assertEquals(1, time3.Hours());
        assertEquals(1, time3.Minutes());
        assertEquals(1, time3.Seconds());
        assertEquals(10, time3.Milliseconds());
    }

    @Test
    public void Test_For_Static_TimeSpan_Subtraction4(){
        TimeSpan time1 = new TimeSpan(888888888888888L);
        TimeSpan time2 = new TimeSpan(999999999999999L);
        TimeSpan time3 = TimeSpan.Subtract(time1, time2);
        assertEquals(-111111111111111L, time3.Ticks());
        assertEquals(-128.600830078125, time3.TotalDays());
        assertEquals(-128, time3.Days());
        assertEquals(-14, time3.Hours());
        assertEquals(-25, time3.Minutes());
        assertEquals(-11, time3.Seconds());
        assertEquals(-111, time3.Milliseconds());
    }

    @Test
    public void Test_For_TimeSpan_Subtraction(){
        TimeSpan time1 = new TimeSpan(1, 0, 56, 0);
        TimeSpan time2 = new TimeSpan(12, 0, 0);
        TimeSpan time3 = time1.Subtract(time2);

        assertEquals(0, time3.Days());
        assertEquals(12, time3.Hours());
        assertEquals(56, time3.Minutes());
        assertEquals(0, time3.Seconds());
        assertEquals(0, time3.Milliseconds());
    }

    @Test
    public void Test_For_Equals(){
        TimeSpan time1 = new TimeSpan(2, 0, 0);
        TimeSpan time2 = new TimeSpan(0, 120, 0);

        assertTrue(time1.Equals(time2));
    }

    @Test
    public void Test_For_Equals1(){
        TimeSpan time1 = new TimeSpan(2, 0, 0);
        TimeSpan time2 = new TimeSpan(2, 0, 1);

        assertFalse(time1.Equals(time2));
    }

    @Test
    public void Test_For_Equals2(){
        TimeSpan time1 = new TimeSpan(2, 0, 0);
        TimeSpan time2 = new TimeSpan(2, 0, -1);

        assertFalse(time1.Equals(time2));
    }


    @Test
    public void Test_For_GreaterThan(){
        TimeSpan time1 = new TimeSpan(2, 0, 0);
        TimeSpan time2 = new TimeSpan(0, 120, 0);

        assertFalse(time1.GreaterThan(time2));
    }

    @Test
    public void Test_For_GreaterThan1(){
        TimeSpan time1 = new TimeSpan(2, 0, 0);
        TimeSpan time2 = new TimeSpan(2, 0, 1);

        assertFalse(time1.GreaterThan(time2));
    }

    @Test
    public void Test_For_GreatherThan2(){
        TimeSpan time1 = new TimeSpan(2, 0, 0);
        TimeSpan time2 = new TimeSpan(2, 0, -1);

        assertTrue(time1.GreaterThan(time2));
    }

    @Test
    public void Test_For_GreaterThanOrEquals(){
        TimeSpan time1 = new TimeSpan(2, 0, 0);
        TimeSpan time2 = new TimeSpan(0, 120, 0);

        assertTrue(time1.GreaterThanOrEqual(time2));
    }

    @Test
    public void Test_For_GreaterThanOrEquals1(){
        TimeSpan time1 = new TimeSpan(2, 0, 0);
        TimeSpan time2 = new TimeSpan(2, 0, 1);

        assertFalse(time1.GreaterThanOrEqual(time2));
    }

    @Test
    public void Test_For_GreatherThanOrEquals2(){
        TimeSpan time1 = new TimeSpan(2, 0, 0);
        TimeSpan time2 = new TimeSpan(2, 0, -1);

        assertTrue(time1.GreaterThanOrEqual(time2));
    }

    @Test
    public void Test_For_LessThan(){
        TimeSpan time1 = new TimeSpan(2, 0, 0);
        TimeSpan time2 = new TimeSpan(0, 120, 0);

        assertFalse(time1.LessThan(time2));
    }

    @Test
    public void Test_For_LessThan1(){
        TimeSpan time1 = new TimeSpan(2, 0, 0);
        TimeSpan time2 = new TimeSpan(2, 0, 1);

        assertTrue(time1.LessThan(time2));
    }

    @Test
    public void Test_For_LessThan2(){
        TimeSpan time1 = new TimeSpan(2, 0, 0);
        TimeSpan time2 = new TimeSpan(2, 0, -1);

        assertFalse(time1.LessThan(time2));
    }

    @Test
    public void Test_For_LessThanOrEquals(){
        TimeSpan time1 = new TimeSpan(2, 0, 0);
        TimeSpan time2 = new TimeSpan(0, 120, 0);

        assertTrue(time1.LessThanOrEqual(time2));
    }

    @Test
    public void Test_For_LessThanOrEquals1(){
        TimeSpan time1 = new TimeSpan(2, 0, 0);
        TimeSpan time2 = new TimeSpan(2, 0, 1);

        assertTrue(time1.LessThanOrEqual(time2));
    }

    @Test
    public void Test_For_LessThanOrEquals2(){
        TimeSpan time1 = new TimeSpan(2, 0, 0);
        TimeSpan time2 = new TimeSpan(2, 0, -1);

        assertFalse(time1.LessThanOrEqual(time2));
    }


    @Test
    public void Test_For_NotEquals(){
        TimeSpan time1 = new TimeSpan(2, 0, 0);
        TimeSpan time2 = new TimeSpan(0, 120, 0);

        assertFalse(time1.NotEquals(time2));
    }

    @Test
    public void Test_For_NotEquals1(){
        TimeSpan time1 = new TimeSpan(2, 0, 0);
        TimeSpan time2 = new TimeSpan(2, 0, 1);

        assertTrue(time1.NotEquals(time2));
    }

    @Test
    public void Test_For_NotEquals2(){
        TimeSpan time1 = new TimeSpan(2, 0, 0);
        TimeSpan time2 = new TimeSpan(2, 0, -1);

        assertTrue(time1.NotEquals(time2));
    }

    @Test
    public void Test_For_Duration(){
        TimeSpan time = new TimeSpan(1);
        assertEquals(1.0f, Math.signum(time.Duration().Ticks()));
    }

    @Test
    public void Test_For_Duration1(){
        TimeSpan time = new TimeSpan(-1234567);
        assertEquals(1.0f, Math.signum(time.Duration().Ticks()));
    }

    @Test
    public void Test_For_Duration2(){
        TimeSpan time = new TimeSpan(0, 0, 10, -20, -30);
        assertEquals(1.0f, Math.signum(time.Duration().Ticks()));
    }

    @Test
    public void Test_For_Duration3(){
        TimeSpan time = new TimeSpan(0, -10, 20, -30, 40);
        assertEquals(1.0f, Math.signum(time.Duration().Ticks()));
    }

    @Test
    public void Test_For_Duration4(){
        TimeSpan time = new TimeSpan(1, 10, 20, 40, 160);
        assertEquals(1.0f, Math.signum(time.Duration().Ticks()));
    }

    @Test
    public void Test_For_Duration5(){
        TimeSpan time = new TimeSpan(-10, -20, -30, -40, -50);
        assertEquals(1.0f, Math.signum(time.Duration().Ticks()));
    }

    @Test
    public void Test_For_FromDays(){
        TimeSpan time = TimeSpan.FromDays(0.000000006);
        assertEquals(0, time.Days());
        assertEquals(0, time.Hours());
        assertEquals(0, time.Minutes());
        assertEquals(0, time.Seconds());
        assertEquals(1, time.Milliseconds());
    }

    @Test
    public void Test_For_FromDays1(){
        TimeSpan time = TimeSpan.FromDays(0.000000017);
        assertEquals(0, time.Days());
        assertEquals(0, time.Hours());
        assertEquals(0, time.Minutes());
        assertEquals(0, time.Seconds());
        assertEquals(2, time.Milliseconds());
    }

    @Test
    public void Test_For_FromDays2(){
        TimeSpan time = TimeSpan.FromDays(0.000123456);
        assertEquals(0, time.Days());
        assertEquals(0, time.Hours());
        assertEquals(0, time.Minutes());
        assertEquals(10, time.Seconds());
        assertEquals(667, time.Milliseconds());
    }

    @Test
    public void Test_For_FromDays3(){
        TimeSpan time = TimeSpan.FromDays(1.234567898);
        assertEquals(1.2345678806304932, time.TotalDays());
        assertEquals(1, time.Days());
        assertEquals(5, time.Hours());
        assertEquals(37, time.Minutes());
        assertEquals(46, time.Seconds());
        assertEquals(667, time.Milliseconds());
    }

    @Test
    public void Test_For_FromDays4(){
        TimeSpan time = TimeSpan.FromDays(12345.678987654);
        assertEquals(12345, time.Days());
        assertEquals(16, time.Hours());
        assertEquals(17, time.Minutes());
        assertEquals(44, time.Seconds());
        assertEquals(534, time.Milliseconds());
    }
    @Test
    public void Test_For_FromDays5(){
        TimeSpan time = TimeSpan.FromDays(0.000011574);
        assertEquals(0, time.Days());
        assertEquals(0, time.Hours());
        assertEquals(0, time.Minutes());
        assertEquals(1, time.Seconds());
        assertEquals(0, time.Milliseconds());
        assertEquals(10000000, time.Ticks());
    }
    @Test
    public void Test_For_FromDays6(){
        TimeSpan time = TimeSpan.FromDays(0.000694444);
        assertEquals(0, time.Days());
        assertEquals(0, time.Hours());
        assertEquals(1, time.Minutes());
        assertEquals(0, time.Seconds());
        assertEquals(0, time.Milliseconds());
    }
    @Test
    public void Test_For_FromDays7(){
        TimeSpan time = TimeSpan.FromDays(0.041666666);
        assertEquals(0, time.Days());
        assertEquals(1, time.Hours());
        assertEquals(0, time.Minutes());
        assertEquals(0, time.Seconds());
        assertEquals(0, time.Milliseconds());
    }

    @Test
    public void Test_For_FromDays8(){
        TimeSpan time = TimeSpan.FromDays(1);
        assertEquals(1, time.Days());
        assertEquals(0, time.Hours());
        assertEquals(0, time.Minutes());
        assertEquals(0, time.Seconds());
        assertEquals(0, time.Milliseconds());
    }

    @Test
    public void Test_For_FromDays9(){
        TimeSpan time = TimeSpan.FromDays(20.84745602);
        assertEquals(20, time.Days());
        assertEquals(20, time.Hours());
        assertEquals(20, time.Minutes());
        assertEquals(20, time.Seconds());
        assertEquals(201, time.Milliseconds());
    }

    @Test
    public void Test_For_FromHours1(){
        TimeSpan time = TimeSpan.FromHours(0.0000002);
        assertEquals(0, time.Days());
        assertEquals(0, time.Hours());
        assertEquals(0, time.Minutes());
        assertEquals(0, time.Seconds());
        assertEquals(1, time.Milliseconds());
    }

    @Test
    public void Test_For_FromHours2(){
        TimeSpan time = TimeSpan.FromHours(0.0000003);
        assertEquals(0, time.Days());
        assertEquals(0, time.Hours());
        assertEquals(0, time.Minutes());
        assertEquals(0, time.Seconds());
        assertEquals(2, time.Milliseconds());
    }

    @Test
    public void Test_For_FromHours3(){
        TimeSpan time = TimeSpan.FromHours(0.0012345);
        assertEquals(5.1446757424855605E-5, time.TotalDays());
        assertEquals(0, time.Days());
        assertEquals(0, time.Hours());
        assertEquals(0, time.Minutes());
        assertEquals(4, time.Seconds());
        assertEquals(445, time.Milliseconds());
    }

    @Test
    public void Test_For_FromHours4(){
        TimeSpan time = TimeSpan.FromHours(123456.7898765);
        assertEquals(5144, time.Days());
        assertEquals(0, time.Hours());
        assertEquals(47, time.Minutes());
        assertEquals(23, time.Seconds());
        assertEquals(556, time.Milliseconds());
    }
    @Test
    public void Test_For_FromHours5(){
        TimeSpan time = TimeSpan.FromHours(0.0002777);
        assertEquals(0, time.Days());
        assertEquals(0, time.Hours());
        assertEquals(0, time.Minutes());
        assertEquals(1, time.Seconds());
        assertEquals(0, time.Milliseconds());
        assertEquals(10000000, time.Ticks());
    }
    @Test
    public void Test_For_FromHours6(){
        TimeSpan time = TimeSpan.FromHours(0.0166666);
        assertEquals(0, time.Days());
        assertEquals(0, time.Hours());
        assertEquals(1, time.Minutes());
        assertEquals(0, time.Seconds());
        assertEquals(0, time.Milliseconds());
    }
    @Test
    public void Test_For_FromHours7(){
        TimeSpan time = TimeSpan.FromHours(1);
        assertEquals(0, time.Days());
        assertEquals(1, time.Hours());
        assertEquals(0, time.Minutes());
        assertEquals(0, time.Seconds());
        assertEquals(0, time.Milliseconds());
    }

    @Test
    public void Test_For_FromHours8(){
        TimeSpan time = TimeSpan.FromHours(24);
        assertEquals(1, time.Days());
        assertEquals(0, time.Hours());
        assertEquals(0, time.Minutes());
        assertEquals(0, time.Seconds());
        assertEquals(0, time.Milliseconds());
    }

    @Test
    public void Test_For_FromHours9(){
        TimeSpan time = TimeSpan.FromHours(500.3389445);
        assertEquals(20, time.Days());
        assertEquals(20, time.Hours());
        assertEquals(20, time.Minutes());
        assertEquals(20, time.Seconds());
        assertEquals(201, time.Milliseconds());
    }

    @Test
    public void Test_For_FromHours10(){
        TimeSpan time = TimeSpan.FromHours(12.3456789);
        assertEquals(0, time.Days());
        assertEquals(12, time.Hours());
        assertEquals(20, time.Minutes());
        assertEquals(44, time.Seconds());
        assertEquals(445, time.Milliseconds());
    }

    @Test
    public void Test_For_FromMinutes1(){
        TimeSpan time = TimeSpan.FromMinutes(0.0000001);
        assertEquals(0, time.Days());
        assertEquals(0, time.Hours());
        assertEquals(0, time.Minutes());
        assertEquals(0, time.Seconds());
        assertEquals(1, time.Milliseconds());
    }

    @Test
    public void Test_For_FromMinutes2(){
        TimeSpan time = TimeSpan.FromMinutes(0.0000002);
        assertEquals(0, time.Days());
        assertEquals(0, time.Hours());
        assertEquals(0, time.Minutes());
        assertEquals(0, time.Seconds());
        assertEquals(1, time.Milliseconds());
    }

    @Test
    public void Test_For_FromMinutes3(){
        TimeSpan time = TimeSpan.FromMinutes(0.12345);
        assertEquals(0, time.Days());
        assertEquals(0, time.Hours());
        assertEquals(0, time.Minutes());
        assertEquals(7, time.Seconds());
        assertEquals(407, time.Milliseconds());
    }

    @Test
    public void Test_For_FromMinutes4(){
        TimeSpan time = TimeSpan.FromMinutes(1234.56789);
        assertEquals(0, time.Days());
        assertEquals(20, time.Hours());
        assertEquals(34, time.Minutes());
        assertEquals(34, time.Seconds());
        assertEquals(74, time.Milliseconds());
    }
    @Test
    public void Test_For_FromMinutes5(){
        TimeSpan time = TimeSpan.FromMinutes(12345678.98765);
        assertEquals(8573, time.Days());
        assertEquals(9, time.Hours());
        assertEquals(18, time.Minutes());
        assertEquals(59, time.Seconds());
        assertEquals(259, time.Milliseconds());
    }
    @Test
    public void Test_For_FromMinutes6(){
        TimeSpan time = TimeSpan.FromMinutes(0.01666);
        assertEquals(0, time.Days());
        assertEquals(0, time.Hours());
        assertEquals(0, time.Minutes());
        assertEquals(1, time.Seconds());
        assertEquals(0, time.Milliseconds());
    }
    @Test
    public void Test_For_FromMinutes7(){
        TimeSpan time = TimeSpan.FromMinutes(1);
        assertEquals(0, time.Days());
        assertEquals(0, time.Hours());
        assertEquals(1, time.Minutes());
        assertEquals(0, time.Seconds());
        assertEquals(0, time.Milliseconds());
    }

    @Test
    public void Test_For_FromMinutes8(){
        TimeSpan time = TimeSpan.FromMinutes(60);
        assertEquals(0, time.Days());
        assertEquals(1, time.Hours());
        assertEquals(0, time.Minutes());
        assertEquals(0, time.Seconds());
        assertEquals(0, time.Milliseconds());
    }

    @Test
    public void Test_For_FromMinutes9(){
        TimeSpan time = TimeSpan.FromMinutes(1440);
        assertEquals(1, time.Days());
        assertEquals(0, time.Hours());
        assertEquals(0, time.Minutes());
        assertEquals(0, time.Seconds());
        assertEquals(0, time.Milliseconds());
    }

    @Test
    public void Test_For_FromMinutes10(){
        TimeSpan time = TimeSpan.FromMinutes(30020.33667);
        assertEquals(20, time.Days());
        assertEquals(20, time.Hours());
        assertEquals(20, time.Minutes());
        assertEquals(20, time.Seconds());
        assertEquals(201, time.Milliseconds());
    }

    @Test
    public void Test_For_FromSeconds1(){
        TimeSpan time = TimeSpan.FromSeconds(0.001);
        assertEquals(0, time.Days());
        assertEquals(0, time.Hours());
        assertEquals(0, time.Minutes());
        assertEquals(0, time.Seconds());
        assertEquals(1, time.Milliseconds());
    }

    @Test
    public void Test_For_FromSeconds2(){
        TimeSpan time = TimeSpan.FromSeconds(0.0015);
        assertEquals(0, time.Days());
        assertEquals(0, time.Hours());
        assertEquals(0, time.Minutes());
        assertEquals(0, time.Seconds());
        assertEquals(2, time.Milliseconds());
    }

    @Test
    public void Test_For_FromSeconds3(){
        TimeSpan time = TimeSpan.FromSeconds(12.3456);
        assertEquals(0, time.Days());
        assertEquals(0, time.Hours());
        assertEquals(0, time.Minutes());
        assertEquals(12, time.Seconds());
        assertEquals(346, time.Milliseconds());
    }

    @Test
    public void Test_For_FromSeconds4(){
        TimeSpan time = TimeSpan.FromSeconds(123456.7898);
        assertEquals(1, time.Days());
        assertEquals(10, time.Hours());
        assertEquals(17, time.Minutes());
        assertEquals(36, time.Seconds());
        assertEquals(790, time.Milliseconds());
    }
    @Test
    public void Test_For_FromSeconds5(){
        TimeSpan time = TimeSpan.FromSeconds(1234567898.7654);
        assertEquals(14288, time.Days());
        assertEquals(23, time.Hours());
        assertEquals(31, time.Minutes());
        assertEquals(38, time.Seconds());
        assertEquals(766, time.Milliseconds());
    }
    @Test
    public void Test_For_FromSeconds6(){
        TimeSpan time = TimeSpan.FromSeconds(1);
        assertEquals(0, time.Days());
        assertEquals(0, time.Hours());
        assertEquals(0, time.Minutes());
        assertEquals(1, time.Seconds());
        assertEquals(0, time.Milliseconds());
    }
    @Test
    public void Test_For_FromSeconds7(){
        TimeSpan time = TimeSpan.FromSeconds(60);
        assertEquals(0, time.Days());
        assertEquals(0, time.Hours());
        assertEquals(1, time.Minutes());
        assertEquals(0, time.Seconds());
        assertEquals(0, time.Milliseconds());
    }

    @Test
    public void Test_For_FromSeconds8(){
        TimeSpan time = TimeSpan.FromSeconds(3600);
        assertEquals(0, time.Days());
        assertEquals(1, time.Hours());
        assertEquals(0, time.Minutes());
        assertEquals(0, time.Seconds());
        assertEquals(0, time.Milliseconds());
    }

    @Test
    public void Test_For_FromSeconds9(){
        TimeSpan time = TimeSpan.FromSeconds(86400);
        assertEquals(1, time.Days());
        assertEquals(0, time.Hours());
        assertEquals(0, time.Minutes());
        assertEquals(0, time.Seconds());
        assertEquals(0, time.Milliseconds());
    }

    @Test
    public void Test_For_FromSeconds10(){
        TimeSpan time = TimeSpan.FromSeconds(1801220.2);
        assertEquals(20, time.Days());
        assertEquals(20, time.Hours());
        assertEquals(20, time.Minutes());
        assertEquals(20, time.Seconds());
        assertEquals(200, time.Milliseconds());
    }

    @Test
    public void Test_For_FromMilliseconds1(){
        TimeSpan time = TimeSpan.FromMilliseconds(1);
        assertEquals(0, time.Days());
        assertEquals(0, time.Hours());
        assertEquals(0, time.Minutes());
        assertEquals(0, time.Seconds());
        assertEquals(1, time.Milliseconds());
    }

    @Test
    public void Test_For_FromMilliseconds2(){
        TimeSpan time = TimeSpan.FromMilliseconds(1.5);
        assertEquals(0, time.Days());
        assertEquals(0, time.Hours());
        assertEquals(0, time.Minutes());
        assertEquals(0, time.Seconds());
        assertEquals(2, time.Milliseconds());
    }

    @Test
    public void Test_For_FromMilliseconds3(){
        TimeSpan time = TimeSpan.FromMilliseconds(12345.6);
        assertEquals(0, time.Days());
        assertEquals(0, time.Hours());
        assertEquals(0, time.Minutes());
        assertEquals(12, time.Seconds());
        assertEquals(346, time.Milliseconds());
    }

    @Test
    public void Test_For_FromMilliseconds4(){
        TimeSpan time = TimeSpan.FromMilliseconds(123456789.8);
        assertEquals(1, time.Days());
        assertEquals(10, time.Hours());
        assertEquals(17, time.Minutes());
        assertEquals(36, time.Seconds());
        assertEquals(790, time.Milliseconds());
    }
    @Test
    public void Test_For_FromMilliseconds5(){
        TimeSpan time = TimeSpan.FromMilliseconds(1234567898765.4);
        assertEquals(14288, time.Days());
        assertEquals(23, time.Hours());
        assertEquals(31, time.Minutes());
        assertEquals(38, time.Seconds());
        assertEquals(766, time.Milliseconds());
    }
    @Test
    public void Test_For_FromMilliseconds6(){
        TimeSpan time = TimeSpan.FromMilliseconds(1000);
        assertEquals(0, time.Days());
        assertEquals(0, time.Hours());
        assertEquals(0, time.Minutes());
        assertEquals(1, time.Seconds());
        assertEquals(0, time.Milliseconds());
    }
    @Test
    public void Test_For_FromMilliseconds7(){
        TimeSpan time = TimeSpan.FromMilliseconds(60000);
        assertEquals(0, time.Days());
        assertEquals(0, time.Hours());
        assertEquals(1, time.Minutes());
        assertEquals(0, time.Seconds());
        assertEquals(0, time.Milliseconds());
    }

    @Test
    public void Test_For_FromMilliseconds8(){
        TimeSpan time = TimeSpan.FromMilliseconds(3600000);
        assertEquals(0, time.Days());
        assertEquals(1, time.Hours());
        assertEquals(0, time.Minutes());
        assertEquals(0, time.Seconds());
        assertEquals(0, time.Milliseconds());
    }

    @Test
    public void Test_For_FromMilliseconds9(){
        TimeSpan time = TimeSpan.FromMilliseconds(86400000);
        assertEquals(1, time.Days());
        assertEquals(0, time.Hours());
        assertEquals(0, time.Minutes());
        assertEquals(0, time.Seconds());
        assertEquals(0, time.Milliseconds());
    }

    @Test
    public void Test_For_FromMilliseconds10(){
        TimeSpan time = TimeSpan.FromMilliseconds(1801220200);
        assertEquals(20, time.Days());
        assertEquals(20, time.Hours());
        assertEquals(20, time.Minutes());
        assertEquals(20, time.Seconds());
        assertEquals(200, time.Milliseconds());
    }

    @Test
    public void Test_For_FromTicks1(){
        TimeSpan time = TimeSpan.FromTicks(1);
        assertEquals(0, time.Days());
        assertEquals(0, time.Hours());
        assertEquals(0, time.Minutes());
        assertEquals(0, time.Seconds());
        assertEquals(0, time.Milliseconds());
    }

    @Test
    public void Test_For_FromTicks2(){
        TimeSpan time = TimeSpan.FromTicks(12345);
        assertEquals(0, time.Days());
        assertEquals(0, time.Hours());
        assertEquals(0, time.Minutes());
        assertEquals(0, time.Seconds());
        assertEquals(1, time.Milliseconds());
    }

    @Test
    public void Test_For_FromTicks3(){
        TimeSpan time = TimeSpan.FromTicks(123456789);
        assertEquals(0, time.Days());
        assertEquals(0, time.Hours());
        assertEquals(0, time.Minutes());
        assertEquals(12, time.Seconds());
        assertEquals(345, time.Milliseconds());
    }

    @Test
    public void Test_For_FromTicks4(){
        TimeSpan time = TimeSpan.FromTicks(1234567898765L);
        assertEquals(1, time.Days());
        assertEquals(10, time.Hours());
        assertEquals(17, time.Minutes());
        assertEquals(36, time.Seconds());
        assertEquals(789, time.Milliseconds());
    }
    @Test
    public void Test_For_FromTicks5(){
        TimeSpan time = TimeSpan.FromTicks(12345678987654321L);
        assertEquals(14288, time.Days());
        assertEquals(23, time.Hours());
        assertEquals(31, time.Minutes());
        assertEquals(38, time.Seconds());
        assertEquals(765, time.Milliseconds());
    }
    @Test
    public void Test_For_FromTicks6(){
        TimeSpan time = TimeSpan.FromTicks(10000000);
        assertEquals(0, time.Days());
        assertEquals(0, time.Hours());
        assertEquals(0, time.Minutes());
        assertEquals(1, time.Seconds());
        assertEquals(0, time.Milliseconds());
    }
    @Test
    public void Test_For_FromTicks7(){
        TimeSpan time = TimeSpan.FromTicks(600000000L);
        assertEquals(0, time.Days());
        assertEquals(0, time.Hours());
        assertEquals(1, time.Minutes());
        assertEquals(0, time.Seconds());
        assertEquals(0, time.Milliseconds());
    }

    @Test
    public void Test_For_FromTicks8(){
        TimeSpan time = TimeSpan.FromTicks(36000000000L);
        assertEquals(0, time.Days());
        assertEquals(1, time.Hours());
        assertEquals(0, time.Minutes());
        assertEquals(0, time.Seconds());
        assertEquals(0, time.Milliseconds());
    }

    @Test
    public void Test_For_FromTicks9(){
        TimeSpan time = TimeSpan.FromTicks(864000000000L);
        assertEquals(1, time.Days());
        assertEquals(0, time.Hours());
        assertEquals(0, time.Minutes());
        assertEquals(0, time.Seconds());
        assertEquals(0, time.Milliseconds());
    }

    @Test
    public void Test_For_FromTicks10(){
        TimeSpan time = TimeSpan.FromTicks(18012202000000L);
        assertEquals(20, time.Days());
        assertEquals(20, time.Hours());
        assertEquals(20, time.Minutes());
        assertEquals(20, time.Seconds());
        assertEquals(200, time.Milliseconds());
    }

    @Test
    public void To_String_Test1(){
        TimeSpan time = TimeSpan.Zero;
        assertEquals("00:00:00", time.toString());
    }
    @Test
    public void To_String_Test2(){
        TimeSpan time = new TimeSpan(-14, 0, 0, 0, 0);
        assertEquals("-14.00:00:00", time.toString());
    }
    @Test
    public void To_String_Test3(){
        TimeSpan time = new TimeSpan(1, 2, 3);
        assertEquals("01:02:03", time.toString());
    }
    @Test
    public void To_String_Test4(){
        TimeSpan time = new TimeSpan(0, 0, 0, 0, 250);
        assertEquals("00:00:00.2500000", time.toString());
    }
    @Test
    public void To_String_Test5(){
        TimeSpan time = new TimeSpan(99, 23, 59, 59, 999);
        assertEquals("99.23:59:59.9990000", time.toString());
    }
    @Test
    public void To_String_Test6(){
        TimeSpan time = new TimeSpan(3, 0, 0);
        assertEquals("03:00:00", time.toString());
    }
    @Test
    public void To_String_Test7(){
        TimeSpan time = new TimeSpan(0, 0, 0, 0, 25);
        assertEquals("00:00:00.2500000", time.toString());
    }
}
