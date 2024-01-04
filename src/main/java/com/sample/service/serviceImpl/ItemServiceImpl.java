package com.sample.service.serviceImpl;

import com.sample.dto.CustomerDTO;
import com.sample.dto.ItemDTO;
import com.sample.dto.paginated.ResponseItemPaginatedDTO;
import com.sample.dto.request.RequestItemDTO;
import com.sample.dto.response.ResponseItemDTO;
import com.sample.entity.Customer;
import com.sample.entity.Item;
import com.sample.repository.ItemRepository;
import com.sample.service.ItemService;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class ItemServiceImpl implements ItemService {


    private final ItemRepository itemRepository;
    @Override
    public String saveItem(RequestItemDTO requestItemDTO) {

        Item item = new Item(
                requestItemDTO.getName(),
                requestItemDTO.getQty(),
                requestItemDTO.getUnitPrice()
        );

        itemRepository.save(item);
        return item.getName() + " Saved";
    }

    @Override
    public ResponseItemDTO getItemById(long itemId) {
        if (itemRepository.existsById(itemId)){
            Item item = itemRepository.getReferenceById(itemId);

            return new ResponseItemDTO(
                    item.getId(),
                    item.getName(),
                    item.getQty(),
                    item.getUnitPrice()
            );
        }else {
            throw new RuntimeException("Not Found");
        }

    }

    @Override
    public String updateItem(ItemDTO itemDTO) {
        if (itemRepository.existsById(itemDTO.getId())){

            Item item = itemRepository.getReferenceById(itemDTO.getId());
            item.setName(itemDTO.getName());
            item.setQty(itemDTO.getQty());
            item.setUnitPrice(itemDTO.getUnitPrice());

            itemRepository.save(item);
            return item.getName()+ " updated";
        }else {
            throw new RuntimeException("Not Found");
        }

    }

    @Override
    public List<ResponseItemDTO> getAllItems() {

        List<Item> items = itemRepository.findAll();

        List<ResponseItemDTO> responseItemDTOS = new ArrayList<>();

        for(Item item : items){
            ResponseItemDTO responseItemDTO = new ResponseItemDTO(
                    item.getId(),
                    item.getName(),
                    item.getQty(),
                    item.getUnitPrice()
            );
            responseItemDTOS.add(responseItemDTO);
        }
        return responseItemDTOS;
    }

    @Override
    public ResponseItemPaginatedDTO getAllItemsPaginate(int page, int size) {

        Page<Item> items = itemRepository.findAll(PageRequest.of(page, size));

        List<ResponseItemDTO> responseItemDTOS = new ArrayList<>();

        long count = itemRepository.count();

        for(Item item : items){
            ResponseItemDTO responseItemDTO = new ResponseItemDTO(
                    item.getId(),
                    item.getName(),
                    item.getQty(),
                    item.getUnitPrice()
            );
            responseItemDTOS.add(responseItemDTO);
        }

        return new ResponseItemPaginatedDTO(
                responseItemDTOS,
                count
        );
    }
}
