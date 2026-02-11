# Aula - Estruturas Condicionais (if / else)

Nesta aula, aprendemos como ensinar o computador a tomar decisões. O código deixa de seguir uma linha reta e pode seguir caminhos diferentes dependendo da situação.

---

## 1️⃣ A Estrutura Básica

Imagine uma encruzilhada. O computador verifica uma **condição** (uma pergunta de Sim ou Não).
* Se for Verdadeira (`true`), ele entra no bloco.
* Se for Falsa (`false`), ele pula ou vai para o `else`.

```java
if (condicao) {
    // Executa se for VERDADEIRO
} else if (outra_condicao) {
    // Executa se a primeira for falsa e essa for VERDADEIRA
} else {
    // Executa se NENHUMA das anteriores for verdadeira
}
