## **BOBBLE IME SDK**
Bobble IME SDK is an Android Library which allows you to integrate power of BobbleKeyboard in your android application with minimal effort. The keyboard if fully featured Input Method Editor having support for various languages, Live Themes, Cool Fonts, Emoji, Clipboard, Bigmauji, Quick Reply Shortcuts, Swipe typing etc.

## **WHY USE IME?**
* There is one common target of all the apps which is provide excellent user experience. we tend to do lot of things to work towards user experience, however we neglect one thing, which is used in almost all the apps which is input method or keyboard. no matter how hard we strive to improve the user experience, if the way by which the user is entering the data in the app is differing then app experience will differ, so the IME SDK provides consistant user experience
* It is more secure, as the data entered by the user strictly stays with you, and not captured by any third party IME provider
* You can provide your content and services at all the places in users phone without even opening the app.  

To make it tailored for every application and user specific needs, you can use embed Bobble IME SDK in your application in two ways. 

### **Implementing And Packaging full SDK with the App:**
This is a simple Method which packs the full SDK with your application, and it ready to use as soon as your application is downloaded.

* It will cause the slight download size increase(approax 5MBs) depending on how you distribute the Application
* The SDK is always available with your app, no matter how you distribute it.

Want to know more about this method, go to [Bobble IME Distribution Library](https://github.com/touchtalent/BobbleIMESDK/blob/dynamic_module/README.md)
### **Implementing The SDK By Packaging it in a Dynamic Module**
This Method allows you to use the power of Bobble Dynamic IME Distribution Library, which allows you to distribute the SDK as a Dynamic Module.

* It Results in the minimal increase in the APK Download size.
* Dynamic Module Distribution allows your user to download SDK as and when he actually needs it
* Once downloaded, it functions like the normal SDK, and is fully featured
* Bobble Dynamic IME Distribution Library takes care of the complex logic needed for the dynamic module to work.

Want to know more about this method, go to [Bobble Dynamic IME Distribution Library](https://github.com/rKDev20/BobbleIMESDK/blob/patch-1/README.md)
 
