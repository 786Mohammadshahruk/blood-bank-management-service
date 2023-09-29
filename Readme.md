//AddBloodDetails
curl --location 'http://localhost:9090/bbms/create' \
--header 'Content-Type: application/json' \
--data '[
{
"bloodType": "B+",
"donateTo": [
"AB+",
"B+"
],
"receiveFrom": [
"B+",
"B-",
"O+",
"O-"
]
},
{
"bloodType": "A-",
"donateTo": [
"A+",
"A-",
"AB+",
"AB-"
],
"receiveFrom": [
"A-",
"O-"
]
},
{
"bloodType": "O-",
"donateTo": [
"O-"
],
"receiveFrom": [
"O-"
]
},
{
"bloodType": "B-",
"donateTo": [
"B+",
"B-",
"AB+",
"AB-"
],
"receiveFrom": [
"B-",
"O-"
]
},
{
"bloodType": "AB-",
"donateTo": [
"AB+",
"AB-"
],
"receiveFrom": [
"AB-",
"A-",
"B-",
"O-"
]
}
]'