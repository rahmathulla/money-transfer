package com.money.resource;

import javax.annotation.Priority;
import javax.ws.rs.NotAllowedException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.money.dto.ErrorResponseDTO;
import com.money.exception.AccountNotFoundException;
import com.money.exception.NotSufficientBalanceException;
import com.money.exception.TransferNotFoundException;

@Provider
@Priority(1)
public class ResourceExceptionMapper implements ExceptionMapper<RuntimeException> {

	@Override
	public Response toResponse(RuntimeException exception) {
		exception.printStackTrace();
		Status status = Status.INTERNAL_SERVER_ERROR;
		String message = exception.getMessage();
		if (exception instanceof WebApplicationException) {
			int statusCode = ((WebApplicationException) exception).getResponse().getStatus();
			status = Status.fromStatusCode(statusCode);
			if (exception instanceof NotFoundException) {
				message = "Can't find requested resource";
			} else if (exception instanceof NotAllowedException) {
				message = "Requested resource is not allowed by specified method";
			}
		} else if (exception instanceof AccountNotFoundException || exception instanceof TransferNotFoundException) {
			status = Status.NOT_FOUND;
		} else if (exception instanceof NotSufficientBalanceException) {
			status = Status.BAD_REQUEST;
		}
		return Response.status(status).entity(new ErrorResponseDTO(status.toString(), message))
				.type(MediaType.APPLICATION_JSON).build();
	}

}
