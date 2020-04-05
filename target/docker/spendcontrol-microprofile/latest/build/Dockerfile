FROM fabric8/s2i-java:2.3
ENV AB_PROMETHEUS_OFF=true JAVA_APP_DIR=/deployments AB_OFF=true
LABEL org.label-schema.description="" org.label-schema.version=1.0.0-SNAPSHOT org.label-schema.schema-version=1.0 org.label-schema.build-date=2020-04-04T22:30:33.453 org.label-schema.name="Thorntail Example"
EXPOSE 8080 8778 9779
COPY maven /deployments/
