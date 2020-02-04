package com.money.dto;

import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransferResponseDTO {

	private Long id;

	private String transferType;

	private BigDecimal amount;

	private String createdDate;

	private Long fromAccountId;

	private Long toAccountId;

}
