https://gigsterous.github.io/engineering/2017/03/01/spring-boot-4.html



curl -H "Content-Type: application/json" -X POST -d '{"description": "my description","image": [{"type": "jeans"}],"name": "product 1"}' -H "Authorization: Bearer $TOKEN" http://localhost:8080/product

curl -i -H "Accept: application/json" -H "Authorization: Bearer  $TOKEN" -X GET http://localhost:8080/product/

curl -i -H "Accept: application/json" -H "Authorization: Bearer  $TOKEN" -X GET http://localhost:8080/product/1

{"description": "my description","image": [{"type": "jeans"}],"name": "product 1"}



Profiles
mvn spring-boot:run -Drun.profiles={profile}

mvn test -Dspring.profiles.active={profile}







