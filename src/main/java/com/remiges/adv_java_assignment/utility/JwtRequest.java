package com.remiges.adv_java_assignment.utility;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JwtRequest {

    private String email;

    private String password;
}
