package com.zg.android_view;

import android.app.Dialog;
import android.content.Context;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

public class BaseDialog<B extends ViewDataBinding> extends Dialog {

    private TextView title;
    private TextView message;
    private ViewGroup btnContainer;
    private ViewGroup viewContainer;
    private View contentView;
    private View dividerLine;
    private View bottomDividerLine;
    private View bottomButtonGroup;
    private B binding;

    public BaseDialog(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = getWindow();
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        View view = LayoutInflater.from(context).inflate(R.layout.custom_dialog, null);
        super.setContentView(view);
        title = view.findViewById(R.id.title);
        dividerLine = view.findViewById(R.id.divider_line);
        bottomDividerLine = findViewById(R.id.bottom_divider_line);
        message = view.findViewById(R.id.message);
        btnContainer = view.findViewById(R.id.btn_container);
        viewContainer = view.findViewById(R.id.view_container);
        bottomButtonGroup = view.findViewById(R.id.layout_button);
    }

    public BaseDialog addButton(int stringRes, View.OnClickListener onClickListener) {
        return addButton(getContext().getString(stringRes), onClickListener);
    }

    public BaseDialog addButton(String name, View.OnClickListener onClickListener) {
        return addButton(name, -1, onClickListener);
    }

    public BaseDialog addButton(int stringRes, int backgroundRes, View.OnClickListener onClickListener) {
        return addButton(getContext().getString(stringRes), backgroundRes, onClickListener);
    }

    public BaseDialog addButton(String name, int backgroundRes, View.OnClickListener onClickListener) {
        Button button = (Button) LayoutInflater.from(getContext()).inflate(R.layout.custom_dialog_button, btnContainer, false);
        button.setText(name);
        button.setOnClickListener(onClickListener);
        if (backgroundRes != -1) {
            button.setBackgroundResource(backgroundRes);
        }
        btnContainer.addView(button);
        return this;
    }

    @Override
    public void setContentView(int layoutResID) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), layoutResID, viewContainer, false);
        if (binding != null) {
            setContentView(binding.getRoot());
        } else {
            setContentView(LayoutInflater.from(getContext()).inflate(layoutResID, viewContainer, false));
        }
    }

    @Override
    public void setContentView(View view) {
        contentView = view;
        viewContainer.removeAllViews();
        viewContainer.addView(view);
        contentView.post(new Runnable() {
            @Override
            public void run() {
                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) contentView.getLayoutParams();
                params.gravity = Gravity.CENTER;
                contentView.setLayoutParams(params);
            }
        });
    }

    public B getBinding() {
        return binding;
    }

    @Override
    public void setTitle(int stringRes) {
        title.setText(stringRes);
    }

    @Override
    public void setTitle(CharSequence string) {
        title.setText(string);
    }

    public void showDividerLine(boolean visible) {
        if (visible) {
            dividerLine.setVisibility(View.VISIBLE);
        } else {
            dividerLine.setVisibility(View.GONE);
        }
    }

    public void showBottomDividerLine(boolean visible) {
        bottomDividerLine.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    public void showButtonGroup(boolean visible) {//底部按钮组true为显示，false为隐藏，默认显示
        bottomButtonGroup.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    public BaseDialog setMessage(String msg) {
        message.setText(msg);
        return this;
    }

    public BaseDialog setMessage(int msg) {
        message.setText(msg);
        return this;
    }

    public View getContentView() {
        return contentView;
    }

    public BaseDialog addCancelButton() {
        addButton(R.string.cancel, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        return this;
    }

    public BaseDialog addCancelButton(View.OnClickListener listener) {
        addButton(R.string.cancel, listener);
        return this;
    }

    @Override
    public void dismiss() {
//        if (getCurrentFocus() != null) {
//            WindowUtils.hideKeyboard(getContext(), getCurrentFocus().getWindowToken());
//        }
        super.dismiss();

    }
}
