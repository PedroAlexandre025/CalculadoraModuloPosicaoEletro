# ⚡ Calculadora de Campo Elétrico – Ponto P

Este projeto consiste em uma aplicação gráfica desenvolvida em **Java (Swing)** para simular e calcular o **campo elétrico resultante** em um ponto específico de um sistema de cargas pontuais.

O programa foi inspirado em um problema clássico de **Física Universitária (Eletromagnetismo/Eletrostática)** e implementa a solução analítica do exercício, permitindo alterar os valores das cargas e da distância entre elas, mantendo a mesma configuração geométrica.

---

# 📐 O Problema Inspirador

O programa foi desenvolvido a partir do seguinte exercício:
<img width="1133" height="532" alt="image" src="https://github.com/user-attachments/assets/b2d805e7-46c5-4e08-a595-0aa314099b3a" />


> Na Fig. 22.21, as três partículas são mantidas fixas no lugar e têm cargas
>
> \[
> q_1=q_2=+e
> \]
>
> e
>
> \[
> q_3=+2e
> \]
>
> A distância
>
> \[
> a=6,00\ \mu m
> \]
>
> Determine:
>
> - (a) o módulo do campo elétrico no ponto P;
> - (b) a direção do campo elétrico no ponto P.

---

# 🎯 Objetivo do Projeto

O objetivo do simulador é determinar o vetor campo elétrico resultante no ponto **P**, utilizando o princípio da superposição vetorial.

O programa permite alterar:

- o valor de \(q_1\);
- o valor de \(q_2\);
- o valor de \(q_3\);
- a distância \(a\).

Mantendo a geometria original do problema.

---
# 🧮 Resolução Matemática (Passo a Passo)

A lógica implementada no programa é baseada na resolução analítica do problema original de eletrostática.

---

## 🔴 Passo 1 — Determinando a posição do ponto P

A geometria do problema é um triângulo retângulo isósceles:

```text
                y

            q1(0,a)
               ●
               |\
               | \
               |  \
               | P \
               ●----●
           q3      q2
          (0,0)   (a,0)

                    x
```

As posições das cargas são:

```text
q1 = (0,a)
q2 = (a,0)
q3 = (0,0)
```

O ponto P encontra-se exatamente no ponto médio da hipotenusa.

Calculando a média das coordenadas:

```text
xP = (0 + a)/2
yP = (a + 0)/2
```

Portanto:

```text
P = (a/2 , a/2)
```

---

## 🔴 Passo 2 — Calculando a distância até P

Aplicando o Teorema de Pitágoras:

```text
r² = (a/2)² + (a/2)²
```

Desenvolvendo:

```text
r² = a²/4 + a²/4

r² = a²/2
```

Logo:

```text
r = a/√2
```

Essa distância é a mesma para as três cargas.

---

## 🔴 Passo 3 — Campo elétrico de uma carga

A fórmula geral do campo elétrico é:

```text
E = kq/r²
```

Substituindo a distância encontrada:

```text
E = kq/(a²/2)
```

Dividir por uma fração equivale a multiplicar pelo inverso:

```text
E = 2kq/a²
```

Como o programa utiliza a carga elementar (e), define-se:

```text
E0 = 2ke/a²
```

Essa é exatamente a expressão implementada no código:

```java
double E0 = (2*k*eCarga)/Math.pow(aMetros,2);
```

---

## 🔴 Passo 4 — Decomposição Vetorial

Todos os vetores formam um ângulo de 45° com os eixos.

Sabemos que:

```text
sen(45°) = cos(45°) = √2/2
```

Portanto, as componentes dos campos elétricos são:

### Campo produzido por q1

```text
E1x = +(E0·√2/2)·q1
E1y = -(E0·√2/2)·q1
```

### Campo produzido por q2

```text
E2x = -(E0·√2/2)·q2
E2y = +(E0·√2/2)·q2
```

### Campo produzido por q3

```text
E3x = +(E0·√2/2)·q3
E3y = +(E0·√2/2)·q3
```

---

## 🔴 Passo 5 — Superposição Vetorial

Aplicando o princípio da superposição:

```text
Ex = E1x + E2x + E3x

Ey = E1y + E2y + E3y
```

Substituindo as componentes:

```text
Ex = E0·(√2/2)·(q1 - q2 + q3)

Ey = E0·(√2/2)·(-q1 + q2 + q3)
```

Estas são exatamente as fórmulas implementadas no programa:

```java
double Ex = E0 * raiz2sobre2 * (q1 - q2 + q3);

double Ey = E0 * raiz2sobre2 * (-q1 + q2 + q3);
```

---

## 🔴 Passo 6 — Cálculo do Módulo Resultante

Após calcular as componentes X e Y, utiliza-se o Teorema de Pitágoras:

```text
E = √(Ex² + Ey²)
```

Implementado como:

```java
double moduloResultante =
Math.sqrt(Ex*Ex + Ey*Ey);
```

---

## 🔴 Passo 7 — Determinação da Direção

A direção do vetor campo elétrico é obtida pela tangente inversa:

```text
θ = arctan(Ey/Ex)
```

No programa:

```java
double angulo =
Math.toDegrees(
    Math.atan2(Ey, Ex)
);
```

O resultado é normalizado para permanecer no intervalo:

```text
0° ≤ θ ≤ 360°
```

---

## ⚖ Caso Especial de Simetria

Quando:

```text
q1 = q2
```

os campos produzidos por q1 e q2 possuem:

- mesmo módulo;
- direções opostas.

Consequentemente:

```text
E1 + E2 = 0
```

e o campo resultante passa a depender exclusivamente da carga q3.

O programa detecta automaticamente essa condição e apresenta uma observação física ao usuário.
---

# 🛡 Tratamento de Erros

O simulador implementa validações para garantir coerência física e computacional.

### Erro de geometria

A distância deve obedecer:

\[
a>0
\]

Caso contrário:

```text
Erro de Geometria:
A distância 'a' deve ser estritamente maior que zero.
```

---

### Erro de entrada

Caso o usuário informe letras ou símbolos:

```text
Erro de Entrada:
Por favor, insira apenas valores numéricos válidos.
```

---

# 💻 Tecnologias Utilizadas

- Java
- Java Swing
- Programação Orientada a Objetos
- Eletrostática
- Álgebra Vetorial

---

# 🚀 Como Executar

Compile:

```bash
javac CalculadoraEletrostatica.java
```

Execute:

```bash
java CalculadoraEletrostatica
```

---

# 📊 Exemplo de Execução

Entrada:

```text
q1 = 1
q2 = 1
q3 = 2
a = 6 μm
```

Saída aproximada:

```text
Ex = 113,1 N/C
Ey = 113,1 N/C

Módulo = 160 N/C

Direção = 45°
```

Observação:

```text
Como q1 = q2, os campos gerados
por elas se anulam no ponto P.
O campo resultante é causado
apenas pela carga q3.
```

---

# 👨‍💻 Autor

Projeto desenvolvido para fins acadêmicos na disciplina de Física/Computação, com foco em modelagem matemática, programação e simulação computacional aplicada à eletrostática.
