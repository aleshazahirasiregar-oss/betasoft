# Net Browser 3.5 (Android Source Project)

Application ID: `com.netbeta.browser`  
Target SDK: `API 36` (Android 15+)

## How to Build the APK:

### Option 1: Android Studio (Recommended)
1. Open Android Studio.
2. Click **File > Open** and select this extracted folder.
3. Wait for Gradle sync to complete.
4. Click **Build > Build Bundle(s) / APK(s) > Build APK(s)**.
5. Once built, Android Studio will display a popup: click **locate** to get `app-debug.apk`!

### Option 2: Terminal / Command Line
Run the following in the project root:
```bash
./gradlew assembleDebug
```
The generated APK will be at:
```
app/build/outputs/apk/debug/app-debug.apk
```

### Option 3: GitHub Actions (Free Cloud Build)
1. Push this project to a new GitHub repository.
2. The included `.github/workflows/build-apk.yml` will automatically build the APK on every push.
3. Go to the **Actions** tab on GitHub and download the **NetBrowser-3.5-Debug-APK** artifact!
