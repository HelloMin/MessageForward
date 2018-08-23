# MessageForward

Android 运行方式

$ lsof -i :8081

Method 2: Connect via Wi-Fi
You can also connect to the development server over Wi-Fi. You'll first need to install the app on your device using a USB cable, but once that has been done you can debug wirelessly by following these instructions. You'll need your development machine's current IP address before proceeding.

You can find the IP address in System Preferences → Network.

Make sure your laptop and your phone are on the same Wi-Fi network.
Open your React Native app on your device.
You'll see a red screen with an error. This is OK. The following steps will fix that.
Open the in-app Developer menu.
Go to Dev Settings → Debug server host & port for device.
Type in your machine's IP address and the port of the local dev server (e.g. 10.0.1.1:8081).
Go back to the Developer menu and select Reload JS.
You can now enable Live reloading from the Developer menu. Your app will reload whenever your JavaScript code has changed.
