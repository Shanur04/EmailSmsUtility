package gov.cdac.utils;

import java.util.ArrayList;
import java.util.HashMap;

import gov.cdac.models.DeletionModel;


public class StructureDetails {
	
	
	/**
	 * @author ketant
	 *         <p>
	 *         This method will structure details of centre and its slots
	 *         </p>
	 * @param tempList
	 * @return
	 */
	public static HashMap<Integer, DeletionModel> getOrganizedCentreSlotDetails(ArrayList<DeletionModel> tempList) {

		HashMap<Integer, DeletionModel> organizedMap = new HashMap<>();
		for (DeletionModel model : tempList) {
			if (organizedMap.containsKey(model.getCentreId())) {
				/**
				 * if map already has entry of this object then we need only to update the slot
				 * details
				 */
				organizedMap.get(model.getCentreId()).getSlotDetails().put(model.getSlotId(), model.getSlotName());
			} else {
				/**
				 * if map do not have this model then we need to update the map
				 */
				organizedMap.put(model.getCentreId(), new DeletionModel(model.getCentreId(), model.getCentreName(),model.getCenterCode()));
				organizedMap.get(model.getCentreId()).getSlotDetails().put(model.getSlotId(), model.getSlotName());
			}

		}

		return organizedMap;
	}


}
