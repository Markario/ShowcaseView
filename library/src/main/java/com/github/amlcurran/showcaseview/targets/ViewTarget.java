/*
 * Copyright 2014 Alex Curran
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.amlcurran.showcaseview.targets;

import android.app.Activity;
import android.graphics.Point;
import android.view.View;

/**
 * Target a view on the screen. This will centre the target on the view.
 */
public class ViewTarget implements Target {

    private final View mView;
    private float sx = .5f;
    private float sy = .5f;

    public ViewTarget(View view) {
        mView = view;
    }

    public ViewTarget(int viewId, Activity activity) {
        mView = activity.findViewById(viewId);
    }
    
    public ViewTarget(View view, float sx, float sy) {
        mView = view;
        this.sx = sx;
        this.sy = sy;
    }

    public ViewTarget(int viewId, Activity activity, float sx, float sy) {
        mView = activity.findViewById(viewId);
        this.sx = sx;
        this.sy = sy;
    }

    @Override
    public Point getPoint() 
    {
    	int[] location = new int[2];
        mView.getLocationInWindow(location);
        int x = location[0] + (int)((float)(mView.getWidth()) * sx);
        int y = location[1] + (int)((float)(mView.getHeight()) * sy);
        
        return new Point(x, y);
    }
}
