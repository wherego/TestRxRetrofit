# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in F:\Android\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# This is a configuration file for ProGuard.
# http://proguard.sourceforge.net/index.html#manual/usage.html

-dontusemixedcaseclassnames # 是否使用大小写混合
-dontskipnonpubliclibraryclasses 
-verbose # 混淆时是否记录日志
-dontoptimize
-dontpreverify # 混淆时是否做预校验

-keepattributes *Annotation*
-keep public class com.google.vending.licensing.ILicensingService
-keep public class com.android.vending.licensing.ILicensingService

-keepclasseswithmembernames class * {
    native <methods>;
}

-keepclassmembers public class * extends android.view.View {
   void set*(***);
   *** get*();
}

-keepclassmembers class **.R$* {
    public static <fields>;
}

-dontwarn android.support.** #包里面的所有类和所有方法不被混淆


-optimizationpasses 5          # 指定代码的压缩级别
-dontusemixedcaseclassnames   # 是否使用大小写混合

-keep public class * extends android.app.Application   # 保持哪些类不被混淆
-keep public class * extends android.app.Service       # 保持哪些类不被混淆
-keep public class * extends android.content.BroadcastReceiver  # 保持哪些类不被混淆
-keep public class * extends android.content.ContentProvider    # 保持哪些类不被混淆
-keep public class * extends android.app.backup.BackupAgentHelper # 保持哪些类不被混淆
-keep public class * extends android.preference.Preference        # 保持哪些类不被混淆
-keep public class com.android.vending.licensing.ILicensingService    # 保持哪些类不被混淆

-keepclasseswithmembernames class * {  # 保持 native 方法不被混淆
    native <methods>;
}
-keepclasseswithmembers class * {   # 保持自定义控件类不被混淆
    public <init>(android.content.Context, android.util.AttributeSet);
}
-keepclasseswithmembers class * {# 保持自定义控件类不被混淆

    public <init>(android.content.Context, android.util.AttributeSet, int);

}
-keepclassmembers class * extends android.app.Activity {  # 保持自定义控件类不被混淆   
    public void *(android.view.View);
}
-keepclassmembers enum * {     # 保持枚举 enum 类不被混淆   
 public static **[] values();   
 public static ** valueOf(java.lang.String);
}
-keep class * implements android.os.Parcelable { # 保持 Parcelable 不被混淆 
 public static final android.os.Parcelable$Creator *;
}
-keep class * implements java.io.Serializable # 保持 Serializable 不被混淆 
