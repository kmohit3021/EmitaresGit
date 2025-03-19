import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

res = WS.sendRequest(findTestObject('Object Repository/HTML Response/Rest/Google Search'))

status = WS.getResponseStatusCode(res)

println(status)

