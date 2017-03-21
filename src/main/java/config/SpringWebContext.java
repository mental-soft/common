package config;


import constant.Encoding;
import constant.MessagesFile;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.StringUtils;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"controller"})
@Import(value = {WebMvcResolverConfig.class})
public class SpringWebContext extends WebMvcConfigurerAdapter {

  /**
   * Utf8.
   */
  private static final String UTF8 = "UTF-8";

  /**
   * Define charset utf8.
   */
  private static final Charset CHARSET_UTF8 = Charset.forName(UTF8);

  private static final String MESSAGE_SOURCE_BASE_NAME =
      MessagesFile.MESSAGES + "/" + MessagesFile.MESSAGES;

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/static/**").addResourceLocations("/static/");
  }

  @Bean
  MessageSource messageSource() {
    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
    messageSource.setBasename(MESSAGE_SOURCE_BASE_NAME);
    messageSource.setUseCodeAsDefaultMessage(true);
    messageSource.setCacheSeconds(0);
    messageSource.setDefaultEncoding(Encoding.UTF8);
    return messageSource;
  }

  @Bean
  PropertySourcesPlaceholderConfigurer propertyPlaceHolderConfigurer() {
    return new PropertySourcesPlaceholderConfigurer();
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    HandlerInterceptorAdapter handlerInterceptorAdapter = new HandlerInterceptorAdapter() {
      @Override
      public boolean preHandle(HttpServletRequest request,
                               HttpServletResponse response, Object handler)
          throws Exception {
        request.setCharacterEncoding(Encoding.UTF8);
        response.setCharacterEncoding(Encoding.UTF8);
        return true;
      }
    };

    registry.addInterceptor(handlerInterceptorAdapter);
    registry.addInterceptor(localeChangeInterceptor());
  }

  /**
   * Locale change interceptor for internationalization.
   *
   * @return LocaleChangeInterceptor
   */
  @Bean
  public LocaleChangeInterceptor localeChangeInterceptor() {
    LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
    localeChangeInterceptor.setParamName("lang");
    return localeChangeInterceptor;
  }

  /**
   * Locale resolver for Turkish.
   *
   * @return Default Turkish locale.
   */
  @Bean
  public SessionLocaleResolver localeResolver() {
    SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
    sessionLocaleResolver.setDefaultLocale(StringUtils.parseLocaleString("tr"));
    return sessionLocaleResolver;
  }


  /**
   * For Negotiation.
   *
   * @param configurer config
   */
  @Override
  public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
    configurer.favorPathExtension(true)
        .ignoreAcceptHeader(true)
        .useJaf(false)
        .defaultContentType(MediaType.TEXT_HTML)
        .mediaType("html", MediaType.TEXT_HTML)
        .mediaType("json", MediaType.APPLICATION_JSON_UTF8);
  }

  /**
   * Configure message converters.
   *
   * @param converters HttpMessageConverter
   */
  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();
    List<MediaType> mediaTypelist = new ArrayList<>();
    mediaTypelist.add(new MediaType("text", "html", CHARSET_UTF8));
    mediaTypelist.add(new MediaType("application", "json", CHARSET_UTF8));
    stringConverter.setSupportedMediaTypes(mediaTypelist);
    converters.add(stringConverter);
    converters.add(new MappingJackson2HttpMessageConverter());
    super.configureMessageConverters(converters);
  }

  @Override
  public Validator getValidator() {
    LocalValidatorFactoryBean factory = new LocalValidatorFactoryBean();
    factory.setValidationMessageSource(messageSource());
    return factory;
  }
}
