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

# 📍 Geometria Utilizada

O sistema adota a seguinte configuração cartesiana:

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

As posições são:

\[
q_1=(0,a)
\]

\[
q_2=(a,0)
\]

\[
q_3=(0,0)
\]

O ponto P encontra-se exatamente no ponto médio da hipotenusa:

\[
P=
\left(
\frac a2,
\frac a2
\right)
\]

---

# 📚 Fundamentação Teórica

O campo elétrico produzido por uma carga puntiforme é definido por:

\[
E=
\frac{kq}{r^2}
\]

onde:

| Símbolo | Descrição |
|---|---|
| \(E\) | Campo elétrico |
| \(k\) | Constante eletrostática |
| \(q\) | Valor da carga |
| \(r\) | Distância até o ponto analisado |

A constante eletrostática utilizada é:

\[
k=8,99\times10^9\ N\cdot m^2/C^2
\]

---

# 🔴 Passo 1 — Encontrando o ponto P

Como o triângulo é retângulo e isósceles, o ponto P encontra-se exatamente no centro da hipotenusa.

Logo:

\[
x_P=\frac{0+a}{2}
\]

\[
y_P=\frac{a+0}{2}
\]

resultando em:

\[
P=
\left(
\frac a2,
\frac a2
\right)
\]

---

# 🔴 Passo 2 — Calculando a distância até P

Aplicando Pitágoras:

\[
r^2=
\left(
\frac a2
\right)^2+
\left(
\frac a2
\right)^2
\]

\[
r^2=
\frac{a^2}{4}+
\frac{a^2}{4}
\]

\[
r^2=
\frac{a^2}{2}
\]

Portanto:

\[
r=
\frac a{\sqrt2}
\]

Essa distância é a mesma para as três cargas.

---

# 🔴 Passo 3 — Campo produzido por uma carga elementar

Substituindo a distância na fórmula do campo elétrico:

\[
E=
\frac{kq}{a^2/2}
\]

Dividir por uma fração equivale a multiplicar pelo inverso:

\[
E=
\frac{2kq}{a^2}
\]

Adotando:

\[
q=e
\]

obtemos o fator utilizado pelo programa:

\[
E_0=
\frac{2ke}{a^2}
\]

Implementado como:

```java
double E0 = (2*k*eCarga)/Math.pow(aMetros,2);
```

---

# 🔴 Passo 4 — Decomposição Vetorial

Como todos os vetores fazem um ângulo de 45°:

\[
\sin45^\circ=
\cos45^\circ=
\frac{\sqrt2}{2}
\]

As componentes produzidas por cada carga são:

### Carga q₁

\[
E_{1x}
=
E_0
\frac{\sqrt2}{2}
q_1
\]

\[
E_{1y}
=
-
E_0
\frac{\sqrt2}{2}
q_1
\]

---

### Carga q₂

\[
E_{2x}
=
-
E_0
\frac{\sqrt2}{2}
q_2
\]

\[
E_{2y}
=
E_0
\frac{\sqrt2}{2}
q_2
\]

---

### Carga q₃

\[
E_{3x}
=
E_0
\frac{\sqrt2}{2}
q_3
\]

\[
E_{3y}
=
E_0
\frac{\sqrt2}{2}
q_3
\]

---

# 🔴 Passo 5 — Campo Elétrico Resultante

Somando as componentes:

\[
E_x=
E_0
\frac{\sqrt2}{2}
(q_1-q_2+q_3)
\]

\[
E_y=
E_0
\frac{\sqrt2}{2}
(-q_1+q_2+q_3)
\]

Estas são exatamente as fórmulas implementadas:

```java
double Ex =
E0*raiz2sobre2*(q1-q2+q3);

double Ey =
E0*raiz2sobre2*(-q1+q2+q3);
```

---

# 🔴 Passo 6 — Módulo Resultante

Após obter as componentes:

\[
E=
\sqrt{
E_x^2+
E_y^2
}
\]

Implementação:

```java
double moduloResultante =
Math.sqrt(Ex*Ex + Ey*Ey);
```

---

# 🔴 Passo 7 — Direção do Vetor

A direção do campo elétrico é calculada por:

\[
\theta=
\tan^{-1}
\left(
\frac{E_y}{E_x}
\right)
\]

Implementação:

```java
double angulo =
Math.toDegrees(
Math.atan2(Ey,Ex)
);
```

---

# ⚖ Caso Especial de Simetria

Quando:

\[
q_1=q_2
\]

os campos produzidos por essas duas cargas possuem:

- mesmo módulo;
- sentidos opostos.

Portanto:

\[
E_1+E_2=0
\]

Restando apenas o campo produzido por \(q_3\).

O programa detecta automaticamente essa situação e informa ao usuário.

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
