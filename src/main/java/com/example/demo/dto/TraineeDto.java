package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TraineeDto {
    private Long id;
    @NotNull(message = "name不能为空")
    private String name;
    @NotNull(message = "office不能为空")
    private String office;
    @NotNull(message = "email不能为空")
    @Email(message = "email格式不正确")
    private String email;
    @NotNull(message = "zoomId不能为空")
    private String zoomId;
}
