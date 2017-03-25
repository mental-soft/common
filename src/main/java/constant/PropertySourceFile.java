package constant;

/**
 * Created by Admin on 4.4.2016.
 */
public class PropertySourceFile {
  public static final String APPLICATION_SOURCE = Common.CLASSPATH
      + ":" + PropertiesFile.APPLICATION_PROPERTIES;
  public static final String DEVELOPER_SOURCE = Common.CLASSPATH
      + ":environment/" + PropertiesFile.DEVELOPER_PROPERTIES;
  public static final String QUALITY_ASSURANCE_SOURCE = Common.CLASSPATH
      + ":environment/" + PropertiesFile.QUALITY_ASSURANCE_PROPERTIES;
  public static final String PRODUCT_SOURCE = Common.CLASSPATH
      + ":environment/" + PropertiesFile.PRODUCT_PROPERTIES;
}
