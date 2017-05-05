FROM tomcat:8.0
ADD build/libs/common.war webapps/common.war
CMD ["catalina.sh","run"]
