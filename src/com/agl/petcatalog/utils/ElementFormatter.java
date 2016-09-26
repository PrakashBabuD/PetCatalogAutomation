package com.agl.petcatalog.utils;

import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ElementFormatter 
{
	private String elementName;
	private String elementValue;
	private String elementType;
	private String errorMessage;
	public static final String XPATH = "XPATH";
	public static final String CSS = "CSS";
	public static final String ID = "ID";
	public static final String CLASS = "CLASS";
	public static final String NAME = "NAME";
	public static final String LINKTEXT = "LINKTEXT";
	public static final String PARTIALLINKTEXT = "PARTIALLINKTEXT";
	public static final String TAGNAME = "TAGNAME";

	public ElementFormatter(final String elementName, final String elementValue, final String elementType) {
		super();
		this.errorMessage = "";
		this.elementName = elementName;
		this.elementValue = elementValue;
		this.elementType = elementType;
	}		    

	public String getelementName() {
		return this.elementName;
	}   

	public String getelementValue() {
		return this.elementValue;
	}    

	public String getelementType() {
		return this.elementType;
	}		    

	public String getErrorMessage() {
		return this.errorMessage;
	}		    

	public ElementFormatter format(final Object... substitutions) {
		String elementValue = this.elementValue;
		final Pattern typePattern = Pattern.compile("[=,]''[^\\]]*''");
		final Matcher typeMatcher = typePattern.matcher(elementValue);
		if (typeMatcher.find()) {
			elementValue = MessageFormat.format(elementValue, substitutions);
		}
		else {
			Pattern pattern = Pattern.compile("([{][0-9]+[}])");
			Matcher matcher = pattern.matcher(elementValue);
			int count = 0;
			while (matcher.find()) {
				++count;
			}
			for (int i = 0; i < count; ++i) {
				pattern = Pattern.compile("([{]" + i + "[}])");
				matcher = pattern.matcher(elementValue);
				elementValue = matcher.replaceAll(substitutions[i].toString());
			}
		}
		return this.fixXpath(elementValue);
	}		    

	public ElementFormatter format(final String substitution) {
		String elementValue = this.elementValue;
		final Pattern typePattern = Pattern.compile("[=,]''[^\\]]*''");
		final Matcher typeMatcher = typePattern.matcher(elementValue);
		if (typeMatcher.find()) {
			elementValue = MessageFormat.format(elementValue, substitution);
		}
		else {
			Pattern pattern = Pattern.compile("([{][0-9]+[}])");
			Matcher matcher = pattern.matcher(elementValue);
			if (matcher.find()) {
				pattern = Pattern.compile("([{]0[}])");
				matcher = pattern.matcher(elementValue);
				elementValue = matcher.replaceAll(substitution.toString());
			}
		}
		return this.fixXpath(elementValue);
	}		    

	private ElementFormatter fixXpath(String elementValue) {
		Pattern replacePattern = Pattern.compile("[=,]'[^']*(['][\\w\\s!@#$%^&*-;:.\342\u201E\242/]*)+'");
		final Matcher replaceMatcher = replacePattern.matcher(elementValue);
		while (replaceMatcher.find()) {
			String matchValue = replaceMatcher.group();
			matchValue = matchValue.replace("='", "=\"");
			matchValue = matchValue.replace(",'", ",\"");
			matchValue = matchValue.substring(0, matchValue.length() - 1) + "\"";
			elementValue = elementValue.replace(replaceMatcher.group(), matchValue);
		}
		// elementValue=elementValue.replace("\"", "'");
		return new ElementFormatter(this.elementName, elementValue, this.elementType);
	}		    

	public ElementFormatter replace(final String findString, final String replaceString) {
		return new ElementFormatter(this.elementName, this.elementValue.replaceFirst(findString, replaceString), this.elementType);
	}

}

