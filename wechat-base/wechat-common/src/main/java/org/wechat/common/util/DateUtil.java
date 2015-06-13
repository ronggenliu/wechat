package org.wechat.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class DateUtil {

	private static final String defaultDatePattern = "yyyy-MM-dd";
	private static final String defaultTimePattern = "HH:mm:ss";
	private static final String defaultDTPattern="yyyy-MM-dd HH:mm:ss";
	private static final String defaultDHMPattern="yyyy-MM-dd HH:mm";
	private static final String defaultYMPattern="yyyy-MM";
	
	/**
	 * 获取当前时间：HH:mm:ss
	 * @return HH:mm:ss
	 */
	public static String getNowTime(){
		Date now = new Date();
		return format(now,defaultTimePattern);
	}
	
	/**
	 * 获取当前日期时间：yyyy-MM-dd HH:mm:ss
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String getNowDateTime(){
		Date now = new Date();
		return format(now,defaultDTPattern);
	}
	
	/**
	 * 获取当前日期时间，但是只精确到分：yyyy-MM-dd HH:mm
	 * @return ：yyyy-MM-dd HH:mm
	 */
	public static String getDateWithDMH(){
		Date now = new Date();
		return format(now,defaultDHMPattern);
	}
	
	/**
	 * 返回时间差的秒数
	 * @param time1 19:23:12
	 * @param time2 20:23:12
	 * @return 返回秒数 3600
	 */
	public static int getSecondsLag(String time1,String time2){
		int lag=0;
		String[] tArr1=time1.trim().split(":");
		String[] tArr2=time2.trim().split(":");
		int hour1=Integer.parseInt(tArr1[0]);
		int hour2=Integer.parseInt(tArr2[0]);
		int minute1=Integer.parseInt(tArr1[1]);
		int minute2=Integer.parseInt(tArr2[1]);
		int second1=Integer.parseInt(tArr1[2]);
		int second2=Integer.parseInt(tArr2[2]);
		lag=(hour2-hour1)*3600+(minute2-minute1)*60+(second2-second1);
		return lag;
		
	}


	/**
	 * 返回当前日期字符串：yyyy-MM-dd
	 */
	public static String getToday() {
		Date today = new Date();
		return format(today);
	}
	
	/**
	 * 转成这种格式的字符串：yyyy-MM，值为当前年月
	 */
	public static String getThisYearMonth() {
		Date today = new Date();
		return format(today,defaultYMPattern);
	}

	/**
	 * 转成这种格式的字符串：yyyy-MM-dd
	 */
	public static String format(Date date) {
		return date == null ? " " : format(date, defaultDatePattern);
	}
	
	/**
	 *  转成这种格式的字符串:HH:mm:ss
	 */
	public static String formatTime(Date date) {
		return date == null ? " " : format(date, defaultTimePattern);
	}
	
	/**
	 * 转成这种格式的字符串：yyyy-MM-dd HH:mm:ss
	 */
	public static String formatDateTime(Date date) {
		return date == null ? " " : format(date, defaultDTPattern);
	}

	/**
	 * 使用参数Format格式化Date成字符串
	 */
	public static String format(Date date, String pattern) {
		return date == null ? " " : new SimpleDateFormat(pattern).format(date);
	}
	
	/**
	 * 使用参数Format格式化Date成字符串
	 */
	public static String format(String date, String pattern) {
		return date == null ? " " : new SimpleDateFormat(pattern).format(date);
	}

	/**
	 * 使用预设格式将字符串转为Date:yyyy-MM-dd
	 */
	public static Date parse(String strDate)  {
		strDate=strDate.trim();
		return StringUtils.isBlank(strDate) ? null : parse(strDate, defaultDatePattern);
	}

	/**
	 * 使用参数Format将字符串转为Date
	 */
	public static Date parse(String strDate, String pattern) {
		pattern=pattern.trim();
		try {
			return StringUtils.isBlank(strDate.trim()) ? null : new SimpleDateFormat(pattern).parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 在日期上增加数个整月
	 */
	public static Date addMonth(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, n);
		return cal.getTime();
	}
	
	/**
	 * 计算和此刻相差的时间单位
	 *	String today="2014-12-03 18:05:00";
		getBetweenTime(today));
		return map 相差多少天多少小时多少分钟多少秒
	 */
	public static Map<String,Long> getBetweenTime(String start){
		Map<String,Long> lagTime=new HashMap<String,Long>();
		String pattern="yyyy-MM-dd HH:mm:ss";
		if(!start.contains(":")){//如果传过来的是日期型，即没有时间的
			pattern="yyyy-MM-dd";
		}
		Date begin =DateUtil.parse(start, pattern);
		long between = (System.currentTimeMillis() - begin.getTime()) / 1000;// 除以1000是为了转换成秒
		long day = between / (24 * 3600);
		long hour = between % (24 * 3600) / 3600;
		long minute = between % 3600 / 60;
		long second = between%60;
		lagTime.put("between", between);//相差的秒数
		lagTime.put("year", day/365);//相差的年数
		lagTime.put("month", day/30);//相差的月数
		lagTime.put("day", day);//相差的天数
		lagTime.put("hour",day*24+ hour);//相差的小时数
		lagTime.put("minute", (day*24+ hour)*60+minute);//相差的分钟
		
		//相差多少年多少月多少天多少时
		lagTime.put("lagYear", day/365);
		lagTime.put("lagMonth", day/30-(day/365*12));
		lagTime.put("lagDay", day-(day/30)*30);
		lagTime.put("lagHour", hour);
		lagTime.put("lagMinute", minute);
		lagTime.put("lagSecond", second);
		return lagTime;
		
//		System.out.println( "相差" + day1 + "天" + hour1 + "小时" + minute1+ "分" + second1 + "秒");
	}

	public static String getLastDayOfMonth(String year, String month) {
		Calendar cal = Calendar.getInstance();
		// 年
		cal.set(Calendar.YEAR, Integer.parseInt(year));
		// 月，因为Calendar里的月是从0开始，所以要-1
		// cal.set(Calendar.MONTH, Integer.parseInt(month) - 1);
		// 日，设为一号
		cal.set(Calendar.DATE, 1);
		// 月份加一，得到下个月的一号
		cal.add(Calendar.MONTH, 1);
		// 下一个月减一为本月最后一天
		cal.add(Calendar.DATE, -1);
		return String.valueOf(cal.get(Calendar.DAY_OF_MONTH));// 获得月末是几号
	}

	public static Date getDate(String year, String month, String day) throws ParseException {
		String result = year + "- " + (month.length() == 1 ? ("0 " + month) : month) + "- " + (day.length() == 1 ? ("0 " + day) : day);
		return parse(result);
	}
	
	/**
	 * 将日期的字符串，变成系统日期的long数据，类似：System.currentTimeMillis()
	 * @param dateStr
	 */
	public static Long getDateLong(String dateStr){
		if(!dateStr.contains(":")){//如果传过来的是日期型，即没有时间的
			return DateUtil.parse(dateStr,defaultDatePattern).getTime();
		}
		return DateUtil.parse(dateStr,defaultDTPattern).getTime();
	}
	
	/**
	 * 将类似System.currentTimeMillis()的长整数变为可识别的日期格式
	 * @param dateTime
	 * @return
	 */
	public static String getDateStr(Long dateTime){
		return format(new Date(dateTime),defaultDTPattern);
	}
	
	/**
	 * 将时间字符串变为可识别的数字，比如：
	 * 2015-05-02 17:59:31——>20150502175931+随机数=20150502175976
	 * @return Long 时间数字
	 */
	public static Long getNowValue(){
		String dateStr=getNowDateTime();
		System.out.println(dateStr);
		StringBuffer result=new StringBuffer();
		String[] dateStrArr=dateStr.split("\\ ");
		String[] dates=dateStrArr[0].split("-");
		String[] times=dateStrArr[1].split(":");
		result.append(dates[0]).append(dates[1]).append(dates[2]);
		result.append(times[0]).append(times[1]).append(times[2]);
		Long dateTime=Long.parseLong(result.toString());
//		System.out.println(dateTime);
		return dateTime+(long) (Math.random() * 100);
	}
	
	
	
	public static void main(String[] args) {
//		for(int i=0;i<10;i++){
//			System.out.println(getNowValue());
//			
//		}
		System.out.println(getNowDateTime());
		System.out.println(getBetweenTime("2012-06-02"));
	}
}
