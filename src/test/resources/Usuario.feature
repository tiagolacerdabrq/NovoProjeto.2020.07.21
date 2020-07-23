#language: pt
#encoding: utf-8

Funcionalidade: Cadastro de Usuario

  Esquema do Cenario: Cadastro de Usuario com Sucesso

    Dado a requisicao contem <id> <username> <firstname> <lastname> <email> <password> <phone> <userStatus>
    Quando conecto com a uri da PetShop
    Entao valido <code> <type> <message>

    Exemplos:
      | id        | username   | firstname | lastname | email               | password | phone         | userStatus | code | type     | message     |
      | 119890418 | "AnaClara" | "Ana"     | "Clara"  | "anaclara@test.com" | "123456" | "11999990001" | 1          | 200  | "unknow" | "119890418" |
      | 219890418 | "JucaPato" | "Juca"    | "Pato"   | "jucapato@test.com" | "654321" | "11999990002" | 1          | 200  | "unknow" | "219890418" |