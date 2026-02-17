FROM ubuntu:22.04
ENV DEBIAN_FRONTEND=noninteractive

RUN apt-get update \
 && apt-get install -y wget curl unzip sqlite3 git lsb-release gnupg ca-certificates --no-install-recommends \
 && apt-get install -y maven openjdk-21-jdk --no-install-recommends \
 && rm -rf /var/lib/apt/lists/*

ENV CATALINA_HOME=/opt/tomcat
ENV TOMCAT_VERSION=10.1.50

RUN wget -qO /tmp/tomcat.tar.gz https://archive.apache.org/dist/tomcat/tomcat-10/v${TOMCAT_VERSION%.*}/bin/apache-tomcat-${TOMCAT_VERSION}.tar.gz \
 && mkdir -p /opt \
 && tar xzf /tmp/tomcat.tar.gz -C /opt \
 && mv /opt/apache-tomcat-${TOMCAT_VERSION} $CATALINA_HOME \
 && rm /tmp/tomcat.tar.gz

ENV PATH=$CATALINA_HOME/bin:$PATH

WORKDIR /workspace

EXPOSE 8081

CMD ["bash"]
