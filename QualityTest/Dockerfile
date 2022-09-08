# select parent image
FROM maven:3.6-openjdk-17
# copy the source tree and the pom.xml to our new container
# we will use openjdk 8 with alpine as it is a very small linux distro
# copy the packaged jar file into our docker image
COPY out/artifacts/QualityTest_jar/QualityTest.jar /QualityTest.jar

ENV DISPLAY=:0.0
# set the startup command to execute the jar
CMD ["java", "-cp", "/QualityTest.jar","MainListner"]