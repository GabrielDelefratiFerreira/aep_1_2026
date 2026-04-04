POST {{base_url}}/{{module}}
Content-Type: application/json
[Options]
variable: base_url=http://127.0.0.1:8080
variable: module=solicitacao
variable: mail=mail@mail.com
variable: password=pass123
HTTP 401


POST {{base_url}}/{{module}}/login
Content-Type: application/json
[Options]
variable: module=auth
{
    "email": "{{mail}}",
    "password": "{{password}}"
}
HTTP 404


POST {{base_url}}/{{module}}/signup
{
    "email": "{{mail}}",
    "password": "{{password}}"
}
HTTP 200
[Captures]
token: jsonpath "$.bearer"


POST {{base_url}}/{{module}}
Authorization: Bearer {{token}}
[Options]
variable: module=solicitacao
{
    "descricao": "descricao da solicitacao",
    "local": "local da solicitacao",
    "prazo": "2026-04-02T16:42:24",
    "id_usuario": 1,
    "id_categoria": 1,
    "prioridade": "High",
    "status": "ABERTO"
}
HTTP 200

# testa a sequencia do id
POST {{base_url}}/{{module}}
Content-Type: application/json
Authorization: Bearer {{token}}
[Options]
variable: module=solicitacao
{
    "descricao": "descricao da solicitacao",
    "local": "local da solicitacao",
    "prazo": "2026-04-02T16:42:24",
    "id_usuario": 1,
    "id_categoria": 1,
    "prioridade": "High",
    "status": "ABERTO"
}
HTTP 200


GET {{base_url}}/{{module}}/by-id/1
Content-Type: application/json
HTTP 200

GET {{base_url}}/{{module}}/by-id/2
Content-Type: application/json
HTTP 200

PUT {{base_url}}/{{module}}/1
Content-Type: application/json
Authorization: Bearer {{token}}
{
    "descricao": "descricao da solicitacao",
    "local": "local da solicitacao",
    "prazo": "2026-04-02T16:42:24",
    "id_usuario": 1,
    "id_categoria": 1,
    "prioridade": "High",
    "status": "TRIAGEM"
}
HTTP 204


GET {{base_url}}/{{module}}/historico/1
Content-Type: application/json
Authorization: Bearer {{token}}
HTTP 200
[Asserts]
jsonpath "$" count == 2
jsonpath "$[0].status" == "ABERTO"
jsonpath "$[1].status" == "TRIAGEM"


PUT {{base_url}}/{{module}}/1
Content-Type: application/json
Authorization: Bearer {{token}}
[Options]
variable: module=usuario
{
    "id": 1,
    "nome": "nome123",
    "email": "email@gmail.com"
}
HTTP 204

