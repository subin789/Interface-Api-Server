package com.ifsju.interfaceweb.sign.controller;

import com.ifsju.interfaceweb.sign.dto.MemberDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/sign")
public class SignController {
    private final Logger LOGGER = LoggerFactory.getLogger(SignController.class);
    @Tag(name = "Test")
    @Operation(summary = "get 테스트 1", description = "차후 설명 추가")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(schema = @Schema(implementation = SignController.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SEVER ERROR")
    })
    @GetMapping("/test/get1")
    public String getTest1(@RequestParam Map<String ,String > param){
        StringBuilder stringBuilder = new StringBuilder();

        param.entrySet().forEach(map-> stringBuilder.append(map.getKey()).append(" : ").append(map.getValue()).append("\n"));

        LOGGER.info("getTest1 is called");
        return stringBuilder.toString();
    }

    @Tag(name = "Test")
    @GetMapping("/test/get2")
    public String getTest2(@Parameter(description = "멤버 정보", required = true) MemberDto memberDto){ {
            LOGGER.info("getTest2 is called");
            return memberDto.toString();
        }
    }
}
