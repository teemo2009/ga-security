package com.ga.security.core.validate.code;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @author:luqi
 * @description: 图片验证码
 * @date:2018/10/22_10:03
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
public class ImageCode {
    //图片
    private BufferedImage image;
    //验证码
    private String code;
    //过期时间
    private LocalDateTime expireTime;

    public ImageCode(BufferedImage image,String code,int expireIn){
        this.image=image;
        this.code=code;
        this.expireTime=LocalDateTime.now().plusSeconds(expireIn);
    }
}
