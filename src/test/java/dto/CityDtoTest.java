package dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.junit.Test;

/**
 * Created by Coskun on 18.3.2016.
 */
public class CityDtoTest {

  //todo burayı anlat
  //  @Test
  //  public void testCreatedDate() {
  //    CityDto cityDto = new CityDto();
  //
  //    Date date = DateUtil.asDate(LocalDateTime.of(2016, 1, 20));
  //
  //    cityDto.setCreatedDate(date);
  //
  //    LocalDateTime localDateTime = DateUtil.asLocalDateTime(cityDto.getCreatedDate());
  //
  //    assertEquals(2016, localDateTime.getYear());
  //  }
  //
  //  @Test
  //  public void testCreatedDate_AfterChangeOverGetCreatedDate() {
  //    CityDto cityDto = new CityDto();
  //
  //    Date date = DateUtil.asDate(LocalDateTime.of(2016, 1, 20));
  //
  //    cityDto.setCreatedDate(date);
  //
  //    //Asıl setCreatedDate e verdiğin değeri değiştirdi.
  //    cityDto.getCreatedDate().setTime(new Date().getTime());
  //
  //    LocalDateTime localDateTime = DateUtil.asLocalDateTime(cityDto.getCreatedDate());
  //
  //    assertEquals(2016, localDateTime.getYear());
  //  }
  //
  //  @Test
  //  public void testDate_AfterChangeOverGetCreatedDate() {
  //    CityDto cityDto = new CityDto();
  //
  //    Date date = DateUtil.asDate(LocalDateTime.of(2016, 1, 20));
  //
  //    cityDto.setCreatedDate(date);
  //
  //    //Bu işlem date i de değiştirdi
  //    cityDto.getCreatedDate().setTime(new Date().getTime());
  //
  //    LocalDateTime localDateTime = DateUtil.asLocalDateTime(date);
  //
  //    assertEquals(2016, localDateTime.getYear());
  //  }

}
