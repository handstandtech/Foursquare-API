package com.handstandtech.foursquare.v2;

import com.handstandtech.foursquare.shared.model.v2.FoursquareMeta;
public class FoursquareNot200Exception extends Exception {

	/**
	 * Default Serialization UID
	 */
	private static final long serialVersionUID = 1L;
	
	private FoursquareMeta meta;

	@SuppressWarnings("unused")
	private FoursquareNot200Exception() {
		super();
	}

	public FoursquareNot200Exception(FoursquareMeta meta) {
		this.setMeta(meta);
	}

	public void setMeta(FoursquareMeta meta) {
		this.meta = meta;
	}

	public FoursquareMeta getMeta() {
		return meta;
	}

}
