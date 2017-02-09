package com.mazouri.tools.sample.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.lb.materialdesigndialog.base.DialogWithTitle;
import com.lb.utils.ViewUtil;
import com.mazouri.tools.sample.R;

/**
 * Created by wangdongdong on 17-2-8.
 */

public class MaterialDialogCustomContent extends DialogWithTitle {

    private View contextView;
    private TextView tvContent;

    public MaterialDialogCustomContent(Context context) {
        super(context);
    }

    @Override
    protected View initContent() {
        contextView = LayoutInflater.from(context).inflate(R.layout.dialog_content_mock, null, false);
        findView();
        return contextView;
    }

    private void findView() {
        tvContent = ViewUtil.findViewById(contextView, R.id.content);
    }

    public View addContentView(int layoutId) {
        contextView = LayoutInflater.from(context).inflate(layoutId, null, false);
        fl_base_content.removeAllViews();
        fl_base_content.addView(contextView);
        return contextView;
    }

    public TextView getTvContent() {
        return tvContent;
    }

    public void setContent(String title) {
        tvContent.setText(title);
    }
}
