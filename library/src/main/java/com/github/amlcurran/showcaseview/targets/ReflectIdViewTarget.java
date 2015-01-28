package com.github.amlcurran.showcaseview.targets;

import android.app.Activity;


public class ReflectIdViewTarget extends ViewTarget
{

	public ReflectIdViewTarget(String idName, Activity activity)
	{
		super(activity.getResources().getIdentifier(idName, "id", activity.getPackageName()), activity);
	}

}
