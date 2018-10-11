package com.ga.security.core.properties;

import lombok.Data;

@Data
public class BrowserProperties {

    private String loginPage="/ga-signIn.html";

    private LoginType loginType=LoginType.JSON;

}
