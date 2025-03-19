package com.katalon.emirates

import com.kms.katalon.core.annotation.Keyword

import org.jsoup.Jsoup
import groovy.xml.XmlSlurper

public class HTMLReponseExtractor {


	@Keyword
	def extractTitleAndCheckDoctype(String content, String attribute) {
		content = content.trim()  // Trim whitespace
		boolean hasDoctype = content.startsWith("<!DOCTYPE html>")  // Check for DOCTYPE

		String title = ""
		try {
			if (hasDoctype || content.contains("<html")) {
				// Process as HTML using Jsoup
				def doc = Jsoup.parse(content)
				title = doc.title()
			} else {
				// Process as XML using XmlSlurper
				def xml = new XmlSlurper().parseText(content)
				title = xml.head.attribute.text()
			}
		} catch (Exception e) {
			println "Error parsing content: ${e.message}"
		}

		//return [title: title, hasDoctype: hasDoctype]
		return title
	}
}