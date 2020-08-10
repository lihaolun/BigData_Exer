package com.atguigu.bigdata.project.weibo.controller;

import com.atguigu.bigdata.common.constant.Column;
import com.atguigu.bigdata.common.constant.Name;
import com.atguigu.bigdata.project.weibo.service.WeiBoService;

public class WeiBoController {

    private WeiBoService weiBoService = new WeiBoService();

    public void init() {
        //创建命名空间

        weiBoService.createNameSpace(Name.NAMESPACE_ATGUIGU.getName());
        //创建表
        //weibo:info:content
        weiBoService.createTable(Name.TABLE_WEIBO.getName(), Column.COLUMN_FAMILY_INFO.getName());
        //relation:attends:fans:userid
        weiBoService.createTable(Name.TABLE_RELATION.getName(), Column.COLUMN_FAMILY_ATTENDS.getName(), Column.COLUMN_FAMILY_FANS.getName());
        //inbox:info:userid
        weiBoService.createTable(Name.TABLE_WEIBO.getName(), Column.COLUMN_FAMILY_CONTENT.getName());

    }

    //发布微博
    public void publishWeibo(String userid,String content,long timestamp){
        weiBoService.publishWeibo(userid,content,timestamp);
    }

    //关注用户
    public void attendUser(String fanid,String starid){
        weiBoService.attendUser(fanid,starid);
    }
    //查看微博
    public void viewWeiBo(String userid,String starid){
        weiBoService.viewWeiBo(userid,starid);
    }

    //取消关注
    public void removeAttend(String fanid,String starid){
        weiBoService.removeAttend(fanid,starid);
    }


}
