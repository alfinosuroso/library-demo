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

# Add rules specific to your application here

# Add rules for the AAR library you are using
# If the library's AAR file is named 'library.aar', you can specify it like this:

# Keep public classes and methods in the library
#-keep public class com.mkpmobile.apps2pay.sunmi.sdk.payment.BuildConfig {
#    public *;
#}

# Keep all public and protected classes/methods in the library
#-keepclassmembers class com.mkpmobile.apps2pay.sunmi.sdk.payment.R {
#    public protected *;
##    public static <fields>;
#}
# keep all public and protected method names,
# which could be used by Java reflection.
#-keepclassmembernames class * {
#    public protected <methods>;
#}

# Keep any interfaces used by the library
-keep interface * {
    *;
}

# If the library uses any external libraries, add their ProGuard rules here
# For example, if the library uses Gson:
-keep class com.google.gson.Gson {
    *;
}

# Add any additional ProGuard rules for your application here

# Add rules to keep all classes and methods of your application
-keep public class * {
    public <methods> ;
}

# If you are using any libraries that use reflection or annotations,
# you might need to add specific rules to keep them.

# For example, to keep classes that are referenced in annotations:
-keepclassmembers class * {
    @com.example.annotations.* <fields>;
}

# To keep classes that are used with reflection (e.g., using Class.forName):
#-keep class com.mkpmobile.apps2pay.sunmi.sdk.apps2pay_sdk_demo.TransactionActivity {
#    *;
#}

# Add any additional rules specific to your application here
#-printmapping out.map
#-keepparameternames
#-renamesourcefileattribute SourceFile
#-keepattributes Exceptions,InnerClasses,Signature,Deprecated,SourceFile,LineNumberTable,EnclosingMethod

# Preserve all annotations.

#-keepattributes *Annotation*

# Preserve all public classes, and their public and protected fields and
# methods.

#-keep public class * {
#    public protected *;
#}

# Preserve all .class method names.

-keepclassmembernames class * {
    java.lang.Class class$(java.lang.String);
    java.lang.Class class$(java.lang.String, boolean);
}

# Preserve all native method names and the names of their classes.

-keepclasseswithmembernames class * {
    native <methods>;
}

# Preserve the special static methods that are required in all enumeration
# classes.

#-keepclassmembers class * extends java.lang.Enum {
#    public static **[] values();
#    public static ** valueOf(java.lang.String);
#}

#this is new, if code was error please delete this
-keepclassmembers class * {
#    static java.lang.String *;
}

# Explicitly preserve all serialization members. The Serializable interface
# is only a marker interface, so it wouldn't save them.
# You can comment this out if your library doesn't use serialization.
# If your code contains serializable classes that have to be backward
# compatible, please refer to the manual.

-keepclassmembers class * implements java.io.Serializable {
#    static final long serialVersionUID;
#    static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

-keepclasseswithmembernames,includedescriptorclasses class * {
    native <methods>;
}

# The library may contain more items that need to be preserved;
# typically classes that are dynamically created using Class.forName:

# -keep public class mypackage.MyClass
# -keep public interface mypackage.MyInterface
# -keep public class * implements mypackage.MyInterface


-keepclassmembers,allowobfuscation class * {
    @com.google.gson.annotations.SerializedName <fields>;
}


