## 📚 Resumo dos Tipos de Dados

No Java, precisamos "avisar" a memória qual tipo de dado vamos guardar.

| Tipo | O que guarda? | Detalhe Importante |
| :--- | :--- | :--- |
| **byte** | Números pequenos | Vai de -128 até 127 |
| **short** | Números médios | Vai até 32.767 |
| **int** | Inteiros padrão | O mais usado para contar coisas |
| **long** | Números gigantes | Precisa colocar **L** no final (`1000L`) |
| **float** | Decimal pequeno | Precisa colocar **f** no final (`1.5f`) |
| **double** | Decimal preciso | O padrão para dinheiro e média |
| **char** | Uma letra | Usa aspas simples `'A'` |
| **boolean**| Verdadeiro/Falso | Só aceita `true` ou `false` |
| **String** | Texto | Usa aspas duplas `"Texto"` (Não é primitivo) |

### ⚠️ Dicas de Ouro (Pegadinhas de Prova)

1.  **O "L" do Long:** Se você digitar um número muito grande sem o `L` no final, o Java acha que é um `int` e dá erro.
2.  **O "f" do Float:** Todo decimal o Java acha que é `double`. Para forçar ser `float` (que gasta menos memória), tem que pôr o `f`.
3.  **Aspas:** `char` usa aspas simples `' '`. `String` usa aspas duplas `" "`.
