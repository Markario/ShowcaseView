package com.github.amlcurran.showcaseview.targets;

import java.lang.reflect.Field;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageButton;

public class AppCompatV7Reflector implements Reflector
{

	private Activity mActivity;

	public AppCompatV7Reflector(Activity activity)
	{
		mActivity = activity;
	}

	@Override
	public ViewParent getActionBarView()
	{
		return (ViewParent) getActionBarViewCompat();
	}

	@Override
	public View getHomeButton()
	{
		View homeButton = null;
		
		ViewGroup actionBarView = (ViewGroup) getActionBarView();
		
		for(int i = 0; i < actionBarView.getChildCount(); i++)
		{
			View child = actionBarView.getChildAt(i);
			
			if(child.getClass().equals(ImageButton.class))
			{
				return child;
			}
		}
		
		try
		{
			homeButton = ((ViewGroup) getActionBarView()).getChildAt(0);
		}
		catch (Exception e)
		{
			homeButton = null;
		}

		if (homeButton == null)
		{
			throw new RuntimeException("insertShowcaseViewWithType cannot be used when the theme " + "has no ActionBar");
		}
		return homeButton;
	}

	@Override
	public void showcaseActionItem(int itemId)
	{
	}

	private View getActionBarContainerComapat()
	{
		int actionBarContainerId = mActivity.getResources().getIdentifier("action_bar_container", "id", mActivity.getPackageName());

		View actionBarContainer = (View) mActivity.findViewById(actionBarContainerId);
		
		int actionBarId = mActivity.getResources().getIdentifier("action_bar", "id", mActivity.getPackageName());

		View actionBar = (View) mActivity.findViewById(actionBarId);

		if (actionBarContainer == null)
		{
			throw new RuntimeException("insertShowcaseViewWithType cannot be used when the theme " + "has no ActionBar");
		}

		return actionBarContainer;
	}

	private View getActionBarViewCompat()
	{
		View actionBarContainer = getActionBarContainerComapat();
		Class<?> actionBarContainerClass = actionBarContainer.getClass();
		
		try
		{
			Field actionBarViewField = actionBarContainerClass.getDeclaredField("mActionBarView");
			actionBarViewField.setAccessible(true);
			View actionBar = (View) actionBarViewField.get(actionBarContainer);
			return actionBar;
		}
		catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}
		catch (NoSuchFieldException e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
