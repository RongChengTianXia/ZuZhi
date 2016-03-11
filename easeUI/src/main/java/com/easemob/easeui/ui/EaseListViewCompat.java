package com.easemob.easeui.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

/**
 * Created by Corydon on 2016/3/9.
 */
public class EaseListViewCompat extends ListView {

    private static final String TAG = "ListViewCompat";

    private EaseSlideView mFocusedItemView;

    public EaseListViewCompat(Context context) {
        super(context);
    }

    public EaseListViewCompat(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EaseListViewCompat(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void shrinkListItem(int position) {
        View item = getChildAt(position);

        if (item != null) {
            try {
                ((EaseSlideView) item).shrink();
            } catch (ClassCastException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                int x = (int) event.getX();
                int y = (int) event.getY();
                int position = pointToPosition(x, y);
                Log.e(TAG, "postion=" + position);
                if (position != INVALID_POSITION) {
//                    MessageItem data = (MessageItem) getItemAtPosition(position);
//                    mFocusedItemView = data.slideView;
                    EaseEmConversation data = (EaseEmConversation) getItemAtPosition(position);
                    mFocusedItemView = data.slideView;
                    Log.e(TAG, "FocusedItemView=" + mFocusedItemView);
                }
            }
            default:
                break;
        }

        if (mFocusedItemView != null) {
            mFocusedItemView.onRequireTouchEvent(event);
        }

        return super.onTouchEvent(event);
    }
}
