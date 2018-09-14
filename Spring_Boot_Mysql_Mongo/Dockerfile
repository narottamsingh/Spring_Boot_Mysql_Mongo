FROM tomcat:8-jre8
MAINTAINER "Narottam Singh<n.kumar9@yahoo.com>"
ENV CATALINA_OPTS="-DPROD_HOME=/home"
ADD Spring_Boot_Mysql_Mongo/target/Spring_Boot_Mysql_Mongo.war /usr/local/tomcat/webapps/
EXPOSE 8081
CMD ["catalina.sh", "run"]
