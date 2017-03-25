package entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Created by Coskun on 19.3.2017.
 */
@Converter(autoApply = true)
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Timestamp> {

  @Override
  public Timestamp convertToDatabaseColumn(LocalDateTime localDateTime) {
    return Optional.ofNullable(localDateTime)
        .map(Timestamp::valueOf)
        .orElse(null);
  }

  @Override
  public LocalDateTime convertToEntityAttribute(Timestamp timestamp) {
    return Optional.ofNullable(timestamp)
        .map(Timestamp::toLocalDateTime)
        .orElse(null);
  }

}
