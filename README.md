# spring-data-advanced

- Colocar findall como fetcher para evitar o problema de n+1
- Colocar clear no delete

# Alguns conceitos
- ACID -> transação que fornece atomicidade, consistência, isolamento e durabilidade.

# Uso do @Lock
- para consultas podemos utilizar no método alguns tipos do alocação da tabela
  - @Lock(LockModeType.PESSIMISTIC_READ) -> aloca a tabela imediatamente, não permite multiplos acessos
    - recomenda-se colocar um tempo para alocar: @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "3000")})
  - @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT) -> versiona a entidade, caso ela esteja diferente no momento da atualização, com o versionamento na base de dados, ocorre uma exception e é realizado rollback.

# Uso do isolation
- para transações devemos seguir, dependendo de cada caso, o ACID -> Atomicidade, Consistência, Isolamento e Durabilidade
- Em vez de utilizar o @Lock, podemos utilizar o @Transactional(isolation = Isolation.**), onde é mais seguro e gerenciável diretamente pelo spring data
- temos alguns tipos de isolamento, como:
  - READ_UNCOMMITTED -> permite acessos simultâneos e leitura de dados não comitados.
  - READ_COMMITTED -> permite apenas dados comitados, no entanto se outra transação confirmar os dados, teremos um resultado diferente.
  - REPEATABLE_READ -> permite apenas dados comitados e não demonstra dados comitados em outra transação por um tempo
  - SERIALIZÁVEL -> evita todos os problemas acima, mas limta o acesso simultâneo ao recurso.

# CAP
- C (consistencia) -> todos leêm as mesmas informações ao mesmo tempo, os dados devem ser replicados a todos os nós
- a (disponibilidade) -> sempre temos respostas da base, mesmo diante a nós inativos.
- P (particionamento) -> cliente deve continuar recebendo os dados, mesmo em ocorra falha em algum nó
