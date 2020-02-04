package com.money.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@ApiModel("AccountRequestDTO")
@NoArgsConstructor
public class AccountRequestDTO {

	@NotBlank(message = "userName cannot be blank!")
	private String userName;

	@NotNull(message = "balance cannot be null!")
	private BigDecimal balance;

}
