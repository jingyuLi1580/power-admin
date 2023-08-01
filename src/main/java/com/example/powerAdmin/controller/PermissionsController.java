package com.example.powerAdmin.controller;


import com.example.powerAdmin.service.IPermissionsService;
import lombok.RequiredArgsConstructor;
import org.junit.runner.RunWith;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 权限表 前端控制器
 * </p>
 *
 * @author ljy
 * @since 2023-03-27
 */
@RestController
@RequestMapping("/powerAdmin/permissions")
@RequiredArgsConstructor
public class PermissionsController {
    private final IPermissionsService permissionsService;

//    @PostMapping("searchList")
//    public void searchList(@RequestBody @Validated ) {
//
//    }

}

