import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.impl.HttpTextBodyContent
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import groovy.json.JsonSlurper
import groovy.json.JsonOutput

def addHeaderConfiguration(request) {
    def content_type_header = new TestObjectProperty("content-type", ConditionType.EQUALS, "application/json")
    request.getHttpHeaderProperties().add(content_type_header)
}

uuid = UUID.randomUUID().toString()

def userPayload = '{"userName": "test_user__unique__"}'
def userRequest = findTestObject('Object Repository/FakeRESTApi_Web V1/_api_v1_Users/POST')
def userPayloadContent = new HttpTextBodyContent(replaceSuffixWithUUID(userPayload))
userRequest.setBodyContent(userPayloadContent)
addHeaderConfiguration(userRequest)
def userResponse = WSBuiltInKeywords.sendRequest(userRequest)
WSBuiltInKeywords.verifyResponseStatusCode(userResponse, 200)

def activityPayload = '{"id": 1, "title": "Test Activity", "dueDate": "2022-12-31T23:59:59Z", "completed": false, "userName": ""}'
def activityRequest = findTestObject('Object Repository/FakeRESTApi_Web V1/_api_v1_Activities/POST')
def activityPayloadContent = new HttpTextBodyContent(replaceSuffixWithUUID(activityPayload))
activityRequest.setBodyContent(activityPayloadContent)
addHeaderConfiguration(activityRequest)
def activityResponse = WSBuiltInKeywords.sendRequest(activityRequest)
WSBuiltInKeywords.verifyResponseStatusCode(activityResponse, 200)

println(activityResponse.getStatusCode())

def replaceSuffixWithUUID(payload) {
    replacedString = payload.replaceAll('unique__', uuid)
    return replacedString
}

