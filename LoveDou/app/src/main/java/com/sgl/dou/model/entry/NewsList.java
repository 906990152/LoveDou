/**
 *
 * @版权所有: 网龙天晴程序部应用软件开发组
 * @作  者  : 陈希
 * @创建时间: 2014-4-14 下午2:57:44
 * @文件描述:
 * @version
 */
package com.sgl.dou.model.entry;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;


/**
 * 资讯列表
 * @see
 */
public class NewsList extends GraphQlModel {

    private ArrayList<NewsEntry> normalNewsList;
    private ArrayList<NewsEntry> queryNormalNews;

    public ArrayList<NewsEntry> getQueryNormalNews() {
        return queryNormalNews;
    }

    public void setQueryNormalNews(ArrayList<NewsEntry> queryNormalNews) {
        this.queryNormalNews = queryNormalNews;
    }

    public ArrayList<NewsEntry> getNormalNewsList() {
        return normalNewsList;
    }

    public void setNormalNewsList(ArrayList<NewsEntry> normalNewsList) {
        this.normalNewsList = normalNewsList;
    }

    @Override
    public ArrayList getList() {
        if (normalNewsList != null){
            return normalNewsList;
        }else if (queryNormalNews != null){
            return queryNormalNews;
        }
        return normalNewsList;
    }
}
