package com.netcracker.edu.api.controller.util;

import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.*;

public class TestValidationUtility {
    public static void validateResponse(MockHttpServletResponse actualResponse, String expectedResponse) throws UnsupportedEncodingException, JSONException {
        validate(actualResponse);
        if (expectedResponse != null) {
            validateEquals(expectedResponse, actualResponse.getContentAsString());
        }
    }

    private static void validate(MockHttpServletResponse response) throws UnsupportedEncodingException {
        assertNull(response.getErrorMessage());
        assertNotNull(response.getContentAsString());
        assertFalse(response.getContentAsString().isEmpty());
    }

    private static void validateEquals(String jsonReq, String jsonResp) throws JSONException {
        JSONAssert.assertEquals(jsonReq, jsonResp, JSONCompareMode.LENIENT);
    }
}