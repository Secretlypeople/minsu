package com.jk.service;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "cloud-provider",fallback = HomeServieError.class)//封装了ribbon的功能
public interface HomeServiceFeign extends HomeServices {
}
