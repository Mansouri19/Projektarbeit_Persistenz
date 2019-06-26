package kunden.lib;

public abstract class StringCommonLib {

	public static String getLeftPaddedString(String strValue, char padChar, int strLen) {
		StringBuffer retVal = new StringBuffer(((strValue != null) ? strValue : ""));

		while (retVal.length() < strLen) {
// Todo: JUnit-Test mit padChar == null ("str",((char)0),10) / ("str",null,10)
			
			retVal = new StringBuffer().append(padChar).append(retVal);
		}

		return retVal.toString();
	}
}
