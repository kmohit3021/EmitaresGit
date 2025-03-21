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

def activityPayload = '''
{
    "id": 1,
    "title": "Activity Title",
    "dueDate": "2022-12-31T23:59:59",
    "completed": false,
    "userName": "non_existing_user__unique__"
}
'''

def postActivityRequest = findTestObject("Object Repository/FakeRESTApi_Web V1/_api_v1_Activities/POST")
def bodyContent = new HttpTextBodyContent(replaceSuffixWithUUID(activityPayload))
postActivityRequest.setBodyContent(bodyContent)
addHeaderConfiguration(postActivityRequest)

def postActivityResponse = WSBuiltInKeywords.sendRequest(postActivityRequest)
WSBuiltInKeywords.verifyResponseStatusCode(postActivityResponse, 200)

println(postActivityResponse.getStatusCode())

def replaceSuffixWithUUID(payload) {
    replacedString = payload.replaceAll('unique__', uuid)
    return replacedString
}

