package com.zuzhi.tianyou.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.alertview.AlertView;
import com.easemob.easeui.EaseConstant;
import com.easemob.easeui.ui.EaseChatFragment;
import com.zuzhi.tianyou.R;
import com.zuzhi.tianyou.activity.CollectionActivity;
import com.zuzhi.tianyou.activity.IMActivity;
import com.zuzhi.tianyou.activity.MyCouponActivity;
import com.zuzhi.tianyou.activity.MyOrderActivity;
import com.zuzhi.tianyou.activity.OpinionActivity;
import com.zuzhi.tianyou.activity.PersonalDataActivity;
import com.zuzhi.tianyou.activity.SetActivity;
import com.zuzhi.tianyou.base.BaseFragment;
import com.zuzhi.tianyou.utils.Cons;
import com.zuzhi.tianyou.utils.ToastUtil;

public class MyFragment extends BaseFragment implements View.OnClickListener,
        com.bigkoo.alertview.OnItemClickListener, com.bigkoo.alertview.OnDismissListener {


    //获取头部控件
    private ImageView im_my_set, im_my_notice, im_my_info, im_my_header;

    /**
     * 用户名
     */
    private TextView tv_my_name;

    //单项点击区域
    private RelativeLayout rl_my_order, rl_my_collection, rl_my_coupons,
            rl_my_opinion, rl_my_help, rl_my_about, rl_my_exit;

    //titlebar 标题栏透传
    LinearLayout titleBar;

    public MyFragment(){};
    public MyFragment(LinearLayout titleBar) {
        this.titleBar = titleBar;
    }

    @Override
    protected void setTitleBar() {
        if (titleBar.getVisibility() != View.GONE) {
            titleBar.setVisibility(View.GONE);
        }

    }

    @Override
    //Fragment 从隐藏切换至显示，会调用onHiddenChanged(boolean hidden)方法
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            if (titleBar.getVisibility() != View.GONE) {
                titleBar.setVisibility(View.GONE);
            }
        }
    }

    @Override
    protected int setLayoutID() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initView(View view) {

        tv_my_name = (TextView) view.findViewById(R.id.tv_my_name);
        //从SharedPreferences取用户名赋值
//        tv_my_name.setText("");

        im_my_set = (ImageView) view.findViewById(R.id.im_my_set);
        im_my_notice = (ImageView) view.findViewById(R.id.im_my_notice);
        im_my_info = (ImageView) view.findViewById(R.id.im_my_info);
        im_my_header = (ImageView) view.findViewById(R.id.civ_my_head);

        rl_my_order = (RelativeLayout) view.findViewById(R.id.rl_my_order);
        rl_my_collection = (RelativeLayout) view.findViewById(R.id.rl_my_collection);
        rl_my_coupons = (RelativeLayout) view.findViewById(R.id.rl_my_coupons);
        rl_my_opinion = (RelativeLayout) view.findViewById(R.id.rl_my_opinion);
        rl_my_help = (RelativeLayout) view.findViewById(R.id.rl_my_help);
        rl_my_about = (RelativeLayout) view.findViewById(R.id.rl_my_about);
        rl_my_exit = (RelativeLayout) view.findViewById(R.id.rl_my_exit);

        //注册点击事件
        im_my_set.setOnClickListener(this);
        im_my_notice.setOnClickListener(this);
        im_my_info.setOnClickListener(this);
        im_my_header.setOnClickListener(this);

        rl_my_order.setOnClickListener(this);
        rl_my_collection.setOnClickListener(this);
        rl_my_coupons.setOnClickListener(this);
        rl_my_opinion.setOnClickListener(this);
        rl_my_help.setOnClickListener(this);
        rl_my_about.setOnClickListener(this);
        rl_my_exit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.im_my_set:        //设置
                Intent inSet = new Intent(MyFragment.this.getActivity(), SetActivity.class);
                startActivity(inSet);
                break;
            case R.id.im_my_notice:     //闹钟
                if(!Cons.B_ISLOGIN){
                    ToastUtil.showToast(getActivity(), "请先登录！");
                    return;
                }
                //start im activity 启动社交页
                intent = new Intent(getActivity(), IMActivity.class);
                startActivity(intent);
                break;
            case R.id.im_my_info:       //详情
//                Toast.makeText(getActivity(),"im_my_info",Toast.LENGTH_SHORT).show();
//                break;
            case R.id.civ_my_head:     //点击头像个人设置
                Intent inPersonalDate = new Intent(getActivity(), PersonalDataActivity.class);
                startActivity(inPersonalDate);
//                Toast.makeText(getActivity(),"im_my_header",Toast.LENGTH_SHORT).show();
                break;
            //列表单项按钮
            case R.id.rl_my_order: //my order 我的订单
                //start my order activity 启动我的订单页面
                intent = new Intent(getContext(), MyOrderActivity.class);
                getContext().startActivity(intent);
                break;
            case R.id.rl_my_collection: //我的订收藏
                //start my collection activity 启动我的收藏页面
                intent = new Intent(getContext(), CollectionActivity.class);
                getContext().startActivity(intent);
                break;
            case R.id.rl_my_coupons:    //优惠券
                Intent MyCoupon = new Intent(getActivity(), MyCouponActivity.class);
                startActivity(MyCoupon);
                break;
            case R.id.rl_my_opinion:    //意见反馈
                Intent inOpinion = new Intent(getActivity(), OpinionActivity.class);
                startActivity(inOpinion);

//                Toast.makeText(getActivity(),R.string.my_opinion,Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_my_help:       //帮助
                Toast.makeText(getActivity(), R.string.my_help, Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_my_about:      //关于
                Toast.makeText(getActivity(), R.string.my_about, Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_my_exit:       //退出登录
                new AlertView(null, null,
                        MyFragment.this.getActivity().getResources().getString(R.string.determine_if_exit),
                        MyFragment.this.getActivity().getResources().getString(R.string.cancel),
                        new String[]{MyFragment.this.getActivity().getResources().getString(R.string.determine_exit)},
                        null,
                        MyFragment.this.getActivity(),
                        AlertView.Style.Alert,
                        MyFragment.this)
                        .setCancelable(true)
                        .setOnDismissListener(this)
                        .show();
                break;


        }


    }

    @Override
    public void onDismiss(Object o) {

    }

    @Override
    public void onItemClick(Object o, int position) {
        switch (position) {
            //cancle 取消
            case -1:

                break;
            //cnfirm 确定
            case 0:

                break;
        }
    }


}

