# SmartShareActionProvider

A smart ShareActionProvider that allows a personalization of the intent depending on the app selected by the user.

This version of the library has one dependency : `com.android.support:appcompat-v7:21.0.3`. 

This version of the library requires Android v7+ to work.

![Sample app screenshot](https://raw.github.com/ludovicroland/smartShareActionProvider-android/master/screenshot1.png)

## Usage

For a full example see the `sample` app in the
[repository](https://github.com/ludovicroland/smartShareActionProvider-android/tree/master/sample).

### From Maven Central

Library releases are available on Maven Central

**Gradle**

```groovy
compile 'fr.rolandl:smartshareactionprovider:1.0.0@aar'
```

**Maven**

```xml
<dependency>
  <groupId>fr.rolandl</groupId>
  <artifactId>smartshareactionprovider</artifactId>
  <version>1.0.0</version>
  <type>aar</type>
</dependency>
```

### As Library Project

Alternatively, check out this repository and add it as a library project.

```console
$ git clone https://github.com/ludovicroland/smartShareActionProvider-android.git
```

Import the project into your favorite IDE and add
`android.library.reference.1=/path/to/smartShareActionProvider-android/library` to your
`project.properties`.

### In an Activity or Fragment

The code has to be written into the `onCreateOptionsMenu` method.

#### Step 1 : Create an instance of a `SmartShareActionProvider`

You can use the default share icon provided by the library. You can choose the white one or the black one using the enumeration `ShareIcon.White` or `ShareIcon.Black`.

```java
final SmartShareActionProvider provider = new SmartShareActionProvider(this, ShareIcon.White);
```

Alternatively you can use your own drawable as an icon :

```java
final SmartShareActionProvider provider = new SmartShareActionProvider(this, getResources().getDrawable(R.drawable.ic_launcher));
```

#### Step 2 : Create an intent to share and set it to the `SmartShareActionProvider`

You can now create an intent with default content and set it to your `SmartShareActionProvider` instance :

```java
final Intent intent = new Intent(Intent.ACTION_SEND).setType("text/plain").putExtra(Intent.EXTRA_TEXT, "Hi !");
provider.setShareIntent(intent);
```

#### Step 3 : Create your menu item and set the `ActionProvider`

You can now create dynamically you `MenuItem`, configure it and set your `SmartShareActionProvider` instance as an `ActionProvider` :

```java
final MenuItem item = menu.add(Menu.NONE, Menu.NONE, Menu.NONE, "share");
MenuItemCompat.setActionProvider(item, provider);
MenuItemCompat.setShowAsAction(item, MenuItemCompat.SHOW_AS_ACTION_ALWAYS);
```

#### Step 4 : Set an `OnShareTargetSelectedListener` to the provider

You can now set an `OnShareTargetSelectedListener` to your `SmartShareActionProvider` instance and implement the `onShareTargetSelected` method :

```java
provider.setOnShareTargetSelectedListener(new OnShareTargetSelectedListener()
{

  @Override
  public boolean onShareTargetSelected(SmartShareActionProvider source, Intent intent)
  {
    return false;
  }

});
```

#### Step 5 : Retrieve the app choosed by the user

Into the `onShareTargetSelected` method, you are now able to retrieve the package name of the app choosed by the user to share the content :

```java
final String app = intent.getComponent().getPackageName();
```

#### Step 6 : Change the intent content

You are now able to customize the intent content depending on the app selected by the user : 

```java
if ("com.google.android.gm".equals(app) == true)
{
  intent.setType("message/rfc822");
  intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
  intent.putExtra(Intent.EXTRA_TEXT, "gmail");
}
else if ("com.google.android.keep".equals(app) == true)
{
  intent.putExtra(Intent.EXTRA_TEXT, "keep");
}
```

## License

```
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

## Changelog

### v1.0.0

* First release
