package com.sample.dto.paginated;

import com.sample.dto.response.ResponseCustomerDTO;
import com.sample.dto.response.ResponseItemDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseItemPaginatedDTO {

    List<ResponseItemDTO> list;
    private long dataCount;
}
