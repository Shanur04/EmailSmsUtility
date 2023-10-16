package gov.cdac.utils;

import java.util.ArrayList;

import gov.cdac.exception.DataNotFoundException;


public class CheckDataAccuracyUtil {

	public static void checkForDataAccuracy(ArrayList<?> data, ArrayList<?> dataFound) throws DataNotFoundException {
		if (data.size() != dataFound.size()) {

			data.removeAll(dataFound);
			throw new DataNotFoundException("These Email Ids:" + data.toString() + " do not exist! ");

		}

		return;
	}

}
