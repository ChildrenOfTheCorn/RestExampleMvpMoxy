package com.example.restexample.domain;

import android.util.Log;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import okhttp3.Connection;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * An OkHttp interceptor which logs request and response information. Can be applied as an
 * <p>
 * The format of the logs created by this class should not be considered stable and may change
 * slightly between releases. If you need a stable logging format, use your own interceptor.
 */
public final class HttpLogginInterceptor implements Interceptor {
    private static final Charset UTF8 = Charset.forName("UTF-8");

    public enum Level {
        /**
         * No logs.
         */
        NONE,
        /**
         * Logs request and response lines.
         * <p>
         * Example:
         * <pre>{@code
         * --> POST /greeting HTTP/1.1 (3-byte body)
         *
         * <-- HTTP/1.1 200 OK (22ms, 6-byte body)
         * }</pre>
         */
        BASIC,
        /**
         * Logs request and response lines and their respective headers.
         * <p>
         * Example:
         * <pre>{@code
         * --> POST /greeting HTTP/1.1
         * Host: example.com
         * Content-Type: plain/text
         * Content-Length: 3
         * --> END POST
         *
         * <-- HTTP/1.1 200 OK (22ms)
         * Content-Type: plain/text
         * Content-Length: 6
         * <-- END HTTP
         * }</pre>
         */
        HEADERS,
        /**
         * Logs request and response lines and their respective headers and bodies (if present).
         * <p>
         * Example:
         * <pre>{@code
         * --> POST /greeting HTTP/1.1
         * Host: example.com
         * Content-Type: plain/text
         * Content-Length: 3
         *
         * Hi?
         * --> END GET
         *
         * <-- HTTP/1.1 200 OK (22ms)
         * Content-Type: plain/text
         * Content-Length: 6
         *
         * Hello!
         * <-- END HTTP
         * }</pre>
         */
        BODY
    }

    public interface Logger {
        void log(String message);

        /**
         * A {@link Logger} defaults output appropriate for the current platform.
         */
        Logger DEFAULT = new Logger() {
            @Override
            public void log(final String message) {
                //Platform.get().log(message);
                Log.d("Retrofit", message);
            }
        };
    }

    public HttpLogginInterceptor() {
        this(Logger.DEFAULT);
    }

    public HttpLogginInterceptor(final Logger logger) {
        this.logger = logger;
    }

    private final Logger logger;

    private volatile Level level = Level.NONE;

    /**
     * Change the level at which this interceptor logs.
     */
    public void setLevel(final Level level) {
        this.level = level;
    }

    @Override
    public Response intercept(final Chain chain) throws IOException {
        final Level level = this.level;

        final Request request = chain.request();
        if (level == Level.NONE) {
            return chain.proceed(request);
        }

        final boolean logBody = level == Level.BODY;
        final boolean logHeaders = logBody || level == Level.HEADERS;

        final RequestBody requestBody = request.body();
        final boolean hasRequestBody = requestBody != null;

        final Connection connection = chain.connection();
        final Protocol protocol = connection != null ? connection.protocol() : Protocol.HTTP_1_1;
        String requestStartMessage =
                "--> " + request.method() + ' ' + requestPath(request.url()) + ' ' + protocol(protocol);
        if (!logHeaders && hasRequestBody) {
            requestStartMessage += " (" + requestBody.contentLength() + "-byte body)";
        }
        logger.log(requestStartMessage);

        if (logHeaders) {
            final Headers headers = request.headers();
            for (int i = 0, count = headers.size(); i < count; i++) {
                logger.log(headers.name(i) + ": " + headers.value(i));
            }

            String endMessage = "--> END " + request.method();
            if (logBody && hasRequestBody) {
                final Buffer buffer = new Buffer();
                requestBody.writeTo(buffer);

                final Charset charset = UTF8;
                final MediaType contentType = requestBody.contentType();
                if (contentType != null) {
                    contentType.charset(UTF8);
                }

                logger.log("");
                logger.log(buffer.readString(charset));

                endMessage += " (" + requestBody.contentLength() + "-byte body)";
            }
            logger.log(endMessage);
        }

        final long startNs = System.nanoTime();
        final Response response = chain.proceed(request);
        final long tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs);

        final ResponseBody responseBody = response.body();
        logger.log("<-- " + protocol(response.protocol()) + ' ' + response.code() + ' '
                + response.message() + " (" + tookMs + "ms"
                + (!logHeaders ? ", " + responseBody.contentLength() + "-byte body" : "") + ')');

        if (logHeaders) {
            final Headers headers = response.headers();
            for (int i = 0, count = headers.size(); i < count; i++) {
                logger.log(headers.name(i) + ": " + headers.value(i));
            }

            String endMessage = "<-- END HTTP";
            if (logBody) {
                final BufferedSource source = responseBody.source();
                source.request(Long.MAX_VALUE); // Buffer the entire body.
                final Buffer buffer = source.buffer();

                Charset charset = UTF8;
                final MediaType contentType = responseBody.contentType();
                if (contentType != null) {
                    charset = contentType.charset(UTF8);
                }

                if (responseBody.contentLength() != 0) {
                    logger.log("");
                    logger.log(buffer.clone().readString(charset));
                }

                endMessage += " (" + buffer.size() + "-byte body)";
            }
            logger.log(endMessage);
        }

        return response;
    }

    private static String protocol(final Protocol protocol) {
        return protocol == Protocol.HTTP_1_0 ? "HTTP/1.0" : "HTTP/1.1";
    }

    private static String requestPath(final HttpUrl url) {
        final String path = url.url().getHost() + url.url().getPath();
        final String query = url.encodedQuery();
        return query != null ? (path + '?' + query) : path;
    }
}