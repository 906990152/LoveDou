package com.sgl.dou.model.manager;

import com.sgl.dou.http.HttpRequest;
import com.sgl.dou.http.base.CommonCallback;
import com.sgl.dou.model.entry.NewsList;

/**
 * Created by sugl on 2018/3/2 0002.
 */

public class NewsManager {
    private static final NewsManager instance=new NewsManager();
    private NewsManager(){

    }
    public static NewsManager getInstance(){
        return instance;
    }
    /**
     * 资讯列表
     *
     * @param offset
     * @param limit
     * @param type
     * @param callback
     */
    public void getNewsList(int offset, int limit, int type, CommonCallback callback) {
        new HttpRequest().postUrl(getNewsList(offset, limit, type), callback, NewsList.class);

    }
    /**
     * 资讯
     *
     * @param offset
     * @param limit
     * @param type   # 0 所有 1图文 2 视频 3 公告
     * @return
     */
    private String getNewsList(int offset, int limit, int type) {
        return "{\n" +
                "  normalNewsList(offset: " + offset + ", limit: " + limit + ", type: " + type + ") {\n"
                + getNewContent() +
                "  }\n" +
                "}\n";
    }
    private String getNewContent() {
        return "   id,title,pageUrl\n" +
                "    writer {\n" +
                "      id,name,avatar,isAdmin,manageNews}\n" +
                "    likes,thumbnail4Rec,keyWords,isAwesome,isFixedAtTop,newsType,videoUrl,text4video,createTime,commentCount,imageInContent\n";
    }
}
