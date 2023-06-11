package com.electronicstore.Config;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {

    private String messages;

    private boolean success;

}
