package com.quentindommerc.superlistview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.quentindommerc.superlistview.superlistview.R;


/**
 * Created by kentin on 24/04/14.
 */
public class SuperListview extends BaseSuperAbsListview {

    public SuperListview(Context context) {
        super(context);
    }


    public SuperListview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SuperListview(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public ListView getList(){
       return (ListView) mList;
    }

    @Override
    protected void initAttrs(AttributeSet attrs) {
        super.initAttrs(attrs);
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.superlistview);
        try {
            mSuperListViewMainLayout = a.getResourceId(R.styleable.superlistview_superlv_mainLayoutID, R.layout.view_progress_listview);
        } finally {
            a.recycle();
        }
    }

    @Override
    protected void initAbsListView(View v) {

        View listView = v.findViewById(android.R.id.list);

        if (listView instanceof ListView)
            mList = (ListView) listView;
        else
            throw new IllegalArgumentException("SuperListView works with a List!");


        if (mList!=null) {


            mList.setClipToPadding(mClipToPadding);

            getList().setDivider(new ColorDrawable(getContext().getResources().getColor(android.R.color.transparent)));
            getList().setDividerHeight((int) mDividerHeight);

            mList.setOnScrollListener(this);
            if (mSelector != 0)
                mList.setSelector(mSelector);

            if (mPadding != -1.0f) {
                Log.e("TOTO", String.format("Padding %d", mPadding));
                mList.setPadding(mPadding, mPadding, mPadding, mPadding);
            } else {
                mList.setPadding(mPaddingLeft, mPaddingTop, mPaddingRight, mPaddingBottom);
            }

            mList.setScrollBarStyle(mScrollbarStyle);
        }
    }


}
