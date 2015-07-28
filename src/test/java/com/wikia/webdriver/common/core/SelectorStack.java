package com.wikia.webdriver.common.core;

import org.openqa.selenium.By;

import java.util.Stack;

/**
 * Created by Ludwik on 2015-07-28.
 */
public class SelectorStack {

    private static Stack<By> byStack = new Stack<>();

    public static void write(By selector){
        byStack.push(selector);
    }

    public static By read(){
        return byStack.pop();
    }
}
