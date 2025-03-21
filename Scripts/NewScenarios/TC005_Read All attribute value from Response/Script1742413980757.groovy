import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

response = WS.sendRequest(findTestObject('OR Soap Request/CountryInfoServiceSoapBinding/ListOfContinentsByName'))

WS.verifyResponseStatusCode(response, 200)

def count = WS.getElementsCount(response, 'ListOfContinentsByNameResponse.ListOfContinentsByNameResult.tContinent.sCode')

KeywordUtil.logInfo('Total Count of elements is: ' + count)


for (int i = 0; i < count; i++) {
    tContinenCode = WS.getElementText(response, ('ListOfContinentsByNameResponse.ListOfContinentsByNameResult.tContinent[' + i) + '].sCode')
	
	KeywordUtil.logInfo('Continent Code: ' + tContinenCode)

    tContinenName = WS.getElementText(response, ('ListOfContinentsByNameResponse.ListOfContinentsByNameResult.tContinent[' + i) + '].sName')
	
	KeywordUtil.logInfo('Continent Name: ' + tContinenName)

}