package eu.wecanconsulting.example.hello_world;

import java.util.HashMap;
import java.util.Map;

import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.HttpStatusType;

import lombok.Getter;

/**
 * The mock for HttpResponseMessage, can be used in unit tests to verify if the
 * returned response by HTTP trigger function is correct or not.
 */
@Getter
public class HttpResponseMessageMock implements HttpResponseMessage {
	private final int httpStatusCode;
	private final HttpStatusType status;
	private final Object body;
	private final Map<String, String> headers;

	public HttpResponseMessageMock(final HttpStatusType status, final Map<String, String> headers, final Object body) {
		this.status = status;
		this.httpStatusCode = status.value();
		this.headers = headers;
		this.body = body;
	}

	@Override
	public String getHeader(final String key) {
		return headers.get(key);
	}

	public static class HttpResponseMessageBuilderMock implements HttpResponseMessage.Builder {
		private Object body;
		private final Map<String, String> headers = new HashMap<>();
		private HttpStatusType httpStatus;

		public Builder status(final HttpStatus status) {
			this.httpStatus = status;
			return this;
		}

		@Override
		public Builder status(final HttpStatusType httpStatusType) {
			this.httpStatus = httpStatusType;
			return this;
		}

		@Override
		public Builder header(final String key, final String value) {
			this.headers.put(key, value);
			return this;
		}

		@Override
		public Builder body(final Object body) {
			this.body = body;
			return this;
		}

		@Override
		public HttpResponseMessage build() {
			return new HttpResponseMessageMock(this.httpStatus, this.headers, this.body);
		}
	}
}
