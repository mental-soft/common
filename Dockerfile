FROM mental/jre
ADD build/libs/common.jar /app/common.jar
EXPOSE 10060
CMD java -jar /app/common.jar --connection=cont_postgresql