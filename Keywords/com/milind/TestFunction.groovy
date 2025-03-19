package com.milind

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import internal.GlobalVariable


public class TestFunction {


	public static String loginSp = 'Object Repository/OR_DummyJSON - Milind/Login user and get token'
	public static String getAddressv1 = 'OR_DummyJSON/Get current auth user';


	def static loginUserGetToken() {
		def  AccessToken = WS.sendRequest(findTestObject(loginSp))
		WS.verifyResponseStatusCode(AccessToken, 200)
		def value = WS.getElementPropertyValue(AccessToken, 'accessToken')
		GlobalVariable.accessTokenSP = value

		def refreshToken = WS.getElementPropertyValue(AccessToken, 'refreshToken')
		println("Refresh Token: "+refreshToken)
	}


	def static getCurrentAuthUser() {
		println("Token: "+GlobalVariable.accessTokenSP)
		def  AccessToken = WS.sendRequest(findTestObject(getAddressv1))
		WS.verifyResponseStatusCode(AccessToken, 200)
	}
}
