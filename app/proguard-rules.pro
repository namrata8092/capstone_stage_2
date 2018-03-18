-optimizationpasses 5
-dontpreverify
-repackageclasses ''
-allowaccessmodification
#-optimizations !code/simplification/arithmetic
-keepattributes *Annotation*
-keepattributes SourceFile,LineNumberTable
-useuniqueclassmembernames

-dontwarn android.os.**
-dontwarn com.android.internal.**
-dontwarn android.support.**
-dontwarn com.google.android.**
-dontwarn android.content.pm.**
-dontwarn android.net.**

-keep public class com.github.bumptech.glide.**
-keep class com.github.bumptech.glide.**{
    *;
}

-keepattributes Signature
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.** { *; }
-keep class com.google.gson.examples.android.model.** { *; }

-keep class com.android.volley.**{
    *;
}
-keepclassmembers public class **.R$* {
    public static <fields>;
}

-keepclassmembers class * implements android.os.Parcelable {
    static ** CREATOR;
}