FROM tomcat
ADD build/libs/common.war webapps/common.war
CMD ["catalina.sh","run"]
