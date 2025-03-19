import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.apache.commons.io.FileUtils as FileUtils
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

response = WS.sendRequest(findTestObject('OR Schema/XML'))

String personWrong = FileUtils.readFileToString(new File('Example/xml/personWrong.xsd'))

String personRight = FileUtils.readFileToString(new File('Example/xml/person.xsd'))

WS.validateXmlAgainstSchema(response, personWrong, FailureHandling.CONTINUE_ON_FAILURE)

WS.validateXmlAgainstSchema(response, personRight, FailureHandling.CONTINUE_ON_FAILURE)

