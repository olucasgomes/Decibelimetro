# Relatório Técnico - Projeto Decibelímetro (SoundLimit)

**Disciplina:** N700 - Desenvolvimento para Plataformas Móveis  
**Instituição:** UNIFOR - Universidade de Fortaleza  
**Grupo:** 35  

---

## 1. Identificação da Equipe

| Nome Completo | Matrícula | E-mail | GitHub |
| :--- | :--- | :--- | :--- |
| Marcos Daniel Gomes Dantas | 2425179 | contatomarcosdgomes@gmail.com | mdanieldantas |
| Kaio Bruno Soares dos Santos | 2425109 | Kaiobruno@edu.unifor.br | DevKaioBrunu |
| Raphael Araripe Magalhães | 2425278 | raphael_araripe@hotmail.com | Hiwls |
| Pedro Yuri Rodrigues Nunes | 2425031 | pedroyurirn@edu.unifor.br | PYEDRO |
| Erico Alves de Lima | 2323762 | ericoallima@gmail.com | EricoAlves07 |
| Manoel Lucas Gomes Dantas | 2217137 | gomes@edu.unifor.br | olucasgomes |

---

## 2. Introdução e Definição do Problema

A exposição prolongada a níveis elevados de ruído é uma das causas mais comuns de perda auditiva induzida pelo ruído (PAIR), tanto em ambientes urbanos quanto industriais. Muitas vezes, indivíduos estão expostos a sons acima de 85 dB (limite estabelecido pela NR-15 para jornadas de 8 horas) sem ter a percepção do risco real.

O projeto **SoundLimit** surge como uma solução acessível e prática para transformar qualquer smartphone Android em um decibelímetro funcional, permitindo que o usuário monitore o ambiente em tempo real e receba alertas preventivos sobre a saúde auditiva.

---

## 3. Requisitos do Sistema

### 3.1 Requisitos Funcionais (RF)
* **RF01:** O sistema deve capturar o áudio ambiente através do microfone nativo.
* **RF02:** O sistema deve calcular e exibir o nível de decibéis (dB) em tempo real.
* **RF03:** O sistema deve exibir uma interface visual (Gauge/Medidor) que mude de cor conforme a intensidade do som.
* **RF04:** O sistema deve monitorar a persistência do ruído e exibir um alerta (Bottom Sheet) apenas se o nível ultrapassar 85 dB por mais de **30 segundos contínuos**.
* **RF05:** O sistema deve fornecer informações educativas sobre os limites de tolerância baseados na NR-15 através de diálogos informativos.

### 3.2 Requisitos Não Funcionais (RNF)
* **RNF01:** A interface deve seguir os princípios de Material Design.
* **RNF02:** O aplicativo deve ser desenvolvido de forma nativa para Android (Java).
* **RNF03:** A medição deve ter baixa latência para garantir feedback visual imediato, enquanto o alerta de segurança segue uma lógica de persistência.

---

## 4. Arquitetura e Recursos Utilizados

### 4.1 Tecnologias
* **Linguagem:** Java (Android Nativo).
* **UI/UX:** XML Layouts com Material Design Components (Material 3).
* **Versionamento:** Git e GitHub.

### 4.2 Sensores e Recursos Nativos
O aplicativo utiliza a classe `MediaRecorder` para acessar o **Sensor de Microfone** do dispositivo. Através do método `getMaxAmplitude()`, o software obtém a amplitude sonora e aplica uma fórmula logarítmica para conversão em Decibéis (dB):
`dB = 20 * log10(Amplitude)`

### 4.3 Lógica de Persistência e Segurança
Diferente de medidores comuns que alertam em picos isolados, o **SoundLimit** implementa um algoritmo de monitoramento temporal. Se o nível de ruído detectado for >= 85 dB, um cronômetro é iniciado. O alerta de segurança só é disparado se essa condição persistir por 30 segundos, evitando alarmes falsos causados por ruídos de impacto (ex: batida de porta, palmas) e focando no risco real da exposição prolongada (Ruído Contínuo).

### 4.4 Gestão de Permissões
Foi implementada a verificação de permissão em tempo de execução para o uso do microfone (`Manifest.permission.RECORD_AUDIO`), garantindo a segurança e privacidade do usuário conforme as diretrizes modernas do Android.

---

## 5. Interface e Usabilidade (UX/UI)

A interface foi projetada com foco na clareza e rapidez de leitura:
* **Dark Theme:** Reduz a fadiga visual e economiza bateria em telas OLED.
* **Feedback por Cores (Instantâneo):**
  * Verde: Ambiente silencioso (< 20 dB).
  * Azul: Ambiente moderado (< 60 dB).
  * Laranja: Ambiente ruidoso (< 85 dB).
  * Vermelho: Ambiente perigoso (>= 85 dB).
* **Alertas Inteligentes:** Uso de `BottomSheetDialogFragment` que fornece contexto técnico (NR-15) sobre o motivo do alerta após a validação da persistência sonora.

---

## 6. Conclusão

O desenvolvimento do Decibelímetro permitiu aplicar conceitos fundamentais de desenvolvimento mobile, como ciclo de vida da Activity, gestão de permissões, manipulação de hardware e design centrado no usuário. A inclusão da lógica de persistência de 30 segundos elevou a qualidade técnica do projeto, aproximando-o de uma ferramenta real de higiene ocupacional.

---

## 7. Referências
* Documentação Android Developer: [MediaRecorder](https://developer.android.com/reference/android/media/MediaRecorder)
* Norma Regulamentadora 15 (NR-15) - Atividades e Operações Insalubres.
* Material Design Guidelines.
