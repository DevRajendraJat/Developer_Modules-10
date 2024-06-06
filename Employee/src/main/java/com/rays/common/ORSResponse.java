package com.rays.common;

import java.util.HashMap;

public class ORSResponse {

	public static final String INPUT_ERROR = "inputError";

	public static final String MESSAGE = "message";

	public static final String DATA = "data";

	public boolean Success;

	public HashMap<String, Object> result = new HashMap<String, Object>();

	public boolean isSuccess() {
		return Success;
	}

	public void setSuccess(boolean success) {
		Success = success;
	}

	public HashMap<String, Object> getResult() {
		return result;
	}

	public void setResult(HashMap<String, Object> result) {
		this.result = result;
	}

	public void addInputError(Object Value) {
		result.put(INPUT_ERROR, Value);
	}

	public void addMessage(Object value) {
		result.put(DATA, value);
	}

	public void addData(Object value) {
		result.put(DATA, value);
	}

	public void addResult(String key, Object Value) {
		result.put(key, Value);
	}

	public ORSResponse() {

	}

	public ORSResponse(boolean success) {
		this.Success = success;
	}
}
