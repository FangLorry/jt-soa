/**
 * Copyright &copy; 2012-2015 <a href="http//www.vipshop.net">vipshop</a> All rights reserved.
 */
package com.fangjt.common.utils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringEscapeUtils;



/**
 * 字符串工具类, 继承org.apache.commons.lang3.StringUtils类
 * @author Easin
 * @version 2013-05-22
 */
public class StringUtils extends org.apache.commons.lang.StringUtils {
	
    private static final char SEPARATOR = '_';
    private static final String CHARSET_NAME = "UTF-8";
    
    public static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0 || cs.toString().equalsIgnoreCase("null");
    }
    
	/**
	 * 将以逗号分隔的字符串转换成字符串数组
	 * @param valStr
	 * @return String[]
	 */
	public static String[] StrList(String valStr){
		int i = 0;
		String TempStr = valStr;
		String[] returnStr = new String[valStr.length() + 1 - TempStr.replace(",", "").length()];
		valStr = valStr + ",";
		while (valStr.indexOf(',') > 0)
		{
			returnStr[i] = valStr.substring(0, valStr.indexOf(','));
			valStr = valStr.substring(valStr.indexOf(',')+1 , valStr.length());

			i++;
		}
		return returnStr;
	}
    /**
     * 替换四个字节的字符 ‘\xF0\x9F\x98\x84\xF0\x9F）的解决方案 
     * @param content
     * @return
     */
    public static String removeFourChar(String content) {
    	if(isBlank(content)){
    		return null;
    	}
        byte[] conbyte = content.getBytes();
        for (int i = 0; i < conbyte.length; i++) {
            if ((conbyte[i] & 0xF8) == 0xF0) {
                for (int j = 0; j < 4; j++) {                          
                    conbyte[i+j]=0x30;                     
                }  
                i += 3;
            }
        }
        content = new String(conbyte);
        return content.replaceAll("0000", "");
    }
    
    /**过滤特殊字符
     * @param str
     * @return
     */
    public static String filterSpecialCharacters(String str){
    	
    	StringBuffer sbTitle=new StringBuffer();
    	
    	if(isNotBlank(str)){
    		
        	char[] chars = str.toCharArray(); 
            for(int j = 0; j < chars.length; j ++) { 
            	//字母,数字,_,标点字符,中文匹配
            	Pattern pattern=Pattern.compile("\\w|\\p{P}|[\u4e00-\u9fa5]");
            	Matcher m = pattern.matcher(String.valueOf(chars[j]));
            	if(m.matches()){
            		if(chars!=null){
                		sbTitle.append(chars[j]);
                	}
            	}
            }  
    	}
    	return sbTitle.toString();
    }
    /**
     * 转换为字节数组
     * @param str
     * @return
     */
    public static byte[] getBytes(String str){
    	if (str != null){
    		try {
				return str.getBytes(CHARSET_NAME);
			} catch (UnsupportedEncodingException e) {
				return null;
			}
    	}else{
    		return null;
    	}
    }
    
    /**
     * 转换为字节数组
     * @return
     */
    public static String toString(byte[] bytes){
    	try {
			return new String(bytes, CHARSET_NAME);
		} catch (UnsupportedEncodingException e) {
			return EMPTY;
		}
    }
    
    /**
     * 是否包含字符串
     * @param str 验证字符串
     * @param strs 字符串组
     * @return 包含返回true
     */
    public static boolean inString(String str, String... strs){
    	if (str != null){
        	for (String s : strs){
        		if (str.equals(trim(s))){
        			return true;
        		}
        	}
    	}
    	return false;
    }
    
	/**
	 * 替换掉HTML标签方法
	 */
	public static String replaceHtml(String html) {
		if (isBlank(html)){
			return "";
		}
		String regEx = "<.+?>";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(html);
		String s = m.replaceAll("");
		return s;
	}
	/**
	 * 替换掉HTML成转义字符
	 */
	public static String encodeHtml(String html) {
		if (isBlank(html)){
			return "";
		}
		html = html.replaceAll("&", "&amp;");
		html = html.replaceAll("\"", "&quot;"); // "
		html = html.replaceAll("\t", "&nbsp;&nbsp;");// 替换跳格
		html = html.replaceAll(" ", "&nbsp;");// 替换空格
		html = html.replaceAll("<", "&lt;");
	    String s = html.replaceAll( ">", "&gt;");
		return s;
	}
	
	/**
	 * 替换掉HTML成转义字符
	 */
	public static String encodeHtmls(String html) {
		if (isBlank(html)){
			return "";
		}
		String s = html.replaceAll(" ", "");// 替换空格
		return s;
	}
	
	/**
	 * 替换掉HTML成转义字符
	 */
	public static String decodeHtml(String html) {
		if (isBlank(html)){
			return "";
		}
		html = html.replaceAll("&amp;", "&;");
		html = html.replaceAll("&quot;", "\""); // "
		html = html.replaceAll("&nbsp;&nbsp;", "\t");// 替换跳格
		html = html.replaceAll("&nbsp;", " ");// 替换空格
		html = html.replaceAll("&lt;", "<");
	    String s = html.replaceAll( "&gt;", ">");
		return s;
	}
	
	/**
	 * 替换为手机识别的HTML，去掉样式及属性，保留回车。
	 * @param html
	 * @return
	 */
	public static String replaceMobileHtml(String html){
		if (html == null){
			return "";
		}
		return html.replaceAll("<([a-z]+?)\\s+?.*?>", "<$1>");
	}
	

	/**
	 * 缩略字符串（不区分中英文字符）
	 * @param str 目标字符串
	 * @param length 截取长度
	 * @return
	 */
