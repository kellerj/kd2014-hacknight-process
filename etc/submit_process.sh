# submit document

curl -H "Content-Type: application/json" \
  --data '{"state":"ENROUTE"}' \
  --request PUT http://localhost:8090/processInstances/546566c1fbea4a5a679cf69c
