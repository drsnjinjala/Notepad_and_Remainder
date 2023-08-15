package com.notepad.remainder.api;

import org.json.JSONArray;

public interface APIResponseArray {
	public void onSuccessArray(JSONArray array);
	public void onFailureArray(String error);
}
