package com.hofo.xmlparsing;

import android.text.TextUtils;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * SAX(Simple API XML):使用流的形式处理，是一种以基于事件（方法）为驱动的解析器。
 * 特点：边读边解析，解析速度快，占用内存少，同时sax解析提供了很多方法：
 * 开始、结束标签；开始、结束文档；文本的方法。代码如下：
 */
public class SAX extends BaseActivity {


    @Override
    public void parsing() throws Exception {
        //1、使用SAX解析：特点：边读边解析，基于事件（方法）驱动
        // sax解析提供了很多的方法：开始标签 开始文档的方法，结束标签 结束文档的方法 文本
        //创建sax工厂模式，通过工厂拿到解析器（和DOM解析一样）
        InputStream is = getAssets().open("XmlParsingTest.xml");
        SAXParserFactory parserFactory = SAXParserFactory.newInstance();//创建sax工厂
        SAXParser saxParser = parserFactory.newSAXParser();//拿到解析器
        saxParser.parse(is, new DefaultHandler() {//实现sax解析的方法
            String startname;

            //开始文档的方法
            @Override
            public void startDocument() throws SAXException {
                super.startDocument();
                log("文档开始了!");
            }

            //结束文档的方法
            @Override
            public void endDocument() throws SAXException {
                super.endDocument();
                log("文档结束了!");
            }

            //开始标签的方法
            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                super.startElement(uri, localName, qName, attributes);
                //1、拿到开始标签的名字：在DOM解析中，使用element拿到开始标签的名字。在sax解析中开始标签方法已经拿到了开始标签的名字
                //2、拿到名字进行判断
                startname = localName;

                log("开始标签:" + startname);
                if (TextUtils.equals("RESULT", startname)) {
                    log("最外层标签 RESULT");
                }
                if (TextUtils.equals("VALUE", startname)) {
                    //针对拿属性
                    log("VALUE标签 ，属性名为:name" + "\t属性值为:" + attributes.getValue("name"));
                }
                int attributeCount = attributes.getLength();
                if (TextUtils.equals("NO", startname)) {
                    log("NO标签，属性名为:attr" + "\t属性值为:" + attributes.getValue("attr"));
                    // 全拿出来
                    for (int i = 0; i < attributeCount; i++) {
                        log("NO 标签，属性名为:" + attributes.getQName(i) + "\t属性值为:" + attributes.getValue(i));

                    }
                }
                if (TextUtils.equals("ADDR", startname)) {
                    log("ADDR标签，属性数量为:" + attributes.getLength());

                }


            }

            //结束标签的方法
            @Override
            public void endElement(String uri, String localName, String qName) throws SAXException {
                super.endElement(uri, localName, qName);
                log("结束标签" + localName);
            }

            //字符的方法
            @Override
            public void characters(char[] ch, int start, int length) throws SAXException {
                super.characters(ch, start, length);
                String str = new String(ch, start, length).trim();
                if (!TextUtils.isEmpty(str)) {
                    if (TextUtils.equals(startname, "NO")) {
                        log("NO内容为:" + str + "字符串长度为:" + str.length());
                    }
                    if (TextUtils.equals(startname, "ADDR")) {
                        log("ADDR内容为:" + str + "字符串长度为:" + str.length());
                    }
                }

            }
        });
    }
}