package com.filter;
/**
 * @Author tdragon.
 * @Date 2020/11/6.
 * @Time 19:42
 * @Description: 防止HTML注入，进行编码转换 所以转义字符是这样用的啊
 * */

public class HTMLFilter {
    public static String filter(String message){
        if (message == null) {
            return (null);
        }
        char content[]=new char[message.length()];
        message.getChars(0,message.length(),content,0);
        StringBuffer result=new StringBuffer(content.length+50);
        for (int i=0;i<content.length;i++)
        {
            switch (content[i]){
                case '<':
                    result.append("&lt;");
                    break;
                case '>':
                    result.append("&gt;");
                    break;
                case '&':
                    result.append("&amp;");
                    break;
                case '"':
                    result.append("&quot;");
                    break;
                default:
                    result.append(content[i]);
            }
        }
        return (result.toString());
}
public  static String reverse(String x){
      StringBuffer s = new StringBuffer(x);
      //牛逼源码移位操作 >>右移 <<左移 后面跟着次数表示移动多少位
      return s.reverse().toString();
}

    public static void main(String[] args) {
        System.out.println(reverse("converse"));
        System.out.println(15<<2);
        System.out.println(Integer.bitCount(16)*100000);
        System.out.println(1<<16);
    }
}
