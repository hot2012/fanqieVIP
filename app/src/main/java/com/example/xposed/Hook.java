package com.example.xposed;


import java.util.Set;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class Hook implements IXposedHookLoadPackage {
    private ClassLoader classLoader;
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        if (loadPackageParam.packageName.equals("com.dragon.read"))/*包名*/ {
            Class ActivityThread = XposedHelpers.findClass("android.app.ActivityThread",/*这里不用改*/loadPackageParam.classLoader);
            Set<XC_MethodHook.Unhook> unhooks = XposedBridge.hookAllMethods(ActivityThread, "performLaunchActivity",/*这里不用改*/new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    //会员
                    XposedHelpers.findAndHookMethod("com.dragon.read.user.e", loadPackageParam.classLoader, "b", new Object[]{XC_MethodReplacement.returnConstant(new Boolean(true))});//布尔真假
                   //去广告
                    XposedHelpers.findAndHookMethod("com.dragon.read.ad.util.d", loadPackageParam.classLoader, "a", new Object[]{XC_MethodReplacement.returnConstant(new Boolean(true))});//布尔真假
                    //免登陆相关：为true时，需要登录。为false时，不需要登录
                    XposedHelpers.findAndHookMethod("com.dragon.read.http.a.b", loadPackageParam.classLoader, "a", new Object[]{XC_MethodReplacement.returnConstant(new Boolean(true))});//布尔真假
                    XposedHelpers.findAndHookMethod("com.dragon.read.polaris.n", loadPackageParam.classLoader, "a", new Object[]{XC_MethodReplacement.returnConstant(new Boolean(false))});//布尔真假
                    XposedHelpers.findAndHookMethod("com.bytedance.pangle.Zeus", loadPackageParam.classLoader, "hasInit", new Object[]{XC_MethodReplacement.returnConstant(new Boolean(false))});//布尔真假
                    XposedHelpers.findAndHookMethod("com.dragon.read.ad.util.d", loadPackageParam.classLoader, "a", new Object[]{XC_MethodReplacement.returnConstant(new Boolean(true))});//布尔真假
                    XposedHelpers.findAndHookMethod("com.dragon.read.component.base.ui.absettings.e", loadPackageParam.classLoader, "d", new Object[]{XC_MethodReplacement.returnConstant(new Boolean(true))});//布尔真假



                }



            });


        }}

}