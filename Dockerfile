FROM mental/jre
ADD build/libs/common.jar /app/common.jar
EXPOSE 10060
ENV connection=cont_postgresql
CMD java -jar /app/common.jar