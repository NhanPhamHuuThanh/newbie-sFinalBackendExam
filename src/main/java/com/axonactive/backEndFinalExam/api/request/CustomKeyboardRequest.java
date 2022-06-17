package com.axonactive.backEndFinalExam.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomKeyboardRequest {
    private String switchName;

    private String kitModel;

    private String kitColor;

    private String keyCapSet;

}
