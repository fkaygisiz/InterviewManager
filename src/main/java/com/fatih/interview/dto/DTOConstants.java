package com.fatih.interview.dto;

public class DTOConstants {
	public static final String DATE_FORMAT_REGEXP_MESSAGE = "Date should be in yyyy-MM-dd format";

	public static final String TIME_SLOT_REGEXP_MESSAGE = "Should be in hh:mm format(eg: 15:30). mm can only be 00 or 30";

	public static final String TIME_SLOT_REGEXP = "^([01]\\d|2[0-3]):([0-5][0-5])$";

	public static final String DATE_FORMAT_REGEXP = "^\\d{4}\\-([0][0-9]|[1][012])\\-([012][0-9]|3[01])$";
}
