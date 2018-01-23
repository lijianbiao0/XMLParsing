package com.hofo.xmlparsing;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.Iterator;

public class Dom4J extends BaseActivity {


    @Override
    public void parsing() throws Exception {
        InputStream is = getAssets().open("XmlParsingTest.xml");
//        pars01(is);
        pars02(is);
    }

    private void pars02(InputStream inputStream) throws DocumentException {
        //创建SAXReader读取器，专门用于读取xml
        SAXReader saxReader = new SAXReader();
        //根据saxReader的read重写方法可知，既可以通过inputStream输入流来读取，也可以通过file对象来读取
        Document document = saxReader.read(inputStream);

        Element rootElement = document.getRootElement();
        Iterator<Element> modulesIterator = rootElement.elements("VALUE").iterator();
        //rootElement.element("name");获取某一个子元素
        //rootElement.elements("name");获取根节点下子元素moudule节点的集合，返回List集合类型
        //rootElement.elements("module").iterator();把返回的list集合里面每一个元素迭代子节点，全部返回到一个Iterator集合中
        while (modulesIterator.hasNext()) {
            Element moduleElement = modulesIterator.next();
            Element NOElement = moduleElement.element("NO");
            for (int i = 0; i < NOElement.attributeCount(); i++) {
                Attribute attribute = NOElement.attribute(i);
                log("NO:" + attribute.getName() + ":" + attribute.getValue());
            }
            log(NOElement.getName() + ":" + NOElement.getText());
            Element ADDRElement = moduleElement.element("ADDR");
            log(ADDRElement.getName() + ":" + ADDRElement.getText());
        }
    }

    private void pars01(InputStream is) throws DocumentException {
        //创建SAXReader读取器，专门用于读取xml
        SAXReader saxReader = new SAXReader();
        //根据saxReader的read重写方法可知，既可以通过inputStream输入流来读取，也可以通过file对象来读取
        Document document = saxReader.read(is);
        //另外还可以使用DocumentHelper提供的xml转换器也是可以的。
        //Document document = DocumentHelper.parseText("<?xml version=\"1.0\" encoding=\"UTF-8\"?><modules id=\"123\"><module> 这个是module标签的文本信息</module></modules>");
        //获取根节点对象
        Element rootElement = document.getRootElement();
        log("根节点名称：" + rootElement.getName());//获取节点的名称
        log("根节点有多少属性：" + rootElement.attributeCount());//获取节点属性数目
        log("根节点id属性的值：" + rootElement.attributeValue("id"));//获取节点的属性id的值
        log("根节点内文本：" + rootElement.getText());//如果元素有子节点则返回空字符串，否则返回节点内的文本
        //rootElement.getText() 之所以会换行是因为 标签与标签之间使用了tab键和换行符布局，这个也算是文本所以显示出来换行的效果。
        log("根节点内文本(1)：" + rootElement.getTextTrim());//去掉的是标签与标签之间的tab键和换行符等等，不是内容前后的空格
        log("根节点子节点文本内容：" + rootElement.getStringValue()); //返回当前节点递归所有子节点的文本信息。
        //获取子节点
        Element element = rootElement.element("VALUE");
        if (element != null) {
            log("子节点的文本：" + element.getText());//因为子节点和根节点都是Element对象所以它们的操作方式都是相同的
        }
        //但是有些情况xml比较复杂，规范不统一，某个节点不存在直接java.lang.NullPointerException，所以获取到element对象之后要先判断一下是否为空

        rootElement.setName("root");//支持修改节点名称
        log("根节点修改之后的名称：" + rootElement.getName());
        rootElement.setText("text"); //同样修改标签内的文本也一样
        log("根节点修改之后的文本：" + rootElement.getText());
    }
}
