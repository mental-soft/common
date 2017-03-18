package dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.junit.Test;
import util.DateUtil;

/**
 * Created by Coskun on 18.3.2016.
 */
public class CityDtoTest {

  //  @Test
  //  public void testCreatedDate() {
  //    CityDto cityDto = new CityDto();
  //
  //    Date date = DateUtil.asDate(LocalDate.of(2016, 1, 20));
  //
  //    cityDto.setCreatedDate(date);
  //
  //    LocalDate localDate = DateUtil.asLocalDate(cityDto.getCreatedDate());
  //
  //    assertEquals(2016, localDate.getYear());
  //  }
  //
  //  @Test
  //  public void testCreatedDate_AfterChangeOverGetCreatedDate() {
  //    CityDto cityDto = new CityDto();
  //
  //    Date date = DateUtil.asDate(LocalDate.of(2016, 1, 20));
  //
  //    cityDto.setCreatedDate(date);
  //
  //    //Asıl setCreatedDate e verdiğin değeri değiştirdi.
  //    cityDto.getCreatedDate().setTime(new Date().getTime());
  //
  //    LocalDate localDate = DateUtil.asLocalDate(cityDto.getCreatedDate());
  //
  //    assertEquals(2016, localDate.getYear());
  //  }
  //
  //  @Test
  //  public void testDate_AfterChangeOverGetCreatedDate() {
  //    CityDto cityDto = new CityDto();
  //
  //    Date date = DateUtil.asDate(LocalDate.of(2016, 1, 20));
  //
  //    cityDto.setCreatedDate(date);
  //
  //    //Bu işlem date i de değiştirdi
  //    cityDto.getCreatedDate().setTime(new Date().getTime());
  //
  //    LocalDate localDate = DateUtil.asLocalDate(date);
  //
  //    assertEquals(2016, localDate.getYear());
  //  }

}
