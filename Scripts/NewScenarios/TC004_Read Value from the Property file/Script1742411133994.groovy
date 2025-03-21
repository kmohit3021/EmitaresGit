import com.kms.katalon.core.util.KeywordUtil

remoteWebDriverType = CustomKeywords.'com.katalon.emirates.PropertyFile.readValuePropertyFile'('remoteWebDriverType')

KeywordUtil.logInfo('Value: ' + remoteWebDriverType)

CustomKeywords.'com.katalon.emirates.PropertyFile.writeValuePropertyFile'('name', 'Mohit')

