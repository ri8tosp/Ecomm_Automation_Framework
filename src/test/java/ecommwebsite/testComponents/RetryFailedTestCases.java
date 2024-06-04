package ecommwebsite.testComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryFailedTestCases implements IRetryAnalyzer

{
	int count = 0;
	int maxTry = 1;

	@Override
	public boolean retry(ITestResult result)

	{

		if (count < maxTry) {
			count++;
			return true;
		}
		return false;
	}

}
