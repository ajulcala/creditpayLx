FROM openjdk:11
VOLUME /tmp
EXPOSE 8016
ADD ./target/creditpay-0.0.1-SNAPSHOT.jar creditpay.jar
ENTRYPOINT ["java","-jar","/creditpay.jar"]