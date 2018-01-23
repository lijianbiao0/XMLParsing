package com.hofo.xmlparsing;

import android.text.TextUtils;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;

/**
 * Pull解析器与SAX解析器性质类似，都是基于事件处理模式。不同的是用法：
 * Pull解析器需要自己获取事件后做出相当于的操作，pull是用于Android，用于Java中需要导入pull解析依赖包。
 * 特点：解析速度快、使用方便、效率高。代码如下：
 */
public class XMLPullParsing extends BaseActivity {

    @Override
    public void parsing() throws Exception {
        //使用pull解析
        InputStream is = getAssets().open("XmlParsingTest.xml");
        XmlPullParser pullParser = Xml.newPullParser();
        pullParser.setInput(is, "utf-8");//获得数流形式以及编码格式
        //02、通过解析器拿到事件类型：开始标签，结束标签，开始文档，结束文档，文本
        int type = pullParser.getEventType();//type-- 开始文档
        //03、拿到开始文档进行判断
        while (type != XmlPullParser.END_DOCUMENT) {//如果开始文档不等于结束标签,type必须赋值，否则会出现死循环
            //type不断的改变，需要通过switch来判断
            switch (type) {
                case XmlPullParser.START_TAG://如果是开始标签
                    //获取开始标签的名字
                    String startname = pullParser.getName();
                    log("开始标签:" + startname);
                    if (TextUtils.equals("RESULT", startname)) {
                        log("最外层标签 RESULT");
                    }
                    if (TextUtils.equals("VALUE", startname)) {
                        //针对拿属性
                        log("VALUE 标签 ，" + "属性名为:name" + "\t属性值为:" + pullParser.getAttributeValue(null, "name"));

                    }
                    int attributeCount = 0;
                    if (TextUtils.equals("NO", startname)) {
                        attributeCount = pullParser.getAttributeCount();
//                        log("NO 标签，属性数量为:" + attributeCount + "属性名为:attr" + "\t属性值为:" + pullParser.getAttributeValue(null, "attr") + "\tNO内容:" + pullParser.nextText());
                        // 全拿出来
                        for (int i = 0; i < attributeCount; i++) {
                            log("NO 标签，属性名为:" + pullParser.getAttributeName(i) + "\t属性值为:" + pullParser.getAttributeValue(i));

                        }
                    }
                    if (TextUtils.equals("ADDR", startname)) {
                        attributeCount = pullParser.getAttributeCount();
                        log("ADDR 标签，属性数量为:" + attributeCount + "\tADDR标签内容:" + pullParser.nextText());

                    }
                    break;
                case XmlPullParser.END_TAG://如果是结束标签
                    //获取结束标签
                    String endname = pullParser.getName();//通过pull解析器获取结束标签的名字
                    log("结束标签:" + endname);
                    break;
            }
            type = pullParser.next();//通过解析器拿到下一个的值
        }

    }
}
