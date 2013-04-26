package com.polydriver.driver

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PreferredDriver {
    String[] value()
}

public interface Driver {
    final static String ANDROID = "ANDROID"
    final static String CHROME = "CHROME"
    final static String FIREFOX = "FIREFOX"
    final static String IE = "IE"
    final static String IOS = "IOS"
    final static String PHANTOM = "PHANTOM"
    final static String REMOTE = "REMOTE"
    final static String SAFARI = "SAFARI"

}
