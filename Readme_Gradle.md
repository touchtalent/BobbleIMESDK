
  
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
    implementation 'com.touchtalent.bobblekeyboard:keyboard:1.0.0'
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
Create a custom class declared in the manifest above.
Override the onQuickAccessIconTap() function to handle the clicks on branded icon on the keyboard.
```java
import com.touchtalent.bobbleime.BobbleIME;

class CustomIME extends BobbleIME {
    @Override
    public void onQuickAccessIconTap() {
        Intent intent = new Intent(this, DeepLinkActivity.class);
        intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
```

### Step 7: Initiate keyboard enable and selection
Last step would be to initiate keyboard enabling flow. Create a new activity by extending ```BobbleEnablerActivity``` which would act as a entry point for the complete enabling flow. Use following functions and callbacks to start/track progress.

1. ```getStatus()``` - To know the current status and prepare the UI accordingly.
2. ```startActivationFlow()``` - Calling this will evaluate the current status and initiate the enabling flow accordingly.
3. ```onStatusChange(BobbleIMESDK.IMEInstallStatus status)``` - This callback is received when the enabling flow is complete. ```status``` is one of the following : 
 - SELECTED : User enabled and selected the keyboard as well.
 - ENABLED : User just enabled the keyboard but did not select it as the default one.
 - NONE : User did not do anything and returned from the settings pages. This can be used to initiate any client side flow for educating users on how to enable it.
 4. ```onUserSetup()``` - The user has succesfully been onboarded. At this point, consider redirecting user to the home page of your application.

The following flowchart shows the enabling process and the state in which each callback is fired:

![Flowchart](flowchart.png)

 P.S - The activity extending ```BobbleEnablerActivity``` may restart during the enabling flow because of system limitations. Care has to be taken to maintain activity state. If user presses back on this screen, it is possible that the system's IME chooser activity opens instead of the parent activity. To prevent this and maintain the navigation consider adding this in your activity :

 ```java
  @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(this, <parent_activity>.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }
```

### APIs


#### Check if keyboard is enabled
```java
boolean BobbleIMESDK.isKeyboardEnabled()
```
This API can be used to check if the keyboard is enabled inside the global input method settings.

#### Check if keyboard is selected
```java
boolean BobbleIMESDK.isKeyboardSelected()
```
This API can be used to check if the keyboard is the current selected input method editor in the system.

