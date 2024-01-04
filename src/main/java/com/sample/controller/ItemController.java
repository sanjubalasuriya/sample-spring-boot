package com.sample.controller;

import com.sample.dto.CustomerDTO;
import com.sample.dto.ItemDTO;
import com.sample.dto.paginated.ResponseCustomerPaginatedDTO;
import com.sample.dto.paginated.ResponseItemPaginatedDTO;
import com.sample.dto.request.RequestCustomerDTO;
import com.sample.dto.request.RequestItemDTO;
import com.sample.dto.response.ResponseCustomerDTO;
import com.sample.dto.response.ResponseItemDTO;
import com.sample.service.CustomerService;
import com.sample.service.ItemService;
import com.sample.util.StandardResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/item")
@AllArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping("/save")
    public ResponseEntity<StandardResponse> saveItem(@RequestBody RequestItemDTO requestItemDTO) {

        String message = itemService.saveItem(requestItemDTO);

        return new ResponseEntity<StandardResponse>(new StandardResponse(201, "success", message)
                , HttpStatus.CREATED);
    }

    @GetMapping(
            path = "/get-by-id",
            params = "itemId"
    )
    public ResponseEntity<StandardResponse> getItemById(long itemId) {

        ResponseItemDTO responseItemDTO = itemService.getItemById(itemId);
        return new ResponseEntity<StandardResponse>(new StandardResponse(200, "success", responseItemDTO)
                , HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<StandardResponse> updateItem(@RequestBody ItemDTO itemDTO) {
        String message = itemService.updateItem(itemDTO);
        return new ResponseEntity<StandardResponse>(new StandardResponse(200, "success", message)
                , HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<StandardResponse> getAllItems() {
        List<ResponseItemDTO> allCustomers = itemService.getAllItems();
        return new ResponseEntity<StandardResponse>(new StandardResponse(200, "success", allCustomers)
                , HttpStatus.OK);
    }

    @GetMapping(
            path = "/paginate-all",
            params = {"page", "size"}
    )
    public ResponseEntity<StandardResponse> getAllItemsPaginate(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size
    ) {
        ResponseItemPaginatedDTO responseItemPaginatedDTO = itemService.getAllItemsPaginate(page, size);
//        List<CustomerDTO> allCustomers = customerService.getAllCustomersPaginate(page, size);
        return new ResponseEntity<StandardResponse>(new StandardResponse(200, "success", responseItemPaginatedDTO)
                , HttpStatus.OK);
    }
}
