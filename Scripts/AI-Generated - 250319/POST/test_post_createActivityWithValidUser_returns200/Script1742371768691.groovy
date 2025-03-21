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

def user_payload = [
    "userName": "test_user__unique__"
]

def activity_payload = [
    "id": 1,
    "title": "Test Activity",
    "dueDate": "2022-12-31T23:59:59",
    "completed": false,
    "userName": user_payload["userName"]
]

def userRequest = findTestObject("Object Repository/FakeRESTApi_Web V1/_api_v1_Users/POST")
addHeaderConfiguration(userRequest)
userRequest.setBodyContent(new HttpTextBodyContent(replaceSuffixWithUUID(JsonOutput.toJson(user_payload))))
def userResponse = WSBuiltInKeywords.sendRequest(userRequest)
WSBuiltInKeywords.verifyResponseStatusCode(userResponse, 200)

def activityRequest = findTestObject("Object Repository/FakeRESTApi_Web V1/_api_v1_Activities/POST")
addHeaderConfiguration(activityRequest)
activityRequest.setBodyContent(new HttpTextBodyContent(replaceSuffixWithUUID(JsonOutput.toJson(activity_payload))))
def activityResponse = WSBuiltInKeywords.sendRequest(activityRequest)
WSBuiltInKeywords.verifyResponseStatusCode(activityResponse, 200)

def replaceSuffixWithUUID(payload) {
    replacedString = payload.replaceAll('unique__', uuid)
    return replacedString
}

