# Selenium Tests
This repository contains [Selenium](http://seleniumhq.org/) tests for the [Wikia App](https://github.com/Wikia/app).

## Dependencies

1. Make sure you have [Java](http://www.java.com/) installed on your system, if not follow the vendor instructions for installing them on your operating system.
2. Enable [LOMBOK](https://projectlombok.org/) plugin on your IDE, follow the vendor instructions for installing LOMBOK for your IDE.
  * For eclipse follow [this instruction](https://projectlombok.org/download.html)
  * For IntelliJ Idea:
    * Windows: click Settings -> Plugins -> Browse repositories for "lombok" -> Install. Restart IntelliJ.
    * OSX: click IntelliJ IDEA -> Preferences -> Plugins -> Browse repositories for "lombok" -> Install. Restart Intellij.

3. Go to "Annotation Processors"
    * Windows: click Settings -> Build, Execution, Deployment -> Annotation Processors. Set "Enable annotation processing".
    * OSX: click Intellij IDEA -> Preferences -> Build, Execution, Deployment -> Annotation Processors. Set "Enable annotation processing".

4. Install IDE configurations from [Wikia's Java Coding Guidelines](https://github.com/Wikia/guidelines/tree/master/Java)

## Adding Tests
Please remember to keep this repository up to date as the tests become worthless otherwise. A good rule of thumb is to add a new test that covers whatever bug you're fixing so we can track regressions on it. Also, make sure to code your tests in a cross-operating system compliant manner as it's valid to run them in Windows, Mac or Linux environments.

## Running Tests

The following steps should get you set up for running Selenium tests locally on your machine:

1. Clone this repository to your local machine (not your devbox).
2. Clone selenium-config repository to your local machine.
3. Copy `config_default.yml` file and name it `config.yml` (this will be your local config)
4. Modify `credentialsPath` property in `config.yml` to point to `credentials.xml` file from cloned `selenium-config` repository
5. In selenium-tests repository run gradle wrapper:
```
    ./gradlew test -Dbrowser=<browser> -Denv=<environment> -Dgroups=<group>
```
It is also possible to run mobile tests on real devices (Currently only Android is supported). In case of running tests on real devices additional parameters are supported:

1. -Dplatform - determine if IOS or ANDROID should be used
2. -Dplatform-version - get device's uuid from mobile config for provided platform version
3. -Ddevice-id - get device with provided uuid

If both platform-version and device-id are provided device with provided uuid would be chosen.

### Example commands

All commands must be run from the `selenium-tests` directory cloned during setup process above

##### Running tests on preview for mediawiki119 wikia

    ./gradlew test -Denv=preview -Dgroups=Login

##### Running tests on production for mediawiki119 wikia

    ./gradlew test -Denv=prod -Dgroups=Login

##### Running tests on devbox for muppet wikia

    ./gradlew test -Dbrowser=CHROME -Denv=<devbox-name> -Dwiki-name=muppet -Dgroups=Login

##### Running tests on local instance of Mercury (which fetches data from devbox) for muppet wikia on a Chrome pretending to be an Android device forcing Mercury skin

    ./gradlew test -Dbrowser=CHROMEMOBILEMERCURY -Denv=dev-karol -Dwiki-name=muppet -Dgroups=Login

If everything goes right it should log in as a QATestsUser.

##### Running tests on real device

    ./gradlew test -Dplatform=ANDROID -Ddevice-id=0243e69a8ec7e8de -Dgroups=MobileLogin

    ./gradlew test -Dplatform=ANDROID -Dplatform-version=4.4 -Dgroups=MobileLogin

### Parameters

The following are valid test parameters:

* `-Dbrowser` - Which browser to use, for example "CHROME"
* `-Denv` - The environment on which the test(s) should be run in, for example `prod`, `preview`, `dev-<name>`, `sandbox-<number>`,
* `-Dwiki-name` - The wiki where the test(s) should be run on, for example "mediawiki119", "muppet"
* `-Dgroups` - (Optional) Which test groups to run, for example "Chat". Optional. Uses all tests if omitted
* `-Ddisable-flash` - (Optional) Disable Flash plugin, any String value = true

### Browsers

The following are valid for use in the `-Dbrowser` parameter:

* FF
* CHROME
* CHROMEMOBILEMERCURY
* IE
* GHOST (phantomjs)

### Groups

Whole group of tests can be run with `-Dgroups` parameter. Groups are declared as annotation `@group()` above the tests.

## Reading Logs


Most tests write logs and take screenshots while they run which provide further information about what happened if they failed. These files reside in the `./logs` directory and can be viewed locally in your browser by navigating to that folder.

