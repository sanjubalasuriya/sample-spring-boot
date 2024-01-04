package com.sample.service;

import com.sample.dto.CustomerDTO;
import com.sample.dto.ItemDTO;
import com.sample.dto.paginated.ResponseItemPaginatedDTO;
import com.sample.dto.request.RequestItemDTO;
import com.sample.dto.response.ResponseItemDTO;

import java.util.List;

public interface ItemService {
    String saveItem(RequestItemDTO requestItemDTO);

    ResponseItemDTO getItemById(long itemId);

    String updateItem(ItemDTO itemDTO);

    List<ResponseItemDTO> getAllItems();

    ResponseItemPaginatedDTO getAllItemsPaginate(int page, int size);
}
