package com.ibm.bj.component.solrj.service.impl;

import com.ibm.bj.component.solrj.model.FileModel;
import com.ibm.bj.component.solrj.model.FileTypeModel;
import com.ibm.bj.component.solrj.model.PageModel;
import com.ibm.bj.component.solrj.service.IQueryFileService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


/**
 * Created by zhengxiangyun on 2015/11/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/solrj-spring-config.xml")
public class QueryFileServiceSolrImplTest {

    private IQueryFileService qfs;
    private IQueryFileService qfs2;

    @Before
    public void setUp(){
        qfs = new QueryFileServiceSolrImpl();
        qfs2 = new QueryFileServiceSolrMRImpl();
    }

    @Test
    public void testQueryFiles() {
        String wd = "工资";
        String type = null;
//        String type = "doc";
        PageModel pageModel = qfs2.queryFiles(wd, type, 1, 8);
        List<FileModel> fileModel = pageModel.getDatas();
        System.out.println("Count: " + pageModel.getCount());
        System.out.println("Totalpages: " + pageModel.getTotalPages());
        for (Object obj : fileModel) {
            FileModel fm = (FileModel)obj;
            System.out.println(fm.getName());
//            System.out.println(fm.getModifyTime());
            System.out.println(fm.getHighlightName());
            System.out.println(fm.getHighlightContent());
            System.out.println("----------------------------------------------------");
        }
    }

    @Test
    public void testQueryFileTypes() {
        String wd = "*";
        List<FileTypeModel> models = qfs2.queryFileTypes(wd);
        for (Object obj : models) {
            FileTypeModel fm = (FileTypeModel)obj;
            System.out.println(fm.getTypeName());
            System.out.println(fm.getTypeCount());
            System.out.println("----------------------------------------------------");
        }
    }

    @Test
    public void testGetFileTypeName() {
        QueryFileServiceSolrImpl qfssi = new QueryFileServiceSolrImpl();
        String str = qfssi.getFileTypeName("text/plain*");
        String str2 = qfssi.getFileTypeName("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        System.out.println(str);
        System.out.println(str2);
    }

}