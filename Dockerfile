#Create MySQL Image for JSP Tutorial Application
FROM mysql
MAINTAINER csgeek@mail.com

ENV MYSQL_ROOT_PASSWORD password

EXPOSE 3306