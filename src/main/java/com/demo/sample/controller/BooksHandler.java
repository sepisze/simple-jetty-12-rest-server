package com.demo.sample.controller;

import java.util.List;
import java.util.UUID;

import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.http.HttpStatus;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Response;
import org.eclipse.jetty.util.Callback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.sample.model.Book;
import com.demo.sample.model.ErrorResponse;
import com.demo.sample.service.BooksService;
import com.demo.sample.utils.JsonUtils;
import com.demo.sample.utils.ServerUtils;

public class BooksHandler extends Handler.Abstract {
	private static final Logger logger = LoggerFactory.getLogger(BooksHandler.class);

	private BooksService booksService;

	public BooksHandler(BooksService booksService) {
		super();

		this.booksService = booksService;
	}

	@Override
	public boolean handle(Request request, Response response, Callback callback) throws Exception {
		String instanceId = ServerUtils.getHeaders(request).get("X-Request-ID");
		if (instanceId == null || instanceId.isEmpty()) {
			instanceId = UUID.randomUUID().toString();
		}

		logger.debug("BooksHandler.handle(): request\n"
			+ "Request:\n"
			+ "    URI:        " + request.getHttpURI() + "\n"
			+ "    Method:     " + request.getMethod() + "\n"
			+ "    Headers:    " + ServerUtils.getHeaders(request) + "\n"
			+ "    InstanceId: " + instanceId + "");

		if (request.getMethod().equals("GET")) {
			String fields = ServerUtils.getPathVariables(request);

			if (fields.isEmpty()) {
				List<Book> res = booksService.findAll(instanceId);

				response.setStatus(HttpStatus.OK_200);
				response.getHeaders().put(HttpHeader.CONTENT_TYPE, "application/json; charset=UTF-8");
				response.write(true, ServerUtils.utf_8_encode(JsonUtils.stringify(res)), callback);
			} else {
				String uid = fields;
				Book res = booksService.findByUid(instanceId, uid);

				response.setStatus(HttpStatus.OK_200);
				response.getHeaders().put(HttpHeader.CONTENT_TYPE, "application/json; charset=UTF-8");
				response.write(true, ServerUtils.utf_8_encode(JsonUtils.stringify(res)), callback);
			}
		} if (request.getMethod().equals("POST")) {
			String body = ServerUtils.getRequestBody(request);
			Book params = JsonUtils.parse(Book.class, body);
			Book res = booksService.save(instanceId, params);

			response.setStatus(HttpStatus.CREATED_201);
			response.getHeaders().put(HttpHeader.CONTENT_TYPE, "application/json; charset=UTF-8");
			response.write(true, ServerUtils.utf_8_encode(JsonUtils.stringify(res)), callback);
		} if (request.getMethod().equals("PUT")) {
			String fields = ServerUtils.getPathVariables(request);
			String uid = fields;
			String body = ServerUtils.getRequestBody(request);
			Book params = JsonUtils.parse(Book.class, body);

			Book res = booksService.modify(instanceId, uid, params);

			response.setStatus(HttpStatus.OK_200);
			response.getHeaders().put(HttpHeader.CONTENT_TYPE, "application/json; charset=UTF-8");
			response.write(true, ServerUtils.utf_8_encode(JsonUtils.stringify(res)), callback);
		} if (request.getMethod().equals("DELETE")) {
			String fields = ServerUtils.getPathVariables(request);
			String uid = fields;
			booksService.deleteByUid(instanceId, uid);
			Book res = null;

			response.setStatus(HttpStatus.OK_200);
			response.getHeaders().put(HttpHeader.CONTENT_TYPE, "application/json; charset=UTF-8");
			response.write(true, ServerUtils.utf_8_encode(JsonUtils.stringify(res)), callback);
		} else {
			response.setStatus(HttpStatus.METHOD_NOT_ALLOWED_405);
			response.getHeaders().put(HttpHeader.CONTENT_TYPE, "application/json; charset=UTF-8");
			response.write(true, ServerUtils.utf_8_encode(JsonUtils.stringify(new ErrorResponse("Method Not Allowed", response.getStatus(), "Method '" + request.getMethod() + "' is not supported."))), callback);
		}

		return true;
	}
}
