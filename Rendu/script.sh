echo "les brasseries"
curl  --noproxy "*" -X GET -H "Accept: application/json" http://localhost:8080/api/v1/brasserie
echo

echo "la brasserie d'id 1"
curl  --noproxy "*" -X GET -H "Accept: application/json" http://localhost:8080/api/v1/brasserie/1
echo

echo "Un mauvais id : kk"
curl  --noproxy "*"  -X GET -H "Accept: application/json" http://localhost:8080/api/v1/brasserie/kk
echo

echo "Un mauvais id : 24"
curl  --noproxy "*"  -X GET -H "Accept: application/json" http://localhost:8080/api/v1/brasserie/24
echo

body='{"id":1,"name":"brewery1","address":"address1","city":"city1","country":"fr","description":"desc1"}'
echo "ajout d'une brasserie existante $body"
curl --noproxy "*" -i -X POST -H "Content-Type: application/json" -d  $body http://localhost:8080/api/v1/brasserie
echo

body='{"id":4,"nam":"brewery1","address":"address1","city":"city1","country":"fr","description":"desc1"}'
echo "ajout d'une brasserie mal formée $body"
curl --noproxy "*" -i -X POST -H "Content-Type: application/json" -d  $body http://localhost:8080/api/v1/brasserie
echo

body='{"id":3,"name":"brewery3","address":"address3","city":"city3","country":"fr","description":"desc3"}'
echo "ajout d'une brasserie OK $body"
curl --noproxy "*" -i -X POST -H "Content-Type: application/json" -d  $body http://localhost:8080/api/v1/brasserie
echo

body='{"id":3,"name":"brewery3","address":"address3","city":"city3","country":"fr","description":"desc3bis"}'
echo "modification de la description de 3 => desc3bis"
curl --noproxy "*" -i -X PUT -H "Content-Type: application/json" -d  $body http://localhost:8080/api/v1/brasserie/3
echo

body='{"id":4,"name":"brewery3","address":"address3","city":"city3","country":"fr","description":"desc3bis"}'
echo "modification de la description de 3 avec id different"
curl --noproxy "*" -i -X PUT -H "Content-Type: application/json" -d  $body http://localhost:8080/api/v1/brasserie/3
echo

body='{"id":4,"name":"brewery3","address":"address3","city":"city3","country":"fr","description":"desc3bis"}'
echo "modification inexistant de 4"
curl --noproxy "*" -i -X PUT -H "Content-Type: application/json" -d  $body http://localhost:8080/api/v1/brasserie/4
echo

body='{"id":3,"na":"brewery3","address":"address3","city":"city3","country":"fr","description":"desc3bis"}'
echo "modification attribut incorrect de 3"
curl --noproxy "*" -i -X PUT -H "Content-Type: application/json" -d  $body http://localhost:8080/api/v1/brasserie/3
echo

echo "Suppression inexistant"
#curl --noproxy "*" -i -X DELETE -H "Content-Type: application/json" http://localhost:8080/api/v1/brasserie/4
echo

echo "Suppression de 3"
#curl --noproxy "*" -i -X DELETE -H "Content-Type: application/json" http://localhost:8080/api/v1/brasserie/3
echo