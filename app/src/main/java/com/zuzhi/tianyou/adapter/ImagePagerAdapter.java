/*
 * Copyright 2014 trinea.cn All right reserved. This software is the confidential and proprietary information of
 * trinea.cn ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with trinea.cn.
 */
package com.zuzhi.tianyou.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.zuzhi.tianyou.MyApplication;
import com.zuzhi.tianyou.entity.ImageEntity;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * ImagePagerAdapter
 *
 * @author <a href="http://www.trinea.cn" target="_blank">Trinea</a> 2014-2-23
 */
public class ImagePagerAdapter extends RecyclingPagerAdapter{

    private Context context;
    private List<ImageEntity> imageIdList;
    private DisplayImageOptions options;
    private int size;
    private boolean isInfiniteLoop;
    /**
     * bitmap list 位图列表
     */
    List<Bitmap> mBitmaps;

    public ImagePagerAdapter(Context context, List<ImageEntity> imageIdList) {
        this.context = context;
        this.imageIdList = imageIdList;
        // this.size = ListUtils.getSize(imageIdList);
        this.size = imageIdList.size();
        isInfiniteLoop = false;
        mBitmaps = new ArrayList<Bitmap>();

    }

    @Override
    public int getCount() {
        // Infinite loop
        if (size == 0) {
            return 0;
        } else {
            return isInfiniteLoop ? Integer.MAX_VALUE : size;
        }
    }

    /**
     * get really position
     *
     * @param position
     * @return
     */
    private int getPosition(int position) {

        if (size == 0)
            return 0;
        else
            return isInfiniteLoop ? position % size : position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup container) {
        ViewHolder holder = null;
        if (view == null) {
            holder = new ViewHolder();
            holder.imageView = new ImageView(context);
            view = holder.imageView;
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.imageView.setScaleType(ScaleType.FIT_XY);
        ImageLoader.getInstance().displayImage(
                imageIdList.get(getPosition(position)).getUrl(),
                holder.imageView, MyApplication.dis_ImgOptions);

        return view;
    }

    private class ViewHolder {

        ImageView imageView;
    }

    /**
     * @return the isInfiniteLoop
     */
    public boolean isInfiniteLoop() {
        return isInfiniteLoop;
    }

    /**
     * @param isInfiniteLoop the isInfiniteLoop to set
     */
    public ImagePagerAdapter setInfiniteLoop(boolean isInfiniteLoop) {
        this.isInfiniteLoop = isInfiniteLoop;
        return this;
    }

}
