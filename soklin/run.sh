mvn clean install -DskipTests=true
cf create-service mLab  sandbox mongodb
cf push