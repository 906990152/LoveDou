package com.sgl.dou.library.recycleview.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.sgl.dou.library.R;

import java.io.File;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by Administrator on 2017/4/13 0013.
 */

public class ImageDisplayUtil {


    /**
     * 显示圆角图片
     * @param url
     * @param imageView
     */
    public static void displayCircleImage(Context context,String url, ImageView imageView) {
        Glide.with(context).load(url).apply(requestOption(context,0,false,true,R.drawable.default_head,R.color.color_tran))
                .transition(new DrawableTransitionOptions().crossFade(200)).into(imageView);
    }


    /**
     * 圆角矩形
     * @param url
     * @param imageView
     */
    public static void displayRoundImage(Context context,String url, ImageView imageView) {
        displayRoundImage(context,url,imageView,3);
    }

    public static void displayRoundImageDefault(Context context,String url, ImageView imageView,int resId) {
        displayRoundImage(context,url,imageView,3,resId);
    }

    public static void displayRoundImage(Context context,String url, ImageView imageView,int radio) {

        Glide.with(context).load(url)
                .apply(requestOption(context,radio,true,false,R.drawable.default_bg_round, R.drawable.default_bg_round))
                .into(imageView);
    }

    public static void displayRoundImage(Context context,String url, ImageView imageView,int radio,int res) {

        Glide.with(context).load(url)
                .apply(requestOption(context,radio,true,false,res,res))
                .into(imageView);
    }
    /**
     * 显示头像
     * @param url
     * @param imageView
     */
    public static void displayHeadImg(Context context,String url,ImageView imageView){
        displayImg(context,url,imageView,R.drawable.default_head);
    }
    /**
     * 显示图片
     * @param url
     * @param imageView
     */
    public static void displayImg(Context context,String url, ImageView imageView) {

        displayImg(context,url,imageView,R.drawable.default_bg);
    }

    public static void displayRoundAndBlackImg(Context context,String url, ImageView imageView) {
        Glide.with(context).load(url).apply(requestOption(context,false,R.drawable.default_bg,R.drawable.default_bg))
                .into(imageView);
    }

    public static void displayImg(Context context,String url, ImageView imageView, int defaultImg) {
        Glide.with(context).load(url).apply(requestOption(context,0,false,false,defaultImg,defaultImg))
                .transition(new DrawableTransitionOptions().crossFade(200))
                .into(imageView);
    }

    private static RequestOptions requestOption(Context context,int radio,boolean isRound,boolean isCircle,int error,int defaultImg){
        RequestOptions options = new RequestOptions().error(defaultImg)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .format(DecodeFormat.PREFER_RGB_565)
                .placeholder(defaultImg);
        if (isRound){
            options.transform(new MultiTransformation<>(new CenterCrop(),new RoundedCornersTransformation(WrapperUtils.dp2px(context,radio),0, RoundedCornersTransformation.CornerType.ALL)));
//            options.transform(new RoundedCornersTransformation(WrapperUtils.dp2px(context,radio),0, RoundedCornersTransformation.CornerType.ALL));

        }
        if (isCircle){
            options.circleCrop();
        }
        return options;
    }

    /**
     * 黑白
     * @param context
     * @param isRound
     * @param error
     * @param defaultImg
     * @return
     */
    private static RequestOptions requestOption(Context context,boolean isRound,int error,int defaultImg){
        RequestOptions options = new RequestOptions().error(error)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .format(DecodeFormat.PREFER_RGB_565)
                .placeholder(defaultImg);
        if (isRound){
            options.transform(new RoundedCornersTransformation(WrapperUtils.dp2px(context,3), 0, RoundedCornersTransformation.CornerType.ALL));
            options.transform(new BlackWhiteTransformation());
        }

        return options;
    }

    public static void image(ImageView imageView, String url, int resId, boolean isLocalUrl, boolean isCircle) {
        if (!TextUtils.isEmpty(url) && !url.trim().isEmpty()) {
            RequestOptions options;
            if (isCircle) {
                options = RequestOptions.circleCropTransform();
            } else {
                options = new RequestOptions();
            }
            options = options.format(DecodeFormat.PREFER_RGB_565).error(resId);
            if (isLocalUrl) {
                Glide.with(imageView.getContext()).load(new File(url)).apply(options).
                        into(imageView);
            } else {
                Glide.with(imageView.getContext()).load(url).apply(options).
                        into(imageView);
            }
        } else {
            if (resId != 0) {
                imageView.setImageResource(resId);
            }
        }
    }


}
