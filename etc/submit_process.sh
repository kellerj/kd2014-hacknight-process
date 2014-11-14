# submit document

curl -H "Content-Type: application/json" \
  --data '{"id":"546566c4fbea4a5a679cf6a0","state":"ENROUTE"}' \
  --request PUT http://localhost:8090/processInstances
