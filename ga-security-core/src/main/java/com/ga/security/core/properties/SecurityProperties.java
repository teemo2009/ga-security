package com.ga.security.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "ga.security")
public class SecurityProperties {

    private BrowserProperties browser=new BrowserProperties();
}
