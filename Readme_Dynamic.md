
# BobbleIMESDK via Dynamic Module

This guide is for all app developers who wish to add a custom keyboard functionality in their Android apps using the Bobble IME (Input Method Editor) SDK via dynamic delivery. Before you begin integrating the SDK into your app, please email us at android.master@bobble.ai to get the license key in order to avail complete functionalities of the SDK.


Note - Minimum version of supported Android platform is SDK level 23

Dynamic delivery is a delivery mechanism by Google PlayStore where specific modules of your app can be downloaded on a user's device after the core module has been downloaded. This gives developers, the freedom to opt for a smaller download size. This is done by ensuring that the additional features, like this custom keyboard, are delivered to users upon their choice. We provide support for such implementation by dividing our SDK into 2 parts 

- Full size SDK which can added to a dynamic module and 
- an ultra-lightweight support SDK (to be added to main module) to provide necessary support implementations. 

Please read below on how to integrate them.

Please note that there are limitations on using Dynamic delivery, which you must evaluate before opting for them.
1. You must distribute your app only via Play Store using the App Bundle(.aab) publishing format. The dynamic feature will not work with regular apks.
2. User's who don't have Google Play Services, may not be able to avail dynamic features.

For more information on app-bundles and dynamic delivery, it is recommended to go through Android's official documentation.

https://developer.android.com/guide/app-bundle

https://developer.android.com/guide/app-bundle/dynamic-delivery

#### How will this implementation work?
The Support SDK wraps up all necessary steps to download and initialise the IME, which are provided as API (please refer below).

When the user first downloads the client app, only the support library is shipped to them and the core library is yet to be downloaded. The client can  download the module either automatically or on-demand via user initiated action. You are free to implement your own logic of install/uninstall as per your need.

Since the entry for keyboard has to be added in base manifest in the client app, the system will list the keyboard inside list of keyboards available in Languages & Input settings, even if the module isn't installed. In such cases, it is possible that the user may manually navigate to the settings and activate the keyboard even if it's not yet downloaded.

Such situations are gracefully handled and when the keyboard popups, user will be shown a placeholder of downloading the module . You may optionally choose to override BobbleIME.onCreateDefaultView() to return your custom placeholder.

Once the keyboard modules are succesfully downloaded, the keyboard starts functioning as expected.

### Step 1: Adding a dynamic module to your Project
Within Android Studio, navigate to **File -> New -> New Module** and follow on screen instructions as follows
Setup 1             |  Setup 2             |  Setup 3
:-------------------------:|:-------------------------:|:-------------------------:
![](https://github.com/touchtalent/BobbleIMESDK/blob/dynamic_module/module1.png)  |  ![](https://github.com/touchtalent/BobbleIMESDK/blob/dynamic_module/module2.png)  |  ![](https://github.com/touchtalent/BobbleIMESDK/blob/dynamic_module/module3.png) 
### Step 2: Gradle setup
If you are using Gradle to build your Android applications, you can pull the latest version of the SDK from JCenter as described below:

 - Include JCenter in your top-level build.gradle file:

```java
allprojects {
    repositories {
        jcenter()
    }
}
```
- Add the following line to the dependencies element in your **base application module’s** build.gradle.

```java
implementation 'com.touchtalent.bobbleime-dynamic-support:1.0.0'
```
- Add the following line to the dependencies element in your **dynamic module’s** build.gradle.

```java
implementation 'com.touchtalent.bobbleime-dynamic:1.0.0'
```

- Sync your Gradle project to ensure that the dependency is downloaded by the build system.


### Step 3: Adding Permissions
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
    
    
### Step 4: Manifest Changes
The client needs to register the custom IME class in manifest of base module as InputMethod service.

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

### Step 5: Other Build Settings
- Add option to not compress dictionary files by following lines in the android block of your dynamic module's gradle
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

### Step 6: Install SplitCompat to support dynamic modules

Inside onAttachBaseContext() method of your Application class, install SplitCompat in your app by calling
```java
SplitCompat.install(context); 
```

### Step 7: Initialise SDK

Inside onCreate() method of your Application class, initialise the SDK by calling
```java
BobbleIMESDK.initialise(applicationContext, <LICENSE_KEY>, <module-name>) 
```

**\<module-name>** is the name of module that you provide in step 1(**ime_dynamic_module** as per the example).

If you don't have a Licence Key for your host app, you need to request one. Please note that Licence Key do not superimpose any expiration date, but each Licence Key is bounded to host app package name.

### Step 8: Create your custom IME Class
Last step would be to create the custom class declared in the manifest above.
```java
import com.bobblekeyboard.ime.BobbleIME;

class CustomIME extends BobbleIME {
    @Override
    public void onCreate() {
        super.onCreate();
    }
    
    /*
     * Return a default view to be shown in place of keyboard  in-case the keyboard module hasn't yet been installed.
     */
    @Override
    public View onCreateDefaultView(){
        View view = LayoutInflater.from(this).inflate(R.layout.module_not_installed);
    	view.setOnClickListener(v->{
    		BobbleIMESDK.install(this,null);
	});
	return view;
    }
}
```

### API Reference
The BobbleIMESDK class comes with utility functions that can help you check, install and uninstall the module.

#### Check if module is installed or not
```java
boolean isModuleDownloaded = BobbleIMESDK.isDownloaded();
```

#### Initiate installation of module
```java
BobbleIMESDK.install(context, new InstallCompleteListener(){
	
	@Override
	public void onSuccess(){
	
	}
	
	@Override
	public  void onFailure(){
	
	}
});
```

#### Uninstall the module to free up space
```java
BobbleIMESDK.uninstall(context);
```

