package com.phl.blog.util;

import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.TableBlock;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.ext.heading.anchor.HeadingAnchorExtension;
import org.commonmark.node.Link;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.AttributeProvider;
import org.commonmark.renderer.html.AttributeProviderContext;
import org.commonmark.renderer.html.AttributeProviderFactory;
import org.commonmark.renderer.html.HtmlRenderer;
import org.hibernate.CustomEntityDirtinessStrategy;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @author Penghanlin-verysix
 * @create 2020-04-06-15:49
 */
public class MarkDownUtil {

    /**
     * markdown格式转换成HTML格式
     *
     * */
    public static String markdownToHtml(String markdown){
        Parser parser = Parser.builder().build();
        Node document = parser.parse(markdown);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        return renderer.render(document);  // "<p>This is <em>Sparta</em></p>\n"
    }

    /*
     * 增加扩展[标题锚点，表格生成]
     *
     * */
    public static String markdownToHtmlExtensions(String markdown){
        //h标签生产id
        Set<Extension> headingAncharExtensions = Collections.singleton(HeadingAnchorExtension.create());
        //转换table的HTML
        List<Extension> tableExtension = Arrays.asList(TablesExtension.create());
        Parser parser = Parser.builder()
                .extensions(tableExtension)
                .build();
        Node document = parser.parse(markdown);
        HtmlRenderer renderer = HtmlRenderer.builder()
                .extensions(headingAncharExtensions)
                .extensions(tableExtension)
                .attributeProviderFactory(new AttributeProviderFactory() {
                    @Override
                    public AttributeProvider create(AttributeProviderContext context) {
                        return new CustomAttributeProvider();
                    }
                })
                .build();
        return renderer.render(document);
    }



    //处理标签的属性
    static class CustomAttributeProvider implements AttributeProvider{

        @Override
        public void setAttributes(Node node, String tagName, Map<String, String> map) {
            //改变a标签的target属性为_blank
            if(node instanceof Link){
                map.put("target","_blank");
            }
            if (node instanceof TableBlock){
                map.put("class","ui celled table");
            }
        }
    }


    public static void main(String[] args) {
        String table =
                "| hello | hi | 哈哈哈 |\n"+
                "| ----- | ----- | ----- \n"+
                "| 张三  | 李四  | 王五 |\n";

        String a = "百度一下[http://www.baidu.com]";
        System.out.println(markdownToHtmlExtensions(table));
    }
}
