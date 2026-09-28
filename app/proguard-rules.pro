# ===== 데이터 모델 (Firestore + Gson reflection) =====
# Firestore가 필드 이름으로 읽고 쓰는 모델(User, Quest, Place …). 이름이 바뀌면 릴리스에서만 데이터가 기본값으로 읽힌다.
-keep class com.jeepark.onestep.domain.model.** { *; }
-keepclassmembers class com.jeepark.onestep.domain.model.** {
    <init>();
    <fields>;
}
# Gson(Retrofit)이 응답 JSON을 채우는 API 모델(Gemini*, Kma*)
-keep class com.jeepark.onestep.data.remote.** { *; }
-keepclassmembers class com.jeepark.onestep.data.remote.** {
    <init>();
    <fields>;
}

# ===== Firebase =====
-keep class com.google.firebase.** { *; }
-keep class com.google.android.gms.** { *; }
-dontwarn com.google.firebase.**
-dontwarn com.google.android.gms.**

# Firestore가 reflection으로 객체를 변환할 때 필요
-keepattributes Signature
-keepattributes *Annotation*
-keepattributes EnclosingMethod
-keepattributes InnerClasses

# ===== Retrofit + OkHttp =====
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions
-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}

# ===== Gson =====
-keep class com.google.gson.** { *; }
-keep class * implements com.google.gson.TypeAdapter
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer
-keepclassmembers,allowobfuscation class * {
    @com.google.gson.annotations.SerializedName <fields>;
}

# ===== Kotlin Coroutines =====
-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory {}
-keepnames class kotlinx.coroutines.CoroutineExceptionHandler {}
-keepclassmembernames class kotlinx.** {
    volatile <fields>;
}

# ===== WorkManager (NotificationWorker) =====
-keep class * extends androidx.work.Worker
-keep class * extends androidx.work.ListenableWorker {
    public <init>(android.content.Context,androidx.work.WorkerParameters);
}
-keep class androidx.work.** { *; }
-keep class androidx.work.impl.** { *; }
-dontwarn androidx.work.**

# ===== Room (WorkManager 내부에서 사용) =====
-keep class * extends androidx.room.RoomDatabase { *; }
-keep @androidx.room.Entity class * { *; }
-keep @androidx.room.Dao class * { *; }
-keep class androidx.room.** { *; }
-keepclassmembers class * extends androidx.room.RoomDatabase {
    public static <methods>;
}
-keepnames class * extends androidx.room.RoomDatabase
-keep class **_Impl { *; }
-dontwarn androidx.room.paging.**

# SQLite
-keep class androidx.sqlite.** { *; }
-dontwarn androidx.sqlite.**

# ===== 디버깅용 스택 트레이스 (크래시 분석에 유용) =====
-keepattributes SourceFile,LineNumberTable
-renamesourcefileattribute SourceFile
