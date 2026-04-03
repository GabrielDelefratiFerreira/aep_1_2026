POST {{base_url}}/{{module}}
Content-Type: application/json
[Options]
variable: base_url=http://127.0.0.1:8080
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
[Options]
variable: base_url=http://127.0.0.1:8080
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

