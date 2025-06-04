# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
# Original Rules from the User (Retained)
# ----------------------------------------
# ProGuard Rules for Android Applications
# ----------------------------------------

# Obfuscation dictionaries
-obfuscationdictionary "E:\Strong.txt"
-classobfuscationdictionary "E:\Powerful.txt"
-packageobfuscationdictionary "E:\Strong_Short.txt"

# Disable unwanted optimizations for stability
-dontoptimize
-dontpreverify
-dontshrink
-forceprocessing
-allowaccessmodification
-ignorewarnings

# Verbose output during build
-verbose

# Retain line numbers for debugging
-keepattributes SourceFile,LineNumberTable

# Preserve annotations
-keepattributes *Annotation*

# Preserve important Android classes
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class android.support.** { *; }
-keep public class androidx.** { *; }

# Preserve Parcelable and Serializable implementations
-keepclassmembers class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
}

# Keep ButterKnife and similar frameworks
-keep @interface butterknife.*
-keepclasseswithmembers class * {
    @butterknife.* <fields>;
    @butterknife.* <methods>;
}
-keep class **$$ViewBinder { *; }

# Retain important library members (OneSignal, Firebase, etc.)
-keep class com.google.firebase.** { *; }
-keep class com.onesignal.** { *; }
-keepattributes EnclosingMethod
-keepattributes InnerClasses

# Obfuscate strings and prevent decompilation
-adaptresourcefilenames **.properties,**.xml
-adaptresourcefilecontents **.properties,META-INF/MANIFEST.MF
-mergeinterfacesaggressively
-useuniqueclassmembernames

# Disable Log output
-assumenosideeffects class android.util.Log {
    public static boolean isLoggable(java.lang.String, int);
    public static int v(...);
    public static int d(...);
    public static int i(...);
    public static int w(...);
    public static int e(...);
}

# Hide sensitive application constants
-keepclassmembers class ** {
    static <fields>;
}
-keepclassmembers class * {
    *** APP_ID;
    *** API_KEY;
}

# Obfuscate XML resources
-renamesourcefileattribute ProGuard
-repackageclasses usikke

# Preserve R class and fields
-keepclassmembers class **.R$* {
    public static <fields>;
}

# Ensure secure Firebase and Google Services configurations
-keep class com.google.** { *; }
-dontwarn com.google.**
-keepattributes Signature
-keepattributes *JavascriptInterface*

# Anti-debugging and tampering
-dontobfuscate
-assumenoexternalsideeffects class java.lang.StringBuilder {
    public java.lang.StringBuilder();
    public java.lang.StringBuilder(int);
    public java.lang.StringBuilder append(...);
    public java.lang.String toString();
}

# Prevent sensitive layout information exposure
-keepclassmembers class * {
    *** onCreate(...);
}
-keep public class * extends android.view.View {
    *** get*();
    void set*(***);
}

# Ensure security for OneSignal App ID
-keepclassmembers class ** {
    static <fields>;
}

# Preserve important WebView interactions
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#    public *;
#}

# -------------------------------
# Secure Sensitive Data in Strings
# -------------------------------
# Obfuscate sensitive strings (OneSignal App ID, API keys, etc.)
-keepclassmembers class * {
    private static final <fields>;
    private static final <methods>;
}
