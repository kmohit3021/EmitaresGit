import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

def url = CustomKeywords.'com.katalon.emirates.Json.readValue'(RunConfiguration.getProjectDir()+'/TestFiles/remote_caps.json', 
    'remoteWebDriverUrl')

KeywordUtil.logInfo(url)

def automationName = CustomKeywords.'com.katalon.emirates.Json.readValue'(RunConfiguration.getProjectDir()+'/TestFiles/remote_caps.json', 
    'appium:automationName')

KeywordUtil.logInfo(automationName)

CustomKeywords.'com.katalon.emirates.Json.writeValue'(RunConfiguration.getProjectDir()+'/TestFiles/katalon.json', 'project', 
    'KatalonTest')