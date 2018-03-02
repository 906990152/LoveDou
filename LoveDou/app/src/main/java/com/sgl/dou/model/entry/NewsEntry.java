package com.sgl.dou.model.entry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/29 0029.
 */

public class NewsEntry extends GraphQlModel {
    private static String THUMB_IMAGE_END = "?imageView2/1/w/115/h/70";

    private NewsEntry normalNewsDetail;

    private String title;
    private String pageUrl; // url跳转
//    private UserEntity writer; // 新闻作者
    private int likes; // 点赞数
    private List<String> keyWords; // 关键字
    private int newsType; //  0 图文 1 视频 2 公告
    private List<String> imageInContent; // 图片数组 图片链接加?imageView2/1/w/128/h/128 表示获取缩略图
    private List<NewsEntry> recommendNews; // 推荐
    private int commentCount; // 评论数
    private long createTime; //

    private String thumbnail4Rec;
    private String videoUrl; //  视频资讯中的视频连接
    private String text4video; // 视频内容
    private boolean isAwesome; // 精品
    private boolean isFixedAtTop; // 置顶

    public boolean isAwesome() {
        return isAwesome;
    }

    public void setAwesome(boolean awesome) {
        isAwesome = awesome;
    }

    public boolean isFixedAtTop() {
        return isFixedAtTop;
    }

    public void setFixedAtTop(boolean fixedAtTop) {
        isFixedAtTop = fixedAtTop;
    }

    public NewsEntry getNormalNewsDetail() {
        return normalNewsDetail;
    }

    public String getText4video() {
        return text4video;
    }

    public void setText4video(String text4video) {
        this.text4video = text4video;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public void setNormalNewsDetail(NewsEntry normalNewsDetail) {
        this.normalNewsDetail = normalNewsDetail;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getThumbnail4Rec() {
        return thumbnail4Rec;
    }

    public void setThumbnail4Rec(String thumbnail4Rec) {
        this.thumbnail4Rec = thumbnail4Rec;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

//    public UserEntity getWriter() {
//        return writer;
//    }
//
//    public void setWriter(UserEntity writer) {
//        this.writer = writer;
//    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public List<String> getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(List<String> keyWords) {
        this.keyWords = keyWords;
    }

    public int getNewsType() {
        return newsType;
    }

    public void setNewsType(int newsType) {
        this.newsType = newsType;
    }

    public List<String> getImageInContent() {
        List<String> result = new ArrayList<>();
        if (imageInContent != null && imageInContent.size() >0) {
            for (String image : imageInContent) {
                result.add(image + THUMB_IMAGE_END);
            }
        }
        return result;
    }

    public void setImageInContent(List<String> imageInContent) {
        this.imageInContent = imageInContent;
    }


    public List<NewsEntry> getRecommendNews() {
        return recommendNews;
    }

    public void setRecommendNews(List<NewsEntry> recommendNews) {
        this.recommendNews = recommendNews;
    }
}
