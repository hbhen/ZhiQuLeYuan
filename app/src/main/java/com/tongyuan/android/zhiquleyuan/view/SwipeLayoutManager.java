package com.tongyuan.android.zhiquleyuan.view;

/**
 * Created by android on 2017/3/20.
 */
public class SwipeLayoutManager {
    private static SwipeLayoutManager mInstanse=new SwipeLayoutManager();
    private SwipeLayoutManager(){

    }
    public static SwipeLayoutManager create(){
        return mInstanse;
    }
    private SwipeLayout openLayout;
    public void setSwipeLayout(SwipeLayout swipeLayout){
        openLayout=swipeLayout;
    }
    public void clearSwipeLayout(){
        openLayout=null;
    }
    public boolean isCanSwipe(SwipeLayout currentLayout){
        if (openLayout==null){

        return true;
        }
        else{
            return openLayout==currentLayout;
        }
    }
    public void closeLayout(){
        if (openLayout!=null){
            openLayout.close();
        }
    }
}
