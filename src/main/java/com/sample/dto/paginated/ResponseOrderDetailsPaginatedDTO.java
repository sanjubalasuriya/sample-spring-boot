package com.sample.dto.paginated;

import com.sample.dto.response.ResponseItemDTO;
import com.sample.dto.response.ResponseOrderDetailsDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseOrderDetailsPaginatedDTO {

    List<ResponseOrderDetailsDTO> list;
    private long dataCount;
}
