
  
# BobbleIMESDK

This guide is for all app developers who wish to add a custom keyboard functionality in their Android apps using the Bobble IME (Input Method Editor) SDK via traditional method.

Note - Minimum version of supported Android platform is SDK level 21

### Step 1: Adding the BobbleIME SDK to your Project
##### Option 1: Pulling the Latest SDK via Maven
If you are using Gradle to build your Android applications, you can pull the latest version of the SDK from Maven as described below:

 - Include Maven in your top-level build.gradle file along with the credentials(Read URL and Read password):

```java
allprojects {
    repositories {
        maven {
            url myMavenRepoReadUrl
            credentials {
                username 'myMavenRepo'
                password <getPassword>
            }
        }
    }
}

```
- Add the following line to the dependencies element in your application module’s build.gradle.

```java
    implementation 'com.touchtalent.bobblekeyboard:keyboard:1.1.0'

```

- Sync your Gradle project to ensure that the dependency is downloaded by the build system.


##### Option 2: Adding the SDK Library to your Application Project

Alternatively, you can download the latest version of BobbleIME’s SDK and copy the library to your application module’s libs/ directory.

To add the library to your project’s dependencies, add this line to the dependencies element in your module’s <strong>build.gradle</strong>:

```java
implementation fileTree(dir: 'libs', include: ['*.aar'])
```


### Step 2: Adding Permissions
##### Granting Permissions

The SDK uses the permissions granted to your app in order to improve the typing experience, and in order to suggest the most relevant content to your users.
We highly recommend that your app request the following permissions so that we can personalise user experience in a better way:
```java
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
<uses-permission android:name="android.permission.CAMERA" />
<uses-permission android:name="android.permission.RECORD_AUDIO" />
<uses-permission android:name="android.permission.READ_CONTACTS" />
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
```
    
    
### Step 3: Manifest Changes
The client needs to register the custom IME class in manifest as InputMethod service.

```java
<service
    android:name=".CustomIME"
    android:label="<Add your keyboard name here>"
    android:permission="android.permission.BIND_INPUT_METHOD">
    <intent-filter>
        <action android:name="android.view.InputMethod" />
    </intent-filter>
</service>
```

### Step 4: Other Build Settings
- Add option to not compress dictionary files by following lines in the android block of your gradle
```java
aaptOptions {
	noCompress ".dict"
}
```

- Enable data binding for the SDK components to work by adding 
```java
dataBinding {
   enabled true
}
```

### Step 5: Initialise SDK
Inside onCreate() method of your Application class, initialise the SDK by calling
```java
BobbleIme.initialise(applicationContext) 
```


### Step 6: Create your custom IME Class
Last step would be to create the custom class declared in the manifest above.
```java
import com.bobblekeyboard.ime.BobbleIME;

class CustomIME extends BobbleIME {
    @Override
    public void onCreate() {
        super.onCreate();
    }
}
```

### APIs

#### Check if keyboard is enabled
```java
boolean BobbleIme.isKeyboardEnabled()
```
This API can be used to check if the keyboard is enabled inside the global input method settings.

#### Check if keyboard is selected
```java
boolean BobbleIme.isKeyboardSelected()
```
This API can be used to check if the keyboard is the current selected input method editor in the system.

#### Initiate keyboard enable and selection
```java
void BobbleIme.install(new IMEInstallListener() {
    @Override
    public void onComplete(IMEInstallStatus status) {
        //status could be SELECTED, ENABLED, NONE.
    }
});
```
Use the API to start the keyboard enable and selection process. Once process is complete, use callback to check the status.

 - SELECTED : User enabled and selected the keyboard as well.
 - ENABLED : User just enabled the keyboard but did not select it as the default one.
 - NONE : User did not do anything and returned from the settings pages. This can be used to initiate any client side flow for educating users on how to enable it.
