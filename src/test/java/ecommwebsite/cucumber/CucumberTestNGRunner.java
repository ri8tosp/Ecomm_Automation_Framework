package ecommwebsite.cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions (features="src/test/java/ecommwebsite/cucumber",
                  glue="ecommwebsite.stepDefinations", tags= "@EndtoEnd", monochrome=true, 
                  plugin= {"html:reports/cucumber.html"}

                 )
public class CucumberTestNGRunner extends AbstractTestNGCucumberTests

{
	

}
