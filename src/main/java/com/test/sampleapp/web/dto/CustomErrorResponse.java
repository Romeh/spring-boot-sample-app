package com.test.sampleapp.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.io.Serializable;

/**
 * Necessary for proper Swagger documentation.
 *
 * @author romih
 */
@SuppressWarnings("unused")
@AllArgsConstructor
@Getter
public class CustomErrorResponse implements Serializable {

    private static final long serialVersionUID = -7755563009111273632L;

    private String errorCode;

    private String errorMessage;

}
