package config;

import constant.PropertySourceFile;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

/**
 * Bu class bizim uygulamamizdaki
 * property dosyalarini belirtir.
 */
@Configuration
@Import({PersistenceContext.class, ServiceContext.class})
public class ApplicationContext {

  /**
   * Developer (local) makine icin yapilan ayar.
   */
  @Configuration
  @Profile({"default", "dev"})
  @PropertySource({PropertySourceFile.APPLICATION_SOURCE,
      PropertySourceFile.DEVELOPER_SOURCE})
  static class Dev {
    //Extra bean tanimlanabilir.
  }

  /**
   * QA (test) makine icin yapilan ayar.
   */
  @Configuration
  @Profile({"qa"})
  @PropertySource({PropertySourceFile.APPLICATION_SOURCE,
      PropertySourceFile.QUALITY_ASSURANCE_SOURCE})
  static class Qa {
    //Extra bean tanimlanabilir.
  }

  /**
   * Prod (prod) makine icin yapilan ayar.
   */
  @Configuration
  @Profile({"prod"})
  @PropertySource({PropertySourceFile.APPLICATION_SOURCE,
      PropertySourceFile.PRODUCT_SOURCE})
  static class Product {
    //Extra bean tanimlanabilir.
  }

}