//	public static String abbr(String str, int length) {
//		if (str == null) {
//			return "";
//		}
//		try {
//			StringBuilder sb = new StringBuilder();
//			int currentLength = 0;
//			for (char c : replaceHtml(StringEscapeUtils.unescapeHtml4(str)).toCharArray()) {
//				currentLength += String.valueOf(c).getBytes("GBK").length;
//				if (currentLength <= length - 3) {
//					sb.append(c);
//				} else {
//					sb.append("...");
//					break;
//				}
//			}
//			return sb.toString();
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//		return "";
//	}
	
	public static String abbr2(String param, int length) {
		if (param == null) {
			return "";
		}
		StringBuffer result = new StringBuffer();
		int n = 0;
		char temp;
		boolean isCode = false; // 是不是HTML代码
		boolean isHTML = false; // 是不是HTML特殊字符,如&nbsp;
		for (int i = 0; i < param.length(); i++) {
			temp = param.charAt(i);
			if (temp == '<') {
				isCode = true;
			} else if (temp == '&') {
				isHTML = true;
			} else if (temp == '>' && isCode) {
				n = n - 1;
				isCode = false;
			} else if (temp == ';' && isHTML) {
				isHTML = false;
			}
			try {
				if (!isCode && !isHTML) {
					n += String.valueOf(temp).getBytes("GBK").length;
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			if (n <= length - 3) {
				result.append(temp);
			} else {
				result.append("...");
				break;
			}
		}
		// 取出截取字符串中的HTML标记
		String temp_result = result.toString().replaceAll("(>)[^<>]*(<?)",
				"$1$2");
		// 去掉不需要结素标记的HTML标记
		temp_result = temp_result
				.replaceAll(
						"</?(AREA|BASE|BASEFONT|BODY|BR|COL|COLGROUP|DD|DT|FRAME|HEAD|HR|HTML|IMG|INPUT|ISINDEX|LI|LINK|META|OPTION|P|PARAM|TBODY|TD|TFOOT|TH|THEAD|TR|area|base|basefont|body|br|col|colgroup|dd|dt|frame|head|hr|html|img|input|isindex|li|link|meta|option|p|param|tbody|td|tfoot|th|thead|tr)[^<>]*/?>",
						"");
		// 去掉成对的HTML标记
		temp_result = temp_result.replaceAll("<([a-zA-Z]+)[^<>]*>(.*?)</\\1>",
				"$2");
		// 用正则表达式取出标记
		Pattern p = Pattern.compile("<([a-zA-Z]+)[^<>]*>");
		Matcher m = p.matcher(temp_result);
		List<String> endHTML = new ArrayList();
		while (m.find()) {
			endHTML.add(m.group(1));
		}
		// 补全不成对的HTML标记
		for (int i = endHTML.size() - 1; i >= 0; i--) {
			result.append("</");
			result.append(endHTML.get(i));
			result.append(">");
		}
		return result.toString();
	}
	
	/**
	 * 转换为Double类型
	 */
	public static Double toDouble(Object val){
		if (val == null){
			return 0D;
		}
		try {
			return Double.valueOf(trim(val.toString()));
		} catch (Exception e) {
			return 0D;
		}
	}

	/**
	 * 转换为Float类型
	 */
	public static Float toFloat(Object val){
		return toDouble(val).floatValue();
	}

	/**
	 * 转换为Long类型
	 */
	public static Long toLong(Object val){
		return toDouble(val).longValue();
	}

	/**
	 * 转换为Integer类型
	 */
	public static Integer toInteger(Object val){
		return toLong(val).intValue();
	}
	

	/**
	 * 列转字段
	 * 驼峰命名法工具
	 * @return
	 * 		coLumn2Field("hello_world") == "helloWorld"
	 * 		toCapitalizeCamelCase("hello_world") == "HelloWorld"
	 * 		toUnderScoreCase("helloWorld") = "hello_world"
	 */
    public static String coLumn2Field(String s) {
        if (s == null) {
            return null;
        }

        s = s.toLowerCase();

        StringBuilder sb = new StringBuilder(s.length());
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == SEPARATOR) {
                upperCase = true;
            } else if (upperCase) {
                sb.append(Character.toUpperCase(c));
                upperCase = false;
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    /**
	 * 驼峰命名法工具
	 * @return
	 * 		coLumn2Field("hello_world") == "helloWorld"
	 * 		toCapitalizeCamelCase("hello_world") == "HelloWorld"
	 * 		toUnderScoreCase("helloWorld") = "hello_world"
	 */
    public static String toCapitalizeCamelCase(String s) {
        if (s == null) {
            return null;
        }
        s = coLumn2Field(s);
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
    
    /**
	 * 驼峰命名法工具
	 * @return
	 * 		coLumn2Field("hello_world") == "helloWorld"
	 * 		toCapitalizeCamelCase("hello_world") == "HelloWorld"
	 * 		toUnderScoreCase("helloWorld") = "hello_world"
	 */
    public static String toUnderScoreCase(String s) {
        if (s == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            boolean nextUpperCase = true;

            if (i < (s.length() - 1)) {
                nextUpperCase = Character.isUpperCase(s.charAt(i + 1));
            }

            if ((i > 0) && Character.isUpperCase(c)) {
                if (!upperCase || !nextUpperCase) {
                    sb.append(SEPARATOR);
                }
                upperCase = true;
            } else {
                upperCase = false;
            }

            sb.append(Character.toLowerCase(c));
        }

        return sb.toString();
    }
    
    /**
     * 如果不为空，则设置值
     * @param target
     * @param source
     */
    public static void setValueIfNotBlank(String target, String source) {
		if (isNotBlank(source)){
			target = source;
		}
	}
 
    /**
     * @param srcStr 原始字符串SrcStr中找 key的值  key:value的形式
     * @param key
     * @return
     */
    public static String findValueByKey(String srcStr,String key)
	{
		if(!srcStr.contains(key))
		{
			System.err.println(String.format("[{}不包含{}]",srcStr, key));
			return "NO_KEY_CONTAINED";
		}
		String res="";
		try
        {
	        int start =srcStr.indexOf(key)+key.length()+1; //1 for : or , or blank
	        String regex = "\\b[\\w\\u4e00-\\u9fa5\\w]+\\b";
	        Pattern pattern = Pattern.compile(regex);
	        Matcher matcher = pattern.matcher(srcStr);  

	        matcher.find(start);
	        res=matcher.group();
        } catch (Exception e)
        {
	        e.printStackTrace();
        }
		return res;  
		
		//
//		int start =srcStr.indexOf(key)+key.length()+1; //1 for :
//		if(start<key.length()+1)
//		{
//			return null;
//		}
//		String tempStr=srcStr+",";
//		
//		String value=tempStr.substring(start,tempStr.indexOf(",",start));
//		return value.trim();
	}
    /**
     * @param SrcStr SrcStr 按空格分找 第几个字组
     * @param pos
     * @return
     */
    public static String findValueByPos(String SrcStr,Integer pos)
	{
		String regex = "\\b[\\w\\u4e00-\\u9fa5\\w]+\\b";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(SrcStr);  
		int i=0;
		while (i<pos)
		{
			matcher.find();
			i++;
		}
		return matcher.group();  
	}
    /**
     * 转换为JS获取对象值，生成三目运算返回结果
     * @param objectString 对象串
     *   例如：row.user.id
     *   返回：!row?'':!row.user?'':!row.user.id?'':row.user.id
     */
    public static String jsGetVal(String objectString){
    	StringBuilder result = new StringBuilder();
    	StringBuilder val = new StringBuilder();
    	String[] vals = split(objectString, ".");
    	for (int i=0; i<vals.length; i++){
    		val.append("." + vals[i]);
    		result.append("!"+(val.substring(1))+"?'':");
    	}
    	result.append(val.substring(1));
    	return result.toString();
    }
    
    
    
    public static String fieldToCulumn(String field){
    	StringBuilder culumnStr = new StringBuilder();
    	if (field == null)
        {
            return null;
        }
        for (int i = 0; i < field.length(); i++)
        {
            if (Character.isUpperCase(field.charAt(i)))
            {
            	culumnStr.append("_" + field.charAt(i));
            } else
            {
            	culumnStr.append(field.charAt(i));
            }
        }
        return culumnStr.toString().trim().toLowerCase();
    }


	/*
	*加注释，左边补齐
	* */
	public static String fixLengthByLeftHolder(String srcStr, int len, Character holderChar)
	{
		if (null == holderChar)
		{
			holderChar = ' ';
		}
		String res = "";
		if (StringUtils.isBlank(srcStr))
		{
			for (int i = 0; i < len; i++)
			{
				res += holderChar;
			}
		} else
		{
			int holdNum = len - srcStr.length();
			for (int i = 0; i < holdNum; i++)
			{
				srcStr = holderChar + srcStr;
			}
			return srcStr;
		}

		return res;
	}
	
	public static String getParam(String key,String paramsStr)
	{
		if(StringUtils.isBlank(paramsStr))
		return "";
		for (String param : paramsStr.split("&")) {
			String[] p=param.split("=");
			if(p[0].equals(key))
			{
				return (p.length==1||isBlank(p[1]))?"":p[1].trim();
			}
//			map.put(p[0], p[])
		}
		return "";
	}
	public static String extractNum(String str) {
	  if (isBlank(str))
	    return null;
	  Pattern p = Pattern.compile("(\\d+)");
	  Matcher m = p.matcher(str);
	  if (m.find()) {
	    return m.group(1);
	  }
	  return null;
	}
	public static String verNumToStr(String str) {
	  StringBuilder sb = new StringBuilder();
	  if (isBlank(str))
	    return null;
	  for (int i = 0; i < str.length() - 1; i++) {
	    sb.append(str.charAt(i)).append(".");
	  }
	  sb.append(str.charAt(str.length() - 1));
	  return sb.toString();
	}
//	public static void main(String[] args)
//	{
////	  String ver = "104";
////		System.out.println(formatWith0(6,ver));
////	  System.err.println(verNumToStr(ver));
////	  String str = "1,2,2,2,2,1,3,4,4";
////	  String ss = "wmviedeo_101.apk";
////	  Pattern p = Pattern.compile("(\\d+)");
////	  Matcher m = p.matcher(ss);
////	  if (m.find())
////	    System.out.println(m.group(1));
//		
////		String a  = "<script>asfd</script>";
////		
////		System.out.println(toHtml(a));
//
//	}
	
	/**
	 * 替换为手机识别的HTML，去掉样式及属性，保留回车。
	 * @param txt
	 * @return
	 */
//	public static String toHtml(String txt){
//		if (txt == null){
//			return "";
//		}
//		return replace(replace(Encodes.escapeHtml(txt), "\n", "<br/>"), "\t", "&nbsp; &nbsp; ");
//	}
	

	public static String formatWith0(int pos, String issueId) {
//		String res;
		if(isBlank(issueId))
		{
			return null;
		}
		if(issueId.length()>=pos)
		{
			return issueId;
		}

		for (int i = issueId.length(); i <pos ; i++) {
			issueId="0"+issueId;
		}
		return issueId;
	}
	
	public static String htmlEncode(String source) {
        if (source == null) {
            return "";
        }
        String html = "";
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < source.length(); i++) {
            char c = source.charAt(i);
            switch (c) {
            case '<':
                buffer.append("&lt;");
                break;
            case '>':
                buffer.append("&gt;");
                break;
            case '&':
                buffer.append("&amp;");
                break;
            case '"':
                buffer.append("&quot;");
                break;
            case 10:
            case 13:
                break;
            default:
                buffer.append(c);
            }
        }
        html = buffer.toString();
        return html;
    }
	

	/**
	 * 驼峰命名法工具
	 * @return
	 * 		toCamelCase("hello_world") == "helloWorld" 
	 * 		toCapitalizeCamelCase("hello_world") == "HelloWorld"
	 * 		toUnderScoreCase("helloWorld") = "hello_world"
	 */
    public static String toCamelCase(String s) {
        if (s == null) {
            return null;
        }

        s = s.toLowerCase();

        StringBuilder sb = new StringBuilder(s.length());
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == SEPARATOR) {
                upperCase = true;
            } else if (upperCase) {
                sb.append(Character.toUpperCase(c));
                upperCase = false;
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    
}
