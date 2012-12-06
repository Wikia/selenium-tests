package com.wikia.webdriver.Common.TestNGListeners;

import java.util.List;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.internal.InvokedMethod;
import org.testng.internal.invokers.InvokedMethodListenerInvoker;
import org.testng.internal.invokers.InvokedMethodListenerMethod;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Core.CommonFunctions;


public class InvokeMethodAdapter implements IInvokedMethodListener {

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult result) 
	{ 
		//Reporter.setCurrentTestResult(result); 
		if (method.isTestMethod()) 
		{
			List verificationFailures = Assertion.getVerificationFailures(result);
			//if there are verification failures...
			if(verificationFailures.size() > 0) 
			{ 
				//set the test to failed
				result.setStatus(ITestResult.FAILURE);
				int size = verificationFailures.size();				
			}
		}
	}

	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		// TODO Auto-generated method stub
		
	}
}
