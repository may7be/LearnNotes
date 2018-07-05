package com.zg.android_utils.util_common;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


public class MonitorViewPagerAdapter  extends PagerAdapter {
	List<View> viewLists;
	private int currentIndex = 0; //记录当前pager index
	/**
	 * 一般lists.size() >= 2, 否则对ViewPager来说没有意义！！
	 * 
	 * @param lists
	 */
	public MonitorViewPagerAdapter(List<View> lists) {
		viewLists = lists;
	}

	@Override
	public int getCount() {
		if (viewLists != null) {
			return viewLists.size();
		}
		return 0;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView(viewLists.get(position));
	}

	@Override
	public int getItemPosition(Object object) {
		if (viewLists != null && viewLists.contains(object)) {
			return viewLists.indexOf(object);
		}
		return super.getItemPosition(object);
	}

	@Override
	public Object instantiateItem(View container, int position) {
		((ViewPager) container).addView(viewLists.get(position));
		return viewLists.get(position);
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		container.addView(viewLists.get(position));
		return viewLists.get(position);
	}
	public void setCurrentItemIndex(int index) {
		currentIndex = index;
	}

	public int getCurrentItemIndex() {
		return currentIndex;
	}
}
