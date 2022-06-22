package com.axonactive.backEndFinalExam.api.customer;

import com.axonactive.backEndFinalExam.api.request.CustomKeyboardRequest;
import com.axonactive.backEndFinalExam.service.*;
import com.axonactive.backEndFinalExam.service.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/customer")
@RestController
@PreAuthorize("hasRole('CUSTOMER')")
public class CustomerResource {
    @Autowired
    private SwitchBatchService switchBatchService;
    @Autowired
    private KitBatchService kitBatchService;
    @Autowired
    private KeyBoardBatchService keyBoardBatchService;
    @Autowired
    private KeycapSetService  keycapSetService;
    @Autowired
    private CustomKeyboardService customKeyboardService;

    @GetMapping("/switch/{name}")
    public ResponseEntity<SwitchDto> checkAvailableStockForSwitch(@PathVariable ("name") String name){
    return ResponseEntity.ok(switchBatchService.findSwitchForCustomer(name));
    }
    @GetMapping("/kit/{name}/{color}")
    public ResponseEntity<KitDto> checkAvailableStockForKit(@PathVariable ("name") String name,
                                                                 @PathVariable ("color") String color){
        return ResponseEntity.ok(kitBatchService.findKitForCustomer(name,color));
    }
    @GetMapping("/keyboard/{name}")
    public ResponseEntity<KeyboardBatchDto> checkAvailableStockForKit(@PathVariable ("name") String name){
        return ResponseEntity.ok(keyBoardBatchService.checkStockAvailability(name));
    }
    @GetMapping("/keycap/{name}")
    public ResponseEntity<KeyCapSetDto> checkAvailableStockForKeycap(@PathVariable ("name") String name){
        return ResponseEntity.ok(keycapSetService.getAllInStock(name));
    }
    @PostMapping("/custom")
    public ResponseEntity<CustomKeyboardDto> buildKeyboard(@RequestBody CustomKeyboardRequest  customKeyboardRequest){
        return ResponseEntity.ok(customKeyboardService.buildCustomKeyboard(customKeyboardRequest));
    }
}
