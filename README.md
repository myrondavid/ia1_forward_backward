# Sistema de recomendação de Smartphones usando encadeamento para frente.
Projeto da disciplina de IA1 - IC UFAL no semestre de 2019.1
Alunos: Myron David e Leonardo Leite

* Parte 1: implementação de encadeamento pra frente e pra trás
* Parte 2: sistema de recomendação de smartphones

## Como fazer funcionar:
* Criar um banco mysql com o nome "recomendacao_fone"
* Modificar o arquivo hibernate.cfg.xml com a configuração de sua máquina local(user,senha, etc)
* Ao executar pela primeira vez, descomentar as linhas indicadas na classe Main. Após a primeira execução, devem ser comentadas novamente para que não ocorram conflitos no banco.
* Para adicionar regras ou fatos, basta modificar a classe Database, ou mudar diretamente no banco de dados.

## Funcionamento:
* O codigo do encadeamento está na classe Resolver, sendo precisamente o método forwardResult(). 
* Há também o método backwardResult que é a implementação do encadeamento para trás.
* Para utiliza-los há a classe ResolverController, necessário instanciá-la assim como a classe Resolver.
* Basicamente o usuário escolhe os fatos de uma lista de variáveis provenientes do banco, a partir daí o motor utiliza as regras salvas em banco para recomendar smartphones.
