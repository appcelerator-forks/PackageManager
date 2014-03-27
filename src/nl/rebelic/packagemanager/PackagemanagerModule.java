/**
 * This file was auto-generated by the Titanium Module SDK helper for Android
 * Appcelerator Titanium Mobile
 * Copyright (c) 2009-2013 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 *
 */
package nl.rebelic.packagemanager;

import org.appcelerator.kroll.KrollModule;
import org.appcelerator.kroll.annotations.Kroll;

import org.appcelerator.titanium.TiApplication;
import org.appcelerator.kroll.common.Log;
import org.appcelerator.titanium.proxy.IntentProxy;

import java.util.List;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ApplicationInfo;
import android.content.pm.ResolveInfo;

@Kroll.module(name="Packagemanager", id="nl.rebelic.packagemanager")
public class PackagemanagerModule extends KrollModule
{

	// Standard Debugging variables
	private static final String TAG = "PackagemanagerModule";

	// You can define constants with @Kroll.constant, for example:
	// @Kroll.constant public static final String EXTERNAL_NAME = value;
	
	public PackagemanagerModule()
	{
		super();
	}

	@Kroll.onAppCreate
	public static void onAppCreate(TiApplication app)
	{
		Log.d(TAG, "inside onAppCreate");
		// put module init code that needs to run when the application is created
	}

	// Methods
	@Kroll.method
	public boolean isPackageInstalled(String packageName)
	{
        try {
            PackageInfo info = TiApplication.getAppRootOrCurrentActivity().getPackageManager().getPackageInfo(packageName, PackageManager.GET_META_DATA);
            return true;
        }
        catch (NameNotFoundException e) {
            return false;
        }
	}
    
    @Kroll.method
    public boolean isIntentAvailable(IntentProxy intentProxy) {
        List<ResolveInfo> resolveInfo = TiApplication.getAppRootOrCurrentActivity().getPackageManager().queryIntentActivities(intentProxy.getIntent(), 0);
        
        return resolveInfo != null && !resolveInfo.isEmpty();
    }
    
    /**
     * Return whether the given PackgeInfo represents a system package or not.
     * User-installed packages (Market or otherwise) should not be denoted as
     * system packages.
     *
     * @param pkgInfo
     * @return boolean
     */
    private boolean isSystemPackage(PackageInfo pkgInfo) {
        return ((pkgInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) ? true : false;
    }
}

