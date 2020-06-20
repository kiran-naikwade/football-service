# football-service

## Base URL:
GET http://localhost:8080/api/teams

Data:
{
	"name": "team2",
	"city": "pune",
	"league": "league2",
	"country": "US",
	"noOfPlayer": 12,
	"position": "3"
}

## Sample postman sample curl request:

## To add team:

curl --location --request POST 'http://localhost:8080/api/teams' \
--header 'x-api-key: 9bb66184e0c8145384fd2cc0f7b914ada57b4e8fd2e4d6d586adcc27c257a978' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=E2FE8F14597BF31537151AB04B24807B' \
--data-raw '{
	"name": "team2",
	"city": "pune",
	"league": "league2",
	"country": "US",
	"noOfPlayer": 12,
	"position": "3"
}'


## To fetch all teams:

curl --location --request GET 'http://localhost:8080/api/teams' \
--header 'x-api-key: 9bb66184e0c8145384fd2cc0f7b914ada57b4e8fd2e4d6d586adcc27c257a978'

## To fetch by teams name:

curl --location --request GET 'http://localhost:8080/api/teams/name/team2' \
--header 'x-api-key: 9bb66184e0c8145384fd2cc0f7b914ada57b4e8fd2e4d6d586adcc27c257a978'

## To fetch by league name:

curl --location --request GET 'http://localhost:8080/api/teams/league/league2' \
--header 'x-api-key: 9bb66184e0c8145384fd2cc0f7b914ada57b4e8fd2e4d6d586adcc27c257a978'

## To fetch by country name:

curl --location --request GET 'http://localhost:8080/api/teams/country/US' \
--header 'x-api-key: 9bb66184e0c8145384fd2cc0f7b914ada57b4e8fd2e4d6d586adcc27c257a978'
