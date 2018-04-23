package com.yq.zhihurecyclerview;

/**
 * Created by Administrator on 2018/4/20.
 */

public class ItemBean {

    private String msg;
    private boolean showPic;//是否显示图片

    public ItemBean(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public boolean isShowPic() {
        return showPic;
    }

    public void setShowPic(boolean showPic) {
        this.showPic = showPic;
    }
}
