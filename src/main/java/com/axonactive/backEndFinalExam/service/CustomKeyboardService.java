package com.axonactive.backEndFinalExam.service;

import com.axonactive.backEndFinalExam.api.request.CustomKeyboardRequest;
import com.axonactive.backEndFinalExam.entity.CustomKeyboard;
import com.axonactive.backEndFinalExam.service.dto.CustomKeyboardDto;

import java.util.List;
import java.util.Optional;

public interface CustomKeyboardService {
    List<CustomKeyboard> getAll();

    Optional<CustomKeyboard> findById(Integer id);

    CustomKeyboard save(CustomKeyboard caseInput);

    void deleteById(Integer id);

    CustomKeyboardDto buildCustomKeyboard(CustomKeyboardRequest customKeyboardRequest);
}
