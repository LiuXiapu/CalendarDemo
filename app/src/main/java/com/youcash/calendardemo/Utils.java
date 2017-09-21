package com.youcash.calendardemo;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Cecil on 2017/8/2.
 * Email:guixixuan1120@outlook.com
 */

public class Utils {

    //阴历小月，29天
    private static final String[] LUNAR_MONTHS_LITTLE = {"初一", "初二", "初三", "初四", "初五", "初六", "初七", "初八", "初九", "初十", "十一", "十二", "十三", "十四", "十五", "十六", "十七", "十八", "十九", "二十", "廿一", "廿二", "廿三", "廿四", "廿五", "廿六", "廿七", "廿八", "廿九"};
    //阴历大月，30天
    private static final String[] LUNAR_MONTHS_LARGE = {"初一", "初二", "初三", "初四", "初五", "初六", "初七", "初八", "初九", "初十", "十一", "十二", "十三", "十四", "十五", "十六", "十七", "十八", "十九", "二十", "廿一", "廿二", "廿三", "廿四", "廿五", "廿六", "廿七", "廿八", "廿九", "三十"};
    //阴历月份，一月为正月，十一月为冬月，十二月为腊月
    private static final String[] LUNAR_MONTHS_NAME = {"正月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "冬月", "腊月"};
    //1901-2099年每一年阴历对应的int值，利用此int值可以解析出当年阴历的闰月月份，各个月的对应天数，春节当天对应的阳历日期（通过此日期计算阴阳历转换）
    private static final int[] LUNAR_YEARS = new int[]{
            0x04AE53, 0x0A5748, 0x5526BD, 0x0D2650, 0x0D9544, 0x46AAB9, 0x056A4D, 0x09AD42,
            0x24AEB6, 0x04AE4A,/*1901-1910*/
            0x6A4DBE, 0x0A4D52, 0x0D2546, 0x5D52BA, 0x0B544E, 0x0D6A43, 0x296D37, 0x095B4B,
            0x749BC1, 0x049754,/*1911-1920*/
            0x0A4B48, 0x5B25BC, 0x06A550, 0x06D445, 0x4ADAB8, 0x02B64D, 0x095742, 0x2497B7,
            0x04974A, 0x664B3E,/*1921-1930*/
            0x0D4A51, 0x0EA546, 0x56D4BA, 0x05AD4E, 0x02B644, 0x393738, 0x092E4B, 0x7C96BF,
            0x0C9553, 0x0D4A48,/*1931-1940*/
            0x6DA53B, 0x0B554F, 0x056A45, 0x4AADB9, 0x025D4D, 0x092D42, 0x2C95B6, 0x0A954A,
            0x7B4ABD, 0x06CA51,/*1941-1950*/
            0x0B5546, 0x555ABB, 0x04DA4E, 0x0A5B43, 0x352BB8, 0x052B4C, 0x8A953F, 0x0E9552,
            0x06AA48, 0x6AD53C,/*1951-1960*/
            0x0AB54F, 0x04B645, 0x4A5739, 0x0A574D, 0x052642, 0x3E9335, 0x0D9549, 0x75AABE,
            0x056A51, 0x096D46,/*1961-1970*/
            0x54AEBB, 0x04AD4F, 0x0A4D43, 0x4D26B7, 0x0D254B, 0x8D52BF, 0x0B5452, 0x0B6A47,
            0x696D3C, 0x095B50,/*1971-1980*/
            0x049B45, 0x4A4BB9, 0x0A4B4D, 0xAB25C2, 0x06A554, 0x06D449, 0x6ADA3D, 0x0AB651,
            0x093746, 0x5497BB,/*1981-1990*/
            0x04974F, 0x064B44, 0x36A537, 0x0EA54A, 0x86B2BF, 0x05AC53, 0x0AB647, 0x5936BC,
            0x092E50, 0x0C9645,/*1991-2000*/
            0x4D4AB8, 0x0D4A4C, 0x0DA541, 0x25AAB6, 0x056A49, 0x7AADBD, 0x025D52, 0x092D47,
            0x5C95BA, 0x0A954E,/*2001-2010*/
            0x0B4A43, 0x4B5537, 0x0AD54A, 0x955ABF, 0x04BA53, 0x0A5B48, 0x652BBC, 0x052B50,
            0x0A9345, 0x474AB9,/*2011-2020*/
            0x06AA4C, 0x0AD541, 0x24DAB6, 0x04B64A, 0x69573D, 0x0A4E51, 0x0D2646, 0x5E933A,
            0x0D534D, 0x05AA43,/*2021-2030*/
            0x36B537, 0x096D4B, 0xB4AEBF, 0x04AD53, 0x0A4D48, 0x6D25BC, 0x0D254F, 0x0D5244,
            0x5DAA38, 0x0B5A4C,/*2031-2040*/
            0x056D41, 0x24ADB6, 0x049B4A, 0x7A4BBE, 0x0A4B51, 0x0AA546, 0x5B52BA, 0x06D24E,
            0x0ADA42, 0x355B37,/*2041-2050*/
            0x09374B, 0x8497C1, 0x049753, 0x064B48, 0x66A53C, 0x0EA54F, 0x06B244, 0x4AB638,
            0x0AAE4C, 0x092E42,/*2051-2060*/
            0x3C9735, 0x0C9649, 0x7D4ABD, 0x0D4A51, 0x0DA545, 0x55AABA, 0x056A4E, 0x0A6D43,
            0x452EB7, 0x052D4B,/*2061-2070*/
            0x8A95BF, 0x0A9553, 0x0B4A47, 0x6B553B, 0x0AD54F, 0x055A45, 0x4A5D38, 0x0A5B4C,
            0x052B42, 0x3A93B6,/*2071-2080*/
            0x069349, 0x7729BD, 0x06AA51, 0x0AD546, 0x54DABA, 0x04B64E, 0x0A5743, 0x452738,
            0x0D264A, 0x8E933E,/*2081-2090*/
            0x0D5252, 0x0DAA47, 0x66B53B, 0x056D4F, 0x04AE45, 0x4A4EB9, 0x0A4D4C, 0x0D1541,
            0x2D92B5          /*2091-2099*/
    };
    //平年阳历的天数叠加数组，通过此数组计算天数
    private static final int[] AVERAGE_MONTHS_MIX_AMOUNT = {0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334};
    //闰年阳历的天数叠加数组，通过此数组计算天数
    private static final int[] LEAP_MONTHS_MIX_AMOUNT = {0, 31, 60, 91, 121, 152, 182, 213, 244, 274, 305, 335};

    /**
     * 将 int 类型数据转成二进制的字符串，不足 int 类型位数时在前面添“0”以凑足位数
     */
    public static String toFullBinaryString(int num) {
        char[] chs = new char[Integer.SIZE];
        for (int i = 0; i < Integer.SIZE; i++) {
            chs[Integer.SIZE - 1 - i] = (char) (((num >> i) & 1) + '0');
        }
        return new String(chs);
    }

    /**
     * 解析公历年份
     *
     * @param year 为了跟picker匹配，多了一个将入参去掉"年"的操作
     * @return 返回结构为
     * map={year:{xxxx}
     *      month:{1月,...,12月}
     *      day:{
     *          {1日,...,xx日}
     *          ...
     *          {1日,...,xx日}
     *          }
     *      }
     */
    public static HashMap<String, Object> parseAverageYear(String year) {
        HashMap<String, Object> map = new HashMap<>();
        year = year.split("年")[0];
        int yearNum = Integer.parseInt(year);
        List<String> monthList = new ArrayList<>();
        List<List<String>> dayList = new ArrayList<>();
        List<String> secondMonthList = new ArrayList<>();//二月要拿出来区别对待
        List<String> littleMonthList = new ArrayList<>();//公历小月30天
        List<String> largeMonthList = new ArrayList<>();//公历大月30天
        if (yearNum % 4 == 0) {
            for (int i = 1; i < 30; i++) {
                secondMonthList.add(i + "日");
            }
        } else {
            for (int i = 1; i < 29; i++) {
                secondMonthList.add(i + "日");
            }
        }
        for (int i = 1; i < 31; i++) {
            littleMonthList.add(i + "日");
            largeMonthList.add(i + "日");
        }
        largeMonthList.add("31日");
        for (int i = 1; i < 13; i++) {
            monthList.add(i + "月");
            if (i == 2) {
                dayList.add(i - 1, secondMonthList);
            } else if (i == 1 || i == 3 || i == 5 || i == 7 || i == 8 || i == 10
                    || i == 12) {
                dayList.add(i - 1, largeMonthList);
            } else {
                dayList.add(i - 1, littleMonthList);
            }
        }
        map.put("year", year);
        map.put("month", monthList);
        map.put("day", dayList);
        return map;
    }

    /**
     * 解析农历年份
     *
     * @param year 为了跟picker匹配，多了一个将入参去掉"年"的操作
     * @return 返回结构为
     * map={year:{xxxx}
     *      month:{1月,...,12月}
     *      day:{
     *          {1日,...,xx日}
     *          ...
     *          {1日,...,xx日}
     *          }
     *     }
     */
    public static HashMap<String, Object> parseLunarYear(String year) {
        HashMap<String, Object> map = new HashMap<>();
        List<String> monthList = new ArrayList<>();
        List<List<String>> dayList = new ArrayList<>();
        List<String> littleLunarList = new ArrayList<>();//农历的小月月份，29天
        List<String> largeLunarList = new ArrayList<>();//农历的大月月份，30天
        //把数组转化为list
        for (int i = 0; i < LUNAR_MONTHS_LITTLE.length; i++) {
            littleLunarList.add(i, LUNAR_MONTHS_LITTLE[i]);
        }
        for (int i = 0; i < LUNAR_MONTHS_LARGE.length; i++) {
            largeLunarList.add(i, LUNAR_MONTHS_LARGE[i]);
        }
        year = year.split("年")[0];//因为传入的参数中包含了"年"这个字符，所以增加此操作
        int yearNum = Integer.parseInt(year);
        //只要后24位数
        String binaryYear = Utils.toFullBinaryString(LUNAR_YEARS[yearNum - 1901])
                .substring(8, 32);
        //闰月月份
        String leapMonth = binaryYear.substring(0, 4);
        int leapMonthNum = parseStr2Int(leapMonth);
        //每个月份对应的大小月，1是大月，0是小月
        String month = binaryYear.substring(4, 17);
        char[] months = month.toCharArray();
        for (int i = 0; i <= 11; i++) {
            monthList.add(i, LUNAR_MONTHS_NAME[i]);
            if (months[i] == '0') {
                dayList.add(i, littleLunarList);
            } else if (months[i] == '1') {
                dayList.add(i, largeLunarList);
            }
        }
        if (leapMonthNum > 0) {
            //如果有闰月，就需要考虑第十三个数，否则不需要考虑
            if (months[12] == '0') {
                dayList.add(12, littleLunarList);
            } else if (months[12] == '1') {
                dayList.add(12, largeLunarList);
            }
            monthList.add(leapMonthNum, "闰" + monthList.get(leapMonthNum - 1));
        }
        map.put("year", year);
        map.put("month", monthList);
        map.put("day", dayList);
        return map;
    }

    /**
     * 将二进制字符串转化为二进制数字
     */
    public static int parseStr2Int(String leapMonth) {
        char[] s = leapMonth.toCharArray();
        int count = 0;
        for (int i = 0; i < s.length; i++) {
            int j = s.length - i - 2;
            if (j >= 0) {
                count = count + Character.getNumericValue(s[i]) * (2 << (j));
            } else {
                count = count + Character.getNumericValue(s[s.length - 1]);
            }
        }
        return count;
    }

    /**
     * 将选中的农历日期，转化为公历日期，后两个入参皆为position，所以要从0开始计数
     *
     * @param year 为了跟picker匹配，多了一个将入参去掉"年"的操作
     * @return 返回结构为
     * map={year:{xxxx}
     *      month:{1月,...,12月}
     *      day:{
     *          {1日,...,xx日}
     *          ...
     *          {1日,...,xx日}
     *          }
     *      yearPosition:X
     *      monthPosition:X
     *      dayPosition:X
     *     }
     *
     *     yearPosition/monthPosition/dayPosition方便在日期选择器定位，平常使用可忽略这三个数据
     */
    public static HashMap<String, Object> lunar2Average(String year, int monthPosition, int
            dayPosition) {
        HashMap<String, Object> lunarMap = parseLunarYear(year);
        year = year.split("年")[0];
        int yearNum = Integer.parseInt(year);
        int[] monthMixAmount = yearNum%4 == 0?LEAP_MONTHS_MIX_AMOUNT :
                AVERAGE_MONTHS_MIX_AMOUNT;
        HashMap<String, Object> map = average2Lunar(yearNum + 1 + "年", 0, 0);
        int newYearMonth = (int) map.get("monthPosition");//明年元旦对应今年的阴历月份
        int newYearDay = (int) map.get("dayPosition");//明年元旦对应今年的阴历日
        String binaryYear = Utils.toFullBinaryString(LUNAR_YEARS[yearNum - 1901])
                .substring(8, 32);
        int springMonth = parseStr2Int(binaryYear.substring(17, 19));
        int springDay = parseStr2Int(binaryYear.substring(19, 24));
        int springCount = (monthMixAmount[springMonth - 1] + springDay - 1);
        List<List<String>> dayList = (List<List<String>>) lunarMap.get("day");
        int count = 0;//叠加阴历月份
        int tempCount = 0;//记录需要转换的阴历月份离春节有多少天
        for (int i = 0; i < dayList.size(); i++) {
            if (i == monthPosition) {
                tempCount = count + dayPosition;
            }
            count = count + dayList.get(i).size();
        }
        int differ = springCount + tempCount;
        if (monthPosition < newYearMonth || (monthPosition == newYearMonth
                && dayPosition < newYearDay)) {
            //元旦之前
            // 记录当年元旦离选中的阴历有多少天,differ应小于365或366
            Log.d("Cecil", "differ" + differ);
            for (int i = 0; i < monthMixAmount.length; i++) {
                if (monthMixAmount[i] > differ) {
                    //第一次大于
                    map.put("yearPosition", yearNum - 1901);
                    map.put("monthPosition", i - 1);
                    map.put("dayPosition", differ - monthMixAmount[i - 1]);
                    break;
                } else if (differ == monthMixAmount[i]) {
                    //如果等于，则是下个月一号
                    map.put("yearPosition", yearNum - 1901);
                    map.put("monthPosition", i);
                    map.put("dayPosition", 0);
                    break;
                } else if (differ > monthMixAmount[11]) {
                    //如果differ超出前十一个月叠加的天数，则必为12月
                    map.put("yearPosition", yearNum - 1901);
                    map.put("monthPosition", 11);
                    map.put("dayPosition", differ - monthMixAmount[11]);
                    break;
                }
            }
        } else {
            //元旦之后，年份加1
            differ = differ - (yearNum % 4 == 0 ? 366 : 365);
            for (int i = 0; i < monthMixAmount.length; i++) {
                if (monthMixAmount[i] > differ) {
                    //第一次大于
                    map.put("yearPosition", yearNum - 1901 + 1);
                    map.put("monthPosition", i - 1);
                    map.put("dayPosition", differ - monthMixAmount[i - 1]);
                    break;
                } else if (differ == monthMixAmount[i]) {
                    //如果等于，则是下个月一号
                    map.put("yearPosition", yearNum - 1901 + 1);
                    map.put("monthPosition", i);
                    map.put("dayPosition", 0);
                    break;
                }
            }
        }
        return map;
    }

    /**
     * 将选中的公历日期，转化为农历日期，后两个入参皆为position，所以要从0开始计数
     *
     * @param year 为了跟picker匹配，多了一个将入参去掉"年"的操作
     * @return 返回结构为
     * map={year:{xxxx}
     *      month:{1月,...,12月}
     *      day:{
     *          {1日,...,xx日}
     *          ...
     *          {1日,...,xx日}
     *          }
     *      yearPosition:X
     *      monthPosition:X
     *      dayPosition:X
     *     }
     *
     *     yearPosition/monthPosition/dayPosition方便在日期选择器定位，平常使用可忽略这三个数据
     */
    public static HashMap<String, Object> average2Lunar(String year, int monthPosition, int
            dayPosition) {
        HashMap<String, Object> lunarMap = parseLunarYear(year);
        year = year.split("年")[0];
        int yearNum = Integer.parseInt(year);
        int[] monthMixAmount = yearNum%4 == 0?LEAP_MONTHS_MIX_AMOUNT :
                AVERAGE_MONTHS_MIX_AMOUNT;
        String binaryYear = Utils.toFullBinaryString(LUNAR_YEARS[yearNum - 1901])
                .substring(8, 32);
        int springMonth = parseStr2Int(binaryYear.substring(17, 19));
        int springDay = parseStr2Int(binaryYear.substring(19, 24));
        if (monthPosition + 1 > springMonth || (monthPosition + 1 == springMonth
                && dayPosition + 1 >= springDay)) {
            //春节和春节之后（年份不用变）
            List<List<String>> dayList = (List<List<String>>) lunarMap.get("day");
            int count = monthMixAmount[monthPosition] + dayPosition -
                    (monthMixAmount[springMonth - 1] + springDay - 1);
            int tempCount = 0;
            for (int i = 0; i < dayList.size(); i++) {
                int tempCount2 = tempCount + dayList.get(i).size();
                if (tempCount2 > count) {
                    lunarMap.put("yearPosition", yearNum - 1901);
                    lunarMap.put("monthPosition", i);
                    Log.d("Cecil","春节后monthPosition="+i);
                    lunarMap.put("dayPosition", count - tempCount);
                    Log.d("Cecil","春节后dayPosition="+(count - tempCount));
                    break;
                } else {
                    tempCount = tempCount2;
                }
            }
        } else {
            //春节之前（年份需要变）
            lunarMap = parseLunarYear(yearNum - 1 + "年");
            List<List<String>> dayList = (List<List<String>>) lunarMap.get("day");
            int count = (monthMixAmount[springMonth - 1] + springDay - 1) -
                    (monthMixAmount[monthPosition] + dayPosition);
            int tempCount = 0;
            for (int i = dayList.size() - 1; i >= 0; i--) {
                int tempCount2 = tempCount + dayList.get(i).size();
                if (tempCount2 >= count) {
                    lunarMap.put("yearPosition", yearNum - 1 - 1901);
                    lunarMap.put("monthPosition", i);
                    Log.d("Cecil","春节前monthPosition="+i);
                    lunarMap.put("dayPosition", dayList.get(i).size() - (count - tempCount));
                    Log.d("Cecil","春节前dayPosition="+(dayList.get(i).size() - (count - tempCount)));
                    break;
                } else {
                    tempCount = tempCount2;
                }
            }
        }
        return lunarMap;
    }

    public static void hideSoftInput(Activity context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context
                .INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(context.getWindow().getDecorView().getWindowToken(), 0);
    }

    public static void backgroundAlpha(Activity activity, float bgAlpha) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        if (bgAlpha == 1.0) {
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        } else {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        }
        activity.getWindow().setAttributes(lp);
    }
}
