import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

def url = CustomKeywords.'com.katalon.emirates.Json.readValue'('/Users/mohit/Katalon Studio/Emirates/TestFiles/remote_caps.json', 
    'remoteWebDriverUrl')

KeywordUtil.logInfo(url)

def automationName = CustomKeywords.'com.katalon.emirates.Json.readValue'('/Users/mohit/Katalon Studio/Emirates/TestFiles/remote_caps.json', 
    'appium:automationName')

KeywordUtil.logInfo(automationName)

CustomKeywords.'com.katalon.emirates.Json.writeValue'('/Users/mohit/Katalon Studio/Emirates/TestFiles/katalon.json', 'project', 
    'KatalonTest')