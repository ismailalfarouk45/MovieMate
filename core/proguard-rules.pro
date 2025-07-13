## Add project specific ProGuard rules here.
## You can control the set of applied configuration files using the
## proguardFiles setting in build.gradle.
##
## For more details, see
##   http://developer.android.com/guide/developing/tools/proguard.html
#
## If your project uses WebView with JS, uncomment the following
## and specify the fully qualified class name to the JavaScript interface
## class:
##-keepclassmembers class fqcn.of.javascript.interface.for.webview {
##   public *;
##}
## Uncomment this to preserve the line number information for
## debugging stack traces.
##-keepattributes SourceFile,LineNumberTable
#
## If you keep the line number information, uncomment this to
## hide the original source file name.
##-renamesourcefileattribute SourceFile
#-keep,allowobfuscation,allowshrinking class com.google.gson.reflect.TypeToken
#-keep,allowobfuscation,allowshrinking class * extends com.google.gson.reflect.TypeToken
#
#-keep class com.ismail.core.** {*;}
#-dontwarn com.ismail.core.**
#-keepattributes Exceptions, Signature, InnerClasses
#
#
#-keep class com.ismail.core.data.source.remote.network.ApiResponse.** {*;}
#
#-dontobfuscate
#-keepattributes *Annotation*
#-keepclassmembers class **.R$* {
#   public static <fields>;
#}
#
#
#-keepclassmembernames class com.ismail.core.data.source.remote.response.GetMovieResponse{
#  <fields>;
#  <methods>;
#}
#
#-keepclassmembernames class com.ismail.core.data.source.remote.response.Dates{
#  <fields>;
#  <methods>;
#}
#-keepclassmembernames class com.ismail.core.data.source.remote.response.ResultsItem{
#  <fields>;
#  <methods>;
#}
#
#-keepclassmembernames class com.ismail.core.data.source.remote.network.ApiResponse{
#  <fields>;
#  <methods>;
#}
## Keep sealed classes and their subtypes
#-keep class com.ana.core.** { *; }
#-keep class kotlin.Metadata { *; }
#-keepattributes *Annotation*
#
## Keep specific methods and fields that are necessary
#-keepclassmembers class com.ana.core.** {
#    *;
#}
#
## Project specific ProGuard rules
#-ignorewarnings
#
## Keep all public and private members
#-keep class * {
#    public private *;
#}
#
## Google Play Services
#-keep class com.google.android.gms.** { *; }
#-dontwarn com.google.android.gms.**
#
## XML Pull
#-dontwarn org.xmlpull.v1.**
#-dontnote org.xmlpull.v1.**
#-keep class org.xmlpull.** { *; }
#
## Retrofit
#-dontnote retrofit2.Platform
#-dontnote retrofit2.Platform$IOS$MainThreadExecutor
#-dontwarn retrofit2.Platform$Java8
#-keepattributes Signature
#-keepattributes Exceptions
#-keepclasseswithmembers class * {
#    @retrofit2.http.* <methods>;
#}
#-keepclassmembers class * {
#    native <methods>;
#}
#-dontwarn okio.**
#-dontwarn com.squareup.okhttp.**
#-dontwarn okhttp3.**
#-dontwarn javax.annotation.**
#-dontwarn com.android.volley.toolbox.**
#
## Gson
#-keepattributes Signature
#-keepattributes *Annotation*
#-dontwarn sun.misc.**
#-keep class com.google.gson.examples.android.model.** { <fields>; }
#-keep class * extends com.google.gson.TypeAdapter
#-keep class * implements com.google.gson.TypeAdapterFactory
#-keep class * implements com.google.gson.JsonSerializer
#-keep class * implements com.google.gson.JsonDeserializer
#-keepclassmembers,allowobfuscation class * {
#  @com.google.gson.annotations.SerializedName <fields>;
#}
#
## Glide
#-keep public class * implements com.bumptech.glide.module.GlideModule
#-keep class * extends com.bumptech.glide.module.AppGlideModule { <init>(...); }
#-keep public enum com.bumptech.glide.load.ImageHeaderParser$** { **[] $VALUES; public *; }
#-keep class com.bumptech.glide.load.data.ParcelFileDescriptorRewinder$InternalRewinder { *** rewind(); }
#
## SQLCipher
#-keep,includedescriptorclasses class net.sqlcipher.** { *; }
#-keep,includedescriptorclasses interface net.sqlcipher.** { *; }
#
## RxJava (optional)
##-dontwarn java.util.concurrent.Flow*
#
## Core
#-keep class com.ismail.core.** { *; }
#-dontwarn com.ismail.core.**
#
## Room
#-keep class androidx.room.** { *; }
#-keep class com.ismail.core.data.source.local.** { *; }
#-keep class com.ismail.core.data.source.local.entity.** { *; }
#-keep class com.ismail.core.data.source.local.room.** { *; }
#
## Keep parcelable
#-keep class * implements android.os.Parcelable { *; }
#
## Keep R class
#-keepclassmembers class **.R$* { public static <fields>; }
#
## Keep sealed classes and their subtypes
#-keep class kotlin.Metadata { *; }
#-keepattributes *Annotation*

#--------------#
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
-keep,allowobfuscation,allowshrinking class com.google.gson.reflect.TypeToken
-keep,allowobfuscation,allowshrinking class * extends com.google.gson.reflect.TypeToken

-keep class com.ismail.core.** {*;}
-dontwarn com.ismail.core.**
-keepattributes Exceptions, Signature, InnerClasses


-keep class com.ana.core.data.source.remote.network.ApiResponse.** {*;}

-dontobfuscate
-keepattributes *Annotation*
-keepclassmembers class **.R$* {
   public static <fields>;
}


-keepclassmembernames class com.ismail.core.data.source.remote.response.GetMovieResponse{
  <fields>;
  <methods>;
}

-keepclassmembernames class com.ismail.core.data.source.remote.response.Dates{
  <fields>;
  <methods>;
}
-keepclassmembernames class com.ismail.core.data.source.remote.response.ResultsItem{
  <fields>;
  <methods>;
}

-keepclassmembernames class com.ismail.core.data.source.remote.network.ApiResponse{
  <fields>;
  <methods>;
}
# Keep sealed classes and their subtypes
-keep class com.ismail.core.** { *; }
-keep class kotlin.Metadata { *; }
-keepattributes *Annotation*

# Keep specific methods and fields that are necessary
-keepclassmembers class com.ana.core.** {
    *;
}