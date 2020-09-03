## **BOBBLE IME SDK**
This guide is for all app developers who wish to add full fledged keyboard functionality in their Android apps using the Bobble IME (Input Method Editor) SDK. Before you begin integrating the SDK into your app, please email us at android.master@bobble.ai to get the license key in order to avail complete functionalities of the SDK.

Note - Minimum version of supported Android platform is SDK level 21

## **Methods to Integrate?**
To make it tailored for every application and user specific needs, you can integrate Bobble IME SDK in your application in two ways. 

### **Option 1: Implementing And Packaging full SDK with the App:**
In case, you wish to integrate via traditional method which packs the full SDK with your application, please refer to [Bobble IME Distribution Library](https://github.com/touchtalent/BobbleIMESDK/blob/master/Readme_Gradle.md). 

* The SDK is bundled inside the app, and the keyboard gets registered in the system at the time of APK installation itself.
* SDK Size would be approximately 5MB.
 
### **Option 2: Implementing The SDK By Packaging it in a Dynamic Module**
In case, you wish to integrate via dynamic module method which downloads the SDK at runtime, please refer to [Bobble Dynamic IME Distribution Library](https://github.com/touchtalent/BobbleIMESDK/blob/master/Readme_Dynamic.md).

* Dynamic Module Distribution allows user to download SDK as and when he actually needs it. Once downloaded, it functions like a normal SDK.
* It results in minimal increase in the APK Download size; by around 200KB.
* This method is applicable only if you plan to distribute your application as an Android Bundle via Google Playstore.
 
