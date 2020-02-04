package com.money.resource;

import java.math.BigDecimal;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.money.dto.AccountResponseDTO;
import com.money.dto.TransferResponseDTO;
import com.money.exception.TransferNotFoundException;
import com.money.provider.GuiceInjectorProvider;
import com.money.service.TransferService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Transfer Resource")
@Path("/transfer")
public class TransferResource {

	private final TransferService transferService;

	public TransferResource() {
		this.transferService = GuiceInjectorProvider.getInjector().getInstance(TransferService.class);
	}

	@ApiOperation(value = "Deposit money to Account", response = TransferResponseDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Account not found") })
	@POST()
	@Path("/deposit")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deposit(@QueryParam("accountId") Long accountId, @QueryParam("amount") BigDecimal amount) {
		TransferResponseDTO response = transferService.deposit(accountId, amount);
		return Response.ok().entity(response).build();
	}

	@ApiOperation(value = "Withdraw money from Account", response = TransferResponseDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Account not found") })
	@POST()
	@Path("/withdraw")
	@Produces(MediaType.APPLICATION_JSON)
	public Response withdraw(@QueryParam("accountId") Long accountId, @QueryParam("amount") BigDecimal amount) {
		TransferResponseDTO response = transferService.withdraw(accountId, amount);
		return Response.ok().entity(response).build();
	}

	@ApiOperation(value = "Transfer money from one Account to another", response = TransferResponseDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Account not found") })
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response transfer(@QueryParam("fromAccountId") Long fromAccountId, @QueryParam("toAccountId") Long toAccountId,
			@QueryParam("amount") BigDecimal amount) {
		TransferResponseDTO response = transferService.transfer(fromAccountId, toAccountId, amount);
		return Response.ok().entity(response).build();
	}

	@ApiOperation(value = "Get Transfer by ID", response = AccountResponseDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 404, message = "Transfer doesn't exist") })
	@GET
	@Path("/{transferId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTransfer(@PathParam("transferId") Long transferId) throws TransferNotFoundException {

		TransferResponseDTO response = transferService.getTransfer(transferId);
		return Response.ok().entity(response).build();
	}

}
