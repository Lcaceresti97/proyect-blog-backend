package baas.com.sistemablogspringbootapirest.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * Parent exception for 404 not found status code
 *
 * @autor Laurent Cáceres
 */
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -7272303639193378954L;

    public ResourceNotFoundException() {
        super();
    }

    private ResourceNotFoundException(Class entityClass, String... searchParamsMap) {
        super(ResourceNotFoundException.generateExceptionMessage(entityClass.getSimpleName(),
                toMap(String.class, String.class, searchParamsMap)));
    }

    private static String generateExceptionMessage(String entity, Map<String, String> searchParams) {
        return new StringBuilder(StringUtils.capitalize(entity))
                .append(" was not found for parameters ")
                .append(searchParams)
                .toString();
    }

    private static <K, V> Map<K, V> toMap(Class<K> keyType, Class<V> valueType, Object... entries) {
        if (entries.length % 2 != 0)
            throw new IllegalArgumentException("Invalid entries");
        return IntStream
                .range(0, entries.length / 2)
                .map(i -> i * 2)
                .collect(HashMap::new,
                        (m, i) -> m.put(keyType.cast(entries[i]), valueType.cast(entries[i + 1])), Map::putAll);
    }

    protected static ResourceNotFoundException resourceNotFoundExceptionOf(Class thrownClass, String... searchParams) {
        return new ResourceNotFoundException(thrownClass, searchParams);
    }
}
