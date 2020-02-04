package com.money.resource;

import java.net.URI;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.money.dto.AccountRequestDTO;
import com.money.dto.AccountResponseDTO;
import com.money.exception.AccountNotFoundException;
import com.money.provider.GuiceInjectorProvider;
import com.money.service.AccountService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Account Resource")
@Path("/accounts")
public class AccountResource {

	private final AccountService accountService;

	public AccountResource() {
		this.accountService = GuiceInjectorProvider.getInjector().getInstance(AccountService.class);
	}

	@ApiOperation(value = "Create a new Account", response = AccountResponseDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created"),
			@ApiResponse(code = 400, message = "Bad Request") })
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createAccount(@Valid AccountRequestDTO accountRequestDTO, @Context UriInfo uriInfo) {

		AccountResponseDTO response = accountService.createAccount(accountRequestDTO);

		URI uri = uriInfo.getAbsolutePathBuilder().path(response.getId().toString()).build();

		return Response.created(uri).entity(response).build();
	}

	@ApiOperation(value = "Find an Account by ID", response = AccountResponseDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 404, message = "Account doesn't exist") })
	@GET
	@Path("/{accountId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAccount(@PathParam("accountId") Long accountId) throws AccountNotFoundException {

		AccountResponseDTO response = accountService.getAccount(accountId);
		return Response.ok().entity(response).build();
	}

}
