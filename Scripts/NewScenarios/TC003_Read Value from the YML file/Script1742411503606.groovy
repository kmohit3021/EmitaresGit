import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.util.KeywordUtil

String filePath = RunConfiguration.getProjectDir()+'/TestFiles/pipel.yml' // Replace with actual file path

String keyToRetrieve = 'schedules.displayName' // Example: Nested key in YAML

//schedules.displayName

//pool.vmImage

Object result = CustomKeywords.'com.katalon.emirates.YamlReader.readValueFromYaml'(filePath, keyToRetrieve)


KeywordUtil.logInfo('Value: ' + result)



