# Calculadora de Campo Elétrico - Ponto P ⚡

Este projeto é uma aplicação gráfica desenvolvida em Java (Swing) para simular e calcular o vetor campo elétrico resultante em um sistema de cargas pontuais. O sistema foi inspirado em um problema clássico de Física Universitária (Eletromagnetismo/Eletrostática).



## 📐 O Problema Inspirador

<img width="761" height="364" alt="image" src="https://github.com/user-attachments/assets/0f741654-b046-4fde-93f1-48aaed055ca7" />


O programa resolve o seguinte arranjo geométrico:
* Três partículas carregadas são mantidas fixas em um plano cartesiano.
* A carga $q_1$ está no eixo y (coordenada $0, a$).
* A carga $q_2$ está no eixo x (coordenada $a, 0$).
* A carga $q_3$ está na origem (coordenada $0, 0$).
* O objetivo é determinar o campo elétrico resultante no **ponto P**, que está localizado no ponto médio da reta (hipotenusa) que conecta $q_1$ e $q_2$.

---

## 🧮 Resolução Matemática (Passo a Passo)

A lógica do código é baseada na seguinte resolução teórica:

### 1. Entendendo a Geometria e as Posições
O ponto P está exatamente no meio da reta que liga $q_1$ e $q_2$. Fazendo a média das coordenadas das extremidades:
* $x_p = \frac{0 + a}{2} = \frac{a}{2}$
* $y_p = \frac{a + 0}{2} = \frac{a}{2}$

Logo, o ponto P está na coordenada $(\frac{a}{2}, \frac{a}{2})$.

### 2. A Distância até o Ponto P
Aplicando o Teorema de Pitágoras para encontrar a distância ao quadrado ($d^2$) da origem ($q_3$) até P:
$$d_3^2 = \left(\frac{a}{2} - 0\right)^2 + \left(\frac{a}{2} - 0\right)^2$$
$$d_3^2 = \frac{a^2}{4} + \frac{a^2}{4} = \frac{a^2}{2}$$

Se calcularmos a distância de P até $q_1$ e $q_2$, veremos que é idêntica: $d_1^2 = d_2^2 = \frac{a^2}{2}$.

### 3. A Superposição Vetorial (O Cancelamento)
   
O campo elétrico total em P é a soma vetorial: E_total = E_1 + E_2 + E_3. Como q_1 = +e e q_2 = +e, elas têm o mesmo valor e estão à mesma distância de P. Seus vetores de campo possuem a mesma intensidade, mas apontam em direções opostas ao longo da mesma reta. Portanto, eles se anulam (E_1 + E_2 = 0). O campo resultante é causado apenas por q_3:

E_total = E_3

### 4. O Cálculo do Módulo Resultante
Aplicando a fórmula do campo elétrico para $q_3 = 2e$:
$$E = k \frac{|q|}{d^2} \implies E = k \frac{2e}{\frac{a^2}{2}} \implies E = \frac{4ke}{a^2}$$

Substituindo com as constantes ($k = 8,99 \times 10^9$, $e = 1,60 \times 10^{-19}$, $a = 6,00 \times 10^{-6}$):
$$E = \frac{4 \cdot (8,99 \times 10^9) \cdot (1,60 \times 10^{-19})}{(6,00 \times 10^{-6})^2}$$
$$E = \frac{57,536 \times 10^{-10}}{36,0 \times 10^{-12}} \approx 160 \text{ N/C}$$

### 5. Determinando a Direção
Como a coordenada X e Y de P são iguais $(\frac{a}{2}, \frac{a}{2})$, a reta que sai da origem até P divide o quadrante perfeitamente ao meio. Logo, a direção do vetor resultante é de **45°** em relação ao semieixo x positivo.

---

## 💻 Tecnologia e Implementação

* **Linguagem:** Java
* **Interface Gráfica:** Java Swing (`JFrame`, `JPanel`, `JTextField`, `JTextArea`)
* **Implementação Matemática:** O código decompõe os vetores nos eixos X e Y usando projeções trigonométricas de 45° ($\frac{\sqrt{2}}{2}$):
  * $E_x = E_0 \cdot \frac{\sqrt{2}}{2} \cdot (q_1 - q_2 + q_3)$
  * $E_y = E_0 \cdot \frac{\sqrt{2}}{2} \cdot (-q_1 + q_2 + q_3)$

## 🛡️ Restrições de Input

Para garantir a estabilidade do programa e a coerência física, o sistema possui as seguintes validações:
* **Restrição de Tipo:** Uso de `try-catch` para capturar `NumberFormatException`. Letras e caracteres especiais são bloqueados, exibindo um aviso na tela.
* **Restrição de Domínio Físico:** A distância $a$ define a geometria do triângulo. O programa exige que $a > 0$. Valores negativos ou nulos são bloqueados para evitar divisão por zero ($E = \frac{kq}{d^2}$) e inversão não-física dos vetores.

## 📊 Output do Simulador

Ao processar o cálculo, a interface exibe um relatório vetorial contendo:
1. Componente X ($E_x$) e Componente Y ($E_y$) em N/C.
2. O **Módulo Resultante** total do campo.
3. A **Direção** (ângulo em graus).
4. **Observação Física:** Se o usuário inserir $q_1 = q_2$, o programa identifica a simetria dinamicamente e adiciona uma explicação teórica avisando sobre o cancelamento vetorial perfeito.

## 🚀 Como Executar

1. Certifique-se de ter o [JDK](https://www.oracle.com/java/technologies/downloads/) instalado.
2. Clone o repositório ou baixe o arquivo `CalculadoraEletrostatica.java`.
3. Abra o terminal na pasta do arquivo e compile:
   ```bash
   javac CalculadoraEletrostatica.java
