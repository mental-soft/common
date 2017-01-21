#Mental Team - Common Projesi

Veritabanı Kullanıcısı oluştur

Development ortamı için:
```
create user common identified by common;
grant connect, resource to common;
```

Test ortamı için:
```
create user common identified by common_qa;
grant connect, resource to common_qa;
```


Gradle Liquibase ile çalıştır

```

gradlew update

```

Test ortamında çalışmak için örnek script:
```

gradlew update -Penv=qa

```
